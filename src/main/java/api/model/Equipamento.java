
package api.model;

import jakarta.persistence.*;

@Entity
@Table(name = "equipamentos")
public class Equipamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String nome;

    @Column(nullable = false, length = 50, unique = true)
    private String codigo;

    @Column(nullable = false, length = 100)
    private String setor;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private EstadoEquipamento estado = EstadoEquipamento.ATIVO;

    public Equipamento() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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
