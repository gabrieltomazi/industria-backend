
package api.service;

import java.util.List;

import org.springframework.stereotype.Service;

import api.dto.EquipamentoRequestDTO;
import api.dto.EquipamentoResponseDTO;
import api.mapper.EquipamentoMapper;
import api.model.Equipamento;
import api.repository.EquipamentoRepository;

// camada: service
// responsabilidade: regras e operações dos equipamentos industriais.
// rotas atendidas: as de /equipamentos em docs/contrato-da-api.md.
@Service
public class EquipamentoService {

    private final EquipamentoRepository repository;
    private final EquipamentoMapper mapper;

    public EquipamentoService(
            EquipamentoRepository repository,
            EquipamentoMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public EquipamentoResponseDTO cadastrar(
            EquipamentoRequestDTO dto) {

        Equipamento equipamento = mapper.toEntity(dto);
        Equipamento equipamentoSalvo = repository.save(equipamento);

        return mapper.toResponse(equipamentoSalvo);
    }

    public List<EquipamentoResponseDTO> listar() {
        return repository.findAll()
                .stream()
                .map(mapper::toResponse)
                .toList();
    }

    public EquipamentoResponseDTO buscarPorId(Long id) {
        Equipamento equipamento = repository.findById(id)
                .orElseThrow(() -> new EquipamentoNaoEncontradoException(id));

        return mapper.toResponse(equipamento);
    }
}
