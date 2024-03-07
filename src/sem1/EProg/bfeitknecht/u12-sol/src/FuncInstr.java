
import java.util.Map;
import java.util.Stack;

/**
 * An instruction that pops a value from the operand stack, applies a given function to it and
 * pushes the result back on the stack.
 * <p>
 * There are multiple subclasses of this class, one for each kind of function. Use the static
 * {@link #create(String)} method to obtain an instance for a given function name.
 */
public class FuncInstr implements Instr {
    
    /**
     * Returns a {@link FuncInstr} instance for the given function name. If that function is not
     * supported, an {@link IllegalArgumentException} is thrown.
     */
    public static FuncInstr create(String name) {
        if(name.equals("sin"))
            return new SinInstr();
        if(name.equals("cos"))
            return new CosInstr();
        if(name.equals("tan"))
            return new TanInstr();
        if(name.equals("signum"))
            return new SignumInstr();
        if(name.equals("abs"))
            return new AbsInstr();
        if(name.equals("round"))
            return new RoundInstr();
        
        throw new IllegalArgumentException("unknown function '" + name + "'");
    }
    
    public void execute(Stack<Double> operandStack, Map<String, Double> vars)
            throws ExecutionException {
        if(operandStack.isEmpty())
            throw new ExecutionException("empty operand stack when applying function");
                
        double arg = operandStack.pop();
        double result = applyFunc(arg);
        operandStack.push(result);
    }
    
    protected double applyFunc(double arg) {
        throw new Error("the applyFunc() method must be overridden");
    }
}

class SinInstr extends FuncInstr {
    @Override
    protected double applyFunc(double arg) {
        return Math.sin(arg);
    }
}

class CosInstr extends FuncInstr {
    @Override
    protected double applyFunc(double arg) {
        return Math.cos(arg);
    }
}

class TanInstr extends FuncInstr {
    @Override
    protected double applyFunc(double arg) {
        return Math.tan(arg);
    }
}

class SignumInstr extends FuncInstr {
    @Override
    protected double applyFunc(double arg) {
        return Math.signum(arg);
    }
}

class AbsInstr extends FuncInstr {
    @Override
    protected double applyFunc(double arg) {
        return Math.abs(arg);
    }
}

class RoundInstr extends FuncInstr {
    @Override
    protected double applyFunc(double arg) {
        return Math.round(arg);
    }
}
