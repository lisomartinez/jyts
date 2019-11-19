package cloud.liso.jyts.exceptions;

public class ArgumentMissingException extends RuntimeException {
    public ArgumentMissingException(String option) {
        super("Missing Argument for option " + option);
    }
}
