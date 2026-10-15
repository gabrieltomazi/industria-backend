
package api.repository;

import api.model.Equipamento;
import org.springframework.data.jpa.repository.JpaRepository;

// Acesso aos dados dos equipamentos no banco.
public interface EquipamentoRepository
        extends JpaRepository<Equipamento, Long> {
}
