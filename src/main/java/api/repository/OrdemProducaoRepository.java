package api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import api.model.OrdemProducao;

// camada: repository
// responsabilidade: operações de banco de dados para a entidade ordemproducao.
// situação: implementada.
@Repository
public interface OrdemProducaoRepository extends JpaRepository<OrdemProducao, Long> {
}
