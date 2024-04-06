package TrickShotFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Point2D;
import java.util.ArrayList;

public class LevelOne extends JPanel {
    private ControlPanel gP; // creates an instance of the control panel which holds all the buttons
    private int goalX = 700; // used to hold the x-coordinate for the goal
    private int goalY = 695; // used to hold the y-coordintate for the goal
    public int ballX = 115; // used to hold the x-coordinate for the ball
    public int ballY = 705; // used to hold the y-coordinate for the ball
    public int timeTaken = 100; // used as a timer which ticks down every second
    double degrees = 1; // used to hold the degrees for the ball to fly
    double theta = Math.toRadians(degrees); // used to change the degrees into the radians
    public double velocity = 15; // used to hold the velocity for the equation for making ball move
    public double sinTheta = 0; // used to hold value for using the sin function on theta
    public double gravity = 3.5;    // used to hold the value for the gravity
    double time = 2.5;  // used to hold the time value in the equation for making the ball move
    public Timer timer; // create the timer for the level
    public Timer scoreTimer; // create the timer for the score
    public int finalScore = 0;
    public int shotsThrown;
    private Color dayColor = new Color(42, 119, 190); // used to hold the color of the sky
    private Color dayGrass = new Color(68, 133, 10); // used to hold the color of the grass
    private Color print = new Color(6, 87, 114); // used to hold the color of the text which may pop up
    private boolean gameOver = false; // boolean used for telling if game is over or not
    public boolean start = false; // boolean for telling if the game is started or not
    public boolean paused = false; // boolean for telling if the game is paused or not
    private double bX; // x vector for the ball
    private double bY; // y vector for the ball
    double xSpeed = 2.5; // x vector for the speed
    double change = 25.0; // used to hold the value of the change of the ball
    boolean shootPress = false; // used to hold whether the shoot button has been pressed or not
    boolean clear = false; // used to hold whether the clear checkbox has been pressed or not
    boolean timeOff = false; // used to hold whether the timeoff checkbox has been pressed or not

    boolean char1 = false; // used to hold whether the timeoff checkbox has been pressed or not
    boolean char2 = false; // used to hold whether the timeoff checkbox has been pressed or not
    boolean char3 = false; // used to hold whether the timeoff checkbox has been pressed or not
    boolean trajectory = false; // used to hold whether the timeoff checkbox has been pressed or not

    boolean difficulty = true;

    public int windDirection = 1; // 1 for right, -1 for left
    public double windSpeed = 2.5;

    public boolean showRainAnimation = false;
    public boolean showWindAnimation = false;



    // all the images which will be put into the level
    ImageIcon can = new ImageIcon("trash-removebg-preview.png");
    ImageIcon ball = new ImageIcon("basketball-removebg-preview.png");
    ImageIcon sky = new ImageIcon("sky.png");
    ImageIcon grass = new ImageIcon("grass-removebg-preview (1).png");
    ImageIcon tree = new ImageIcon("tree-removebg-preview.png");
    ImageIcon player1A = new ImageIcon("player1.png");
    ImageIcon player1B = new ImageIcon("player2.png");
    ImageIcon player2A = new ImageIcon("player2A.png");
    ImageIcon player2B = new ImageIcon("player2B.png");
    ImageIcon player3B = new ImageIcon("player3A.png");
    ImageIcon player3A = new ImageIcon("player3B.png");

    //ImageIcon player2 = new ImageIcon("player3.png");
    ImageIcon cloud = new ImageIcon("cloud.png");


    public ArrayList<Point2D> trajectoryPoints; // to store trajectory points
    private double dragFactor = -0.6;


    // creates an instance of the level one class which starts the timers and sets the size
    public LevelOne() {
        setSize(1100, 1000);
        scoreTimer = new Timer(1000, new ScoreListener());
        timer = new Timer(5, new TimerListener());
        trajectoryPoints = new ArrayList<>();

    }

     public void calculateTrajectory(int startX, int startY) {
        trajectoryPoints.clear(); // Clear previous trajectory points

        double initialX = startX; // Initial x-coordinate
        double initialY = startY; // Initial y-coordinate

        // Initial velocity components
        double vx = velocity * Math.cos(theta);
        double vy = -velocity * Math.sin(theta);

        // Time step
        double dt = 0.005; // Adjust as needed for accuracy

        // Calculate trajectory points using Euler's method
        double t = 0.0;
        double x = initialX;
        double y = initialY;

        while (y <= getHeight()) { // Modify condition to ensure the trajectory ends at the bottom of the panel
            trajectoryPoints.add(new Point2D.Double(x, y)); // Add current point to trajectory

            // Update velocity components
            double v = Math.sqrt(vx * vx + vy * vy);
            double ax = -vx * dragFactor / v; // Drag force along x-axis
            double ay = 1.7*gravity - vy * dragFactor / v; // Gravity and drag force along y-axis (inverted)

            // Update position using Euler's method
            x += vx * dt;
            y += vy * dt;

            // Update velocity components
            vx += ax * dt;
            vy += ay * dt;

            t += dt;
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        // used for drawing all the images
        Image sImage = sky.getImage();
        g.drawImage(sImage, 0, 0, null);

        Image gImage = grass.getImage();
        g.drawImage(gImage, 0, 532, null);

        Image tImage = tree.getImage();
        g.drawImage(tImage, 250, 375, null);


        // Draw trajectory
        if (!trajectoryPoints.isEmpty()) {
            g2d.setColor(Color.BLUE);
            // Set the stroke to make the line thicker
            Stroke oldStroke = g2d.getStroke();
            g2d.setStroke(new BasicStroke(3)); // Adjust the thickness as needed
            for (int i = 1; i < trajectoryPoints.size(); i++) {
                Point2D p1 = trajectoryPoints.get(i - 1);
                Point2D p2 = trajectoryPoints.get(i);
                g.drawLine((int) p1.getX(), (int) p1.getY(), (int) p2.getX(), (int) p2.getY());
            }
            g2d.setStroke(oldStroke);

        }

        // once the start button is pressed remove the instructions
        if (!start) {
            Image cImage = cloud.getImage();
            g.drawImage(cImage, -150, -300, null);

            // used to set the color of the text which shows to press start button to start game and timer
            g.setColor(print);
            g.setFont(new Font("Comic Sans MS", Font.BOLD, 22));
            g.drawString("Welcome to Sporty Game", 425, 115);
            g.setFont(new Font("Comic Sans MS", Font.PLAIN, 18));
            g.drawString("How to Play:", 500, 150);
            g.drawString("Press Start Button to Begin Level and Game Timer", 320, 175);
            g.drawString("Press the Shoot Button to get the ball into the Goal", 315, 200);
            g.drawString("Use the sliders for speed and angle to aim the ball into the goal", 285, 225);
            g.drawString("Be sure to not hit any objects, but if you do, press reset to try again", 255, 250);
            g.drawString("Get the ball into the goal in the least amount of shots and lowest time possible", 260, 275);
            g.drawString("Click More Difficult to make game more difficult, if you're up for a real challenge", 262, 300);
            g.drawString("To make most difficult also hit Clear Screen and Turn Off Time", 275, 325);
            g.drawString("Have Fun", 520, 350);
            repaint();
        } else {
            g.setColor(print);
            g.setFont(new Font("serif", Font.BOLD, 30));
            // if the timeOff box is pressed than the time will not show up
            if (timeOff == false) {
                g.drawString("Time Remaining:" + timeTaken, 0, 150);
            }
            // if the clear checkbox is pressed than all values will not show up
            if (clear == false) {
                g.drawString("Angle = " + degrees + " degrees", 0, 30);
                g.drawString("Speed = " + velocity + " m/s", 0, 70);
                g.drawString("Shots Taken:" + shotsThrown, 0, 110);
            }
        }




        Image pImage1A = player1A.getImage();
        Image pImage1B = player1B.getImage();
        Image pImage2A = player2A.getImage();
        Image pImage2B = player2B.getImage();
        Image pImage3A = player3A.getImage();
        Image pImage3B = player3B.getImage();

        if(char1==true) {
            // used to changing the image for throwing
            if (shootPress == true) {
                g.drawImage(pImage1B, -250, 640, null);
                //g.drawImage(pImage2, -100, 633, null);
            } else {
                g.drawImage(pImage1A, 85, 650, null);
                ballX = 115; // used to hold the x-coordinate for the ball
                ballY = 705; // used to hold the y-coordinate for the ball
                if(trajectory == true) {
                    // Angle 79.0
                    // Speed 91.0
                    calculateTrajectory(175, 750);
                }
                else
                {
                    trajectoryPoints.clear();
                }
            }
        }

        if(char2==true) {
            // used to changing the image for throwing
            if (shootPress == true) {
                g.drawImage(pImage2B, 10, 640, null);
                //g.drawImage(pImage2, -100, 633, null);
            } else {
                g.drawImage(pImage2A, 85, 650, null);
                ballX = 170;
                ballY = 665;
                if(trajectory == true) {
                    calculateTrajectory(225, 700);
                }
                else
                {
                    trajectoryPoints.clear();
                }
            }
        }

        if(char3==true) {
            // used to changing the image for throwing
            if (shootPress == true) {
                g.drawImage(pImage3B, 115, 640, null);
                //g.drawImage(pImage2, -100, 633, null);
            } else {
                g.drawImage(pImage3A, 85, 640, null);
                ballX = 60;
                ballY = 613;
                if(trajectory == true) {
                    calculateTrajectory(110, 635);
                }
                else
                {
                    trajectoryPoints.clear();
                }
            }
        }


        if(difficulty == true) {
            Image pImage = can.getImage();
            g.drawImage(pImage, goalX, goalY, null);
        }

        // draws the image of the ball
        Image bImage = ball.getImage();
        g.drawImage(bImage, ballX, ballY, null);



        // sets the color of the text for when the level ends
        g.setColor(print);
        // used fro drawing the box around the ball
        Rectangle ball = (new Rectangle(ballX + 25, ballY + 25, 50, 50));

        // top of can
        if (ball.intersects(new Rectangle(goalX + 180, goalY + 55, 10, 25))) {
            calcScore();
            g.setFont(new Font("serif", Font.BOLD, 35));
            g.drawString("Congratulations, you have beaten level 1 with a score of " + finalScore, 40, 350);
            gameOver = true;
            stop();
            scoreTimer.stop();
        }
        // bottom of can under the hole
        if (ball.intersects((new Rectangle(goalX + 150, goalY + 85, 75, 100)))) {
            xSpeed = -2.5;
        }
        // bottom of tree
        if (ball.intersects((new Rectangle(470, 650, 75, 200)))) {
            xSpeed = -1.5;
        }
        // first half of tree
        if (ball.intersects((new Rectangle(285, 500, 10, 100)))) {
            xSpeed = -1.5;
        }
        // 2nd half of tree
        if (ball.intersects((new Rectangle(700, 500, 10, 100)))) {
            xSpeed = 1.5;
        }
        // 3rd half of tree
        if (ball.intersects((new Rectangle(285, 500, 415, 5)))) {
            xSpeed = -1.5;
        }
        // 4th half of tree
        if (ball.intersects((new Rectangle(285, 600, 415, 5)))) {
            xSpeed = -1.5;
        }
        // top of tree
        if (ball.intersects((new Rectangle(370, 400, 250, 100)))) {
            xSpeed = -1.5;
        }

        drawRainAnimation(g2d);
        drawWindAnimation(g2d);
    }


    // used to stop the timer for pausing or ending level
    public void trajectoryOff() {
        trajectory = false;
    }

    // used to stop the timer for pausing or ending level
    public void trajectoryOn() {
        trajectory = true;
    }

    // used for when the game is over
    public boolean gameOver() {
        return gameOver;
    }

    // used to shoot the ball and start the timer
    public void shoot() {
        timer.start();
        shootPress=true;
    }

    // used to stop the timer for pausing or ending level
    public void stop() {
        timer.stop();
    }

    // used to stop the timer for pausing or ending level
    public void reset() {
        shootPress = false;
    }

    // used to stop the timer for pausing or ending level
    public void timeOff() {
        timeOff = true;
    }

    // used to stop the timer for pausing or ending level
    public void timeOn() {
        timeOff = false;
    }

    // used to stop the timer for pausing or ending level
    public void clearScreen() {
        clear = true;
    }

    // used to stop the timer for pausing or ending level
    public void unClearScreen() {
        clear = false;
    }

    // used to stop the timer for pausing or ending level
    public void diffOff() {
        difficulty = true;
    }

    // used to stop the timer for pausing or ending level
    public void diffOn() {
        difficulty = false;
    }

    // used to get the sin of theta for use in the equation
    public void doMath() {
        sinTheta = Math.sin(theta);
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
            // Set a thicker stroke for bolder lines
            Stroke originalStroke = g2d.getStroke();
            g2d.setStroke(new BasicStroke(2.0f)); // You can adjust the thickness as needed

            // Draw wind lines
            for (int i = 0; i < 50; i++) {
                int x1 = (int) (Math.random() * getWidth());
                int y1 = (int) (Math.random() * 800);
                int x2 = x1 + (int) (windDirection * windSpeed * (Math.random() * 50 + 10));
                int y2 = y1 + (int) (Math.random() * 20 - 10);
                g2d.setColor(new Color(255, 255, 255, 180)); // Translucent white color for wind lines
                g2d.drawLine(x1, y1, x2, y2);
            }

            // Restore the original stroke
            g2d.setStroke(originalStroke);
        }
    }


    // used for tracking the ball when it is moving
    public void trackBall() {
        // Adjust velocity based on wind and rain animations
        double adjustedVelocity = velocity;
        if (showWindAnimation) {
            // Reduce velocity when wind animation is on
            adjustedVelocity *= 0.8; // Adjust the factor as needed to slow down the ball
        }
        if (showRainAnimation) {
            // Reduce velocity when rain animation is on
            adjustedVelocity *= 0.9; // Adjust the factor as needed to slow down the ball
        }

        // this math uses the projectile motion equation for throwing a ball or going over a curve
        bX = ballX + xSpeed;
        ballX = (int) bX;
        doMath();
        bY = 658 + 95 - adjustedVelocity * sinTheta * time + gravity * time * time / 1.5;
        ballY = (int) bY;
        ballY = ballY + 35;
        time = time + 0.1;
        repaint();
    }


    // used to set the speed of the ball from slider
    public void setSpeed(double s) {
        velocity = s;
    }

    // used to count the amount of shots taken
    public void shotsTaken(int shots) {
        shotsThrown = shots;
    }

    // used to calc the score after level is completed
    public void calcScore() {
        finalScore = timeTaken - shotsThrown * 3;
    }

    // used to set the arch or theta value used in the equation
    public void setArch(double arch) {
        degrees = arch;
        theta = Math.toRadians(degrees);
        doMath();
        repaint();
    }

    // timerlistener which is used for tracking the ball and allowing the ball to bounce off the ground instead of going through it
    private class TimerListener implements ActionListener {
        public void actionPerformed(ActionEvent b) {
            trackBall();
            if (ballY > 658 + 130) {
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
    // used for the score timer which is also used within the level but is not shown up
    private class ScoreListener implements ActionListener {
        public void actionPerformed(ActionEvent b) {
            timeTaken = timeTaken - 1;
            repaint();
        }
    }
}