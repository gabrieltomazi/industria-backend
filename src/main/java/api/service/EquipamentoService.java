package api.service;

import java.util.List;
import org.springframework.stereotype.Service;
import api.dto.EquipamentoRequestDTO;
import api.dto.EquipamentoResponseDTO;
import api.mapper.EquipamentoMapper;
import api.model.Equipamento;
import api.repository.EquipamentoRepository;
import api.exception.RecursoNaoEncontradoException;

// camada de serviço com a lógica de negócio de equipamentos.
@Service
public class EquipamentoService {

    private final EquipamentoRepository repository;
    private final EquipamentoMapper mapper;

    public EquipamentoService(EquipamentoRepository repository, EquipamentoMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    // cadastra um novo equipamento no sistema.
    public EquipamentoResponseDTO cadastrar(EquipamentoRequestDTO dto) {
        Equipamento equipamento = mapper.paraEntidade(dto);
        Equipamento equipamentoSalvo = repository.save(equipamento);
        return mapper.paraDTO(equipamentoSalvo);
    }

    // lista todos os equipamentos cadastrados.
    public List<EquipamentoResponseDTO> listar() {
        return repository.findAll()
                .stream()
                .map(mapper::paraDTO)
                .toList();
    }

    // busca um equipamento pelo id ou lança 404 se não existir.
    public EquipamentoResponseDTO buscarPorId(Long id) {
        Equipamento equipamento = repository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Equipamento não encontrado: " + id));
        return mapper.paraDTO(equipamento);
    }
}
