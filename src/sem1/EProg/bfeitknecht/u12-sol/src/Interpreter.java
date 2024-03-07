
import java.util.HashMap;
import java.util.Map;

/**
 * Parses and interprets programs according to the following EBNF:
 * 
 * <pre>
 * digit <= 0 | 1 | ... | 9
 *  char <= A | B | ... | Z | a | b | ... | z
 *   num <= digit { digit } [ . digit { digit } ]
 *   var <= char { char }
 *  func <= char { char } (
 *    op <= + | - | * | / | ˆ
 *  atom <= num | var
 *  term <= ( expr ) | func expr ) | atom
 *  expr <= term [ op term ]
 *  stmt <= var = expr ;
 *  prog <= { stmt }
 * </pre>
 */
public class Interpreter {
    
    private final Map<String, Double> vars = new HashMap<>();
    
    /**
     * Creates an interpreter with an empty variable mapping.
     */
    public Interpreter() {}
    
    /**
     * Creates an interpreter with the given initial variable mapping. Note that the given
     * <code>Map</code> instance is not used directly, but its entries are copied to the internal
     * map instead.
     */
    public Interpreter(Map<String, Double> initialVars) {
        // don't use the provided map as our "vars" map directly,
        // clients may accidentally modify it later:
        vars.putAll(initialVars);
    }
    
    /**
     * Returns the map that holds the names and values of the variables that are currently used by
     * the interpreter. The map is "live", i.e., modifications are visible by interpreted programs.
     * This means that you can use it to initialize some variables before a program is interpreted.
     */
    public Map<String, Double> getVars() {
        return vars;
    }
    
    /**
     * Tokenizes, parses, and interprets the given program. After this method has returned, the
     * results of the program can be examined using the {@link #getVars()} variable mapping.
     */
    public void interpret(String expr) throws ProgramException {
        Tokenizer tokenizer = new Tokenizer(expr);
        interpretProg(tokenizer);
        if(tokenizer.hasNext()) {
            throw unexpectedInput(tokenizer, "end of input");
        }
    }
    
    /**
     * Interprets the <i>prog</i> that corresponds to all the tokens in the given Tokenizer.
     */
    private void interpretProg(Tokenizer tokenizer) throws ProgramException {
        while(tokenizer.hasNext()) {
            interpretStmt(tokenizer);
        }
    }
    
    /**
     * Interprets the <i>stmt</i> that corresponds to the next tokens.
     */
    private void interpretStmt(Tokenizer tokenizer) throws ProgramException {
        if(!tokenizer.hasNextVar()) {
            throw unexpectedInput(tokenizer, "var");
        }
        String var = tokenizer.nextVar();
        
        if(!tokenizer.hasNextAssign()) {
            throw unexpectedInput(tokenizer, "'='");
        }
        tokenizer.nextAssign();
        
        double expr = interpretExpr(tokenizer);
        
        if(!tokenizer.hasNextSemi()) {
            throw unexpectedInput(tokenizer, "';'");
        }
        tokenizer.nextSemi();
        
        vars.put(var, expr);
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
        }
        else {
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
            if(!vars.containsKey(varName)) {
				throw new ProgramException("unknown variable '" + varName + "'");
			}
            return vars.get(varName);
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
