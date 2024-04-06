package TrickShotFrame;

//import com.sun.org.apache.xpath.internal.operations.Bool;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.event.*;
import java.util.Random;


public class ControlPanel extends JPanel {
    private JButton shootButton;//When pressed the ball will be shot from the player
    private JButton startButton;//When pressed the game will start
    private JButton resetButton;//When pressed the ball will be reset into the player's hand
    private JButton exitButton;//Used to exit the frame when pressed

    private JRadioButton resumeButton;//When pressed the game will resume after being paused
    private JRadioButton pauseButton;//When pressed the game will pause
    private ButtonGroup bg;//Needed while using the radio buttons for pause and resume

    //Each of these panels is used to hold the buttons, when being placed in the button panel
    private JPanel shootPanel;
    private JPanel startPanel;
    private JPanel resumePanel;
    private JPanel pausePanel;
    private JPanel resetPanel;
    private JPanel exitPanel;

    //Creates each of the custom colors that will be used in the button panel or on the buttons
    private Color buttonColor = new Color(255, 0, 0);
    private Color buttonTextColor = new Color(229, 229, 229);
    private Color panelBackgroundColor = new Color(44, 99, 182);
    private Color sliderColor = new Color(255, 255, 255);

    private JPanel controlPanel;//Used to hold all buttons, textfield, and sliders

    //Each of these are used for holding the sliders or labels that will be placed above the sliders
    private JPanel anglePanel;
    private JPanel speedPanel;
    private JPanel angleLabelPanel;
    private JPanel speedLabelPanel;

    //Used to create the sliders which control the angle and speed
    private JSlider angleSlider;
    private JSlider speedSlider;

    //Used to create the labels that will be placed above the buttons
    private JLabel angleLabel;
    private JLabel speedLabel;

    //These values are used as the defaults for sliders once the game is run
    private double defaultAngle = 25;
    public int dAngleInt = (int) defaultAngle;//Must convert to int for slider to recognize it
    private int defaultSpeed = 25;

    //Panels that will hold the normal panels, used for making the spacing and placement work
    private JPanel shootAndExitPanel;
    private JPanel angleSubPanel;
    private JPanel pauseAndResumePanel;
    private JPanel speedSubPanel;
    private JPanel resetAndShootPanel;
    private JPanel textFieldSubPanel;

    // used for creating all the checkbox items
    private JCheckBox clearScreen;//Checkbox used to clear the screen of values
    private JCheckBox changeBounce;//Checkbox used to make theb all bounce more
    private JCheckBox timeOff;//Checkbox used to turn off time
    private JCheckBox difficulty;//Checkbox used to turn off time
    private JCheckBox wind;//Checkbox used to turn off time
    private JCheckBox rain;//Checkbox used to turn off time
    private JCheckBox trajectory;//Checkbox used to turn off time

    private JPanel checkBoxPanel;//Panel used to hold the check boxes
    private JPanel checkLabelPanel;//Panel used to hold the label which is next to the check boxes
    private JPanel checkSubPanel;//Panel used to hold the check box panel when placed into control panel
    //private JLabel checkBoxLabel;//Panel used to hold the label which is placed next to checkbox

    // used for creating all the checkbox items
    private JRadioButton character1;//When pressed the game will resume after being paused
    private JRadioButton character2;//When pressed the game will pause
    private JRadioButton character3;//When pressed the game will pause
    private ButtonGroup bg2;//Needed while using the radio buttons for pause and resume
    private JPanel characterPanel;//Panel used to hold the check boxes
    private JPanel charLabelPanel;//Panel used to hold the label which is next to the check boxes
    private JPanel charSubPanel;//Panel used to hold the check box panel when placed into control panel

    //Used to hold the amount of shots taken by the user
    private int shotsTaken;

    //Declares instances of each of the other level panels
    private LevelOne levOne;

    Random rand = new Random();


    /*
     * Creates the default constructor which sets the size and builds the control panel into the panel
     */
    public ControlPanel() {
        setSize(300, 1500);
        buildControlPanel();
        add(controlPanel);//Adds the control panel to the left side of the arena
        setVisible(true);
    }

    /*
     * Creates another constructor which accepts an instance of level one
     */
    public ControlPanel(LevelOne current) {
        setSize(400,1500);
        levOne = current;//Sets the instance of level one passed in by the frame to equal levOne
        levOne.setSpeed(defaultSpeed);//Sets the speed on the speed slider to the default value
        levOne.setArch(defaultAngle);//Sets the angle on the angle slider to the default value
        buildControlPanel();//Calls the method which builds the control panel
        add(controlPanel);//Adds the control panel to the left side of the arena
        setVisible(true);
    }

    /*
     * This method is in charge of creating the buttons,panels, sliders and text field that will be placed on the left side of the arena
     */
    public void buildControlPanel() {

        //Creates the control panel and declares the size
        controlPanel = new JPanel();
        controlPanel.setSize(400, 1500);
        controlPanel.setLayout(new GridLayout(4, 2, 0, 0));
        controlPanel.setBackground(panelBackgroundColor);

        //Creation of check boxes begins here


        //Instantiates each of the panels and label
        checkLabelPanel = new JPanel();
        checkSubPanel = new JPanel();
        checkBoxPanel = new JPanel();

        //Sets the layout of the panel that holds the checkboxes
        checkBoxPanel.setLayout(new GridLayout(7, 1));


        //Creates the checkboxes and adds the item listener
        clearScreen = new JCheckBox("Clear Screen");
        changeBounce = new JCheckBox("Increase Bounce");
        timeOff = new JCheckBox("Turn off time");
        difficulty = new JCheckBox("Make More Difficult");
        wind = new JCheckBox("Wind");
        rain = new JCheckBox("Rain");
        trajectory = new JCheckBox("Turn on Trajectory");


        clearScreen.addItemListener(new CheckListener());
        timeOff.addItemListener(new CheckListener());
        changeBounce.addItemListener(new CheckListener());
        difficulty.addItemListener(new CheckListener());
        trajectory.addItemListener(new CheckListener());
        wind.addItemListener(new CheckListener());
        rain.addItemListener(new CheckListener());


        //Sets the font, background, and text color for the checkboxes
        clearScreen.setFont(new Font("Serif", Font.BOLD, 11));
        timeOff.setFont(new Font("Serif", Font.BOLD, 11));
        changeBounce.setFont(new Font("Serif", Font.BOLD, 11));
        difficulty.setFont(new Font("Serif", Font.BOLD, 11));
        wind.setFont(new Font("Serif", Font.BOLD, 11));
        trajectory.setFont(new Font("Serif", Font.BOLD, 11));
        rain.setFont(new Font("Serif", Font.BOLD, 11));


        clearScreen.setBackground(buttonColor);
        timeOff.setBackground(buttonColor);
        difficulty.setBackground(buttonColor);
        changeBounce.setBackground(buttonColor);
        trajectory.setBackground(buttonColor);
        wind.setBackground(buttonColor);
        rain.setBackground(buttonColor);

        clearScreen.setForeground(buttonTextColor);
        timeOff.setForeground(buttonTextColor);
        changeBounce.setForeground(buttonTextColor);
        difficulty.setForeground(buttonTextColor);
        wind.setForeground(buttonTextColor);
        trajectory.setForeground(buttonTextColor);
        rain.setForeground(buttonTextColor);

        //Changes the background color of the checkbox panel
        checkLabelPanel.setBackground(panelBackgroundColor);

        //Adds the check boxes to the panel which holds them both
        checkBoxPanel.add(clearScreen);
        checkBoxPanel.add(timeOff);
        checkBoxPanel.add(changeBounce);
        checkBoxPanel.add(difficulty);
        checkBoxPanel.add(trajectory);
        checkBoxPanel.add(wind);
        checkBoxPanel.add(rain);



        //Creation of the buttons starts here

        //Creates the panels that will hold the buttons
        shootPanel = new JPanel();
        startPanel = new JPanel();
        resumePanel = new JPanel();
        pausePanel = new JPanel();
        resetPanel = new JPanel();
        exitPanel = new JPanel();
        characterPanel = new JPanel();

        //Creates the sub panels that will hold the panels
        shootAndExitPanel = new JPanel();
        angleSubPanel = new JPanel();
        pauseAndResumePanel = new JPanel();
        speedSubPanel = new JPanel();
        resetAndShootPanel = new JPanel();
        textFieldSubPanel = new JPanel();
        charSubPanel = new JPanel();

        characterPanel.setLayout(new GridLayout(3, 1));


        int horizSpacing = 10;//Used to control the horizantal spacing between each of the buttons
        int vertSpacing = 34;//Used to cotrnol vertical spacing between each of the panels
        //Adds a FlowLayout to each of the panels to put the buttons in the center of the panel
        shootPanel.setLayout(new FlowLayout(FlowLayout.CENTER, horizSpacing, vertSpacing));
        startPanel.setLayout(new FlowLayout(FlowLayout.CENTER, horizSpacing, vertSpacing));
        resumePanel.setLayout(new FlowLayout(FlowLayout.CENTER, horizSpacing, vertSpacing));
        pausePanel.setLayout(new FlowLayout(FlowLayout.CENTER, horizSpacing, vertSpacing));
        resetPanel.setLayout(new FlowLayout(FlowLayout.CENTER, horizSpacing, vertSpacing));
        exitPanel.setLayout(new FlowLayout(FlowLayout.CENTER, horizSpacing, vertSpacing));


        //Creates all the buttons and adds text to be displayed on each
        shootButton = new JButton("Shoot");
        startButton = new JButton("Start");
        resetButton = new JButton("Reset");
        exitButton = new JButton("Exit");

        //Creates the radio buttons
        resumeButton = new JRadioButton("Resume");
        pauseButton = new JRadioButton("Pause");

        character1 = new JRadioButton("Character 1");
        character2 = new JRadioButton("Character 2");
        character3 = new JRadioButton("Character 3");


        //Creates the button group which works with the radio buttons and then adds the radio buttons to the button group
        bg = new ButtonGroup();
        bg.add(resumeButton);
        bg.add(pauseButton);

        bg2 = new ButtonGroup();
        bg2.add(character1);
        bg2.add(character2);
        bg2.add(character3);

        character1.setSelected(true); // This line preselects character1


        //Sets the font of each button to a size of 35 and makes it bold
        shootButton.setFont(new Font("Serif", Font.BOLD, 35));
        startButton.setFont(new Font("Serif", Font.BOLD, 35));
        resumeButton.setFont(new Font("Serif", Font.BOLD, 35));
        pauseButton.setFont(new Font("Serif", Font.BOLD, 35));
        resetButton.setFont(new Font("Serif", Font.BOLD, 35));
        exitButton.setFont(new Font("Serif", Font.BOLD, 35));
        character1.setFont(new Font("Serif", Font.BOLD, 12));
        character2.setFont(new Font("Serif", Font.BOLD, 12));
        character3.setFont(new Font("Serif", Font.BOLD, 12));




        //Sets the background of each button
        shootButton.setBackground(buttonColor);
        startButton.setBackground(buttonColor);
        resumeButton.setBackground(buttonColor);
        pauseButton.setBackground(buttonColor);
        resetButton.setBackground(buttonColor);
        exitButton.setBackground(buttonColor);
        character1.setBackground(buttonColor);
        character2.setBackground(buttonColor);
        character3.setBackground(buttonColor);


        //Sets the text of each button to a different color
        shootButton.setForeground(buttonTextColor);
        startButton.setForeground(buttonTextColor);
        resumeButton.setForeground(buttonTextColor);
        pauseButton.setForeground(buttonTextColor);
        resetButton.setForeground(buttonTextColor);
        exitButton.setForeground(buttonTextColor);
        character1.setForeground(buttonTextColor);
        character2.setForeground(buttonTextColor);
        character3.setForeground(buttonTextColor);


        //Sets the background of each panel to a the same color
        shootPanel.setBackground(panelBackgroundColor);
        startPanel.setBackground(panelBackgroundColor);
        resumePanel.setBackground(panelBackgroundColor);
        pausePanel.setBackground(panelBackgroundColor);
        resetPanel.setBackground(panelBackgroundColor);
        exitPanel.setBackground(panelBackgroundColor);
        characterPanel.setBackground(panelBackgroundColor);


        //Adds a raised bevel border to each button
        shootButton.setBorder(BorderFactory.createRaisedBevelBorder());
        startButton.setBorder(BorderFactory.createRaisedBevelBorder());
        resumeButton.setBorder(BorderFactory.createRaisedBevelBorder());
        pauseButton.setBorder(BorderFactory.createRaisedBevelBorder());
        resetButton.setBorder(BorderFactory.createRaisedBevelBorder());
        exitButton.setBorder(BorderFactory.createRaisedBevelBorder());

        //Adds the ActionListener to each button
        shootButton.addActionListener(new ListenForButton());
        startButton.addActionListener(new ListenForButton());
        resumeButton.addActionListener(new ListenForButton());
        pauseButton.addActionListener(new ListenForButton());
        resetButton.addActionListener(new ListenForButton());
        exitButton.addActionListener(new ListenForButton());
        character1.addActionListener(new ListenForButton());
        character2.addActionListener(new ListenForButton());
        character3.addActionListener(new ListenForButton());


        //Adds each button to its panel
        //shootPanel.add(shootButton);
        shootPanel.add(shootButton);
        startPanel.add(startButton);
        resumePanel.add(resumeButton);
        pausePanel.add(pauseButton);
        resetPanel.add(resetButton);
        exitPanel.add(exitButton);


        //Creation of sliders begins here


        //Creates each of the panels used with the sliders
        anglePanel = new JPanel();
        speedPanel = new JPanel();
        angleLabelPanel = new JPanel();
        speedLabelPanel = new JPanel();

        //Sets the layout of each panel to a grid layout so the sliders and labels will be laced in the center of the panels
        anglePanel.setLayout(new GridLayout(1, 1));
        speedPanel.setLayout(new GridLayout(1, 1));
        angleLabelPanel.setLayout(new GridLayout(1, 1));
        speedLabelPanel.setLayout(new GridLayout(1, 1));

        //Creates the label that will be placed above the angle slider
        angleLabel = new JLabel(" Slide to change angle");
        angleLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
        angleLabel.setForeground(buttonTextColor);

        //Sets the background of the angle and speed label panels
        angleLabelPanel.setBackground(panelBackgroundColor);
        speedLabelPanel.setBackground(panelBackgroundColor);

        //Creates the label that will be placed above speed slider
        speedLabel = new JLabel(" Slide to change speed");
        speedLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 17));
        speedLabel.setForeground(buttonTextColor);

        //Creates the slider that will control the angle
        angleSlider = new JSlider(JSlider.HORIZONTAL, 0, 100, dAngleInt);
        angleSlider.setMajorTickSpacing(20);
        angleSlider.setMinorTickSpacing(2);
        angleSlider.setPaintTicks(true);//Display the Tickmarks
        angleSlider.setPaintLabels(true);//Display labels on the tickmarks
        Font sliderFont1 = new Font("Serif", Font.BOLD, 16);
        angleSlider.setFont(sliderFont1);
        angleSlider.setBackground(panelBackgroundColor);
        angleSlider.setForeground(sliderColor);

        //Creates the slider that will control the speed
        speedSlider = new JSlider(JSlider.HORIZONTAL, 10, 150, defaultSpeed);
        speedSlider.setMajorTickSpacing(20);
        speedSlider.setMinorTickSpacing(5);
        speedSlider.setPaintTicks(true);//Display the Tickmarks
        speedSlider.setPaintLabels(true);//Display labels on the tickmarks
        Font sliderFont = new Font("Serif", Font.BOLD, 14);
        speedSlider.setFont(sliderFont);
        speedSlider.setBackground(panelBackgroundColor);
        speedSlider.setForeground(sliderColor);

        //Adds the change listeners to the sliders
        speedSlider.addChangeListener(new ReadTheSliders());
        angleSlider.addChangeListener(new ReadTheSliders());

        //Adds the labels to their panels
        angleLabelPanel.add(angleLabel);
        speedLabelPanel.add(speedLabel);

        //Adds the sliders to their panels
        anglePanel.add(angleSlider);
        speedPanel.add(speedSlider);


        //Creates the subpanels which will hold the buttons, sliders or textfields with their labels
        shootAndExitPanel.setLayout(new GridLayout(2, 1));
        angleSubPanel.setLayout(new GridLayout(2, 1));
        pauseAndResumePanel.setLayout(new GridLayout(2, 1));
        speedSubPanel.setLayout(new GridLayout(2, 1));
        resetAndShootPanel.setLayout(new GridLayout(2, 1));
        checkSubPanel.setLayout(new GridLayout(2, 1));

        characterPanel.add(character1);
        characterPanel.add(character2);
        characterPanel.add(character3);

        //Adds each of the panels to their sub panels
        shootAndExitPanel.add(exitPanel);
        shootAndExitPanel.add(startPanel);
        angleSubPanel.add(angleLabelPanel);
        angleSubPanel.add(anglePanel);
        pauseAndResumePanel.add(pausePanel);
        pauseAndResumePanel.add(resumePanel);
        speedSubPanel.add(speedLabelPanel);
        speedSubPanel.add(speedPanel);
        resetAndShootPanel.add(resetPanel);
        resetAndShootPanel.add(shootPanel);
        checkSubPanel.add(checkBoxPanel);
        checkSubPanel.add(characterPanel);

        //Adds each of the sub panels to the control panel
        controlPanel.add(shootAndExitPanel);
        controlPanel.add(angleSubPanel);
        controlPanel.add(pauseAndResumePanel);
        controlPanel.add(speedSubPanel);
        controlPanel.add(resetAndShootPanel);
        controlPanel.add(checkSubPanel);
    }

    /*
     * This method is used to read the numbers from the speed and angle sliders
     */
    private class ReadTheSliders implements ChangeListener {
        public void stateChanged(ChangeEvent e) {
            int angleInt = angleSlider.getValue();
            double angleD = (double) angleInt;
            levOne.setArch(angleD);

            int speedInt = speedSlider.getValue();
            double speedD = (double) speedInt;
            levOne.setSpeed(speedD);
        }
    }

    /*
     * This method is used to check if checkboxes have been pressed or not
     */
    private class CheckListener implements ItemListener {
        public void itemStateChanged(ItemEvent e) {
            //Turns off the timer so that the level can be played without it
            if (timeOff.isSelected()) {
                levOne.timeOff();
            } else {
                levOne.timeOn();
            }

            // Clears the screen of the values for speed and angle
            if (clearScreen.isSelected()) {
                levOne.clearScreen();
            } else {
                levOne.unClearScreen();
            }

            //Makes the ball more bouncy
            if (changeBounce.isSelected()) {
                levOne.change = 0;
            } else {
                levOne.change = 25.0;
            }


            //Makes the ball more bouncy
            if (difficulty.isSelected()) {
                levOne.diffOn();
                speedSlider.setPaintTicks(false);//Display the Tickmarks
                angleSlider.setPaintTicks(false);//Display the Tickmarks
                speedSlider.setPaintLabels(false);//Display labels on the tickmarks
                angleSlider.setPaintLabels(false);//Display labels on the tickmarks

            } else {
                levOne.diffOff();
                speedSlider.setPaintTicks(true);//Display the Tickmarks
                angleSlider.setPaintTicks(true);//Display the Tickmarks
                angleSlider.setPaintLabels(true);//Display labels on the tickmarks
                speedSlider.setPaintLabels(true);//Display labels on the tickmarks
            }

            // Turn on/off trajectory based on checkbox state
            if (character1.isSelected()) {
                if (trajectory.isSelected()) {
                    levOne.trajectoryOn();
                }
                else{
                    levOne.trajectoryOff();
                    levOne.trajectoryPoints.clear(); // Clear previous trajectory points
                }
            }

            // Turn on/off trajectory based on checkbox state
            if (character2.isSelected()) {
                if (trajectory.isSelected()) {
                    levOne.trajectoryOn();
                }
                else{
                    levOne.trajectoryOff();
                    levOne.trajectoryPoints.clear(); // Clear previous trajectory points
                }
            }

            // Turn on/off trajectory based on checkbox state
            if (character3.isSelected()) {
                if (trajectory.isSelected()) {
                    levOne.trajectoryOn();
                }
                else{
                    levOne.trajectoryOff();
                    levOne.trajectoryPoints.clear(); // Clear previous trajectory points
                }
            }

            if (rain.isSelected()) {
                levOne.showRainAnimation = true;
                //levOne.rainTimer.start();
            } else {
                levOne.showRainAnimation = false;
            }
            if (wind.isSelected()) {
                levOne.showWindAnimation = true;
                levOne.windDirection = (int) (Math.random() * 2 * 2 - 1); // Random wind direction
            } else {
                levOne.showWindAnimation = false;
            }
        }

    }


    /*
     * This tells the buttons what to do when they are pressed by the user. This also reads the name entered by the user in the textField
     */
    private class ListenForButton implements ActionListener {
        public void actionPerformed(ActionEvent b) {
            //Unless the start button is pressed the game will not start, once pressed the game will begin
            if (b.getSource() == startButton) {
                levOne.scoreTimer.start();
                levOne.start = true;

            }
            //Once the timer is started by the start button, the shoot button can be pressed and the ball will be fired
            else if (b.getSource() == shootButton) {
                angleSlider.setEnabled(false);
                speedSlider.setEnabled(false);
                if (levOne.start == false) {
                    levOne.stop();
                } else {
                    levOne.shoot();
                    shotsTaken = shotsTaken + 1;
                    levOne.shotsTaken(shotsTaken);
                }
            }

            //resets the ball to the original launch position when reset button is pressed
            else if (b.getSource() == resetButton) {
                levOne.reset();
                angleSlider.setEnabled(true);
                speedSlider.setEnabled(true);

                levOne.ballX = 115;
                levOne.ballY = 705;
                levOne.velocity = speedSlider.getValue();
                levOne.time = 2.5;

                levOne.change = 25.0;
                levOne.xSpeed = 2.5;
                levOne.sinTheta = 0;
                levOne.stop();
            }
            //When the exit button is pressed the game will close
            else if (b.getSource() == exitButton) {
                System.exit(0);
            }

            //pauses the ball and timer in the position they are currently in when the pause button is pressed
            if (pauseButton.isSelected()) {
                levOne.paused = true;

                levOne.stop();
                levOne.scoreTimer.stop();
            }

            //after the pause button is pressed, the resume button must be pressed to resume the game
            else if (resumeButton.isSelected()) {

                levOne.paused = false;
                levOne.scoreTimer.start();

                /*if (levOne.ballX > 100) {
                 levOne.shoot();
                } */

            }

            //pauses the ball and timer in the position they are currently in when the pause button is pressed
            if (character1.isSelected()) {
                levOne.char1 = true;
                levOne.char2 = false;
                levOne.char3 = false;
                levOne.change = 25.0;
            }

            //after the pause button is pressed, the resume button must be pressed to resume the game
            else if (character2.isSelected()) {
                levOne.char1 = false;
                levOne.char2 = true;
                levOne.char3 = false;
                levOne.change = 12.5;
            }

            //after the pause button is pressed, the resume button must be pressed to resume the game
            else if (character3.isSelected()) {
                levOne.char1 = false;
                levOne.char2 = false;
                levOne.char3 = true;
                levOne.change = 8.0;

            }
        }
    }

}
