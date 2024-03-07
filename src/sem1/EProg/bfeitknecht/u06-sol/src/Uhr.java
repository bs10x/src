import static java.lang.Math.PI;
import static java.lang.Math.cos;
import static java.lang.Math.sin;

import gui.Color;
import gui.Window;


/* 
 * Author: Maximiliana Muster
 * for Einfuehrung in die Programmierung
 * 
 * Dieses Programm zeigt die aktuelle Uhrzeit als analoge Uhr an.
 */
public class Uhr {
    private static final int SIZE = 500;
    private static final int CLOCK_RADIUS = 225;
    private static final int HOUR_OFFSET = 1;
    
    public static void main(String[] args) {
        Window window = new Window("Uhr", SIZE, SIZE);
        window.open();
        
        while(window.isOpen()) {
            double seconds = System.currentTimeMillis() / 1000.0;
            double minutes = seconds / 60;
            double hours = minutes / 60 + HOUR_OFFSET;
            
            window.setColor(240, 240, 240);
            window.fillCircle(SIZE/2, SIZE/2, CLOCK_RADIUS);
            
            double   hourAngle =   hours/12 * 2*PI;
            double minuteAngle = minutes/60 * 2*PI;
            double secondAngle = seconds/60 * 2*PI;
            
            window.setColor(new Color(0, 0, 0));
            window.setStrokeWidth(17);
            
            double x = SIZE/2 + sin(hourAngle)*120;
            double y = SIZE/2 - cos(hourAngle)*120;
            window.drawLine(SIZE/2, SIZE/2, x, y);
            
            x = SIZE/2 + sin(minuteAngle)*200;
            y = SIZE/2 - cos(minuteAngle)*200;
            window.drawLine(SIZE/2, SIZE/2, x, y);
            
            x = SIZE/2 + sin(secondAngle)*180;
            y = SIZE/2 - cos(secondAngle)*180;
            window.setStrokeWidth(5);
            window.drawLine(SIZE/2, SIZE/2, x, y);
            
            window.refreshAndClear(20);
        }
    }
}
