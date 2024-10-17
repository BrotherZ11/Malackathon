package Malackathon.Malackathon.excepciones;

public class EntidadNoEncontradaException extends RuntimeException {
    public EntidadNoEncontradaException() {
        super("Entidad no encontrada");
    }
}
