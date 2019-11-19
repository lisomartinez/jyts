package cloud.liso.jyts.exceptions;

public class InvalidOptionException extends RuntimeException {
    public InvalidOptionException(String option) {
        super("Invalid Option: " + option);
    }
}
