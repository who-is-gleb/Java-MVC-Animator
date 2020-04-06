import static org.junit.Assert.assertEquals;

import cs3500.excellence.hw05.Ellipse;
import cs3500.excellence.hw05.ExcellenceModel;
import cs3500.excellence.hw05.Rectangle;
import org.junit.Test;

/**
 * Test class for our ExcellenceModel: unit tests to ensure that everything can be generated
 * correctly and works as expected.
 */
public class TestExcellenceModel {

  ExcellenceModel m1 = new ExcellenceModel();

  //Tests creating a model
  @Test
  public void testCreateModel() {
    assertEquals("", m1.displayLog());
  }

  //Tests adding shapes to the model
  @Test
  public void testAddShapes() {
    m1.addShape(new Rectangle("r1"));
    m1.addShape(new Ellipse("e1"));
    assertEquals("Rectangle r1\n"
        + "No listed changes\n"
        + "Ellipse e1\n"
        + "No listed changes\n", m1.displayLog());
  }

  //Tests removing shapes from the model
  @Test
  public void testRemoveShapes() {
    m1.addShape(new Rectangle("r1"));
    m1.addShape(new Ellipse("e1"));
    m1.addShape(new Rectangle("r2"));
    m1.removeShape("e1");
    assertEquals("Rectangle r1\n"
        + "No listed changes\n"
        + "Rectangle r2\n"
        + "No listed changes\n", m1.displayLog());
  }

  //Tests adding changes to shapes in a model
  @Test
  public void testAddChangesToShape() {
    m1.addShape(new Rectangle("r1"));
    m1.addShape(new Ellipse("e1"));
    m1.addShape(new Rectangle("r2"));
    m1.addChangeToShape("e1", 5, 5, 5, 5, 5, 5, 5, 5, 10, 10, 10, 10, 10, 10, 10, 10);
    m1.addChangeToShape("e1", 1, 1, 1, 1, 1, 1, 1, 1, 5, 5, 5, 5, 5, 5, 5, 5);
    m1.addChangeToShape("e1", 10, 10, 10, 10, 10, 10, 10, 10, 15, 15, 15, 15, 15, 15, 15, 15);
    m1.addChangeToShape("r2", 1, 1, 1, 1, 1, 1, 1, 1, 4, 5, 5, 5, 5, 5, 5, 5);
    assertEquals("Rectangle r1\n"
        + "No listed changes\n"
        + "Ellipse e1\n"
        + "Change:[1, 1, 1, 1, 1, 1, 1, 1, 5, 5, 5, 5, 5, 5, 5, 5]\n"
        + "Change:[5, 5, 5, 5, 5, 5, 5, 5, 10, 10, 10, 10, 10, 10, 10, 10]\n"
        + "Change:[10, 10, 10, 10, 10, 10, 10, 10, 15, 15, 15, 15, 15, 15, 15, 15]\n"
        + "Rectangle r2\n"
        + "Change:[1, 1, 1, 1, 1, 1, 1, 1, 4, 5, 5, 5, 5, 5, 5, 5]\n", m1.displayLog());
  }

  //Tests removing changes from shapes in a model, from both the start of the changes list and the
  // end of the changes list
  @Test
  public void testRemoveChangesToShape() {
    m1.addShape(new Rectangle("r1"));
    m1.addChangeToShape("r1", 5, 5, 5, 5, 5, 5, 5, 5, 10, 10, 10, 10, 10, 10, 10, 10);
    m1.addChangeToShape("r1", 1, 1, 1, 1, 1, 1, 1, 1, 5, 5, 5, 5, 5, 5, 5, 5);
    m1.addChangeToShape("r1", 10, 10, 10, 10, 10, 10, 10, 10, 15, 15, 15, 15, 15, 15, 15, 15);
    //check that everything was added right
    assertEquals("Rectangle r1\n"
            + "Change:[1, 1, 1, 1, 1, 1, 1, 1, 5, 5, 5, 5, 5, 5, 5, 5]\n"
            + "Change:[5, 5, 5, 5, 5, 5, 5, 5, 10, 10, 10, 10, 10, 10, 10, 10]\n"
            + "Change:[10, 10, 10, 10, 10, 10, 10, 10, 15, 15, 15, 15, 15, 15, 15, 15]\n",
        m1.displayLog());
    //remove the first change
    m1.removeChangeFromShape("r1", true);
    assertEquals("Rectangle r1\n"
            + "Change:[5, 5, 5, 5, 5, 5, 5, 5, 10, 10, 10, 10, 10, 10, 10, 10]\n"
            + "Change:[10, 10, 10, 10, 10, 10, 10, 10, 15, 15, 15, 15, 15, 15, 15, 15]\n",
        m1.displayLog());
    //remove the last change
    m1.removeChangeFromShape("r1", false);
    assertEquals("Rectangle r1\n"
        + "Change:[5, 5, 5, 5, 5, 5, 5, 5, 10, 10, 10, 10, 10, 10, 10, 10]\n", m1.displayLog());
  }

  //Tests that trying to add a change that overlaps another change fails
  @Test(expected = IllegalArgumentException.class)
  public void testOverlappingTime() {
    m1.addShape(new Rectangle("r1"));
    m1.addChangeToShape("r1", 5, 5, 5, 5, 5, 5, 5, 5, 10, 10, 10, 10, 10, 10, 10, 10);
    m1.addChangeToShape("r1", 1, 1, 1, 1, 1, 1, 1, 1, 8, 5, 5, 5, 5, 5, 5, 5);
  }

  //Tests that trying to add a change that fully covers the time frame of another change fails
  @Test(expected = IllegalArgumentException.class)
  public void testFullyCoveringTime() {
    m1.addShape(new Rectangle("r1"));
    m1.addChangeToShape("r1", 5, 5, 5, 5, 5, 5, 5, 5, 10, 10, 10, 10, 10, 10, 10, 10);
    m1.addChangeToShape("r1", 1, 1, 1, 1, 1, 1, 1, 1, 12, 5, 5, 5, 5, 5, 5, 5);
  }

  //Tests that trying to add a change that overlaps another change fails, but when adding it to the
  // end of the changes list this time
  @Test(expected = IllegalArgumentException.class)
  public void testOverlappingTimeEnd() {
    m1.addShape(new Rectangle("r1"));
    m1.addChangeToShape("r1", 5, 5, 5, 5, 5, 5, 5, 5, 10, 10, 10, 10, 10, 10, 10, 10);
    m1.addChangeToShape("r1", 8, 10, 10, 10, 10, 10, 10, 10, 50, 5, 5, 5, 5, 5, 5, 5);
  }

  //Tests that trying to add a change that has mismatched values fails
  @Test(expected = IllegalArgumentException.class)
  public void testMismatchValue() {
    m1.addShape(new Rectangle("r1"));
    m1.addChangeToShape("r1", 5, 5, 5, 5, 5, 5, 5, 5, 10, 10, 10, 10, 10, 10, 10, 10);
    m1.addChangeToShape("r1", 1, 1, 1, 1, 1, 1, 1, 1, 4, 5, 5, 5, 6, 5, 5, 5);
  }

  //Tests that trying to add a change that has mismatched values fails, when adding it to the end
  // of the changes list
  @Test(expected = IllegalArgumentException.class)
  public void testMismatchValueEnd() {
    m1.addShape(new Rectangle("r1"));
    m1.addChangeToShape("r1", 5, 5, 5, 5, 5, 5, 5, 5, 10, 10, 10, 10, 10, 10, 10, 10);
    m1.addChangeToShape("r1", 11, 1, 1, 1, 1, 1, 1, 1, 15, 5, 5, 5, 5, 5, 5, 5);
  }

  //Tests that trying to add a change that has it's time values backwards fails
  @Test(expected = IllegalArgumentException.class)
  public void testTimeBackwards() {
    m1.addShape(new Rectangle("r1"));
    m1.addChangeToShape("r1", 5, 5, 5, 5, 5, 5, 5, 5, 10, 10, 10, 10, 10, 10, 10, 10);
    m1.addChangeToShape("r1", 11, 10, 10, 10, 10, 10, 10, 10, 2, 5, 5, 5, 5, 5, 5, 5);
  }
}