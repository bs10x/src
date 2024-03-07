
import java.util.Map;
import java.util.Stack;

/**
 * An instruction that pops two values <i>r</i> and <i>l</i> (in that order) from the operand stack,
 * applies a given operator to them and pushes the result back on the stack.
 * <p>
 * There are multiple subclasses of this class, one for each kind of operator. Use the static
 * {@link #create(String)} method to obtain an instance for a given function name.
 */
public class OpInstr implements Instr {
    
    /**
     * Returns a {@link OpInstr} instance for the given operator name. If that operator is not
     * supported, an {@link IllegalArgumentException} is thrown.
     */
    public static OpInstr create(String name) {
        if(name.equals("+"))
            return new AddInstr();
        if(name.equals("-"))
            return new SubInstr();
        if(name.equals("*"))
            return new MulInstr();
        if(name.equals("/"))
            return new DivInstr();
        if(name.equals("^"))
            return new PowInstr();
        
        throw new IllegalArgumentException("unknown operator '" + name + "'");
    }
    
    public void execute(Stack<Double> operandStack, Map<String, Double> vars)
            throws ExecutionException {
        if(operandStack.size() < 2)
            throw new ExecutionException("not enough operands on stack when applying operator");
        
        double right = operandStack.pop();
        double left = operandStack.pop();
        operandStack.push(applyOp(left, right));
    }
    
    protected double applyOp(double left, double right) {
        throw new Error("the applyOp() method must be overridden");
    }
}

class AddInstr extends OpInstr {
    @Override
    protected double applyOp(double left, double right) {
        return left + right;
    }
}

class SubInstr extends OpInstr {
    @Override
    protected double applyOp(double left, double right) {
        return left - right;
    }
}

class MulInstr extends OpInstr {
    @Override
    protected double applyOp(double left, double right) {
        return left * right;
    }
}

class DivInstr extends OpInstr {
    @Override
    protected double applyOp(double left, double right) {
        return left / right;
    }
}

class PowInstr extends OpInstr {
    @Override
    protected double applyOp(double left, double right) {
        return Math.pow(left, right);
    }
}
