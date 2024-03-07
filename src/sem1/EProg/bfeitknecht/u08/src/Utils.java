import java.io.PrintStream;

/**
 * Some helper methods for working with int and double arrays.
 */
public class Utils {
    
    /**
     * Returns the index of the minimal value in the given array. If multiple minimal value exist,
     * the index of the first one is returned.
     */
    public static int minIndex(int[] values) {
        int minIndex = -1;
        int min = Integer.MAX_VALUE;
        for(int i = 0; i < values.length; i++)
            if(values[i] < min) {
                minIndex = i;
                min = values[i];
            }
        return minIndex;
    }
    
    /**
     * Prints a small (horizontal) bar diagram of the given values to the given stream.
     */
    public static void printBars(double[] values, PrintStream stream) {
        for(int i = 0; i < values.length; i++)
            stream.printf("%4.1f %s\n", values[i], toBar((int) values[i]));
        stream.println();
    }
    
    /**
     * Prints a small (horizontal) bar diagram of the given values to the given stream.
     */
    public static void printBars(int[] values, PrintStream stream) {
        for(int i = 0; i < values.length; i++)
            stream.printf("%4d %s\n", values[i], toBar(values[i]));
        stream.println();
    }
    
    private static String toBar(int value) {
        String bar = "";
        for(int i = 0; i < value; i++)
            bar += "-";
        return bar;
    }
}
