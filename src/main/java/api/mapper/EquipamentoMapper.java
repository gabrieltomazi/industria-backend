
package api.mapper;

import java.util.List;
import api.dto.EquipamentoRequestDTO;
import api.dto.EquipamentoResponseDTO;
import api.model.Equipamento;
import org.springframework.stereotype.Component;

// camada: mapper
// responsabilidade: conversão entre dto e entidade para equipamento.
// situação: implementada.
@Component
public class EquipamentoMapper {

    // converte dto de entrada em entidade equipamento
    public Equipamento toEntity(EquipamentoRequestDTO dto) {
        Equipamento equipamento = new Equipamento();

        equipamento.setNome(dto.nome());
        equipamento.setCodigo(dto.codigo());
        equipamento.setSetor(dto.setor());

        if (dto.estado() != null) {
            equipamento.setEstado(dto.estado());
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
