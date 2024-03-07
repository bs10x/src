import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

import org.junit.jupiter.api.Test;

public class PersonenAnalyseTest {
    
    @Test
    public void testLiesPersonen() {
        String data = 
                "6\n" +
                "42.9 17.7 28.0 21  65.6 174.0 1\n" +
                "43.7 16.9 30.8 23  71.8 175.3 1\n" +
                "40.1 20.9 31.7 28  80.7 193.5 1\n" +
                "44.3 18.4 28.2 23  72.6 186.5 1\n" +
                "42.5 21.5 29.4 22  78.8 187.2 1\n" +
                "43.3 19.6 31.3 21  74.8 181.5 1";
        
        @SuppressWarnings("resource")
        Person[] personen = PersonenAnalyse.liesPersonen(new Scanner(data));
        assertEquals(6, personen.length);
        
        for(int i = 0; i < personen.length; i++)
            assertNotNull(personen[i]);
    }
    
    @Test
    public void testDruckeUngesunde() {
        Person[] p = {
                new Person(20, 75, 175, false, 40, 20, 30),
                new Person(20, 80, 180, false, 40, 20, 30),
                new Person(20, 86, 176, false, 40, 20, 30),
                new Person(20, 55, 174, false, 40, 20, 30),
                new Person(20, 81, 160, false, 40, 20, 30)
        };
        
        String erwarteterOutput =
                p[2].beschreibung() + " ist übergewichtig"  + System.lineSeparator() + 
                p[3].beschreibung() + " ist untergewichtig" + System.lineSeparator() + 
                p[4].beschreibung() + " ist fettleibig"     + System.lineSeparator();
        
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        PersonenAnalyse.druckeUngesunde(p, new PrintStream(bytes));
        assertEquals(erwarteterOutput, bytes.toString());
    }
    

    @Test
    public void testPartnerQualitaet() {
        Person[] p = {
                new Person(20, 75, 175, false, 40, 20, 30),
                new Person(20, 75, 175, false, 40, 15, 40),
                new Person(20, 75, 172, false, 40, 19, 31),
                new Person(20, 75, 183, false, 37, 18, 35)
        };
        
        assertEquals(1.0, PersonenAnalyse.partnerQualitaet(p[0], p[0]), 0.001);
        assertEquals(1.0, PersonenAnalyse.partnerQualitaet(p[0], p[1]), 0.001);
        
        assertEquals(0.082, PersonenAnalyse.partnerQualitaet(p[0], p[2]), 0.001);
        assertEquals(0.082, PersonenAnalyse.partnerQualitaet(p[1], p[2]), 0.001);
        assertEquals(0.013, PersonenAnalyse.partnerQualitaet(p[0], p[3]), 0.001);
        assertEquals(0.013, PersonenAnalyse.partnerQualitaet(p[1], p[3]), 0.001);
        assertEquals(0.007, PersonenAnalyse.partnerQualitaet(p[2], p[3]), 0.001);
        
        for(int i = 0; i < p.length; i++)
            for(int j = 0; j < p.length; j++) {
                double qIJ = PersonenAnalyse.partnerQualitaet(p[i], p[j]);
                double qJI = PersonenAnalyse.partnerQualitaet(p[j], p[i]);
                assertEquals(qIJ, qJI, 0.001);
            }
    }
}
