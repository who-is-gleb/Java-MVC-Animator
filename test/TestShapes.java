import static org.junit.Assert.assertEquals;

import cs3500.excellence.hw05.Rectangle;
import cs3500.excellence.hw05.Shape;
import org.junit.Test;

/**
 * Test class for our Shapes: unit tests to ensure that everything can be generated correctly
 * and works as expected.
 */
public class TestShapes {

  Shape rect1 = new Rectangle("r1");

  //test that a rectangle can be made and have an empty changes list returned
  @Test
  public void testCreateRectangle() {
    assertEquals("Rectangle r1\n"
        + "No listed changes\n", rect1.getChanges());
  }

  //tests adding changes to both the front and back of the changes list of a shape
  @Test
  public void testAddChanges() {
    //add one in the middle of time
    rect1.addChange(5, 5, 5, 5, 5, 5, 5, 5, 10, 10, 10, 10, 10, 10, 10, 10);
    //add to the front
    rect1.addChange(1, 1, 1, 1, 1, 1, 1, 1, 4, 5, 5, 5, 5, 5, 5, 5);
    //add to the back
    rect1.addChange(11, 10, 10, 10, 10, 10, 10, 10, 15, 15, 15, 15, 15, 15, 15, 15);
    assertEquals("Rectangle r1\n"
            + "Change:[1, 1, 1, 1, 1, 1, 1, 1, 4, 5, 5, 5, 5, 5, 5, 5]\n"
            + "Change:[5, 5, 5, 5, 5, 5, 5, 5, 10, 10, 10, 10, 10, 10, 10, 10]\n"
            + "Change:[11, 10, 10, 10, 10, 10, 10, 10, 15, 15, 15, 15, 15, 15, 15, 15]\n",
        rect1.getChanges());
  }

}
