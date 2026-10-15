package api.dto;

import java.time.LocalDate;
import io.swagger.v3.oas.annotations.media.Schema;

// dados devolvidos pela api após registrar ou consultar uma inspeção.
public record InspecaoQualidadeResponseDTO(

        @Schema(description = "id gerado pelo banco", example = "1")
        Long id,

        @Schema(description = "id da ordem de produção", example = "1")
        Long ordemProducaoId,

        @Schema(description = "quantidade de peças inspecionadas", example = "50")
        Integer quantidadeInspecionada,

        @Schema(description = "quantidade de peças reprovadas", example = "2")
        Integer quantidadeReprovada,

        @Schema(description = "data da inspeção", example = "2026-10-14")
        LocalDate data,

        @Schema(description = "observações registradas", example = "Amostragem de peças do primeiro lote")
        String observacoes
) {
}
