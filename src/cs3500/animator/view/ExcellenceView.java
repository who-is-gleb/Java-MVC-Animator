package cs3500.animator.view;

import java.awt.event.ActionListener;
import java.io.PrintStream;

/**
 * The  for our views of the Excellence Animator. All three views implement this interface to ensure
 * they share functionality.
 */
public interface ExcellenceView {

  //All implementations of this need a constructor that takes an ExcellenceOperations!

  /**
   * Toggles if the animation should loop.
   *
   * @throws UnsupportedOperationException if this view does not support this.
   */
  void toggleLooping();

  /**
   * Toggles playback on the view.
   *
   * @throws UnsupportedOperationException if this view does not support this.
   */
  void togglePlayback();

  /**
   * Restarts the playback in this view.
   *
   * @throws UnsupportedOperationException if this view does not support this.
   */
  void restart();

  /**
   * Adds one to the current tick
   *
   * @throws UnsupportedOperationException if this view does not support this.
   */
  void forwardTick();

  /**
   * Removes one from the current tick, stopping at zero.
   *
   * @throws UnsupportedOperationException if this view does not support this.
   */
  void backTick();

  /**
   * Increases the tickRate of this view by one.
   *
   * @throws UnsupportedOperationException if this view does not support this.
   */
  void increaseRate();

  /**
   * Decreases the tickRate of this view by one.
   *
   * @throws UnsupportedOperationException if this view does not support this.
   */
  void decreaseRate();

  /**
   * Sets the ActionListener that the buttons in this view set off.
   *
   * @throws UnsupportedOperationException if this view does not support this.
   */
  void setListener(ActionListener listener);

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
  void startView(PrintStream out);
}
