import gui.Window;

public class PongGui {

    public static void main(String[] args) {
        int width = 800;
        int height = 400;

        PongGame game = new PongGame(width, height);

        Window window = new Window("Pong", width, height);
        window.open();
        while (window.isOpen()) {
            if (window.isKeyPressed("w")) {
                game.move(game.p1, true);
            }
            if (window.isKeyPressed("s")) {
                game.move(game.p1, false);
            }
            if (window.isKeyPressed("up")) {
                game.move(game.p2, true);
            }
            if (window.isKeyPressed("down")) {
                game.move(game.p2, false);
            }

            game.step();

            drawPlayer(window, game.p1);
            drawPlayer(window, game.p2);
            for (PongballNode n = game.balls.first; n != null; n = n.next) {
                drawPongball(window, n.ball);
            }

            window.refreshAndClear(20);
        }
    }

    private static void drawPlayer(Window window, Player player) {
        window.setStrokeWidth(7);
        window.drawLine(player.x, player.y - player.length / 2,
            player.x, player.y + player.length / 2);

        window.setFontSize(20);
        String string = Integer.toString(player.score);
        window.drawString(string, player.x - 7 * string.length(), 30);
    }

    private static void drawPongball(Window window, Pongball ball) {
        window.fillCircle(ball.x, ball.y, 5);
    }
}
