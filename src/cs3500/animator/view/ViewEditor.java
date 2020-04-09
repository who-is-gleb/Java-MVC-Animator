package cs3500.animator.view;

import cs3500.excellence.hw05.ExcellenceOperations;
import cs3500.excellence.hw05.Shape;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.io.PrintStream;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.Timer;
import javax.swing.event.ListSelectionListener;

/**
 * The Editor view for our animation program. Displays a fully animated version of the given
 * animation that can be interacted with.
 */
public class ViewEditor extends JFrame implements ExcellenceView {

  ViewPanel panel;
  int tickRate;
  int tick;
  int[] canvasInfo;
  boolean looping = false;
  boolean playing = true;

  Timer timer;

  private JPanel bottomPanel, framePanel, buttonPanel, editPanel, editLeft, editRight,
      keyframePanel, keyframeMain, keyframeInputGrid;
  private JButton backFrameButton, forwardFrameButton, playPauseButton, toggleLoopButton,
      restartButton, speedUpButton, speedDownButton, addRectButton, addEllipseButton,
      removeShapeButton, addEditButton, removeKeyButton;
  private JLabel currentFrame, currentTickRate, nameInstructions, xText, yText, wText, hText, rText,
      gText, bText;
  private JTextField nameField, xField, yField, widthField, heightField, redField, greenField,
      blueField;
  private JScrollPane shapeList;
  private JList shapeNames;
  private ExcellenceOperations model;

  /**
   * The constructor for the editor view of the animation program. Takes in a model that it needs to
   * display, sets up the panel, and provides stuff for the controller to interact with.
   *
   * @param model the model that we are rendering and editing
   */
  ViewEditor(ExcellenceOperations model) {
    this.tickRate = 1;
    this.tick = 0;
    this.looping = false;
    this.playing = true;
    this.model = model;

    //set up the timer that will be used
    timer = new Timer(1000 / tickRate, e -> {
      refresh();
      tick++;
    });
    timer.setInitialDelay(0);

    //get the canvas info from the model and set up the width and height
    this.canvasInfo = model.getCanvasInfo();
    this.setSize(canvasInfo[2], canvasInfo[3]);

    //set up the view panel
    this.setLayout(new BorderLayout());
    panel = new ViewPanel(model);
    panel.setPreferredSize(new Dimension(canvasInfo[2], canvasInfo[3]));
    this.add(panel, BorderLayout.CENTER);

    //set up the bottom controls panel
    bottomPanel = new JPanel();
    bottomPanel.setLayout(new FlowLayout());
    this.add(bottomPanel, BorderLayout.SOUTH);

    //set up the frame controls panel
    framePanel = new JPanel();
    framePanel.setLayout(new FlowLayout());
    bottomPanel.add(framePanel);

    //back frame button
    backFrameButton = new JButton("<<");
    backFrameButton.setActionCommand("backFrame");
    framePanel.add(backFrameButton);

    //show the current rate
    currentFrame = new JLabel("Current Frame: " + tick);
    framePanel.add(currentFrame);

    //forward frame button
    forwardFrameButton = new JButton(">>");
    forwardFrameButton.setActionCommand("forwardFrame");
    framePanel.add(forwardFrameButton);

    //set up the playback controls panel
    buttonPanel = new JPanel();
    buttonPanel.setLayout(new FlowLayout());
    bottomPanel.add(buttonPanel);

    //restart button
    restartButton = new JButton("|<");
    restartButton.setActionCommand("restart");
    buttonPanel.add(restartButton);

    //play button
    playPauseButton = new JButton("Pause");
    playPauseButton.setActionCommand("playPause");
    buttonPanel.add(playPauseButton);

    //loop toggle button
    toggleLoopButton = new JButton("Looping");
    toggleLoopButton.setActionCommand("toggleLoop");
    buttonPanel.add(toggleLoopButton);

    //slow down button
    speedDownButton = new JButton("<<");
    speedDownButton.setActionCommand("speedDown");
    buttonPanel.add(speedDownButton);

    //show the current rate
    currentTickRate = new JLabel("Speed: " + String.valueOf(tickRate));
    buttonPanel.add(currentTickRate);

    //speed up button
    speedUpButton = new JButton(">>");
    speedUpButton.setActionCommand("speedUp");
    buttonPanel.add(speedUpButton);

    //set up the edit controls panel
    editPanel = new JPanel();
    editPanel.setLayout(new GridLayout(0, 2));
    bottomPanel.add(editPanel);

    editLeft = new JPanel();
    editLeft.setLayout(new GridLayout(5, 1));
    editPanel.add(editLeft);

    editRight = new JPanel();
    editRight.setLayout(new GridLayout(0, 1));
    editPanel.add(editRight);

    //show the current rate
    nameInstructions = new JLabel("Name of shape to Add/Edit/Remove");
    editLeft.add(nameInstructions);

    //set up the name field for adding/removing
    nameField = new JTextField(20);
    editLeft.add(nameField);

    //Add and remove buttons
    addRectButton = new JButton("Add Rectangle with given name");
    addRectButton.setActionCommand("addRect");
    editLeft.add(addRectButton);
    addEllipseButton = new JButton("Add Ellipse with given name");
    addEllipseButton.setActionCommand("addEllipse");
    editLeft.add(addEllipseButton);
    removeShapeButton = new JButton("Remove selected Shape");
    removeShapeButton.setActionCommand("removeShape");
    editLeft.add(removeShapeButton);

    //set up shape list
    DefaultListModel<String> dataForListOfStrings = new DefaultListModel<>();
    for (Shape s : model.returnShapeList()) {
      dataForListOfStrings.addElement(s.name);
    }
    shapeNames = new JList<>(dataForListOfStrings);
    shapeNames.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    editRight.add(new JScrollPane(shapeNames));

    //set up keyframe editor
    keyframePanel = new JPanel();
    keyframePanel.setLayout(new GridLayout(2, 0));
    bottomPanel.add(keyframePanel);

    //Main edit buttons
    keyframeMain = new JPanel();
    keyframeMain.setLayout(new FlowLayout());
    keyframePanel.add(keyframeMain);

    addEditButton = new JButton("Add/Edit Keyframe on current Shape and Tick");
    addEditButton.setActionCommand("addKey");
    keyframeMain.add(addEditButton);

    removeKeyButton = new JButton("Remove Keyframe on current Shape and Tick");
    removeKeyButton.setActionCommand("removeKey");
    keyframeMain.add(removeKeyButton);

    //Input grid
    keyframeInputGrid = new JPanel();
    keyframeInputGrid.setLayout(new GridLayout(2, 7));
    keyframePanel.add(keyframeInputGrid);

    xText = new JLabel("X:");
    keyframeInputGrid.add(xText);

    yText = new JLabel("Y:");
    keyframeInputGrid.add(yText);

    wText = new JLabel("Width:");
    keyframeInputGrid.add(wText);

    hText = new JLabel("Height:");
    keyframeInputGrid.add(hText);

    rText = new JLabel("Red:");
    keyframeInputGrid.add(rText);

    gText = new JLabel("Green:");
    keyframeInputGrid.add(gText);

    bText = new JLabel("Blue:");
    keyframeInputGrid.add(bText);

    xField = new JTextField(2);
    keyframeInputGrid.add(xField);

    yField = new JTextField(2);
    keyframeInputGrid.add(yField);

    widthField = new JTextField(2);
    keyframeInputGrid.add(widthField);

    heightField = new JTextField(2);
    keyframeInputGrid.add(heightField);

    redField = new JTextField(2);
    keyframeInputGrid.add(redField);

    greenField = new JTextField(2);
    keyframeInputGrid.add(greenField);

    blueField = new JTextField(2);
    keyframeInputGrid.add(blueField);

    //set up the rest of the frame
    bottomPanel.setPreferredSize(new Dimension(600, 350));
    this.pack();
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }

  @Override
  public void toggleLooping() {
    looping = !looping;
    refresh();
  }

  @Override
  public void togglePlayback() {
    playing = !playing;
    if (playing) {
      timer.start();
    } else {
      timer.stop();
    }
    refresh();
  }

  @Override
  public void restart() {
    tick = 0;
    refresh();
  }

  @Override
  public void forwardTick() {
    tick = tick + 1;
    refresh();
  }

  @Override
  public void backTick() {
    tick = tick - 1;
    if (tick < 0) {
      tick = 0;
    }
    refresh();
  }

  @Override
  public void increaseRate() {
    tickRate++;
    timer.setDelay(1000 / tickRate);
    currentTickRate.setText("Speed: " + String.valueOf(tickRate));
  }

  @Override
  public void decreaseRate() {
    tickRate = tickRate - 1;
    if (tickRate <= 0) {
      tickRate = 0;
    }
    timer.setDelay(1000 / tickRate);
    currentTickRate.setText("Speed: " + String.valueOf(tickRate));
  }

  @Override
  public void setListener(ActionListener listener) {
    restartButton.addActionListener(listener);
    playPauseButton.addActionListener(listener);
    toggleLoopButton.addActionListener(listener);
    speedDownButton.addActionListener(listener);
    speedUpButton.addActionListener(listener);
    backFrameButton.addActionListener(listener);
    forwardFrameButton.addActionListener(listener);
    addRectButton.addActionListener(listener);
    addEllipseButton.addActionListener(listener);
    removeShapeButton.addActionListener(listener);
    addEditButton.addActionListener(listener);
    removeKeyButton.addActionListener(listener);
    shapeNames.addListSelectionListener((ListSelectionListener) listener);
  }

  @Override
  public void setTickRate(int rate) {
    if (rate <= 0) {
      throw new IllegalArgumentException(
          "You've set the tick rate to 0 or less, which is not allowed!");
    }
    tickRate = rate;
    timer.setDelay(1000 / tickRate);
    currentTickRate.setText("Speed: " + String.valueOf(tickRate));
  }

  @Override
  public void refresh() {

    if (!playing) {
      playPauseButton.setBackground(Color.red);
      playPauseButton.setOpaque(true);
    } else {
      playPauseButton.setBackground(Color.white);
      playPauseButton.setOpaque(false);
    }

    if (looping) {
      toggleLoopButton.setBackground(Color.red);
      toggleLoopButton.setOpaque(true);

      //figure out if we're past the end
      int highestTick = 0;
      for (Shape s : model.returnShapeList()) {
        if (s.keyframes.size() > 0) {
          if (highestTick < s.keyframes.get(s.keyframes.size() - 1).get(0)) {
            highestTick = s.keyframes.get(s.keyframes.size() - 1).get(0);
          }
        }
      }
      //if we're past the end, loop!
      if (tick > highestTick) {
        tick = 0;
      }
    } else {
      toggleLoopButton.setBackground(Color.white);
      toggleLoopButton.setOpaque(false);
    }

    //update the tick display each refresh
    currentFrame.setText("Current Frame: " + tick);

    //actually do the rendering with the panel
    panel.setTick(tick);
    panel.repaint();
  }

  @Override
  public void startView(PrintStream out) {

    //set this view to being visible
    this.setVisible(true);

    //Start the clock
    timer.start();

  }

  @Override
  public String getNameField() {
    String name = nameField.getText();
    nameField.setText("");
    return name;
  }

  @Override
  public void updateShapeNames() {
    //update the shape names
    DefaultListModel<String> data = new DefaultListModel<>();
    for (Shape s : model.returnShapeList()) {
      data.addElement(s.name);
    }
    System.out.print(data);
    shapeNames.setModel(data);
  }

  @Override
  public int getTick() {
    return tick;
  }

  @Override
  public int[] getKeyframeArguments() {
    int[] temp = new int[]{Integer.parseInt(xField.getText()), Integer.parseInt(yField.getText()),
        Integer.parseInt(widthField.getText()), Integer.parseInt(heightField.getText()),
        Integer.parseInt(redField.getText()), Integer.parseInt(greenField.getText()),
        Integer.parseInt(blueField.getText())};
    xField.setText("");
    yField.setText("");
    widthField.setText("");
    heightField.setText("");
    redField.setText("");
    greenField.setText("");
    blueField.setText("");
    return temp;
  }
}
