package cs3500.excellence.hw05;

import java.util.ArrayList;

/**
 * This is the interface of the Excellence animator model. Implementations of this represent
 * animations.
 */
public interface ExcellenceOperations {

  /**
   * Sets up the canvas for this animation. The given x and y values represent the values of the
   * upper-left corner of the screen, and the width and height are used to calculate where the
   * lower-right corner will be.
   *
   * @param x      The value that will represent the left boundary of the animation
   * @param y      The value that will represent the top boundary of the animation
   * @param width  The value that will represent the width of the animation
   * @param height The value that will represent the height of the view
   */
  void setCanvas(int x, int y, int width, int height);

  /**
   * Returns canvas information so that views know what the canvas needs to be.
   *
   * @returns and Array representing canvas information
   */
  int[] getCanvasInfo();

  /**
   * Adds the given Shape to the animation.
   *
   * @param s Shape to be added to the animation
   */
  void addShape(Shape s);

  /**
   * Removes the shape with the given name in the animation.
   *
   * @param name The name of the shape you are removing
   * @throws IllegalArgumentException if the given shapeIndex does not exist
   */
  void removeShape(String name);

  /**
   * Adds a new keyframe to the shape with the given name, with default values. If the keyframe
   * already exists, resets it to default values.
   *
   * @param name The name of the shape you are adding a change to
   * @param tick The tick that this keyframe is at
   * @throws IllegalArgumentException if the given name does not exist
   */
  void addKeyframeToShape(String name, int tick);

  /**
   * Adds a new keyframe to the shape with the given name, with the given values. If the keyframe
   * already exists, set it to the given values.
   *
   * @param name   The name of the shape you are adding a change to
   * @param tick   The tick that this keyframe is at
   * @param x      x position of the keyframe
   * @param y      y position of the keyframe
   * @param width  width of the keyframe
   * @param height height of the keyframe
   * @param red    red color value of the keyframe
   * @param green  green color value of the keyframe
   * @param blue   blue color value of the keyframe
   * @throws IllegalArgumentException if the given name does not exist
   */
  void addKeyframeToShape(String name, int tick, int x, int y, int width, int height, int red,
      int green, int blue);

  /**
   * Removes a keyframe to the shape with the given name at the given tick.
   *
   * @param name The name of the shape you are removing a keyframe from
   * @param tick The tick that this keyframe is at
   * @throws IllegalArgumentException if the given name does not exist
   */
  void removeKeyframeFromShape(String name, int tick);

  /**
   * Adds a new change to the shape with the given name, with the given parameters. Throws an error
   * if there's no shape at that index.
   *
   * @param name The name of the shape you are adding a change to
   * @param t1   Starting time of the change
   * @param x1   Starting x position of the change
   * @param y1   Starting y position of the change
   * @param w1   Starting width of the change
   * @param h1   Starting height of the change
   * @param r1   Starting red color value of the change
   * @param g1   Starting green color value of the change
   * @param b1   Starting blue color value of the change
   * @param t2   Ending time of the change
   * @param x2   Ending x position of the change
   * @param y2   Ending y position of the change
   * @param w2   Ending width of the change
   * @param h2   Ending height of the change
   * @param r2   Ending red color value of the change
   * @param g2   Ending green color value of the change
   * @param b2   Ending blue color value of the change
   * @throws IllegalArgumentException if the given shapeIndex does not exist
   */
  void addChangeToShape(String name, int t1, int x1, int y1, int w1, int h1, int r1, int g1,
      int b1, int t2,
      int x2, int y2, int w2, int h2, int r2, int g2, int b2);

  /**
   * Removes either the first change or the last change of the shape with the given name. Throws an
   * error if it doesn't exist, or there are no changes.
   *
   * @param name  The name of the shape you are removing a change from
   * @param front Are we removing the first change of this shape?
   * @throws IllegalArgumentException if the given shapeIndex does not exist
   * @throws IllegalArgumentException if there are no changes on this shape
   */
  void removeChangeFromShape(String name, boolean front);

  /**
   * Displays all changes that all shapes have during the animation in text format.
   *
   * @return A formatted string of all shapes and their changes in the animation.
   */
  String displayLog();

  /**
   * Returns a clone of the list of shapes that are in this animation. This is used by the animation
   * view in order to be able to read all the shapes so it can animate them.
   *
   * @returns a clone of this model's shapeArrayList
   */
  ArrayList<Shape> returnShapeList();
}
