package cs3500.animator.view;

import cs3500.animator.controller.EditController;
import cs3500.excellence.hw05.ExcellenceOperations;
import cs3500.excellence.hw05.Shape;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.io.PrintStream;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

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

  private JPanel bottomPanel, framePanel, buttonPanel;
  private JButton backFrameButton, forwardFrameButton, playPauseButton, toggleLoopButton,
      restartButton, speedUpButton, speedDownButton;
  private JLabel currentFrame, currentTickRate;
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

    //set up the rest of the frame
    bottomPanel.setPreferredSize(new Dimension(600, 100));
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
    System.out.print("THE BUTTON WORKS!");
    tick = tick + 1;
    refresh();
  }

  @Override
  public void backTick() {
    System.out.print("THE BUTTON WORKS!");
    tick =  tick - 1;
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
        if (highestTick < s.keyframes.get(s.keyframes.size() - 1).get(0)) {
          highestTick = s.keyframes.get(s.keyframes.size() - 1).get(0);
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
}
