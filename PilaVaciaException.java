public class PilaVaciaException extends RuntimeException {
    public PilaVaciaException(String mensaje) {
        super(mensaje); // Envía el mensaje al constructor de RuntimeException.
    }
}
