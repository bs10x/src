
/**
 * Parses and interprets expressions according to the following EBNF:
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
public class Interpreter {
    
    private final double xValue;
    
    /**
     * Creates an interpreter with a variable mapping that contains a single variable <i>x</i> that
     * has the given value.
     */
    public Interpreter(double xValue) {
        this.xValue = xValue;
    }
    
    /**
     * Tokenizes, parses, and interprets the given expression and returns its value.
     */
    public double interpret(String expr) throws ProgramException {
        Tokenizer tokenizer = new Tokenizer(expr);
        double result = interpretExpr(tokenizer);
        if(tokenizer.hasNext()) {
			throw unexpectedInput(tokenizer, "end of input");
		}
        return result;
    }
    
    /**
     * Interprets the <i>expr</i> that corresponds to the next tokens and returns its value.
     */
    private double interpretExpr(Tokenizer tokenizer) throws ProgramException {
        double left = interpretTerm(tokenizer);
        if(tokenizer.hasNextOp()) {
            String opName = tokenizer.nextOp();
            double right = interpretTerm(tokenizer);
            
            if(opName.equals("+")) {
				return left + right;
			}
            if(opName.equals("-")) {
				return left - right;
			}
            if(opName.equals("*")) {
				return left * right;
			}
            if(opName.equals("/")) {
				return left / right;
			}
            if(opName.equals("^")) {
				return Math.pow(left, right);
			}
            
            throw new ProgramException("unknown operator '" + opName + "'");
        } else {
			return left;
		}
    }
    
    /**
     * Interprets the <i>term</i> that corresponds to the next tokens and returns its value.
     */
    private double interpretTerm(Tokenizer tokenizer) throws ProgramException {
        if(tokenizer.hasNextOpen()) {
            tokenizer.nextOpen();
            double expr = interpretExpr(tokenizer);
            if(!tokenizer.hasNextClose()) {
				throw unexpectedInput(tokenizer, "')'");
			}
            tokenizer.nextClose();
            return expr;
        }
        else if(tokenizer.hasNextFunc()) {
            String token = tokenizer.nextFunc(); // includes the '('
            String funcName = token.substring(0, token.length() - 1);
            double arg = interpretExpr(tokenizer);
            if(!tokenizer.hasNextClose()) {
				throw unexpectedInput(tokenizer, "')'");
			}
            tokenizer.nextClose();
            
            if(funcName.equals("sin")) {
				return Math.sin(arg);
			}
            if(funcName.equals("cos")) {
				return Math.cos(arg);
			}
            if(funcName.equals("tan")) {
				return Math.tan(arg);
			}
            if(funcName.equals("signum")) {
				return Math.signum(arg);
			}
            if(funcName.equals("abs")) {
				return Math.abs(arg);
			}
            
            throw new ProgramException("unknown function '" + funcName + "'");
        }
        else {
            return interpretAtom(tokenizer);
        }
    }
    
    /**
     * Interprets the <i>atom</i> that corresponds to the next token and returns its value.
     */
    private double interpretAtom(Tokenizer tokenizer) throws ProgramException {
        if(tokenizer.hasNextNum()) {
			return tokenizer.nextNum();
		} else if(tokenizer.hasNextVar()) {
            String varName = tokenizer.nextVar();
            if(!varName.equals("x")) {
				throw new ProgramException("unknown variable '" + varName + "'");
			}
            return xValue;
        } else {
			throw unexpectedInput(tokenizer, "num or var");
		}
    }
    
    /**
     * Creates a {@link ProgramException} with a message that indicates that some unexpected token
     * occurred. Note that the caller still needs to throw this exception:
     * 
     * <pre>
     * throw unexpectedInput(tokenizer, "a 'foo' token");
     * </pre>
     */
    private static ProgramException unexpectedInput(Tokenizer tokenizer, String expected) {
        String msg;
        if(tokenizer.hasNext()) {
			msg = "Syntax error: unexpected token '" + tokenizer.next() + "', expected " + expected;
		} else {
			msg = "Syntax error: unexpected end of input, expected " + expected;
		}
        
        return new ProgramException(msg);
    }
}
