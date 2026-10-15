package api.controller;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import api.dto.ErroDTO;
import api.dto.InspecaoQualidadeRequestDTO;
import api.dto.InspecaoQualidadeResponseDTO;
import api.service.InspecaoQualidadeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

// porta de entrada http para as inspeções de qualidade
@RestController
@RequestMapping("/inspecoes-qualidade")
@Tag(name = "Inspeções de Qualidade", description = "Registro e consulta de inspeções de qualidade")
public class InspecaoQualidadeController {

    private final InspecaoQualidadeService service;

    public InspecaoQualidadeController(InspecaoQualidadeService service) {
        this.service = service;
    }

    @PostMapping
    @Operation(summary = "Registra uma inspeção de qualidade", description = "Aplica as regras R2 (quantidade inspecionada <= produzida) e R3 (reprovação > 10% suspende OP e solicita manutenção do equipamento)")
    @ApiResponses({
        @ApiResponse(responseCode = "201", description = "Inspeção registrada com sucesso"),
        @ApiResponse(responseCode = "400", description = "Dados inválidos ou violação da regra R2", content = @Content(schema = @Schema(implementation = ErroDTO.class))),
        @ApiResponse(responseCode = "404", description = "Ordem de produção não encontrada", content = @Content(schema = @Schema(implementation = ErroDTO.class)))
    })
    public ResponseEntity<InspecaoQualidadeResponseDTO> cadastrar(
            @Valid @RequestBody InspecaoQualidadeRequestDTO dto) {

        InspecaoQualidadeResponseDTO resposta = service.cadastrar(dto);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(resposta);
    }

    @GetMapping
    @Operation(summary = "Lista todas as inspeções registradas")
    @ApiResponse(responseCode = "200", description = "Lista recuperada com sucesso")
    public ResponseEntity<List<InspecaoQualidadeResponseDTO>> listar() {
        return ResponseEntity.ok(service.listar());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Busca uma inspeção de qualidade pelo ID")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Inspeção encontrada"),
        @ApiResponse(responseCode = "404", description = "Inspeção não encontrada", content = @Content(schema = @Schema(implementation = ErroDTO.class)))
    })
    public ResponseEntity<InspecaoQualidadeResponseDTO> buscarPorId(
            @PathVariable Long id) {

        return ResponseEntity.ok(service.buscarPorId(id));
    }
}
