package api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import api.model.OrdemProducao;

// repositório spring data jpa para a entidade ordemproducao
@Repository
public interface OrdemProducaoRepository extends JpaRepository<OrdemProducao, Long> {
}
