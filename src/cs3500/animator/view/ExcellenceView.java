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

  /**
   * Returns the current typed in string in the name field of the view.
   *
   * @return a string that will be used by the controller for modifying the data in the model
   * @throws UnsupportedOperationException if this view does not support this.
   */
  String getNameField();

  /**
   * Send a signal to the view that the list of shapes needs to be updated.
   *
   * @throws UnsupportedOperationException if this view does not support this.
   */
  void updateShapeNames();

  /**
   * Returns the current tick, so the controller can know what ticks to hand the model.
   *
   * @return the current tick.
   */
  int getTick();

  /**
   * Sends the current keyframe arguments from the view to the controller, for use in
   * adding/modifying keyframes.
   *
   * @return an array representing the x, y, w, h, r, g, and b of a desired keyframe
   */
  int[] getKeyframeArguments();
}
