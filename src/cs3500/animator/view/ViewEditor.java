package cs3500.animator.view;

import cs3500.animator.controller.EditController;
import cs3500.excellence.hw05.ExcellenceOperations;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
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

  private JPanel buttonPanel;
  private JButton playPauseButton, toggleLoopButton, restartButton, speedUpButton, speedDownButton;
  private JLabel currentTickRate;

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

    //get the canvas info from the model and set up the width and height
    this.canvasInfo = model.getCanvasInfo();
    this.setSize(canvasInfo[2], canvasInfo[3]);

    //set up the panel
    this.setLayout(new BorderLayout());
    panel = new ViewPanel(model);
    panel.setPreferredSize(new Dimension(canvasInfo[2], canvasInfo[3]));
    this.add(panel, BorderLayout.CENTER);

    //set up the button panel
    buttonPanel = new JPanel();
    buttonPanel.setLayout(new FlowLayout());
    this.add(buttonPanel, BorderLayout.SOUTH);

    //restart button
    restartButton = new JButton("|<");
    restartButton.setActionCommand("restart");
    buttonPanel.add(restartButton);

    //play button
    playPauseButton = new JButton("Play/Pause");
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
    currentTickRate = new JLabel(String.valueOf(tickRate));
    buttonPanel.add(currentTickRate);

    //speed up button
    speedUpButton = new JButton(">>");
    speedUpButton.setActionCommand("speedUp");
    buttonPanel.add(speedUpButton);

    this.pack();

    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }

  @Override
  public void toggleLooping() {
    looping = !looping;
  }

  @Override
  public void togglePlayback() {
    playing = !playing;
  }

  @Override
  public void restart() {
    tick = 0;
    refresh();
  }

  @Override
  public void increaseRate() {
    tickRate++;
    currentTickRate.setText(String.valueOf(tickRate));
  }

  @Override
  public void decreaseRate() {
    tickRate--;
    if (tickRate <= 0) {
      tickRate = 0;
    }
    System.out.print("DECREASE TO " + tickRate);
    currentTickRate.setText(String.valueOf(tickRate));
  }

  @Override
  public void setListener(ActionListener listener) {
    restartButton.addActionListener(listener);
    playPauseButton.addActionListener(listener);
    toggleLoopButton.addActionListener(listener);
    speedDownButton.addActionListener(listener);
    speedUpButton.addActionListener(listener);
  }

  @Override
  public void setTickRate(int rate) {
    if (rate <= 0) {
      throw new IllegalArgumentException(
          "You've set the tick rate to 0 or less, which is not allowed!");
    }
    tickRate = rate;
    currentTickRate.setText(String.valueOf(tickRate));
  }

  @Override
  public void refresh() {
    panel.setTick(tick);
    panel.repaint();
  }

  @Override
  public void startView(PrintStream out) {

    //TODO: Replace this old clock with something better
    Timer timer = new Timer(1000 / tickRate, e -> {
      refresh();
      tick++;
    });

    //set this view to being visible
    this.setVisible(true);

    //TODO: Actually make it work
    timer.start();

  }
}
