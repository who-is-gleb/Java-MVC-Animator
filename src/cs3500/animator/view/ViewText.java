package cs3500.animator.view;

//TODO: Implement this entire class (this one is the easiest, since we can just yoink the code from
// the model and put it here for the most part

import cs3500.excellence.hw05.ExcellenceOperations;
import cs3500.excellence.hw05.Shape;
import cs3500.excellence.hw05.Ellipse;
import cs3500.excellence.hw05.Rectangle;
import java.io.PrintStream;

/**
 * A textual view for our animation program. Outputs a text description of the given animation.
 */
public class ViewText implements ExcellenceView {

  private final ExcellenceOperations model;
  int[] canvasInfo;

  ViewText(ExcellenceOperations model) {
    this.model = model;
    this.canvasInfo = model.getCanvasInfo();
  }

  @Override
  public void setTickRate(int rate) {
    //Unused in the text view
  }

  @Override
  public void refresh() {
    //Unused in the text view
  }

  @Override
  public void go(PrintStream out) {
    out.append(
        "canvas " + canvasInfo[0] + " " + canvasInfo[1] + " " + canvasInfo[2] + " " + canvasInfo[3]
            + "\n");

    for (Shape s : model.returnShapeList()) {
      if (s instanceof Rectangle) {
        out.append("shape " + s.name + " rectangle" + "\n");
      } else if (s instanceof Ellipse) {
        out.append("shape " + s.name + " ellipse" + "\n");
      }
      //if there are movements
      if (s.changes.size() > 0) {
        //for each change in the shape
        for (int k = 0; k < s.changes.size(); k++) {
          //write that there's a motion belonging to this shape...
          out.append("motion " + s.name);
          //...then write each number...
          for (int j : s.changes.get(k)) {
            out.append(" " + j);
          }
          //...and then go to a new line when done
          out.append("\n");
        }
      }
    }
  }
}
