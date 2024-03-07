
import java.util.Map;
import java.util.Stack;

/**
 * An {@link Instr} that pushes a single constant value on the operand stack.
 */
public class ConstInstr implements Instr {
    
    private final double value;
    
    public ConstInstr(double value) {
        this.value = value;
    }
    
    public void execute(Stack<Double> operandStack, Map<String, Double> vars) {
        operandStack.push(value);
    }
}
