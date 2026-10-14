package api.dto;

import java.time.LocalDate;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

// dados de entrada para registrar uma inspeção de qualidade.
public record InspecaoQualidadeRequestDTO(

        @Schema(description = "id da ordem de produção inspecionada", example = "1")
        @NotNull(message = "O ID da ordem de produção é obrigatório")
        Long ordemProducaoId,

        @Schema(description = "quantidade de peças inspecionadas", example = "50")
        @NotNull(message = "A quantidade inspecionada é obrigatória")
        @Positive(message = "A quantidade inspecionada deve ser maior que zero")
        Integer quantidadeInspecionada,

        @Schema(description = "quantidade de peças reprovadas", example = "2")
        @NotNull(message = "A quantidade reprovada é obrigatória")
        @Min(value = 0, message = "A quantidade reprovada não pode ser negativa")
        Integer quantidadeReprovada,

        @Schema(description = "data da realização do teste", example = "2026-10-14")
        @NotNull(message = "A data da inspeção é obrigatória")
        LocalDate data,

        @Schema(description = "observações adicionais do teste", example = "Amostragem de peças do primeiro lote")
        @Size(max = 255, message = "As observações devem ter no máximo 255 caracteres")
        String observacoes
) {
}
