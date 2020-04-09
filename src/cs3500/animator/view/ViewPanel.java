package cs3500.animator.view;

import cs3500.excellence.hw05.Ellipse;
import cs3500.excellence.hw05.ExcellenceOperations;
import cs3500.excellence.hw05.Rectangle;
import cs3500.excellence.hw05.Shape;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import javax.swing.JPanel;

/**
 * The actual panel that handles rendering of animations. All the calculations for rendering shapes
 * on each tick happen here, as well as the actual drawing of the shapes.
 */
public class ViewPanel extends JPanel {

  private ExcellenceOperations model;
  int tick;
  int[] canvasInfo;

  /**
   * Constructor for this ViewPanel. Takes in a model to be rendered.
   *
   * @param model the model we are going to be rendering
   */
  public ViewPanel(ExcellenceOperations model) {
    super();
    this.setBackground(Color.WHITE);
    this.model = model;
    this.tick = 0;
    canvasInfo = model.getCanvasInfo();
  }

  /**
   * Setter for the current tick to render. Gets updated every clock tick from the View.
   *
   * @param tick what tick to render
   */
  public void setTick(int tick) {
    this.tick = tick;
  }

  /**
   * Override the paintComponent method of the JPanel. This is where things are drawn each tick, and
   * is called each tick.
   *
   * @param g Graphics object that handles drawing the graphics
   */
  @Override
  protected void paintComponent(Graphics g) {
    //super.paintComponent(g);

    Graphics2D g2d = (Graphics2D) g;

    //this is where we draw the frames
    //use tick to get the current tick based on the clock
    //cycle through all the shapes in this animation
    for (Shape s : model.returnShapeList()) {

      //does this shape render at all?
      if (s.keyframes.size() > 0) {
        //cycle through keyframes on this shape
        for (int i = 0; i < s.keyframes.size() - 1; i++) {
          ArrayList<Integer> currentKeyframe = s.keyframes.get(i);

          //is there a next keyframe?
          if (s.keyframes.indexOf(currentKeyframe) < s.keyframes.size()) {
            //are we between these keyframes?
            if (tick >= currentKeyframe.get(0) && tick <= s.keyframes.get(i + 1).get(0)) {
              //render, while tweening
              //set up tweening
              int startTick = currentKeyframe.get(0);
              int endTick = s.keyframes.get(i + 1).get(0);

              //set the color for this shape
              int red = tweener(tick, startTick, currentKeyframe.get(5), endTick,
                  s.keyframes.get(i + 1).get(5));
              int green = tweener(tick, startTick, currentKeyframe.get(6), endTick,
                  s.keyframes.get(i + 1).get(6));
              int blue = tweener(tick, startTick, currentKeyframe.get(7), endTick,
                  s.keyframes.get(i + 1).get(7));
              g2d.setColor(new Color(red, green, blue));

              //Position and size
              int x = tweener(tick, startTick, currentKeyframe.get(1) - canvasInfo[0], endTick,
                  s.keyframes.get(i + 1).get(1) - canvasInfo[0]);
              int y = tweener(tick, startTick, currentKeyframe.get(2) - canvasInfo[1], endTick,
                  s.keyframes.get(i + 1).get(2) - canvasInfo[1]);
              int width = tweener(tick, startTick, currentKeyframe.get(3), endTick,
                  s.keyframes.get(i + 1).get(3));
              int height = tweener(tick, startTick, currentKeyframe.get(4), endTick,
                  s.keyframes.get(i + 1).get(4));

              //Now draw the shapes!
              //This is a bad and messy way to handle this, we should change it later if we have time
              if (s instanceof Rectangle) {
                g2d.fillRect(x, y, width, height);
              } else if (s instanceof Ellipse) {
                g2d.fillOval(x, y, width, height);
              }
            }
          } else if (s.keyframes.size() == 1 && currentKeyframe.get(0) == tick) {
            //render for this tick only!
            g2d.setColor(new Color(s.keyframes.get(i).get(5), s.keyframes.get(i).get(6),
                s.keyframes.get(i).get(7)));
            if (s instanceof Rectangle) {
              g2d.fillRect(s.keyframes.get(i).get(1), s.keyframes.get(i).get(2),
                  s.keyframes.get(i).get(3), s.keyframes.get(i).get(4));
            } else if (s instanceof Ellipse) {
              g2d.fillOval(s.keyframes.get(i).get(1), s.keyframes.get(i).get(2),
                  s.keyframes.get(i).get(3), s.keyframes.get(i).get(4));
            }
          }
        }
      }
    }
  }

  /**
   * Handles tweening of values for smooth animations. Used to calculate size, position, and color
   * between major frames.
   *
   * @param currentTick The current tick to render
   * @param startTick   The starting tick of this thing
   * @param valueA      The starting value of this thing
   * @param endTick     The ending tick of this thing
   * @param valueB      The ending value of this thing
   * @return the value that this should be at the given tick
   */
  private int tweener(int currentTick, int startTick, int valueA, int endTick, int valueB) {
    //this is messy, but the only way I can get it to work is to split every calculation
    // into a separate line
    double funcATop = endTick - currentTick;
    double funcABottom = endTick - startTick;
    double funcA = valueA * (funcATop / funcABottom);
    double funcBTop = currentTick - startTick;
    double funcBBottom = endTick - startTick;
    double funcB = valueB * (funcBTop / funcBBottom);
    return (int) Math.round(funcA + funcB);
  }
}
