public class BlackBox {

	public static void rotateArray(int[] values, int steps) {
        if(values == null || values.length == 0 || steps == 0) {
            /* nothing to do */
            return;
        }

        /* make 'steps' always non-negative and less than values.length */
        steps = ((steps % values.length) + values.length) % values.length;

        /* repeatedly rotate by 1 position to the right */
        for(int i = 0; i < steps; i++) {
            int leftValue = values[values.length - 1];
            for(int j = 0; j < values.length; j++) {
                int nextLeftValue = values[j];
                values[j] = leftValue;
                leftValue = nextLeftValue;
            }
        }

        return;
	}
}
