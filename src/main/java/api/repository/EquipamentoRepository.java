
package api.repository;

import api.model.Equipamento;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

// camada: repository
// responsabilidade: operações de banco de dados para a entidade equipamento.
// situação: implementada.
@Repository
public interface EquipamentoRepository
        extends JpaRepository<Equipamento, Long> {
}
