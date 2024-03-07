/**
 * Thrown when there is an error while executing a {@link Program} (or a single {@link Instr}
 * thereof).
 */
public class ExecutionException extends Exception {
    
    public ExecutionException(String message) {
        super(message);
    }
}
