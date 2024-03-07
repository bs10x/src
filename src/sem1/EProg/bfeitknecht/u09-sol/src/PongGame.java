import static java.lang.Math.PI;

public class PongGame {

    private int playerDist = 30;
    private int playerLength = 100;
    private int speed = 5;

    private int width;
    private int height;

    public Player p1;
    public Player p2;
    public LinkedPongballList balls;

    private int ballAddCounter = 0;

    public PongGame(int width, int height) {
        this.width = width;
        this.height = height;

        p1 = new Player(playerLength, playerDist, height / 2);
        p2 = new Player(playerLength, width - playerDist, height / 2);
        balls = new LinkedPongballList();
    }

    public void move(Player player, boolean up) {
        player.y += up ? -10 : 10;
        player.y = Math.max(player.y, player.length / 2);
        player.y = Math.min(player.y, height - player.length / 2);
    }

    public void step() {
        if (ballAddCounter >= 50) {
            double angle = Math.random() * PI / 2 - PI / 4 + (Math.random() < 0.5 ? PI : 0);
            balls.addLast(new Pongball(width / 2, height / 2, speed * Math.cos(angle), speed * Math.sin(angle)));
            ballAddCounter = 0;
        }
        ballAddCounter++;

        for (PongballNode n = balls.first; n != null; n = n.next) {
            Pongball ball = n.ball;
            if (ball.x < 0) {
                p2.score++;
                balls.removeNode(n);
            } else if (ball.x > width) {
                p1.score++;
                balls.removeNode(n);
            } else {
                if (collides(ball, p1) || collides(ball, p2)) {
                    ball.vx = -ball.vx;
                }
                if (ball.y < 0 || ball.y > height) {
                    ball.vy = -ball.vy;
                }
                ball.x += ball.vx;
                ball.y += ball.vy;
            }
        }
    }

    private boolean collides(Pongball ball, Player player) {
        boolean vCollision = ball.y < player.y + player.length / 2 && ball.y > player.y - player.length / 2;
        boolean hCollision = Math.signum(ball.x - player.x) != Math.signum(ball.x + ball.vx - player.x);
        return vCollision && hCollision;
    }
}
