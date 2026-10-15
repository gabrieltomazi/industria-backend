package api.service;

// exceção lançada quando uma ordem de produção não é encontrada pelo id
public class OrdemProducaoNaoEncontradaException extends RecursoNaoEncontradoException {

    public OrdemProducaoNaoEncontradaException(Long id) {
        super("Ordem de produção não encontrada: " + id);
    }
}
