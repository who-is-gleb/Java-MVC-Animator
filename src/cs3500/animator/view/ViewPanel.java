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
      if (s.changes.size() > 0) {
        //cycle through movements on this shape
        for (int i = 0; i < s.changes.size(); i++) {
          ArrayList<Integer> currentChange = s.changes.get(i);
          //does the current tick mean to use this movement?
          if (tick >= currentChange.get(0) && tick <= currentChange.get(8)) {

            //render the shape at this moment!

            //set up tweening
            int startTick = currentChange.get(0);
            int endTick = currentChange.get(8);

            //set the color for this shape
            int red = tweener(tick, startTick, currentChange.get(5), endTick,
                currentChange.get(13));
            int green = tweener(tick, startTick, currentChange.get(6), endTick,
                currentChange.get(14));
            int blue = tweener(tick, startTick, currentChange.get(7), endTick,
                currentChange.get(15));
            g2d.setColor(new Color(red, green, blue));

            //Position and size
            int x = tweener(tick, startTick, currentChange.get(1) - canvasInfo[0], endTick,
                currentChange.get(9) - canvasInfo[0]);
            int y = tweener(tick, startTick, currentChange.get(2) - canvasInfo[1], endTick,
                currentChange.get(10) - canvasInfo[1]);
            int width = tweener(tick, startTick, currentChange.get(3), endTick,
                currentChange.get(11));
            int height = tweener(tick, startTick, currentChange.get(4), endTick,
                currentChange.get(12));

            //Now draw the shapes!
            //This is a bad and messy way to handle this, we should change it later if we have time
            if (s instanceof Rectangle) {
              g2d.fillRect(x, y, width, height);
            } else if (s instanceof Ellipse) {
              g2d.fillOval(x, y, width, height);
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
