
import java.util.Map;
import java.util.Stack;

/**
 * An {@link Instr} that pops a value off the operand stack and stores it in a given variable.
 */
public class StoreInstr implements Instr {
    
    private final String var;
    
    public StoreInstr(String var) {
        this.var = var;
    }
    
    public void execute(Stack<Double> operandStack, Map<String, Double> vars)
            throws ExecutionException {
        if(operandStack.isEmpty())
            throw new ExecutionException("empty stack when executing store");
        vars.put(var, operandStack.pop());
    }
}
