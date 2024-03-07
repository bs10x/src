
import java.util.Map;
import java.util.Stack;

/**
 * A single instruction in a {@link Program}.
 */
public interface Instr {
    
    /**
     * Executes this instruction. The instruction may have an effect on the operand stack or on the
     * variable mapping (or both, of course).
     */
    void execute(Stack<Double> operandStack, Map<String, Double> vars) throws ExecutionException;
}
