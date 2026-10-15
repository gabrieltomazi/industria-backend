package api.dto;

import api.model.EstadoEquipamento;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

// dados recebidos para cadastrar um novo equipamento
public record EquipamentoRequestDTO(

        @NotBlank(message = "Nome é obrigatório")
        @Size(max = 100, message = "Nome deve ter no máximo 100 caracteres")
        @Schema(description = "nome do equipamento", example = "Torno CNC 01")
        String nome,

        @NotBlank(message = "Código é obrigatório")
        @Size(max = 50, message = "Código deve ter no máximo 50 caracteres")
        @Schema(description = "código único do equipamento", example = "EQ-1001")
        String codigo,

        @NotBlank(message = "Setor é obrigatório")
        @Size(max = 100, message = "Setor deve ter no máximo 100 caracteres")
        @Schema(description = "setor onde o equipamento opera", example = "Usinagem")
        String setor,

        @Schema(description = "estado operacional do equipamento", example = "ATIVO")
        EstadoEquipamento estado
) {
}
