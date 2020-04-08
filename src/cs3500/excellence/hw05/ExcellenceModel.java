package cs3500.excellence.hw05;

import cs3500.animator.util.AnimationBuilder;
import java.util.ArrayList;

/**
 * A model that represents an animation created with our Excellence animator. Stores information
 * about shapes, which each hold their own list of changes. Also stores information about the
 * animation's canvas, such as height, width, and how the X and Y values on the canvas relate to
 * that.
 */
public class ExcellenceModel implements ExcellenceOperations {

  ArrayList<Shape> shapeArrayList = new ArrayList<>();
  int x = 0;
  int y = 0;
  int width = 100;
  int height = 100;

  @Override
  public void setCanvas(int x, int y, int width, int height) {
    this.x = x;
    this.y = y;
    this.width = width;
    this.height = height;
  }

  @Override
  public int[] getCanvasInfo() {
    return new int[]{x, y, width, height};
  }

  @Override
  public void addShape(Shape s) {
    shapeArrayList.add(s);
  }

  @Override
  public void removeShape(String name) {

    for (Shape s : shapeArrayList) {
      if (s.name.equals(name)) {
        shapeArrayList.remove(s);
        return;
      }
    }
    throw new IllegalArgumentException("There's no shape named " + name + "!");
  }

  @Override
  public void addKeyframeToShape(String name, int tick) {
    for (Shape s : shapeArrayList) {
      if (s.name.equals(name)) {
        s.setKeyframe(tick);
        return;
      }
    }
    throw new IllegalArgumentException("There's no shape named " + name + "!");
  }

  @Override
  public void addKeyframeToShape(String name, int tick, int x, int y, int width, int height,
      int red, int green, int blue) {
    for (Shape s : shapeArrayList) {
      if (s.name.equals(name)) {
        s.setKeyframe(tick, x, y, width, height, red, green, blue);
        return;
      }
    }
    throw new IllegalArgumentException("There's no shape named " + name + "!");
  }

  @Override
  public void removeKeyframeFromShape(String name, int tick) {
    for (Shape s : shapeArrayList) {
      if (s.name.equals(name)) {
        s.removeKeyframe(tick);
        return;
      }
    }
    throw new IllegalArgumentException("There's no shape named " + name + "!");
  }

  @Override
  public void addChangeToShape(String name, int t1, int x1, int y1, int w1, int h1, int r1,
      int g1,
      int b1, int t2, int x2, int y2, int w2, int h2, int r2, int g2, int b2) {
    for (Shape s : shapeArrayList) {
      if (s.name.equals(name)) {
        shapeArrayList.get(shapeArrayList.indexOf(s))
            .addChange(t1, x1, y1, w1, h1, r1, g1, b1, t2, x2, y2, w2, h2, r2, g2, b2);
        return;
      }
    }
    throw new IllegalArgumentException("There's no shape named " + name + "!");
  }

  @Override
  public void removeChangeFromShape(String name, boolean front) {

    for (Shape s : shapeArrayList) {
      if (s.name.equals(name)) {
        if (front) {
          shapeArrayList.get(shapeArrayList.indexOf(s))
              .removeFirstChange();
        } else {
          shapeArrayList.get(shapeArrayList.indexOf(s))
              .removeLastChange();
        }
        return;
      }
    }
    throw new IllegalArgumentException("There's no shape named " + name + "!");
  }

  @Override
  public String displayLog() {
    StringBuilder output = new StringBuilder();
    for (Shape s : shapeArrayList) {
      output.append(s.getChanges());
    }
    return output.toString();
  }

  @Override
  public ArrayList<Shape> returnShapeList() {
    return new ArrayList<>(shapeArrayList);
  }

  /**
   * The implementation of the AnimationBuilder interface. Used to translate inputs from
   * AnimationReader into a form that our model can understand! It's nested here because that's how
   * it is in the assignment, though we're not entirely sure why considering it doesn't seem to have
   * any apparent advantages for our implementation.
   */
  public static final class Builder implements AnimationBuilder<ExcellenceOperations> {

    ExcellenceOperations model = new ExcellenceModel();

    @Override
    public ExcellenceOperations build() {
      return model;
    }

    @Override
    public AnimationBuilder<ExcellenceOperations> setBounds(int x, int y, int width, int height) {
      model.setCanvas(x, y, width, height);
      return this;
    }

    @Override
    public AnimationBuilder<ExcellenceOperations> declareShape(String name, String type) {
      if (type.equals("rectangle")) {
        model.addShape(new Rectangle(name));
      } else if (type.equals("ellipse")) {
        model.addShape(new Ellipse(name));
      }
      return this;
    }

    @Override
    public AnimationBuilder<ExcellenceOperations> addMotion(String name, int t1, int x1, int y1,
        int w1, int h1, int r1, int g1, int b1, int t2, int x2, int y2, int w2, int h2, int r2,
        int g2, int b2) {
      model.addChangeToShape(name, t1, x1, y1, w1, h1, r1, g1, b1, t2, x2, y2, w2, h2, r2, g2, b2);
      //also add all the changes as keyframes, since that what we really care about
      model.addKeyframeToShape(name, t1, x1, y1, w1, h1, r1, g1, b1);
      model.addKeyframeToShape(name, t2, x2, y2, w2, h2, r2, g2, b2);
      return this;
    }

    @Override
    public AnimationBuilder<ExcellenceOperations> addKeyframe(String name, int t, int x, int y,
        int w, int h, int r, int g, int b) {
      model.addKeyframeToShape(name, t, x, y, w, h, r, g, b);
      return this;
    }
  }
}