import static java.util.stream.Collectors.joining;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.Test;

public class ScrabbleTest {

    @Test
    void testAlfred() {
        String expected =
                "+---+---+---+---+---+---+\n" +
                "| A | L | F | R | E | D |\n" +
                "+---+---+---+---+---+---+\n" +
                "| L |               | E |\n" +
                "+---+               +---+\n" +
                "| F |               | R |\n" +
                "+---+               +---+\n" +
                "| R |               | F |\n" +
                "+---+               +---+\n" +
                "| E |               | L |\n" +
                "+---+---+---+---+---+---+\n" +
                "| D | E | R | F | L | A |\n" +
                "+---+---+---+---+---+---+";
        String output = captureOutput(() -> Scrabble.drawNameSquare("Alfred"));
        assertEquals(expected, output);
    }

    @Test
    void testJim() {
        String expected =
                "+---+---+---+\n" +
                "| J | I | M |\n" +
                "+---+---+---+\n" +
                "| I |   | I |\n" +
                "+---+---+---+\n" +
                "| M | I | J |\n" +
                "+---+---+---+";
        String output = captureOutput(() -> Scrabble.drawNameSquare("Jim"));
        assertEquals(expected, output);
    }

    private String captureOutput(Runnable r) {
        var out = new ByteArrayOutputStream();
        var temp = System.out;
        System.setOut(new PrintStream(out));
        try {
            r.run();
            return out.toString().lines()
                    .map(String::stripTrailing)
                    .collect(joining("\n"))
                    .stripTrailing();
        } finally {
            System.setOut(temp);
        }
    }
}
