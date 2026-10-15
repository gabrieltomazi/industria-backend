package api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import api.model.InspecaoQualidade;



@Repository
public interface InspecaoQualidadeRepository extends JpaRepository<InspecaoQualidade, Long> {
  
}