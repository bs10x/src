public class SimulierenderNGrammSpieler extends SimulierenderTippsSpieler {

    private String[] nGramme = {"e", "n", "s", "r", "i", "h", "a", "en", "l", "t", "c", "ch", "u",
        "g", "f", "d", "er", "m", "b", "o", "w", "ei", "ge", "k", "in", "te", "sch", "sc", "he",
        "le", "se", "z", "st", "ie", "ic", "re", "be", "au", "ss", "ich", "ne", "el", "fe",
        "de", "an", "gen", "li", "ü", "un", "es", "hen", "hr", "ng", "che", "me", "v", "ra",
        "we", "la", "ll", "eh", "al", "as", "ar", "nd", "p", "ein", "nen", "sse", "ht", "ha",
        "cht", "wi", "is", "fen", "mm", "ke", "da", "wa", "hl", "us", "nn", "na", "ter", "ste",
        "lic", "sen", "ig", "fr", "so", "ö", "rt", "ri", "ben", "it", "hi", "hn", "ac", "lle",
        "ä"};

    public String[] tipps(String[] verwendeteWoerter) {
        return nGramme;
    }

    public String name() {
        return "Simulierender N-Gramm-Spieler";
    }
}
