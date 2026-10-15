package api.dto;

import api.model.EstadoOrdemProducao;
import io.swagger.v3.oas.annotations.media.Schema;

// dados devolvidos pela api após cadastrar ou consultar uma ordem de produção
public record OrdemProducaoResponseDTO(

        @Schema(description = "identificador gerado pelo banco", example = "1")
        Long id,

        @Schema(description = "código único da ordem", example = "OP-1001")
        String codigo,

        @Schema(description = "produto fabricado", example = "Eixo de Transmissão")
        String produto,

        @Schema(description = "quantidade planejada", example = "100")
        Integer quantidadePlanejada,

        @Schema(description = "quantidade já produzida", example = "0")
        Integer quantidadeProduzida,

        @Schema(description = "estado atual da ordem", example = "CRIADA")
        EstadoOrdemProducao estado,

        @Schema(description = "id do equipamento associado", example = "1")
        Long equipamentoId
) {
}
