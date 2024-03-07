import java.util.Locale;
import java.util.Scanner;

/**
 * Splits a string into a stream of tokens. A tokenizer is stateful, like
 * {@link Scanner}. The kind of the next token can be determined using the
 * <code>hasNext*()</code> methods, while tokens can be consumed using the
 * <code>next*()</code> methods.
 */
public class Tokenizer {

    private static final String NUM_RULE = "[0-9]+(\\.[0-9]+)?";
    private static final String VAR_RULE = "[A-Za-z]+";
    private static final String FUNC_RULE = "[A-Za-z]+\\(";
    private static final String OP_RULE = "\\+|-|\\*|/|\\^";
    private static final String OPEN_RULE = "\\(";
    private static final String CLOSE_RULE = "\\)";

    private static final String DELIMITER = "\\s+|(?![A-Za-z0-9.\\(])|(?<![A-Za-z0-9.])";

    private final Scanner scanner;

    /**
     * Creates a tokenizer which tokenizes the given string.
     */
    public Tokenizer(String text) {
        this.scanner = new Scanner(text).useDelimiter(DELIMITER);
        this.scanner.useLocale(Locale.US);
    }

    /**
     * Returns <code>true</code> if there is any input left (no matter what kind of
     * token).
     */
    public boolean hasNext() {
        return scanner.hasNext();
    }

    /**
     * Returns <code>true</code> if the next token is a <i>num</i>,
     * <code>false</code> otherwise.
     */
    public boolean hasNextNum() {
        return scanner.hasNext(NUM_RULE);
    }

    /**
     * Returns <code>true</code> if the next token is a <i>var</i>,
     * <code>false</code> otherwise.
     */
    public boolean hasNextVar() {
        return scanner.hasNext(VAR_RULE);
    }

    /**
     * Returns <code>true</code> if the next token is a <i>func</i>,
     * <code>false</code> otherwise.
     */
    public boolean hasNextFunc() {
        return scanner.hasNext(FUNC_RULE);
    }

    /**
     * Returns <code>true</code> if the next token is a <i>op</i>,
     * <code>false</code> otherwise.
     */
    public boolean hasNextOp() {
        return scanner.hasNext(OP_RULE);
    }

    /**
     * Returns <code>true</code> if the next token is a <i>open</i>,
     * <code>false</code> otherwise.
     */
    public boolean hasNextOpen() {
        return scanner.hasNext(OPEN_RULE);
    }

    /**
     * Returns <code>true</code> if the next token is a <i>close</i>,
     * <code>false</code> otherwise.
     */
    public boolean hasNextClose() {
        return scanner.hasNext(CLOSE_RULE);
    }

    /**
     * Consumes the next token, no matter the kind.
     */
    public String next() {
        return scanner.next();
    }

    /**
     * Consumes the next <i>num</i> token and returns its numerical value as a
     * double.
     */
    public double nextNum() {
        return scanner.nextDouble();
    }

    /**
     * Consumes the next <i>var</i> token and returns its value as a string.
     */
    public String nextVar() {
        return scanner.next(VAR_RULE);
    }

    /**
     * Consumes the next <i>func</i> token and returns its value as a string
     * (including the left parenthesis).
     */
    public String nextFunc() {
        return scanner.next(FUNC_RULE);
    }

    /**
     * Consumes the next <i>op</i> token and returns its value as a string.
     */
    public String nextOp() {
        return scanner.next(OP_RULE);
    }

    /**
     * Consumes the next <i>open</i> token.
     */
    public void nextOpen() {
        scanner.next(OPEN_RULE);
    }

    /**
     * Consumes the next <i>close</i> token.
     */
    public void nextClose() {
        scanner.next(CLOSE_RULE);
    }
}
