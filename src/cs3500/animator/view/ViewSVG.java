package cs3500.animator.view;

import cs3500.excellence.hw05.Ellipse;
import cs3500.excellence.hw05.ExcellenceOperations;
import cs3500.excellence.hw05.Rectangle;
import cs3500.excellence.hw05.Shape;
import java.awt.event.ActionListener;
import java.io.PrintStream;

//TODO: Branden, please finish this and write the tests. It didn't work when you wrote it for
// Assignment 6, so can you fix it for Assignment 7? I've almost everything else so far.

/**
 * A SVG view for our animation program. Outputs text in the style of an SVG file for the given
 * animation.
 */
public class ViewSVG implements ExcellenceView {

  int[] canvasInfo;
  private final ExcellenceOperations model;

  ViewSVG(ExcellenceOperations model) {
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
    throw new UnsupportedOperationException("Can't call setListener on this view!");
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
  public void startView(PrintStream out) {
    out.append(
        "<svg width=\"" + canvasInfo[0] + "\" height=\"" + canvasInfo[3] + "\" version=\"1.1\" "
            + "xmnls=\"http://www.w3.org/2000/svg\">\n");
    //for every shape
    for (Shape s : model.returnShapeList()) {
      if (s instanceof Rectangle) {
        out.append(
            "<rect id=\"" + s.name + "\" x=\"" + s.changes.get(0).get(1) + "\" y=\"" + s.changes
                .get(0).get(2) + "\" width=\"" + s.changes.get(0).get(3) + "\" height=\""
                + s.changes.get(0).get(4) + "\" fill=\"rgb(" + s.changes.get(0).get(5) + "."
                + s.changes.get(0).get(6) + "." + s.changes.get(0).get(7)
                + ")\" visibility=\"visible\" >" + "\n");
      } else if (s instanceof Ellipse) {
        out.append("<ellipse id=\"" + s.name + "\" cx=\"" + s.changes.get(0).get(1) + "\" cy=\""
            + s.changes.get(0).get(2) + "\" rx=\"" + (s.changes.get(0).get(3) / 2) + "\" ry=\"" + (
            s.changes.get(0).get(4) / 2) + "\" fill=\"rgb(" + s.changes.get(0).get(5) + "."
            + s.changes.get(0).get(6) + "." + s.changes.get(0).get(7)
            + ")\" visibility=\"visible\" >" + "\n");
      }
      //if there are movements
      if (s.changes.size() > 0) {
        //for each change in the shape
        for (int i = 0; i < s.changes.size(); i++) {
          //write that there's a motion belonging to this shape...
          if (s instanceof Rectangle) {
            out.append(
                "<animate attributeType=\"xml\" begin=\"" + s.changes.get(i).get(0) + "ms\" dur=\""
                    + (s.changes.get(i).get(8) - s.changes.get(i).get(0))
                    + "ms\" attributeName=\"");
            if (s.changes.get(i).get(1) != s.changes.get(i).get(9)) {
              out.append(
                  "x\" from=\"" + s.changes.get(i).get(1) + "\" to=\"" + s.changes.get(i).get(9)
                      + "\" fill=\"freeze\" />" + "\n");
            } else if (s.changes.get(i).get(2) != s.changes.get(i).get(10)) {
              out.append(
                  "y\" from=\"" + s.changes.get(i).get(2) + "\" to=\"" + s.changes.get(i).get(10)
                      + "\" fill=\"freeze\" />" + "\n");
            } else if (s.changes.get(i).get(3) != s.changes.get(i).get(11)) {
              out.append(
                  "w\" from=\"" + s.changes.get(i).get(3) + "\" to=\"" + s.changes.get(i).get(11)
                      + "\" fill=\"freeze\" />" + "\n");
            } else if (s.changes.get(i).get(4) != s.changes.get(i).get(12)) {
              out.append(
                  "h\" from=\"" + s.changes.get(i).get(4) + "\" to=\"" + s.changes.get(i).get(12)
                      + "\" fill=\"freeze\" />" + "\n");
            } else if (s.changes.get(i).get(5) != s.changes.get(i).get(13)) {
              out.append(
                  "r\" from=\"" + s.changes.get(i).get(5) + "\" to=\"" + s.changes.get(i).get(13)
                      + "\" fill=\"freeze\" />" + "\n");
            } else if (s.changes.get(i).get(6) != s.changes.get(i).get(14)) {
              out.append(
                  "g\" from=\"" + s.changes.get(i).get(6) + "\" to=\"" + s.changes.get(i).get(14)
                      + "\" fill=\"freeze\" />" + "\n");
            } else if (s.changes.get(i).get(7) != s.changes.get(i).get(15)) {
              out.append(
                  "b\" from=\"" + s.changes.get(i).get(7) + "\" to=\"" + s.changes.get(i).get(15)
                      + "\" fill=\"freeze\" />" + "\n");
            }
          } else if (s instanceof Ellipse) {
            out.append(
                "<animate attributeType=\"xml\" begin=\"" + s.changes.get(i).get(0) + "ms\" dur=\""
                    + (s.changes.get(i).get(8) - s.changes.get(i).get(0))
                    + "ms\" attributeName=\"");
            if (s.changes.get(i).get(1) != s.changes.get(i).get(9)) {
              out.append(
                  "cx\" from=\"" + s.changes.get(i).get(1) + "\" to=\"" + s.changes.get(i).get(9)
                      + "\" fill=\"freeze\" />" + "\n");
            } else if (s.changes.get(i).get(2) != s.changes.get(i).get(10)) {
              out.append(
                  "cy\" from=\"" + s.changes.get(i).get(2) + "\" to=\"" + s.changes.get(i).get(10)
                      + "\" fill=\"freeze\" />" + "\n");
            } else if (s.changes.get(i).get(3) != s.changes.get(i).get(11)) {
              out.append("rx\" from=\"" + (s.changes.get(i).get(3) / 2) + "\" to=\"" + (
                  s.changes.get(i).get(11) / 2) + "\" fill=\"freeze\" />" + "\n");
            } else if (s.changes.get(i).get(4) != s.changes.get(i).get(12)) {
              out.append("ry\" from=\"" + (s.changes.get(i).get(4) / 2) + "\" to=\"" + (
                  s.changes.get(i).get(12) / 2) + "\" fill=\"freeze\" />" + "\n");
            } else if (s.changes.get(i).get(5) != s.changes.get(i).get(13)) {
              out.append(
                  "r\" from=\"" + s.changes.get(i).get(5) + "\" to=\"" + s.changes.get(i).get(13)
                      + "\" fill=\"freeze\" />" + "\n");
            } else if (s.changes.get(i).get(6) != s.changes.get(i).get(14)) {
              out.append(
                  "g\" from=\"" + s.changes.get(i).get(6) + "\" to=\"" + s.changes.get(i).get(14)
                      + "\" fill=\"freeze\" />" + "\n");
            } else if (s.changes.get(i).get(7) != s.changes.get(i).get(15)) {
              out.append(
                  "b\" from=\"" + s.changes.get(i).get(7) + "\" to=\"" + s.changes.get(i).get(15)
                      + "\" fill=\"freeze\" />" + "\n");
            }
          }
        }
      }
      if (s instanceof Rectangle) {
        out.append("</rect>" + "\n");
      } else if (s instanceof Ellipse) {
        out.append("</ellipse>" + "\n");
      }
    }
    out.append("</svg>");
    System.exit(0);
  }
}
