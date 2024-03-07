
import java.util.ArrayList;
import java.util.List;

/**
 * Parses and compiles programs according to the following EBNF:
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
 * 
 * The compiled form of a program is and instance of {@link Program}, essentially a list of
 * {@link Instr}s.
 */
public class Compiler {
    
    /**
     * Tokenizes, parses, and compiles the given program string.
     */
    public Program compile(String expr) throws ProgramException {
        Tokenizer tokenizer = new Tokenizer(expr);
        List<Instr> instrs = compileProg(tokenizer);
        if(tokenizer.hasNext())
            throw unexpectedInput(tokenizer, "end of input");
        return new Program(instrs);
    }
    
    /**
     * Compiles the <i>prog</i> that corresponds to all the tokens in the given Tokenizer.
     */
    private List<Instr> compileProg(Tokenizer tokenizer) throws ProgramException {
        ArrayList<Instr> instrs = new ArrayList<>();
        while(tokenizer.hasNext())
            instrs.addAll(compileStmt(tokenizer));
        return instrs;
    }
    
    /**
     * Compiles the <i>stmt</i> that corresponds to the next tokens.
     */
    private List<Instr> compileStmt(Tokenizer tokenizer) throws ProgramException {
        if(!tokenizer.hasNextVar())
            throw unexpectedInput(tokenizer, "var");
        String var = tokenizer.nextVar();
        
        if(!tokenizer.hasNextAssign())
            throw unexpectedInput(tokenizer, "'='");
        tokenizer.nextAssign();
        
        List<Instr> instrs = compileExpr(tokenizer);
        
        if(!tokenizer.hasNextSemi())
            throw unexpectedInput(tokenizer, "';'");
        tokenizer.nextSemi();
        
        instrs.add(new StoreInstr(var));
        return instrs;
    }
    
    /**
     * Compiles the <i>expr</i> that corresponds to the next tokens.
     */
    private List<Instr> compileExpr(Tokenizer tokenizer) throws ProgramException {
        List<Instr> instrs = compileTerm(tokenizer);
        if(tokenizer.hasNextOp()) {
            String opName = tokenizer.nextOp();
            instrs.addAll(compileTerm(tokenizer));
            instrs.add(OpInstr.create(opName));
        }
        return instrs;
    }
    
    /**
     * Compiles the <i>term</i> that corresponds to the next tokens.
     */
    private List<Instr> compileTerm(Tokenizer tokenizer) throws ProgramException {
        if(tokenizer.hasNextOpen()) {
            tokenizer.nextOpen();
            List<Instr> instrs = compileExpr(tokenizer);
            if(!tokenizer.hasNextClose())
                throw unexpectedInput(tokenizer, "')'");
            tokenizer.nextClose();
            return instrs;
        }
        else if(tokenizer.hasNextFunc()) {
            String token = tokenizer.nextFunc(); // includes the '('
            String funcName = token.substring(0, token.length() - 1);
            List<Instr> instrs = compileExpr(tokenizer);
            if(!tokenizer.hasNextClose())
                throw unexpectedInput(tokenizer, "')'");
            tokenizer.nextClose();
            
            try {
            	instrs.add(FuncInstr.create(funcName));
            } catch (IllegalArgumentException e) {
            	throw new ProgramException(e.getMessage());
            }
            
            return instrs;
        }
        else
            return compileAtom(tokenizer);
    }
    
    /**
     * Compiles the <i>atom</i> that corresponds to the next token.
     */
    private List<Instr> compileAtom(Tokenizer tokenizer) throws ProgramException {
        List<Instr> instrs = new ArrayList<>();
        if(tokenizer.hasNextNum())
            instrs.add(new ConstInstr(tokenizer.nextNum()));
        else if(tokenizer.hasNextVar()) {
            String varName = tokenizer.nextVar();
            instrs.add(new LoadInstr(varName));
        }
        else
            throw unexpectedInput(tokenizer, "num or var");
        return instrs;
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
        if(tokenizer.hasNext())
            msg = "Syntax error: unexpected token '" + tokenizer.next() + "', expected " + expected;
        else
            msg = "Syntax error: unexpected end of input, expected " + expected;
        
        return new ProgramException(msg);
    }
}