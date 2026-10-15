package api.service;

// exceção lançada quando um equipamento não é encontrado pelo id
public class EquipamentoNaoEncontradoException extends RecursoNaoEncontradoException {

    public EquipamentoNaoEncontradoException(Long id) {
        super("Equipamento não encontrado: " + id);
    }
}
