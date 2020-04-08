package cs3500.animator.view;

import java.awt.event.ActionListener;
import java.io.PrintStream;

import cs3500.excellence.hw05.Ellipse;
import cs3500.excellence.hw05.ExcellenceOperations;
import cs3500.excellence.hw05.Rectangle;
import cs3500.excellence.hw05.Shape;

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
                    + "xmlns=\"http://www.w3.org/2000/svg\">\n");
    //for every shape
    for (Shape s : model.returnShapeList()) {
      if (s instanceof Rectangle) {
        out.append(
                "<rect id=\"" + s.name + "\" x=\"" + s.changes.get(0).get(1) + "\" y=\"" + s.changes
                        .get(0).get(2) + "\" width=\"" + s.changes.get(0).get(3) + "\" height=\""
                        + s.changes.get(0).get(4) + "\" fill=\"rgb(" + s.changes.get(0).get(5) + "."
                        + s.changes.get(0).get(6) + "." + s.changes.get(0).get(7)
                        + ")\" visibility=\"visible\" >" + "\n");
        if (s.changes.size() > 0) {
          //for each change in the shape
          for (int i = 0; i < s.changes.size(); i++) {
            for (int k = 1; k < (s.changes.get(i).size() / 2); k++) {
              if (!s.changes.get(i).get(k).equals(s.changes.get(i).get(k + 8))) {
                out.append(
                        "<animate attributeType=\"xml\" begin=\"" + (s.changes.get(i).get(0) * 1000) + "ms\" dur=\""
                                + ((s.changes.get(i).get(8) - s.changes.get(i).get(0)) * 1000)
                                + "ms\" attributeName=\"");
                if (k == 1) {
                  out.append("x\" from=\"" + s.changes.get(i).get(k) + "\" to=\"" + s.changes.get(i).get(k + 8)
                          + "\" fill=\"freeze\" />" + "\n");
                } else if (k == 2) {
                  out.append("y\" from=\"" + s.changes.get(i).get(k) + "\" to=\"" + s.changes.get(i).get(k + 8)
                          + "\" fill=\"freeze\" />" + "\n");
                } else if (k == 3) {
                  out.append("w\" from=\"" + s.changes.get(i).get(k) + "\" to=\"" + s.changes.get(i).get(k + 8)
                          + "\" fill=\"freeze\" />" + "\n");
                } else if (k == 4) {
                  out.append("h\" from=\"" + s.changes.get(i).get(k) + "\" to=\"" + s.changes.get(i).get(k + 8)
                          + "\" fill=\"freeze\" />" + "\n");
                } else if (k == 5) {
                  out.append("r\" from=\"" + s.changes.get(i).get(k) + "\" to=\"" + s.changes.get(i).get(k + 8)
                          + "\" fill=\"freeze\" />" + "\n");
                } else if (k == 6) {
                  out.append("g\" from=\"" + s.changes.get(i).get(k) + "\" to=\"" + s.changes.get(i).get(k + 8)
                          + "\" fill=\"freeze\" />" + "\n");
                } else if (k == 7) {
                  out.append("b\" from=\"" + s.changes.get(i).get(k) + "\" to=\"" + s.changes.get(i).get(k + 8)
                          + "\" fill=\"freeze\" />" + "\n");
                }
              }
            }
          }
          out.append("</rect>" + "\n");
        }
      } else if (s instanceof Ellipse) {
        out.append("<ellipse id=\"" + s.name + "\" cx=\"" + s.changes.get(0).get(1) + "\" cy=\""
                + s.changes.get(0).get(2) + "\" rx=\"" + (s.changes.get(0).get(3) / 2) + "\" ry=\"" + (
                s.changes.get(0).get(4) / 2) + "\" fill=\"rgb(" + s.changes.get(0).get(5) + "."
                + s.changes.get(0).get(6) + "." + s.changes.get(0).get(7)
                + ")\" visibility=\"visible\" >" + "\n");
        if (s.changes.size() > 0) {
          //for each change in the shape
          for (int j = 0; j < s.changes.size(); j++) {
            for (int f = 1; f < (s.changes.get(j).size() / 2); f++)
              if (!s.changes.get(j).get(f).equals(s.changes.get(j).get(f + 8))) {
                out.append(
                        "<animate attributeType=\"xml\" begin=\"" + (s.changes.get(j).get(0) * 1000) + "ms\" dur=\""
                                + ((s.changes.get(j).get(8) - s.changes.get(j).get(0)) * 1000)
                                + "ms\" attributeName=\"");
                if (f == 1) {
                  out.append("cx\" from=\"" + s.changes.get(j).get(f) + "\" to=\"" + s.changes.get(j).get(f + 8)
                          + "\" fill=\"freeze\" />" + "\n");
                } else if (f == 2) {
                  out.append("cy\" from=\"" + s.changes.get(j).get(f) + "\" to=\"" + s.changes.get(j).get(f + 8)
                          + "\" fill=\"freeze\" />" + "\n");
                } else if (f == 3) {
                  out.append("rx\" from=\"" + s.changes.get(j).get(f) + "\" to=\"" + s.changes.get(j).get(f + 8)
                          + "\" fill=\"freeze\" />" + "\n");
                } else if (f == 4) {
                  out.append("ry\" from=\"" + s.changes.get(j).get(f) + "\" to=\"" + s.changes.get(j).get(f + 8)
                          + "\" fill=\"freeze\" />" + "\n");
                } else if (f == 5) {
                  out.append("r\" from=\"" + s.changes.get(j).get(f) + "\" to=\"" + s.changes.get(j).get(f + 8)
                          + "\" fill=\"freeze\" />" + "\n");
                } else if (f == 6) {
                  out.append("g\" from=\"" + s.changes.get(j).get(f) + "\" to=\"" + s.changes.get(j).get(f + 8)
                          + "\" fill=\"freeze\" />" + "\n");
                } else if (f == 7) {
                  out.append("b\" from=\"" + s.changes.get(j).get(f) + "\" to=\"" + s.changes.get(j).get(f + 8)
                          + "\" fill=\"freeze\" />" + "\n");
                }
              }
          }
        }
        out.append("</ellipse>" + "\n");
      }
    }
    out.append("</svg>");
  }


}
