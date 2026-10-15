
package api.dto;

import api.model.EstadoEquipamento;

public class EquipamentoResponseDTO {

    private Long id;
    private String nome;
    private String codigo;
    private String setor;
    private EstadoEquipamento estado;

    public EquipamentoResponseDTO(
            Long id,
            String nome,
            String codigo,
            String setor,
            EstadoEquipamento estado) {
        this.id = id;
        this.nome = nome;
        this.codigo = codigo;
        this.setor = setor;
        this.estado = estado;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getSetor() {
        return setor;
    }

    public EstadoEquipamento getEstado() {
        return estado;
    }
}
