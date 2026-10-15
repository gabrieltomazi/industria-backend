
package api.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import api.dto.EquipamentoRequestDTO;
import api.dto.EquipamentoResponseDTO;
import api.service.EquipamentoService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/equipamentos")
public class EquipamentoController {

    private final EquipamentoService service;

    public EquipamentoController(EquipamentoService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<EquipamentoResponseDTO> cadastrar(
            @Valid @RequestBody EquipamentoRequestDTO dto) {

        EquipamentoResponseDTO resposta = service.cadastrar(dto);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(resposta);
    }

    @GetMapping
    public ResponseEntity<List<EquipamentoResponseDTO>> listar() {
        return ResponseEntity.ok(service.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EquipamentoResponseDTO> buscarPorId(
            @PathVariable Long id) {

        return ResponseEntity.ok(service.buscarPorId(id));
    }
}
