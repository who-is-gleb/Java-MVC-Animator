package cs3500.animator.view;

import java.io.PrintStream;

/**
 * The  for our views of the Excellence Animator. All three views implement this interface to ensure
 * they share functionality.
 */
public interface ExcellenceView {

  //All implementations of this need a constructor that takes an ExcellenceOperations!

  /**
   * Sets the tickRate for this animation. If this isn't called, the default tickRate remains at one
   * tick per second.
   *
   * @param rate The new tickRate we are setting for this animation
   * @throws IllegalArgumentException if you pass it zero or a negative
   */
  void setTickRate(int rate);

  /**
   * Refresh this view to show any changes. Will be useful later, not sure if needed now.
   */
  void refresh();

  /**
   * Set this view into motion. Does different things for each view. For example, outputs the
   * results of the text-based views, or shows the animation for the animation view. Takes an
   * PrintStream, which is passed all the way in from the command line.
   *
   * @param out The PrintStream we're outputting to
   */
  void go(PrintStream out);
}
