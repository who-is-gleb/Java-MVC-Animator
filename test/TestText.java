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
public class TestText {

  //test that we can read a file, and output the expected results
  @Test
  public void testTextView() throws IOException {

    //Set up the thing to read for tests
    ByteArrayOutputStream outArray = new ByteArrayOutputStream();
    PrintStream stream = new PrintStream(outArray);

    //Set up input to test
    BufferedReader inputBR = new BufferedReader(new FileReader(new File("src/smalldemo.txt")));
    ExcellenceOperations ourModel = new AnimationReader().parseFile(inputBR, new Builder());
    //Set up the view we are using with the factory and our constructed model
    ExcellenceView ourView = new ViewFactory().getView("text", ourModel);
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

  //test reading a different,larger file
  @Test
  public void testBiggerTextView() throws IOException {

    //Set up the thing to read for tests
    ByteArrayOutputStream outArray = new ByteArrayOutputStream();
    PrintStream stream = new PrintStream(outArray);

    //Set up input to test
    BufferedReader inputBR = new BufferedReader(new FileReader(new File("src/toh-3.txt")));
    ExcellenceOperations ourModel = new AnimationReader().parseFile(inputBR, new Builder());
    //Set up the view we are using with the factory and our constructed model
    ExcellenceView ourView = new ViewFactory().getView("text", ourModel);
    //Give the view our output, and set it going!
    ourView.startView(stream);

    assertEquals("canvas 145 50 410 220\n"
            + "shape disk1 rectangle\n"
            + "motion disk1 1 190 180 20 30 0 49 90 1 190 180 20 30 0 49 90\n"
            + "motion disk1 1 190 180 20 30 0 49 90 25 190 180 20 30 0 49 90\n"
            + "motion disk1 25 190 180 20 30 0 49 90 35 190 50 20 30 0 49 90\n"
            + "motion disk1 35 190 50 20 30 0 49 90 36 190 50 20 30 0 49 90\n"
            + "motion disk1 36 190 50 20 30 0 49 90 46 490 50 20 30 0 49 90\n"
            + "motion disk1 46 490 50 20 30 0 49 90 47 490 50 20 30 0 49 90\n"
            + "motion disk1 47 490 50 20 30 0 49 90 57 490 240 20 30 0 49 90\n"
            + "motion disk1 57 490 240 20 30 0 49 90 89 490 240 20 30 0 49 90\n"
            + "motion disk1 89 490 240 20 30 0 49 90 99 490 50 20 30 0 49 90\n"
            + "motion disk1 99 490 50 20 30 0 49 90 100 490 50 20 30 0 49 90\n"
            + "motion disk1 100 490 50 20 30 0 49 90 110 340 50 20 30 0 49 90\n"
            + "motion disk1 110 340 50 20 30 0 49 90 111 340 50 20 30 0 49 90\n"
            + "motion disk1 111 340 50 20 30 0 49 90 121 340 210 20 30 0 49 90\n"
            + "motion disk1 121 340 210 20 30 0 49 90 153 340 210 20 30 0 49 90\n"
            + "motion disk1 153 340 210 20 30 0 49 90 163 340 50 20 30 0 49 90\n"
            + "motion disk1 163 340 50 20 30 0 49 90 164 340 50 20 30 0 49 90\n"
            + "motion disk1 164 340 50 20 30 0 49 90 174 190 50 20 30 0 49 90\n"
            + "motion disk1 174 190 50 20 30 0 49 90 175 190 50 20 30 0 49 90\n"
            + "motion disk1 175 190 50 20 30 0 49 90 185 190 240 20 30 0 49 90\n"
            + "motion disk1 185 190 240 20 30 0 49 90 217 190 240 20 30 0 49 90\n"
            + "motion disk1 217 190 240 20 30 0 49 90 227 190 50 20 30 0 49 90\n"
            + "motion disk1 227 190 50 20 30 0 49 90 228 190 50 20 30 0 49 90\n"
            + "motion disk1 228 190 50 20 30 0 49 90 238 490 50 20 30 0 49 90\n"
            + "motion disk1 238 490 50 20 30 0 49 90 239 490 50 20 30 0 49 90\n"
            + "motion disk1 239 490 50 20 30 0 49 90 249 490 180 20 30 0 49 90\n"
            + "motion disk1 249 490 180 20 30 0 49 90 257 490 180 20 30 0 255 0\n"
            + "motion disk1 257 490 180 20 30 0 255 0 302 490 180 20 30 0 255 0\n"
            + "shape disk2 rectangle\n"
            + "motion disk2 1 167 210 65 30 6 247 41 1 167 210 65 30 6 247 41\n"
            + "motion disk2 1 167 210 65 30 6 247 41 57 167 210 65 30 6 247 41\n"
            + "motion disk2 57 167 210 65 30 6 247 41 67 167 50 65 30 6 247 41\n"
            + "motion disk2 67 167 50 65 30 6 247 41 68 167 50 65 30 6 247 41\n"
            + "motion disk2 68 167 50 65 30 6 247 41 78 317 50 65 30 6 247 41\n"
            + "motion disk2 78 317 50 65 30 6 247 41 79 317 50 65 30 6 247 41\n"
            + "motion disk2 79 317 50 65 30 6 247 41 89 317 240 65 30 6 247 41\n"
            + "motion disk2 89 317 240 65 30 6 247 41 185 317 240 65 30 6 247 41\n"
            + "motion disk2 185 317 240 65 30 6 247 41 195 317 50 65 30 6 247 41\n"
            + "motion disk2 195 317 50 65 30 6 247 41 196 317 50 65 30 6 247 41\n"
            + "motion disk2 196 317 50 65 30 6 247 41 206 467 50 65 30 6 247 41\n"
            + "motion disk2 206 467 50 65 30 6 247 41 207 467 50 65 30 6 247 41\n"
            + "motion disk2 207 467 50 65 30 6 247 41 217 467 210 65 30 6 247 41\n"
            + "motion disk2 217 467 210 65 30 6 247 41 225 467 210 65 30 0 255 0\n"
            + "motion disk2 225 467 210 65 30 0 255 0 302 467 210 65 30 0 255 0\n"
            + "shape disk3 rectangle\n"
            + "motion disk3 1 145 240 110 30 11 45 175 1 145 240 110 30 11 45 175\n"
            + "motion disk3 1 145 240 110 30 11 45 175 121 145 240 110 30 11 45 175\n"
            + "motion disk3 121 145 240 110 30 11 45 175 131 145 50 110 30 11 45 175\n"
            + "motion disk3 131 145 50 110 30 11 45 175 132 145 50 110 30 11 45 175\n"
            + "motion disk3 132 145 50 110 30 11 45 175 142 445 50 110 30 11 45 175\n"
            + "motion disk3 142 445 50 110 30 11 45 175 143 445 50 110 30 11 45 175\n"
            + "motion disk3 143 445 50 110 30 11 45 175 153 445 240 110 30 11 45 175\n"
            + "motion disk3 153 445 240 110 30 11 45 175 161 445 240 110 30 0 255 0\n"
            + "motion disk3 161 445 240 110 30 0 255 0 302 445 240 110 30 0 255 0\n",
        outArray.toString());
  }
}
