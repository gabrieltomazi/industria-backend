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
import api.dto.OrdemProducaoRequestDTO;
import api.dto.OrdemProducaoResponseDTO;
import api.service.OrdemProducaoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

// porta de entrada http para as ordens de produção
@RestController
@RequestMapping("/ordens-producao")
@Tag(name = "Ordens de Produção", description = "Endpoints para gerenciamento de ordens de produção")
public class OrdemProducaoController {

    private final OrdemProducaoService service;

    public OrdemProducaoController(OrdemProducaoService service) {
        this.service = service;
    }

    @PostMapping
    @Operation(summary = "Cadastra uma nova ordem de produção", description = "Aplica a regra R1 (o equipamento associado deve estar no estado ATIVO)")
    @ApiResponses({
        @ApiResponse(responseCode = "201", description = "Ordem de produção criada com sucesso"),
        @ApiResponse(responseCode = "400", description = "Dados inválidos ou equipamento não está ATIVO (R1)", content = @Content(schema = @Schema(implementation = ErroDTO.class))),
        @ApiResponse(responseCode = "404", description = "Equipamento não encontrado", content = @Content(schema = @Schema(implementation = ErroDTO.class)))
    })
    public ResponseEntity<OrdemProducaoResponseDTO> cadastrar(
            @Valid @RequestBody OrdemProducaoRequestDTO dto) {

        OrdemProducaoResponseDTO resposta = service.cadastrar(dto);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(resposta);
    }

    @GetMapping
    @Operation(summary = "Lista todas as ordens de produção")
    @ApiResponse(responseCode = "200", description = "Lista recuperada com sucesso")
    public ResponseEntity<List<OrdemProducaoResponseDTO>> listar() {
        return ResponseEntity.ok(service.listar());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Busca uma ordem de produção pelo ID")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Ordem encontrada"),
        @ApiResponse(responseCode = "404", description = "Ordem de produção não encontrada", content = @Content(schema = @Schema(implementation = ErroDTO.class)))
    })
    public ResponseEntity<OrdemProducaoResponseDTO> buscarPorId(
            @PathVariable Long id) {

        return ResponseEntity.ok(service.buscarPorId(id));
    }
}
