
import java.util.List;
import java.util.Map;
import java.util.Stack;

/**
 * An executable program. A program consists of a list of {@link Instr}s (instructions). The state
 * of the program consists of an operand stack and a variable mapping.
 */
public class Program {
    
    private final List<Instr> instrs;
    
    public Program(List<Instr> instrs) {
        this.instrs = instrs;
    }
    
    public void execute(Map<String, Double> vars) throws ExecutionException {
        int programCounter = 0;
        Stack<Double> operandStack = new Stack<>();
        
        while(programCounter < instrs.size()) {
            Instr instr = instrs.get(programCounter);
            programCounter++;
            
            instr.execute(operandStack, vars);
        }
        
        if(!operandStack.isEmpty())
            throw new ExecutionException("unexpected operand stack size (" + operandStack.size()
                    + ") at the end of execution");
    }
}