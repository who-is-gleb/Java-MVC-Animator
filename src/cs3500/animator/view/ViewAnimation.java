package cs3500.animator.view;

import cs3500.excellence.hw05.ExcellenceOperations;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.io.PrintStream;
import javax.swing.JFrame;
import javax.swing.Timer;

/**
 * A animated view for our animation program. Displays a fully animated version of the given
 * animation.
 */
public class ViewAnimation extends JFrame implements ExcellenceView {

  ViewPanel panel;
  int tickRate;
  int tick;
  int[] canvasInfo;

  /**
   * The constructor for the visual view of the animation program. Takes in a model that it needs to
   * display, and sets up the panel and starts and handles the clock that refreshes the panel.
   *
   * @param model the model that we are rendering
   */
  ViewAnimation(ExcellenceOperations model) {
    this.tickRate = 1;
    this.tick = 0;

    //get the canvas info from the model and set up the width and height
    this.canvasInfo = model.getCanvasInfo();
    this.setSize(canvasInfo[2], canvasInfo[3]);

    //set up the panel
    this.setLayout(new BorderLayout());
    panel = new ViewPanel(model);
    panel.setPreferredSize(new Dimension(canvasInfo[2], canvasInfo[3]));
    this.add(panel, BorderLayout.CENTER);
    this.pack();

    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }

  @Override
  public void setTickRate(int rate) {
    if (rate <= 0) {
      throw new IllegalArgumentException(
          "You've set the tick rate to 0 or less, which is not allowed!");
    }
    tickRate = rate;
  }

  @Override
  public void refresh() {
    panel.setTick(tick);
    panel.repaint();
  }

  @Override
  public void go(PrintStream out) {

    //Set up the clock that ticks
    //There's a lambda expression that acts as the ActionListener for the timer. It listens for each
    //tick of the clock, and each time that happens, refreshes the view and counts the tick upwards
    //by one.
    Timer timer = new Timer(1000 / tickRate, e -> {
      refresh();
      tick++;
    });

    //set this view to being visible
    this.setVisible(true);

    //start the clock, and by extension the animation
    timer.start();
  }
}
