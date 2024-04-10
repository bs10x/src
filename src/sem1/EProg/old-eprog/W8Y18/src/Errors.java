public class Errors {
    
    /**
     * Stops the execution of the program and displays the given message. Use this
     * method in your linked list implementation if a required condition is not met
     * (for example, if <code>get()</code> is called with an index that is greater
     * or equal to the current size of the list).
     */
    public static void error(String message) {
        throw new RuntimeException(message);
    }
}
