public class SimulierenderBuchstabenSpieler extends SimulierenderTippsSpieler {

    private String[] buchstaben = {"a", "ä", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l",
        "m", "n", "o", "ö", "p", "q", "r", "s", "t", "u", "ü", "v", "w", "x", "y", "z"};

    public String[] tipps(String[] verwendeteWoerter) {
        return buchstaben;
    }

    public String name() {
        return "Simulierender Buchstaben-Spieler";
    }
}
