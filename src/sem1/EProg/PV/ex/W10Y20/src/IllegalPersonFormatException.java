/**
 * Thrown by the Person constructor if the data row is not in the right format.
 */
public class IllegalPersonFormatException extends Exception {
    public IllegalPersonFormatException(String message) {
        super(message);
    }
}