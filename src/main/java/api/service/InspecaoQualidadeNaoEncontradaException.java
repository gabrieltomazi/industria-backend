package api.service;

// exceção lançada quando uma inspeção de qualidade não é encontrada pelo id
public class InspecaoQualidadeNaoEncontradaException extends RecursoNaoEncontradoException {

    public InspecaoQualidadeNaoEncontradaException(Long id) {
        super("Inspeção de qualidade não encontrada: " + id);
    }
}
