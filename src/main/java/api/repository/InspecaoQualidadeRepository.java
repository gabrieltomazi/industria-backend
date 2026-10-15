package api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import api.model.InspecaoQualidade;



// camada: repository
// responsabilidade: operações de banco de dados para a entidade inspecaoqualidade.
// situação: implementada.
@Repository
public interface InspecaoQualidadeRepository extends JpaRepository<InspecaoQualidade, Long> {
  
}