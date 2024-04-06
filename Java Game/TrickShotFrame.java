package TrickShotFrame;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class TrickShotFrame extends JFrame
{
    private ControlPanel gP;//Declares an instance of the control panel
    private LevelOne levOne; //Declares an instance of level 1 panel
    //private TrickShotFrame2 tS2;//Declares an instance of the second level
    private Timer gameTimer;//Used to constantly check if previous level is completed
    /*
     * The constructor which creates the instances of the other classes and sets size and makes the frame visible
     */
    public TrickShotFrame()
    {
        setTitle("Trick Shot Mania");
        setSize(1500,1000);
        levOne = new LevelOne();//Creates an instance of the level one panel
        gameTimer = new Timer(1000, new GameTimer());//Creates the timer that is constantly checking if previous level is over
        gP = new ControlPanel(levOne);//Creates the instance of the control panel and passes in the instance of level one
        add(gP,BorderLayout.WEST);//Adds the control panel to the left side of the frame
        add(levOne);//Adds the game play arena to the right side of the screen
        gameTimer.start();//Starts the timer so that it is constantly checking if previous level is over
        setVisible(true);
    }

    private class GameTimer implements ActionListener
    {
        public void actionPerformed(ActionEvent b)
        {
            if(levOne.gameOver()==true)
            {
                gP.setVisible(false);
                dispose();

                //tS2 = new TrickShotFrame2();
                //tS2.setVisible(true);

                gameTimer.stop();

            }
        }
    }
    //main class for game
    public static void main(String[] args)
    {
        new TrickShotFrame();
    }
}
