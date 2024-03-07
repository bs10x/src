import static java.lang.Math.min;
import gui.Window;

public class SwissMap {
    
    static int BG_PIXEL_WIDTH = 3000;
    
    static int BG_MIN_EAST  = 480000;
    static int BG_MAX_EAST  = 840000;
    static int BG_MIN_NORTH =  62000;
    static int BG_MAX_NORTH = 300000;
    
    static int BG_WIDTH  = BG_MAX_EAST - BG_MIN_EAST;
    static int BG_HEIGHT = BG_MAX_NORTH - BG_MIN_NORTH;
    
    static int SMALL_RADIUS = 5;
    static int LARGE_RADIUS = 8;
    
    public static void main(String[] args) {
        new SwissMap().show();
    }
    
    Window window;
    
    SwissMap() {
        window = new Window("Map", 600, 400);
    }
    
    /**
     * Opens a {@link Window} and displays an interactive map of Switzerland
     */
    void show() {
        window.setResizable(true);
        window.setFontSize(18);
        window.open();
        
        PointOfInterest[] pois = new PointOfInterest[] {
                new City("Zürich", 683354, 247353, 396030, 91.88),
                new City("Genf",   500532, 117325, 201810, 15.89),
                new City("Basel",  611220, 267503, 175130, 22.75),
                new City("Bern",   600670, 199655, 141660, 51.60),
                new City("Lugano", 717505,  96295,  63580, 75.80),
                new City("Chur",   759662, 190702,  37110, 28.09),

                new Lake("Bodensee",           744895, 277632, 536,    251),
                new Lake("Genfersee",          529160, 144713, 580,    310),
                new Lake("Neuenburgersee",     555829, 195103, 217.9,  152),
                new Lake("Lago Maggiore",      693884,  91043, 212.5,  372),
                new Lake("Vierwaldstättersee", 673175, 208048, 113.72, 214),
                new Lake("Zürichsee",          691603, 234802,  88.17, 136),
                
                new Mountain("Dufourspitze", 633220,  87321, 4634),
                new Mountain("Dom",          632330, 104856, 4545),
                new Mountain("Matterhorn",   617049,  91670, 4478),
                new Mountain("Grand Combin", 589008,  86994, 4314),
                new Mountain("Jungfrau",     640278, 154213, 4158),
                new Mountain("Piz Bernina",  789947, 139751, 4049)};
        
        while(window.isOpen()) {
            double mouseX = window.getMouseX();
            double mouseY = window.getMouseY();
            
            window.drawImage("Switzerland_adm_location_map.png",
                    toGuiX(BG_MIN_EAST), toGuiY(BG_MAX_NORTH),
                    BG_WIDTH / BG_PIXEL_WIDTH * scale());
            
            for(PointOfInterest poi : pois) {
                double guiX = toGuiX(poi.east);
                double guiY = toGuiY(poi.north);
                
                window.setColor(0, 0, 0);
                int radius;
                if((mouseX-guiX) * (mouseX-guiX) + (mouseY-guiY) * (mouseY-guiY) <= LARGE_RADIUS * LARGE_RADIUS) {
                    window.drawString(poi.description(), guiX + LARGE_RADIUS * 2, guiY + 6);
                    radius = LARGE_RADIUS;
                }
                else
                    radius = SMALL_RADIUS;
                window.fillCircle(guiX, guiY, radius + 1);
                window.setColor(poi.color());
                window.fillCircle(guiX, guiY, radius);
            }
            window.refreshAndClear(20);
        }
    }
    
    /**
     * Computes the scaling factor of the map, based on the current size of the window.
     */
    double scale() {
        return min(window.getWidth() / BG_WIDTH, window.getHeight() / BG_HEIGHT);
    }
    
    /**
     * Converts a CH1903 east coordinate into a GUI x coordinate
     */
    double toGuiX(double east) {
        double x0 = BG_MIN_EAST - (window.getWidth()/scale() - BG_WIDTH) / 2;
        return (east - x0) * scale();
    }
    
    /**
     * Converts a CH1903 north coordinate into a GUI y coordinate
     */
    double toGuiY(double north) {
        double y0 = BG_MAX_NORTH + (window.getHeight()/scale() - BG_HEIGHT) / 2;
        return (y0 - north) * scale();
    }
}
