package api.mapper;

import java.util.List;
import org.springframework.stereotype.Component;

import api.dto.InspecaoQualidadeRequestDTO;
import api.dto.InspecaoQualidadeResponseDTO;
import api.model.InspecaoQualidade;
import api.model.OrdemProducao;

// conversão entre dto e entidade para inspecaoqualidade
@Component
public class InspecaoQualidadeMapper {

    // converte o dto de entrada em entidade inspecaoqualidade
    public InspecaoQualidade toEntity(InspecaoQualidadeRequestDTO dto, OrdemProducao ordemProducao) {
        InspecaoQualidade inspecao = new InspecaoQualidade();
        inspecao.setOrdemProducao(ordemProducao);
        inspecao.setQuantidadeInspecionada(dto.quantidadeInspecionada());
        inspecao.setQuantidadeReprovada(dto.quantidadeReprovada());
        inspecao.setData(dto.data());
        inspecao.setObservacoes(dto.observacoes());
        return inspecao;
    }

    // converte a entidade inspecaoqualidade em dto de saída
    public InspecaoQualidadeResponseDTO toResponse(InspecaoQualidade inspecao) {
        Long ordemProducaoId = inspecao.getOrdemProducao() != null ? inspecao.getOrdemProducao().getId() : null;
        return new InspecaoQualidadeResponseDTO(
                inspecao.getId(),
                ordemProducaoId,
                inspecao.getQuantidadeInspecionada(),
                inspecao.getQuantidadeReprovada(),
                inspecao.getData(),
                inspecao.getObservacoes()
        );
    }

    // converte uma lista de inspeções em lista de dtos de resposta
    public List<InspecaoQualidadeResponseDTO> toResponseList(List<InspecaoQualidade> inspecoes) {
        return inspecoes.stream()
                .map(this::toResponse)
                .toList();
    }
}
