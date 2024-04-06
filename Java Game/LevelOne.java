package TrickShotFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class LevelOne extends JPanel {
    public boolean showRainAnimation = false;
    public boolean showWindAnimation = false;
    private ControlPanel gP;
    private int goalX = 800;
    private int goalY = 715;
    private int shooterX = 50;
    private int shooterY = 600;
    public int armX = 65;
    public int armY = 658;
    private double arm = 70.0;
    public int ballX = 115;
    public int ballY = 650;
    public int timeTaken = 100;
    public int finalScore = 0;
    public int shotsThrown;
    double degrees = 60;
    double theta = Math.toRadians(degrees);
    double rotate = Math.PI / 12;
    public double velocity = 25;
    public double sinTheta;
    public double gravity = 3.5;
    int x2 = (int) arm;
    int y2 = 0;
    double armTheta = -theta;
    double time = 2.5;
    double change = 25.0;
    public Timer timer;
    public Timer scoreTimer;
    //public Timer rainTimer;
    private Color dayColor = new Color(84, 158, 227);
    private Color dayGrass = new Color(109, 212, 25);
    private Color print = new Color(215, 7, 7);
    private boolean gameOver = false;
    public boolean start = false;
    public boolean paused = false;
    public boolean over = false;
    private double bX;
    private double bY;
    double xSpeed = 2.5;
    public int windDirection = 1; // 1 for right, -1 for left
    public double windSpeed = 2.5;

    ImageIcon icon = new ImageIcon("path/to/your/image.jpg"); // Replace with the actual path to your image


    private String playerName = "";

    public LevelOne() {
        setSize(1100, 1000);
        scoreTimer = new Timer(1000, new ScoreListener());
        timer = new Timer(5, new TimerListener());
        // Initialize the timer with a delay of 1000 milliseconds (1 second)
        /*rainTimer = new Timer(10000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Repaint the component to trigger the animation
                repaint();
            }
        });

        // Start the timer
        rainTimer.start();
         */


    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        g.setColor(dayColor);
        g.fillRect(0, 0, 1500, 1000);

        g.setColor(dayGrass);
        g.fillRect(0, 800, getWidth(), 50);

        g.setColor(print);
        g.setFont(new Font("serif", Font.BOLD, 30));
        g.drawString("The value of degrees is " + degrees, 500, 30);
        g.drawString("The value of ballY is " + ballY, 500, 70);
        g.drawString("The value of velocity is " + velocity, 500, 100);

        g.setFont(new Font("serif", Font.BOLD, 40));
        g.drawString("Time Remaining:" + timeTaken, 10, 30);

        g.setFont(new Font("serif", Font.BOLD, 40));
        g.drawString("ShotsTaken:" + shotsThrown, 50, 75);

        g.setFont(new Font("serif", Font.BOLD, 60));
        if (!start) {
            g.drawString("Level 1: Press Start to Start.", 150, 300);
            repaint();
        }

        if (paused) {
            g.drawString("Press Resume to Resume.", 200, 300);
            g.drawString("Then Press Shoot to Continue", 200, 350 );
            repaint();
        }

        if (timeTaken == 0) {
            g.drawString("Game Over!", 250, 300);
            repaint();
            timer.stop();
            scoreTimer.stop();
        }

        //Image pImage = player.getImage();
        //g.drawImage(pImage, shooterX, shooterY, null);

        g.setColor(Color.BLUE);
        g.fillOval(ballX, ballY, 30, 30);

        //Image tImage = trashcan.getImage();
        //g.drawImage(tImage, goalX, goalY, null);

        g.setColor(Color.MAGENTA);
        g.fillRect(goalX, goalY, 50, 75);

        //Image aImage = armImage.getImage();
        //g.drawImage(aImage, armX, armY, null);

        g.setColor(print);
        if (new Rectangle(ballX, ballY, 30, 30).intersects(new Rectangle(goalX + 25, goalY + 50, 50, 75))) {
            gameOver = true;
            stop();
            scoreTimer.stop();
            calcScore();
            g.setFont(new Font("serif", Font.BOLD, 35));
            g.drawString("Congratulations, you have beaten level 1 with a score of " + finalScore, 40, 350);
        } else if ((new Rectangle(815, 740, 3, 200)).intersects(new Rectangle(ballX, ballY, 30, 30))) {
            xSpeed = -1.5;
        } else if ((new Rectangle(875, 725, 3, 200)).intersects(new Rectangle(ballX, ballY, 30, 30))) {
            xSpeed = -1.5;
        } else if ((new Rectangle(880, 740, 3, 200)).intersects(new Rectangle(ballX, ballY, 30, 30))) {
            xSpeed = 1.5;
        } else if ((new Rectangle(820, 725, 3, 200)).intersects(new Rectangle(ballX, ballY, 30, 30))) {
            xSpeed = 1.5;
        }

        getName();
        g.setFont(new Font("serif", Font.BOLD, 15));
        g.drawString("" + playerName, shooterX - 10, shooterY - 10);
        drawRainAnimation(g2d);
        drawWindAnimation(g2d);
    }

    public boolean gameOver() {
        return gameOver;
    }

    public void shoot() {
        timer.start();
    }

    public void stop() {
        timer.stop();
    }

    public void getName(String name) {
        playerName = name;
        repaint();
    }
    private void drawRainAnimation(Graphics2D g2d) {
        if (showRainAnimation) {
            // Draw rain drops
            // Set the stroke for thicker lines
            g2d.setStroke(new BasicStroke(3.0f)); // Adjust the thickness as needed
            for (int i = 0; i < 200; i++) {
                int x = (int) (Math.random() * getWidth());
                int y = (int) (Math.random() * 800);
                int length = (int) (Math.random() * 20) + 10;
                g2d.setColor(new Color(0, 34, 255, 255)); // Translucent blue color for rain drops
                g2d.drawLine(x, y, x, y + length);
            }
        }
    }
    private void drawWindAnimation(Graphics2D g2d) {
        if (showWindAnimation) {
            // Draw wind lines
            for (int i = 0; i < 50; i++) {
                int x1 = (int) (Math.random() * getWidth());
                int y1 = (int) (Math.random() * 800);
                int x2 = x1 + (int) (windDirection * windSpeed * (Math.random() * 50 + 10));
                int y2 = y1 + (int) (Math.random() * 20 - 10);
                g2d.setColor(new Color(255, 255, 255, 180)); // Translucent white color for wind lines
                g2d.drawLine(x1, y1, x2, y2);
            }
        }
    }
    public void doMath() {
        sinTheta = Math.sin(theta);
    }

    public void trackBall() {
        bouncyball();
        bX = ballX + xSpeed;
        ballX = (int) bX;
        doMath();
        bY = armY + 95 - velocity * sinTheta * time + gravity * time * time / 1.5;
        ballY = (int) bY;
        ballY = ballY + 35;
        time = time + 0.1;
        repaint();
    }

    public void positionArch() {
        armTheta = -theta;
        double deltaX = (int) arm * Math.cos(armTheta);
        x2 = (int) deltaX;
        double deltaY = (int) arm * Math.sin(armTheta);
        y2 = (int) deltaY;
    }

    public double getTheta() {
        return theta;
    }

    public void setSpeed(double s) {
        velocity = s;
    }

    public void shotsTaken(int shots) {
        shotsThrown = shots;
    }

    public void calcScore() {
        finalScore = timeTaken - shotsThrown * 3;
    }

    public void setArch(double arch) {
        degrees = arch;
        theta = Math.toRadians(degrees);
        doMath();
        repaint();
    }

    public void bouncyball() {
        change = 8;
    }

    private class TimerListener implements ActionListener {
        public void actionPerformed(ActionEvent b) {
            trackBall();
            if (ballY > armY + 130) {
                time = 0.0;
                velocity -= change;
            }
            if (30 + ballX > getWidth()) {
                xSpeed = -1.5;
            } else if (ballX < 0) {
                xSpeed = 1.5;
            }
            if (velocity <= 0) {
                timer.stop();
            }
        }
    }

    private class ScoreListener implements ActionListener {
        public void actionPerformed(ActionEvent b) {
            timeTaken = timeTaken - 1;
            repaint();
        }
    }
}
