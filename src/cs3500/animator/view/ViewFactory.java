package cs3500.animator.view;

import cs3500.excellence.hw05.ExcellenceOperations;

/**
 * A factory for the different views. Used to figure out which view to create when setting one up
 * from the command line.
 */
public class ViewFactory {

  /**
   * Figure out which view is needed from the given string, and return it.
   *
   * @param viewString The view that needs to be created
   * @param model      The model to be used with the view
   * @return a new view of the correct type
   */
  public ExcellenceView getView(String viewString, ExcellenceOperations model) {
    switch (viewString) {
      case "text":
        return new ViewText(model);
      case "visual":
        return new ViewAnimation(model);
      case "svg":
        return new ViewSVG(model);
      default:
        return null;
    }
  }
}
