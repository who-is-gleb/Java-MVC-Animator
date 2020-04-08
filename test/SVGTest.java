import static org.junit.Assert.assertEquals;

import cs3500.animator.util.AnimationReader;
import cs3500.animator.view.ExcellenceView;
import cs3500.animator.view.ViewFactory;
import cs3500.excellence.hw05.ExcellenceModel.Builder;
import cs3500.excellence.hw05.ExcellenceOperations;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import org.junit.Test;

/**
 * Test the text view.
 */
public class TestSVG {



  // test that a file can be read and outputed into SVG format
  @Test
  public void testSVGView() throws IOException {

    //Set up the thing to read for tests
    ByteArrayOutputStream outArray = new ByteArrayOutputStream();
    PrintStream stream = new PrintStream(outArray);

    //Set up input to test
    BufferedReader inputBR = new BufferedReader(new FileReader(new File("src/smalldemo.txt")));
    ExcellenceOperations ourModel = new AnimationReader().parseFile(inputBR, new Builder());
    //Set up the view we are using with the factory and our constructed model
    ExcellenceView ourView = new ViewFactory().getView("svg", ourModel);
    //Give the view our output, and set it going!
    ourView.startView(stream);

    assertEquals("canvas 200 70 360 360\n"
            + "shape R rectangle\n"
            + "motion R 1 200 200 50 100 255 0 0 10 200 200 50 100 255 0 0\n"
            + "motion R 10 200 200 50 100 255 0 0 50 300 300 50 100 255 0 0\n"
            + "motion R 50 300 300 50 100 255 0 0 51 300 300 50 100 255 0 0\n"
            + "motion R 51 300 300 50 100 255 0 0 70 300 300 25 100 255 0 0\n"
            + "motion R 70 300 300 25 100 255 0 0 100 200 200 25 100 255 0 0\n"
            + "shape C ellipse\n"
            + "motion C 6 440 70 120 60 0 0 255 20 440 70 120 60 0 0 255\n"
            + "motion C 20 440 70 120 60 0 0 255 50 440 250 120 60 0 0 255\n"
            + "motion C 50 440 250 120 60 0 0 255 70 440 370 120 60 0 170 85\n"
            + "motion C 70 440 370 120 60 0 170 85 80 440 370 120 60 0 255 0\n"
            + "motion C 80 440 370 120 60 0 255 0 100 440 370 120 60 0 255 0\n", outArray.toString());
  }
}
