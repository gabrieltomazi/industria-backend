
package api.dto;

import api.model.EstadoEquipamento;
import jakarta.validation.constraints.NotBlank;

public class EquipamentoRequestDTO {

    @NotBlank(message = "Nome é obrigatório")
    private String nome;

    @NotBlank(message = "Código é obrigatório")
    private String codigo;

    @NotBlank(message = "Setor é obrigatório")
    private String setor;

    private EstadoEquipamento estado = EstadoEquipamento.ATIVO;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getSetor() {
        return setor;
    }

    public void setSetor(String setor) {
        this.setor = setor;
    }

    public EstadoEquipamento getEstado() {
        return estado;
    }

    public void setEstado(EstadoEquipamento estado) {
        this.estado = estado;
    }
}
