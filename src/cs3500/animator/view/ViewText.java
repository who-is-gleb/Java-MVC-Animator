package cs3500.animator.view;

import cs3500.excellence.hw05.ExcellenceOperations;
import cs3500.excellence.hw05.Shape;
import cs3500.excellence.hw05.Ellipse;
import cs3500.excellence.hw05.Rectangle;
import java.awt.event.ActionListener;
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
  public void toggleLooping() {
    throw new UnsupportedOperationException("Can't call toggleLooping on this view!");
  }

  @Override
  public void togglePlayback() {
    throw new UnsupportedOperationException("Can't call togglePlayback on this view!");
  }

  @Override
  public void restart() {
    throw new UnsupportedOperationException("Can't call restart on this view!");
  }

  @Override
  public void increaseRate() {
    throw new UnsupportedOperationException("Can't call increaseRate on this view!");
  }

  @Override
  public void decreaseRate() {
    throw new UnsupportedOperationException("Can't call decreaseRate on this view!");
  }

  @Override
  public void setListener(ActionListener listener) {
    //setListener doesn't matter, but don't throw an error!
  }

  @Override
  public void setTickRate(int rate) {
    //Tickrate doesn't matter here, but don't throw an error!
  }

  @Override
  public void refresh() {
    //Refresh doesn't matter here, but don't throw an error!
  }

  @Override
  public void forwardTick() {
    throw new UnsupportedOperationException("Can't call forwardTick on this view!");
  }

  @Override
  public void backTick() {
    throw new UnsupportedOperationException("Can't call backTick on this view!");
  }

  @Override
  public void startView(PrintStream out) {
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

  @Override
  public String getNameField() {
    throw new UnsupportedOperationException("Can't call getNameField on this view!");
  }

  @Override
  public void updateShapeNames() {
    throw new UnsupportedOperationException("Can't call updateShapeNames on this view!");
  }

  @Override
  public int getTick() {
    throw new UnsupportedOperationException("Can't call getTick on this view!");
  }

  @Override
  public int[] getKeyframeArguments() {
    throw new UnsupportedOperationException("Can't call getKeyframeArguments on this view!");
  }
}
