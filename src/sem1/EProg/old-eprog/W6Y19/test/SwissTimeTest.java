import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class SwissTimeTest {

    /*
     * Test correct minutes ("1 ab", "viertel ab", "halbi", etc.)
     */

    @Test
    public void testCorrectMinutesSharp() {
        assertTrue(SwissTime.toSwissGerman("00:00").startsWith("12"));
        assertTrue(SwissTime.toSwissGerman("01:00").startsWith("1"));
        assertTrue(SwissTime.toSwissGerman("05:00").startsWith("5"));
        assertTrue(SwissTime.toSwissGerman("09:00").startsWith("9"));
        assertTrue(SwissTime.toSwissGerman("12:00").startsWith("12"));
        assertTrue(SwissTime.toSwissGerman("15:00").startsWith("3"));
        assertTrue(SwissTime.toSwissGerman("18:00").startsWith("6"));
        assertTrue(SwissTime.toSwissGerman("23:00").startsWith("11"));
    }

    @Test
    public void testCorrectMinutesViertelHalbi() {
        assertTrue(SwissTime.toSwissGerman("00:15").startsWith("viertel ab"));
        assertTrue(SwissTime.toSwissGerman("09:45").startsWith("viertel vor"));
        assertTrue(SwissTime.toSwissGerman("12:30").startsWith("halbi"));
    }

    @Test
    public void testCorrectMinutesAb() {
        assertTrue(SwissTime.toSwissGerman("00:01").startsWith("1 ab"));
        assertTrue(SwissTime.toSwissGerman("01:02").startsWith("2 ab"));
        assertTrue(SwissTime.toSwissGerman("05:03").startsWith("3 ab"));
        assertTrue(SwissTime.toSwissGerman("09:05").startsWith("5 ab"));
        assertTrue(SwissTime.toSwissGerman("12:10").startsWith("10 ab"));
        assertTrue(SwissTime.toSwissGerman("15:11").startsWith("11 ab"));
        assertTrue(SwissTime.toSwissGerman("18:14").startsWith("14 ab"));
        assertTrue(SwissTime.toSwissGerman("19:16").startsWith("16 ab"));
        assertTrue(SwissTime.toSwissGerman("20:20").startsWith("20 ab"));
        assertTrue(SwissTime.toSwissGerman("21:22").startsWith("22 ab"));
        assertTrue(SwissTime.toSwissGerman("22:23").startsWith("23 ab"));
        assertTrue(SwissTime.toSwissGerman("23:24").startsWith("24 ab"));
    }

    @Test
    public void testCorrectMinutesVor() {
        assertTrue(SwissTime.toSwissGerman("00:40").startsWith("20 vor"));
        assertTrue(SwissTime.toSwissGerman("01:41").startsWith("19 vor"));
        assertTrue(SwissTime.toSwissGerman("05:42").startsWith("18 vor"));
        assertTrue(SwissTime.toSwissGerman("09:44").startsWith("16 vor"));
        assertTrue(SwissTime.toSwissGerman("12:46").startsWith("14 vor"));
        assertTrue(SwissTime.toSwissGerman("15:50").startsWith("10 vor"));
        assertTrue(SwissTime.toSwissGerman("18:55").startsWith("5 vor"));
        assertTrue(SwissTime.toSwissGerman("19:58").startsWith("2 vor"));
        assertTrue(SwissTime.toSwissGerman("20:59").startsWith("1 vor"));
    }

    @Test
    public void testCorrectMinutesVorHalbi() {
        assertTrue(SwissTime.toSwissGerman("00:25").startsWith("5 vor halbi"));
        assertTrue(SwissTime.toSwissGerman("01:26").startsWith("4 vor halbi"));
        assertTrue(SwissTime.toSwissGerman("05:28").startsWith("2 vor halbi"));
        assertTrue(SwissTime.toSwissGerman("09:29").startsWith("1 vor halbi"));
    }

    @Test
    public void testCorrectMinutesAbHalbi() {
        assertTrue(SwissTime.toSwissGerman("12:31").startsWith("1 ab halbi"));
        assertTrue(SwissTime.toSwissGerman("15:33").startsWith("3 ab halbi"));
        assertTrue(SwissTime.toSwissGerman("18:35").startsWith("5 ab halbi"));
        assertTrue(SwissTime.toSwissGerman("19:37").startsWith("7 ab halbi"));
        assertTrue(SwissTime.toSwissGerman("20:39").startsWith("9 ab halbi"));
    }

    /*
     * Test correct hour (12i, 1, 6i, etc.)
     */

    @Test
    public void testCorrectHour12i() {
        // everything between 23:25 and 00:24 should have the hour "12i"
        assertTrue(SwissTime.toSwissGerman("23:25").contains("12i"));
        assertTrue(SwissTime.toSwissGerman("23:26").contains("12i"));
        assertTrue(SwissTime.toSwissGerman("23:59").contains("12i"));
        assertTrue(SwissTime.toSwissGerman("00:00").contains("12i"));
        assertTrue(SwissTime.toSwissGerman("00:01").contains("12i"));
        assertTrue(SwissTime.toSwissGerman("00:23").contains("12i"));
        assertTrue(SwissTime.toSwissGerman("00:24").contains("12i"));

        // same for times between 11:25 and 12:24
        assertTrue(SwissTime.toSwissGerman("11:25").contains("12i"));
        assertTrue(SwissTime.toSwissGerman("11:26").contains("12i"));
        assertTrue(SwissTime.toSwissGerman("11:59").contains("12i"));
        assertTrue(SwissTime.toSwissGerman("12:00").contains("12i"));
        assertTrue(SwissTime.toSwissGerman("12:01").contains("12i"));
        assertTrue(SwissTime.toSwissGerman("12:23").contains("12i"));
        assertTrue(SwissTime.toSwissGerman("12:24").contains("12i"));
    }

    @Test
    public void testCorrectHour1() {
        // everything between 00:25 and 01:24 should have the hour "1"
        assertTrue(SwissTime.toSwissGerman("23:25").contains("1"));
        assertTrue(SwissTime.toSwissGerman("23:26").contains("1"));
        assertTrue(SwissTime.toSwissGerman("23:59").contains("1"));
        assertTrue(SwissTime.toSwissGerman("00:00").contains("1"));
        assertTrue(SwissTime.toSwissGerman("00:02").contains("1"));
        assertTrue(SwissTime.toSwissGerman("00:23").contains("1"));
        assertTrue(SwissTime.toSwissGerman("00:24").contains("1"));

        // same for times between 12:25 and 13:24
        assertTrue(SwissTime.toSwissGerman("12:25").contains("1"));
        assertTrue(SwissTime.toSwissGerman("12:26").contains("1"));
        assertTrue(SwissTime.toSwissGerman("12:59").contains("1"));
        assertTrue(SwissTime.toSwissGerman("13:00").contains("1"));
        assertTrue(SwissTime.toSwissGerman("13:02").contains("1"));
        assertTrue(SwissTime.toSwissGerman("13:23").contains("1"));
        assertTrue(SwissTime.toSwissGerman("13:24").contains("1"));
    }

    @Test
    public void testCorrectHour2() {
        // everything between 01:25 and 02:24 should have the hour "2"
        assertTrue(SwissTime.toSwissGerman("01:25").contains("2"));
        assertTrue(SwissTime.toSwissGerman("01:26").contains("2"));
        assertTrue(SwissTime.toSwissGerman("01:59").contains("2"));
        assertTrue(SwissTime.toSwissGerman("02:00").contains("2"));
        assertTrue(SwissTime.toSwissGerman("02:01").contains("2"));
        // special handling for these two, because they already contain
        // a "2" in the minutes:
        assertTrue(SwissTime.toSwissGerman("02:23").replace("23", "").contains("2"));
        assertTrue(SwissTime.toSwissGerman("02:24").replace("24", "").contains("2"));

        // same for times between 13:25 and 14:24
        assertTrue(SwissTime.toSwissGerman("13:25").contains("2"));
        assertTrue(SwissTime.toSwissGerman("13:26").contains("2"));
        assertTrue(SwissTime.toSwissGerman("13:59").contains("2"));
        assertTrue(SwissTime.toSwissGerman("14:00").contains("2"));
        assertTrue(SwissTime.toSwissGerman("14:01").contains("2"));
        // see above
        assertTrue(SwissTime.toSwissGerman("14:23").replace("23", "").contains("2"));
        assertTrue(SwissTime.toSwissGerman("14:24").replace("24", "").contains("2"));
    }

    @Test
    public void testCorrectHour3() {
        // everything between 02:25 and 03:24 should have the hour "3"
        assertTrue(SwissTime.toSwissGerman("02:25").contains("3"));
        assertTrue(SwissTime.toSwissGerman("02:26").contains("3"));
        assertTrue(SwissTime.toSwissGerman("02:59").contains("3"));
        assertTrue(SwissTime.toSwissGerman("03:00").contains("3"));
        assertTrue(SwissTime.toSwissGerman("03:01").contains("3"));
        assertTrue(SwissTime.toSwissGerman("03:22").contains("3"));
        assertTrue(SwissTime.toSwissGerman("03:24").contains("3"));

        // same for times between 14:25 and 15:24
        assertTrue(SwissTime.toSwissGerman("14:25").contains("3"));
        assertTrue(SwissTime.toSwissGerman("14:26").contains("3"));
        assertTrue(SwissTime.toSwissGerman("14:59").contains("3"));
        assertTrue(SwissTime.toSwissGerman("15:00").contains("3"));
        assertTrue(SwissTime.toSwissGerman("15:01").contains("3"));
        assertTrue(SwissTime.toSwissGerman("15:22").contains("3"));
        assertTrue(SwissTime.toSwissGerman("15:24").contains("3"));
    }

    @Test
    public void testCorrectHour4i() {
        // everything between 03:25 and 04:24 should have the hour "4i"
        assertTrue(SwissTime.toSwissGerman("03:25").contains("4i"));
        assertTrue(SwissTime.toSwissGerman("03:26").contains("4i"));
        assertTrue(SwissTime.toSwissGerman("03:59").contains("4i"));
        assertTrue(SwissTime.toSwissGerman("04:00").contains("4i"));
        assertTrue(SwissTime.toSwissGerman("04:01").contains("4i"));
        assertTrue(SwissTime.toSwissGerman("04:23").contains("4i"));
        assertTrue(SwissTime.toSwissGerman("04:24").contains("4i"));

        // same for times between 15:25 and 16:24
        assertTrue(SwissTime.toSwissGerman("15:25").contains("4i"));
        assertTrue(SwissTime.toSwissGerman("15:26").contains("4i"));
        assertTrue(SwissTime.toSwissGerman("15:59").contains("4i"));
        assertTrue(SwissTime.toSwissGerman("16:00").contains("4i"));
        assertTrue(SwissTime.toSwissGerman("16:01").contains("4i"));
        assertTrue(SwissTime.toSwissGerman("16:23").contains("4i"));
        assertTrue(SwissTime.toSwissGerman("16:24").contains("4i"));
    }

    @Test
    public void testCorrectHour5i() {
        // everything between 04:25 and 05:24 should have the hour "5i"
        assertTrue(SwissTime.toSwissGerman("04:25").contains("5i"));
        assertTrue(SwissTime.toSwissGerman("04:26").contains("5i"));
        assertTrue(SwissTime.toSwissGerman("04:59").contains("5i"));
        assertTrue(SwissTime.toSwissGerman("05:00").contains("5i"));
        assertTrue(SwissTime.toSwissGerman("05:01").contains("5i"));
        assertTrue(SwissTime.toSwissGerman("05:23").contains("5i"));
        assertTrue(SwissTime.toSwissGerman("05:24").contains("5i"));

        // same for times between 16:25 and 17:24
        assertTrue(SwissTime.toSwissGerman("16:25").contains("5i"));
        assertTrue(SwissTime.toSwissGerman("16:26").contains("5i"));
        assertTrue(SwissTime.toSwissGerman("16:59").contains("5i"));
        assertTrue(SwissTime.toSwissGerman("17:00").contains("5i"));
        assertTrue(SwissTime.toSwissGerman("17:01").contains("5i"));
        assertTrue(SwissTime.toSwissGerman("17:23").contains("5i"));
        assertTrue(SwissTime.toSwissGerman("17:24").contains("5i"));
    }

    @Test
    public void testCorrectHour6i() {
        // everything between 05:25 and 06:24 should have the hour "6i"
        assertTrue(SwissTime.toSwissGerman("05:25").contains("6i"));
        assertTrue(SwissTime.toSwissGerman("05:26").contains("6i"));
        assertTrue(SwissTime.toSwissGerman("05:59").contains("6i"));
        assertTrue(SwissTime.toSwissGerman("06:00").contains("6i"));
        assertTrue(SwissTime.toSwissGerman("06:01").contains("6i"));
        assertTrue(SwissTime.toSwissGerman("06:23").contains("6i"));
        assertTrue(SwissTime.toSwissGerman("06:24").contains("6i"));

        // same for times between 17:25 and 18:24
        assertTrue(SwissTime.toSwissGerman("17:25").contains("6i"));
        assertTrue(SwissTime.toSwissGerman("17:26").contains("6i"));
        assertTrue(SwissTime.toSwissGerman("17:59").contains("6i"));
        assertTrue(SwissTime.toSwissGerman("18:00").contains("6i"));
        assertTrue(SwissTime.toSwissGerman("18:01").contains("6i"));
        assertTrue(SwissTime.toSwissGerman("18:23").contains("6i"));
        assertTrue(SwissTime.toSwissGerman("18:24").contains("6i"));
    }

    @Test
    public void testCorrectHour7i() {
        // everything between 06:25 and 07:24 should have the hour "6i"
        assertTrue(SwissTime.toSwissGerman("06:25").contains("7i"));
        assertTrue(SwissTime.toSwissGerman("06:26").contains("7i"));
        assertTrue(SwissTime.toSwissGerman("06:59").contains("7i"));
        assertTrue(SwissTime.toSwissGerman("07:00").contains("7i"));
        assertTrue(SwissTime.toSwissGerman("07:01").contains("7i"));
        assertTrue(SwissTime.toSwissGerman("07:23").contains("7i"));
        assertTrue(SwissTime.toSwissGerman("07:24").contains("7i"));

        // same for times between 18:25 and 19:24
        assertTrue(SwissTime.toSwissGerman("18:25").contains("7i"));
        assertTrue(SwissTime.toSwissGerman("18:26").contains("7i"));
        assertTrue(SwissTime.toSwissGerman("18:59").contains("7i"));
        assertTrue(SwissTime.toSwissGerman("19:00").contains("7i"));
        assertTrue(SwissTime.toSwissGerman("19:01").contains("7i"));
        assertTrue(SwissTime.toSwissGerman("19:23").contains("7i"));
        assertTrue(SwissTime.toSwissGerman("19:24").contains("7i"));
    }

    @Test
    public void testCorrectHour8i() {
        // everything between 07:25 and 08:24 should have the hour "8i"
        assertTrue(SwissTime.toSwissGerman("07:25").contains("8i"));
        assertTrue(SwissTime.toSwissGerman("07:26").contains("8i"));
        assertTrue(SwissTime.toSwissGerman("07:59").contains("8i"));
        assertTrue(SwissTime.toSwissGerman("08:00").contains("8i"));
        assertTrue(SwissTime.toSwissGerman("08:01").contains("8i"));
        assertTrue(SwissTime.toSwissGerman("08:23").contains("8i"));
        assertTrue(SwissTime.toSwissGerman("08:24").contains("8i"));

        // same for times between 19:25 and 20:24
        assertTrue(SwissTime.toSwissGerman("19:25").contains("8i"));
        assertTrue(SwissTime.toSwissGerman("19:26").contains("8i"));
        assertTrue(SwissTime.toSwissGerman("19:59").contains("8i"));
        assertTrue(SwissTime.toSwissGerman("20:00").contains("8i"));
        assertTrue(SwissTime.toSwissGerman("20:01").contains("8i"));
        assertTrue(SwissTime.toSwissGerman("20:23").contains("8i"));
        assertTrue(SwissTime.toSwissGerman("20:24").contains("8i"));
    }

    @Test
    public void testCorrectHour9i() {
        // everything between 08:25 and 09:24 should have the hour "9i"
        assertTrue(SwissTime.toSwissGerman("08:25").contains("9i"));
        assertTrue(SwissTime.toSwissGerman("08:26").contains("9i"));
        assertTrue(SwissTime.toSwissGerman("08:59").contains("9i"));
        assertTrue(SwissTime.toSwissGerman("09:00").contains("9i"));
        assertTrue(SwissTime.toSwissGerman("09:01").contains("9i"));
        assertTrue(SwissTime.toSwissGerman("09:23").contains("9i"));
        assertTrue(SwissTime.toSwissGerman("09:24").contains("9i"));

        // same for times between 20:25 and 21:24
        assertTrue(SwissTime.toSwissGerman("20:25").contains("9i"));
        assertTrue(SwissTime.toSwissGerman("20:26").contains("9i"));
        assertTrue(SwissTime.toSwissGerman("20:59").contains("9i"));
        assertTrue(SwissTime.toSwissGerman("21:00").contains("9i"));
        assertTrue(SwissTime.toSwissGerman("21:01").contains("9i"));
        assertTrue(SwissTime.toSwissGerman("21:23").contains("9i"));
        assertTrue(SwissTime.toSwissGerman("21:24").contains("9i"));
    }

    @Test
    public void testCorrectHour10i() {
        // everything between 09:25 and 10:24 should have the hour "10i"
        assertTrue(SwissTime.toSwissGerman("09:25").contains("10i"));
        assertTrue(SwissTime.toSwissGerman("09:26").contains("10i"));
        assertTrue(SwissTime.toSwissGerman("09:59").contains("10i"));
        assertTrue(SwissTime.toSwissGerman("10:00").contains("10i"));
        assertTrue(SwissTime.toSwissGerman("10:01").contains("10i"));
        assertTrue(SwissTime.toSwissGerman("10:23").contains("10i"));
        assertTrue(SwissTime.toSwissGerman("10:24").contains("10i"));

        // same for times between 21:25 and 22:24
        assertTrue(SwissTime.toSwissGerman("21:25").contains("10i"));
        assertTrue(SwissTime.toSwissGerman("21:26").contains("10i"));
        assertTrue(SwissTime.toSwissGerman("21:59").contains("10i"));
        assertTrue(SwissTime.toSwissGerman("22:00").contains("10i"));
        assertTrue(SwissTime.toSwissGerman("22:01").contains("10i"));
        assertTrue(SwissTime.toSwissGerman("22:23").contains("10i"));
        assertTrue(SwissTime.toSwissGerman("22:24").contains("10i"));
    }

    @Test
    public void testCorrectHour11i() {
        // everything between 10:25 and 11:24 should have the hour "11i"
        assertTrue(SwissTime.toSwissGerman("10:25").contains("11i"));
        assertTrue(SwissTime.toSwissGerman("10:26").contains("11i"));
        assertTrue(SwissTime.toSwissGerman("10:59").contains("11i"));
        assertTrue(SwissTime.toSwissGerman("11:00").contains("11i"));
        assertTrue(SwissTime.toSwissGerman("11:01").contains("11i"));
        assertTrue(SwissTime.toSwissGerman("11:23").contains("11i"));
        assertTrue(SwissTime.toSwissGerman("11:24").contains("11i"));

        // same for times between 22:25 and 23:24
        assertTrue(SwissTime.toSwissGerman("22:25").contains("11i"));
        assertTrue(SwissTime.toSwissGerman("22:26").contains("11i"));
        assertTrue(SwissTime.toSwissGerman("22:59").contains("11i"));
        assertTrue(SwissTime.toSwissGerman("23:00").contains("11i"));
        assertTrue(SwissTime.toSwissGerman("23:01").contains("11i"));
        assertTrue(SwissTime.toSwissGerman("23:23").contains("11i"));
        assertTrue(SwissTime.toSwissGerman("23:24").contains("11i"));
    }

    /*
     * Test correct suffix (znacht, am morge, am mittag, etc.)
     */

    @Test
    public void testCorrectSuffixZnacht() {
        // night times with the hours "11i", "12i", "1", "2", "3" or "4i"
        // (everything between 22:25 and 04:24) should end with " znacht"
        assertTrue(SwissTime.toSwissGerman("22:25").endsWith(" znacht"));
        assertTrue(SwissTime.toSwissGerman("23:00").endsWith(" znacht"));
        assertTrue(SwissTime.toSwissGerman("00:00").endsWith(" znacht"));
        assertTrue(SwissTime.toSwissGerman("01:15").endsWith(" znacht"));
        assertTrue(SwissTime.toSwissGerman("02:30").endsWith(" znacht"));
        assertTrue(SwissTime.toSwissGerman("03:45").endsWith(" znacht"));
        assertTrue(SwissTime.toSwissGerman("04:00").endsWith(" znacht"));
        assertTrue(SwissTime.toSwissGerman("04:24").endsWith(" znacht"));
    }

    @Test
    public void testCorrectSuffixAmMorge() {
        // morning times with the hours "5i", "6i", "7i", "8i", "9i", "10i" or "11i"
        // (everything between 04:25 and 11:24) should end with " am morge"
        assertTrue(SwissTime.toSwissGerman("04:25").endsWith(" am morge"));
        assertTrue(SwissTime.toSwissGerman("05:00").endsWith(" am morge"));
        assertTrue(SwissTime.toSwissGerman("06:00").endsWith(" am morge"));
        assertTrue(SwissTime.toSwissGerman("07:15").endsWith(" am morge"));
        assertTrue(SwissTime.toSwissGerman("08:30").endsWith(" am morge"));
        assertTrue(SwissTime.toSwissGerman("09:45").endsWith(" am morge"));
        assertTrue(SwissTime.toSwissGerman("10:00").endsWith(" am morge"));
        assertTrue(SwissTime.toSwissGerman("11:00").endsWith(" am morge"));
        assertTrue(SwissTime.toSwissGerman("11:24").endsWith(" am morge"));
    }

    @Test
    public void testCorrectSuffixAmMittag() {
        // noon times with the hour "12i" (everything between 11:25 and 12:24)
        // should end with " am mittag"
        assertTrue(SwissTime.toSwissGerman("11:25").endsWith(" am mittag"));
        assertTrue(SwissTime.toSwissGerman("11:30").endsWith(" am mittag"));
        assertTrue(SwissTime.toSwissGerman("11:45").endsWith(" am mittag"));
        assertTrue(SwissTime.toSwissGerman("12:00").endsWith(" am mittag"));
        assertTrue(SwissTime.toSwissGerman("12:15").endsWith(" am mittag"));
        assertTrue(SwissTime.toSwissGerman("12:24").endsWith(" am mittag"));
    }

    @Test
    public void testCorrectSuffixAmNamittag() {
        // afternoon times with the hours "1", "2", "3", "4i" or "5i"
        // (everything between 12:25 and 17:24) should end with " am namittag"
        assertTrue(SwissTime.toSwissGerman("12:25").endsWith(" am namittag"));
        assertTrue(SwissTime.toSwissGerman("13:00").endsWith(" am namittag"));
        assertTrue(SwissTime.toSwissGerman("14:00").endsWith(" am namittag"));
        assertTrue(SwissTime.toSwissGerman("15:15").endsWith(" am namittag"));
        assertTrue(SwissTime.toSwissGerman("16:30").endsWith(" am namittag"));
        assertTrue(SwissTime.toSwissGerman("16:45").endsWith(" am namittag"));
        assertTrue(SwissTime.toSwissGerman("17:24").endsWith(" am namittag"));
    }

    @Test
    public void testCorrectSuffixAmAbig() {
        // evening times with the hours "6i", "7i", "8i", "9i" or "10i"
        // (everything between 17:25 and 22:24) should end with " am abig"
        assertTrue(SwissTime.toSwissGerman("17:25").endsWith(" am abig"));
        assertTrue(SwissTime.toSwissGerman("18:00").endsWith(" am abig"));
        assertTrue(SwissTime.toSwissGerman("19:00").endsWith(" am abig"));
        assertTrue(SwissTime.toSwissGerman("20:15").endsWith(" am abig"));
        assertTrue(SwissTime.toSwissGerman("21:30").endsWith(" am abig"));
        assertTrue(SwissTime.toSwissGerman("21:45").endsWith(" am abig"));
        assertTrue(SwissTime.toSwissGerman("22:24").endsWith(" am abig"));
    }

    /*
     * Test complete format for some examples
     */

    @Test
    public void testCorrectFormatComplete() {
        assertEquals("12i znacht",               SwissTime.toSwissGerman("00:00"));
        assertEquals("5 vor halbi 10i am morge", SwissTime.toSwissGerman("09:25"));
        assertEquals("1 ab 12i am mittag",       SwissTime.toSwissGerman("12:01"));
        assertEquals("14 vor 5i am namittag",    SwissTime.toSwissGerman("16:46"));
        assertEquals("9 vor 10i am abig",        SwissTime.toSwissGerman("21:51"));
        assertEquals("7 ab halbi 11i znacht",    SwissTime.toSwissGerman("22:37"));
    }
}
