package api.model;

import java.time.LocalDate;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

// entidade que representa a tabela inspecoes_qualidade no banco
@Entity
@Table(name = "inspecoes_qualidade")
public class InspecaoQualidade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // relacionamento muitos para um com a ordem de produção inspecionada
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ordem_producao_id", nullable = false)
    private OrdemProducao ordemProducao;

    @Column(name = "quantidade_inspecionada", nullable = false)
    private Integer quantidadeInspecionada;

    @Column(name = "quantidade_reprovada", nullable = false)
    private Integer quantidadeReprovada;

    @Column(nullable = false)
    private LocalDate data;

    @Column(length = 255)
    private String observacoes;

    // construtor sem argumentos exigido pelo hibernate
    protected InspecaoQualidade() {
    }

    public InspecaoQualidade(Long id, OrdemProducao ordemProducao, Integer quantidadeInspecionada,
                             Integer quantidadeReprovada, LocalDate data, String observacoes) {
        this.id = id;
        this.ordemProducao = ordemProducao;
        this.quantidadeInspecionada = quantidadeInspecionada;
        this.quantidadeReprovada = quantidadeReprovada;
        this.data = data;
        this.observacoes = observacoes;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public OrdemProducao getOrdemProducao() {
        return ordemProducao;
    }

    public void setOrdemProducao(OrdemProducao ordemProducao) {
        this.ordemProducao = ordemProducao;
    }

    public Integer getQuantidadeInspecionada() {
        return quantidadeInspecionada;
    }

    public void setQuantidadeInspecionada(Integer quantidadeInspecionada) {
        this.quantidadeInspecionada = quantidadeInspecionada;
    }

    public Integer getQuantidadeReprovada() {
        return quantidadeReprovada;
    }

    public void setQuantidadeReprovada(Integer quantidadeReprovada) {
        this.quantidadeReprovada = quantidadeReprovada;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }
}
