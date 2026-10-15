package api.service;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import api.dto.InspecaoQualidadeRequestDTO;
import api.dto.InspecaoQualidadeResponseDTO;
import api.mapper.InspecaoQualidadeMapper;
import api.model.Equipamento;
import api.model.EstadoEquipamento;
import api.model.EstadoOrdemProducao;
import api.model.InspecaoQualidade;
import api.model.OrdemProducao;
import api.repository.EquipamentoRepository;
import api.repository.InspecaoQualidadeRepository;
import api.repository.OrdemProducaoRepository;

// camada: service
// responsabilidade: regras de negócio das inspeções de qualidade.
// regras: r2 (quantidade inspecionada não pode superar a produzida) e
//         r3 (se reprovação > 10%, suspende a ordem e solicita manutenção do equipamento).
// rotas atendidas: post /inspecoes-qualidade em docs/contrato-da-api.md.
@Service
public class InspecaoQualidadeService {

    private final InspecaoQualidadeRepository repository;
    private final InspecaoQualidadeMapper mapper;
    private final OrdemProducaoRepository ordemProducaoRepository;
    private final EquipamentoRepository equipamentoRepository;

    public InspecaoQualidadeService(
            InspecaoQualidadeRepository repository,
            InspecaoQualidadeMapper mapper,
            OrdemProducaoRepository ordemProducaoRepository,
            EquipamentoRepository equipamentoRepository) {
        this.repository = repository;
        this.mapper = mapper;
        this.ordemProducaoRepository = ordemProducaoRepository;
        this.equipamentoRepository = equipamentoRepository;
    }

    // registra uma nova inspeção de qualidade validando as regras r2 e r3
    @Transactional
    public InspecaoQualidadeResponseDTO cadastrar(InspecaoQualidadeRequestDTO dto) {
        // busca a ordem de produção associada ou lança 404
        OrdemProducao ordem = ordemProducaoRepository.findById(dto.ordemProducaoId())
                .orElseThrow(() -> new RecursoNaoEncontradoException(
                        "Ordem de produção não encontrada: " + dto.ordemProducaoId()));

        // r2: a quantidade inspecionada não pode ser superior à quantidade produzida na ordem
        if (dto.quantidadeInspecionada() > ordem.getQuantidadeProduzida()) {
            throw new RegraDeNegocioException(
                    "A quantidade inspecionada não pode ser superior à quantidade produzida informada na ordem de produção correspondente.");
        }

        // converte e salva a inspeção no banco de dados
        InspecaoQualidade inspecao = mapper.toEntity(dto, ordem);
        InspecaoQualidade inspecaoSalva = repository.save(inspecao);

        // r3: se mais de 10% das peças forem reprovadas, suspende a ordem e altera equipamento para manutencao_pendente
        double percentualReprovacao = (double) dto.quantidadeReprovada() / dto.quantidadeInspecionada();
        if (percentualReprovacao > 0.10) {
            ordem.setEstado(EstadoOrdemProducao.SUSPENSA);
            ordemProducaoRepository.save(ordem);

            Equipamento equipamento = ordem.getEquipamento();
            if (equipamento != null) {
                equipamento.setEstado(EstadoEquipamento.MANUTENCAO_PENDENTE);
                equipamentoRepository.save(equipamento);
            }
        }

        return mapper.toResponse(inspecaoSalva);
    }

    // lista todas as inspeções realizadas
    public List<InspecaoQualidadeResponseDTO> listar() {
        return repository.findAll()
                .stream()
                .map(mapper::toResponse)
                .toList();
    }

    // busca uma inspeção pelo seu identificador ou lança 404
    public InspecaoQualidadeResponseDTO buscarPorId(Long id) {
        InspecaoQualidade inspecao = repository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException(
                        "Inspeção de qualidade não encontrada: " + id));

        return mapper.toResponse(inspecao);
    }
}
