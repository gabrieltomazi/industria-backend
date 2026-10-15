
package api.mapper;

import api.dto.EquipamentoRequestDTO;
import api.dto.EquipamentoResponseDTO;
import api.model.Equipamento;
import org.springframework.stereotype.Component;

@Component
public class EquipamentoMapper {

    public Equipamento paraEntidade(EquipamentoRequestDTO dto) {
        Equipamento equipamento = new Equipamento();

        equipamento.setNome(dto.getNome());
        equipamento.setCodigo(dto.getCodigo());
        equipamento.setSetor(dto.getSetor());

        if (dto.getEstado() != null) {
            equipamento.setEstado(dto.getEstado());
        }

        return equipamento;
    }

    public EquipamentoResponseDTO paraDTO(Equipamento equipamento) {
        return new EquipamentoResponseDTO(
                equipamento.getId(),
                equipamento.getNome(),
                equipamento.getCodigo(),
                equipamento.getSetor(),
                equipamento.getEstado()
        );
    }
}
