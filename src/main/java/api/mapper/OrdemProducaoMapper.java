package api.mapper;

import java.util.List;
import org.springframework.stereotype.Component;

import api.dto.OrdemProducaoRequestDTO;
import api.dto.OrdemProducaoResponseDTO;
import api.model.Equipamento;
import api.model.EstadoOrdemProducao;
import api.model.OrdemProducao;

// conversão entre dto e entidade para ordemproducao
@Component
public class OrdemProducaoMapper {

    // converte o dto de entrada em entidade ordemproducao vinculando o equipamento
    public OrdemProducao toEntity(OrdemProducaoRequestDTO dto, Equipamento equipamento) {
        OrdemProducao ordem = new OrdemProducao();
        ordem.setCodigo(dto.codigo());
        ordem.setProduto(dto.produto());
        ordem.setQuantidadePlanejada(dto.quantidadePlanejada());
        ordem.setQuantidadeProduzida(dto.quantidadeProduzida());
        ordem.setEstado(EstadoOrdemProducao.CRIADA);
        ordem.setEquipamento(equipamento);
        return ordem;
    }

    // converte a entidade ordemproducao em dto de resposta
    public OrdemProducaoResponseDTO toResponse(OrdemProducao ordem) {
        Long equipamentoId = ordem.getEquipamento() != null ? ordem.getEquipamento().getId() : null;
        return new OrdemProducaoResponseDTO(
                ordem.getId(),
                ordem.getCodigo(),
                ordem.getProduto(),
                ordem.getQuantidadePlanejada(),
                ordem.getQuantidadeProduzida(),
                ordem.getEstado(),
                equipamentoId
        );
    }

    // converte uma lista de ordens em lista de dtos de resposta
    public List<OrdemProducaoResponseDTO> toResponseList(List<OrdemProducao> ordens) {
        return ordens.stream()
                .map(this::toResponse)
                .toList();
    }
}
