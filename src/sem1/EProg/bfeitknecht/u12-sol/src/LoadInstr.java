
import java.util.Map;
import java.util.Stack;

/**
 * An {@link Instr} that loads the value of a given variable and pushes it on the stack.
 */
public class LoadInstr implements Instr {
    
    private final String varName;
    
    public LoadInstr(String varName) {
        this.varName = varName;
    }
    
    public void execute(Stack<Double> operandStack, Map<String, Double> vars)
            throws ExecutionException {
        if(!vars.containsKey(varName))
            throw new ExecutionException("unknown variable '" + varName + "'");
        operandStack.push(vars.get(varName));
    }
}
