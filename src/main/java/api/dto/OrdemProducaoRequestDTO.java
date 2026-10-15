package api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

// dados recebidos pela api para cadastrar uma nova ordem de produção
public record OrdemProducaoRequestDTO(

        @NotBlank(message = "O código da ordem é obrigatório")
        @Size(max = 50, message = "O código deve ter no máximo 50 caracteres")
        @Schema(description = "código único da ordem de produção", example = "OP-1001")
        String codigo,

        @NotBlank(message = "O nome do produto é obrigatório")
        @Size(max = 150, message = "O produto deve ter no máximo 150 caracteres")
        @Schema(description = "produto a ser fabricado", example = "Eixo de Transmissão")
        String produto,

        @NotNull(message = "A quantidade planejada é obrigatória")
        @Positive(message = "A quantidade planejada deve ser maior que zero")
        @Schema(description = "quantidade planejada para fabricação", example = "100")
        Integer quantidadePlanejada,

        @NotNull(message = "A quantidade produzida é obrigatória")
        @Min(value = 0, message = "A quantidade produzida não pode ser negativa")
        @Schema(description = "quantidade inicial já produzida", example = "0")
        Integer quantidadeProduzida,

        @NotNull(message = "O ID do equipamento é obrigatório")
        @Schema(description = "id do equipamento associado", example = "1")
        Long equipamentoId
) {
}
