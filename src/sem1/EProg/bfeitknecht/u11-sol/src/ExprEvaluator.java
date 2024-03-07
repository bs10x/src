/**
 * Parses and evaluates expressions according to the following EBNF:
 * 
 * <pre>
 * digit <= 0 | 1 | ... | 9
 *  char <= A | B | ... | Z | a | b | ... | z
 *   num <= digit { digit } [ . digit { digit } ]
 *   var <= char { char }
 *  func <= char { char } (
 *    op <= + | - | * | / | ˆ
 *  open <= (
 * close <= )
 *  atom <= num | var
 *  term <= open expr close | func expr close | atom
 *  expr <= term [ op term ]
 * </pre>
 */
public class ExprEvaluator {

    private final double xValue;

    /**
     * Creates an evaluator with a variable mapping that contains a single variable
     * <i>x</i> that has the given value.
     */
    public ExprEvaluator(double xValue) {
        this.xValue = xValue;
    }

    /**
     * Tokenizes, parses, and evaluates the given expression and returns its value.
     */
    public double eval(String expr) throws EvaluationException {
        Tokenizer tokenizer = new Tokenizer(expr);
        double result = evalExpr(tokenizer);
        if (tokenizer.hasNext())
            throw unexpectedInput(tokenizer, "end of input");
        return result;
    }

    /**
     * Evaluates the <i>expr</i> that corresponds to the next tokens and returns its
     * value.
     */
    private double evalExpr(Tokenizer tokenizer) throws EvaluationException {
        double left = evalTerm(tokenizer);
        if (tokenizer.hasNextOp()) {
            String opName = tokenizer.nextOp();
            double right = evalTerm(tokenizer);

            if (opName.equals("+"))
                return left + right;
            if (opName.equals("-"))
                return left - right;
            if (opName.equals("*"))
                return left * right;
            if (opName.equals("/"))
                return left / right;
            if (opName.equals("^"))
                return Math.pow(left, right);

            throw new EvaluationException("unknown operator '" + opName + "'");
        } else
            return left;
    }

    /**
     * Evaluates the <i>term</i> that corresponds to the next tokens and returns its
     * value.
     */
    private double evalTerm(Tokenizer tokenizer) throws EvaluationException {
        if (tokenizer.hasNextOpen()) {
            tokenizer.nextOpen();
            double expr = evalExpr(tokenizer);
            if (!tokenizer.hasNextClose())
                throw unexpectedInput(tokenizer, "')'");
            tokenizer.nextClose();
            return expr;
        } else if (tokenizer.hasNextFunc()) {
            String token = tokenizer.nextFunc(); // includes the '('
            String funcName = token.substring(0, token.length() - 1);
            double arg = evalExpr(tokenizer);
            if (!tokenizer.hasNextClose())
                throw unexpectedInput(tokenizer, "')'");
            tokenizer.nextClose();

            if (funcName.equals("sin"))
                return Math.sin(arg);
            if (funcName.equals("cos"))
                return Math.cos(arg);
            if (funcName.equals("tan"))
                return Math.tan(arg);
            if (funcName.equals("signum"))
                return Math.signum(arg);
            if (funcName.equals("abs"))
                return Math.abs(arg);

            throw new EvaluationException("unknown function '" + funcName + "'");
        } else {
            return evalAtom(tokenizer);
        }
    }

    /**
     * Evaluates the <i>atom</i> that corresponds to the next token and returns its
     * value.
     */
    private double evalAtom(Tokenizer tokenizer) throws EvaluationException {
        if (tokenizer.hasNextNum())
            return tokenizer.nextNum();
        else if (tokenizer.hasNextVar()) {
            String varName = tokenizer.nextVar();
            if (!varName.equals("x"))
                throw new EvaluationException("unknown variable '" + varName + "'");
            return xValue;
        } else
            throw unexpectedInput(tokenizer, "num or var");
    }

    /**
     * Creates a {@link EvaluationException} with a message that indicates that some
     * unexpected token occurred. Note that the caller still needs to throw this
     * exception:
     * 
     * <pre>
     * throw unexpectedInput(tokenizer, "a 'foo' token");
     * </pre>
     */
    private static EvaluationException unexpectedInput(Tokenizer tokenizer, String expected) {
        String msg;
        if (tokenizer.hasNext())
            msg = "Syntax error: unexpected token '" + tokenizer.next() + "', expected " + expected;
        else
            msg = "Syntax error: unexpected end of input, expected " + expected;

        return new EvaluationException(msg);
    }
}
