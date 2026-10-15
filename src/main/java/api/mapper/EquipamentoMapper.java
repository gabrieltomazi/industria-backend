
package api.mapper;

import java.util.List;
import api.dto.EquipamentoRequestDTO;
import api.dto.EquipamentoResponseDTO;
import api.model.Equipamento;
import org.springframework.stereotype.Component;

@Component
public class EquipamentoMapper {

    // converte dto de entrada em entidade equipamento
    public Equipamento toEntity(EquipamentoRequestDTO dto) {
        Equipamento equipamento = new Equipamento();

        equipamento.setNome(dto.getNome());
        equipamento.setCodigo(dto.getCodigo());
        equipamento.setSetor(dto.getSetor());

        if (dto.getEstado() != null) {
            equipamento.setEstado(dto.getEstado());
        }

        return equipamento;
    }

    // converte entidade equipamento em dto de resposta
    public EquipamentoResponseDTO toResponse(Equipamento equipamento) {
        return new EquipamentoResponseDTO(
                equipamento.getId(),
                equipamento.getNome(),
                equipamento.getCodigo(),
                equipamento.getSetor(),
                equipamento.getEstado()
        );
    }

    // converte lista de entidades em lista de dtos de resposta
    public List<EquipamentoResponseDTO> toResponseList(List<Equipamento> equipamentos) {
        return equipamentos.stream()
                .map(this::toResponse)
                .toList();
    }
}
