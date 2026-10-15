package api.dto;

import api.model.EstadoEquipamento;
import io.swagger.v3.oas.annotations.media.Schema;

// dados devolvidos após cadastrar ou consultar um equipamento
public record EquipamentoResponseDTO(

        @Schema(description = "identificador gerado pelo banco", example = "1")
        Long id,

        @Schema(description = "nome do equipamento", example = "Torno CNC 01")
        String nome,

        @Schema(description = "código único do equipamento", example = "EQ-1001")
        String codigo,

        @Schema(description = "setor onde o equipamento opera", example = "Usinagem")
        String setor,

        @Schema(description = "estado operacional do equipamento", example = "ATIVO")
        EstadoEquipamento estado
) {
}
