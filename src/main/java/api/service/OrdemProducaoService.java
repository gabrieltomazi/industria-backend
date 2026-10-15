package api.service;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import api.dto.OrdemProducaoRequestDTO;
import api.dto.OrdemProducaoResponseDTO;
import api.mapper.OrdemProducaoMapper;
import api.model.Equipamento;
import api.model.EstadoEquipamento;
import api.model.OrdemProducao;
import api.repository.EquipamentoRepository;
import api.repository.OrdemProducaoRepository;

// camada: service
// responsabilidade: regras de negócio das ordens de produção.
// regras: r1 (uma ordem só pode ser iniciada se o equipamento associado estiver no estado ativo).
// rotas atendidas: post /ordens-producao em docs/contrato-da-api.md.
@Service
public class OrdemProducaoService {

    private final OrdemProducaoRepository repository;
    private final OrdemProducaoMapper mapper;
    private final EquipamentoRepository equipamentoRepository;

    public OrdemProducaoService(
            OrdemProducaoRepository repository,
            OrdemProducaoMapper mapper,
            EquipamentoRepository equipamentoRepository) {
        this.repository = repository;
        this.mapper = mapper;
        this.equipamentoRepository = equipamentoRepository;
    }

    // cadastra e inicia uma nova ordem de produção validando a regra r1
    @Transactional
    public OrdemProducaoResponseDTO cadastrar(OrdemProducaoRequestDTO dto) {
        // busca o equipamento associado ou lança 404
        Equipamento equipamento = equipamentoRepository.findById(dto.equipamentoId())
                .orElseThrow(() -> new EquipamentoNaoEncontradoException(dto.equipamentoId()));

        // r1: uma ordem de produção só pode ser iniciada se o equipamento associado estiver no estado ativo
        if (equipamento.getEstado() != EstadoEquipamento.ATIVO) {
            throw new RegraDeNegocioException(
                    "O equipamento associado deve estar no estado ATIVO para iniciar uma ordem de produção.");
        }

        // converte e salva a ordem de produção
        OrdemProducao ordem = mapper.toEntity(dto, equipamento);
        OrdemProducao ordemSalva = repository.save(ordem);

        return mapper.toResponse(ordemSalva);
    }

    // lista todas as ordens de produção cadastradas
    public List<OrdemProducaoResponseDTO> listar() {
        return repository.findAll()
                .stream()
                .map(mapper::toResponse)
                .toList();
    }

    // busca uma ordem de produção pelo id ou lança 404
    public OrdemProducaoResponseDTO buscarPorId(Long id) {
        OrdemProducao ordem = repository.findById(id)
                .orElseThrow(() -> new OrdemProducaoNaoEncontradaException(id));

        return mapper.toResponse(ordem);
    }
}
