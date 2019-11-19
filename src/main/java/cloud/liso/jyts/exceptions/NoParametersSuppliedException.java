package cloud.liso.jyts.exceptions;

public class NoParametersSuppliedException extends RuntimeException {
    public NoParametersSuppliedException() {
        super("No arguments supplied");
    }
}
