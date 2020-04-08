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

    assertEquals("<svg width=\"200\" height=\"360\" version=\"1.1\" xmlns=\"http://www.w3.org/2000/svg\">" + "\n" +
"<rect id=\"R\" x=\"200\" y=\"200\" width=\"50\" height=\"100\" fill=\"rgb(255.0.0)\" visibility=\"visible\" >" + "\n" +
"<animate attributeType=\"xml\" begin=\"10000ms\" dur=\"40000ms\" attributeName=\"x\" from=\"200\" to=\"300\" fill=\"freeze\" />" + "\n" +
"<animate attributeType=\"xml\" begin=\"10000ms\" dur=\"40000ms\" attributeName=\"y\" from=\"200\" to=\"300\" fill=\"freeze\" />" + "\n" +
"<animate attributeType=\"xml\" begin=\"51000ms\" dur=\"19000ms\" attributeName=\"w\" from=\"50\" to=\"25\" fill=\"freeze\" />" + "\n" +
"<animate attributeType=\"xml\" begin=\"70000ms\" dur=\"30000ms\" attributeName=\"x\" from=\"300\" to=\"200\" fill=\"freeze\" />" + "\n" +
"<animate attributeType=\"xml\" begin=\"70000ms\" dur=\"30000ms\" attributeName=\"y\" from=\"300\" to=\"200\" fill=\"freeze\" />" + "\n" +
"</rect>" + "\n" +
"<ellipse id=\"C\" cx=\"440\" cy=\"70\" rx=\"60\" ry=\"30\" fill=\"rgb(0.0.255)\" visibility=\"visible\" >" + "\n" +
"<animate attributeType=\"xml\" begin=\"20000ms\" dur=\"30000ms\" attributeName=\"cy\" from=\"70\" to=\"250\" fill=\"freeze\" />" + "\n" +
"<animate attributeType=\"xml\" begin=\"50000ms\" dur=\"20000ms\" attributeName=\"cy\" from=\"250\" to=\"370\" fill=\"freeze\" />" + "\n" +
"<animate attributeType=\"xml\" begin=\"50000ms\" dur=\"20000ms\" attributeName=\"g\" from=\"0\" to=\"170\" fill=\"freeze\" />" + "\n" +
"<animate attributeType=\"xml\" begin=\"50000ms\" dur=\"20000ms\" attributeName=\"b\" from=\"255\" to=\"85\" fill=\"freeze\" />" + "\n" +
"<animate attributeType=\"xml\" begin=\"70000ms\" dur=\"10000ms\" attributeName=\"g\" from=\"170\" to=\"255\" fill=\"freeze\" />" + "\n" +
"<animate attributeType=\"xml\" begin=\"70000ms\" dur=\"10000ms\" attributeName=\"b\" from=\"85\" to=\"0\" fill=\"freeze\" />" + "\n" +
"</ellipse>" + "\n" +
"</svg>", outArray.toString());
  }
}
