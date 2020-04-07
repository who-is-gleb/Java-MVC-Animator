package cs3500.animator;

import cs3500.animator.controller.EditController;
import cs3500.animator.controller.ExcellenceController;
import cs3500.animator.util.AnimationReader;
import cs3500.animator.view.ExcellenceView;
import cs3500.animator.view.ViewFactory;
import cs3500.excellence.hw05.ExcellenceModel.Builder;
import cs3500.excellence.hw05.ExcellenceOperations;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * Our main class for running the Animator. Takes command line arguments: -in (filepath) (required)
 * -out (filepath) -view (text, visual, svg)  (required) -speed (integer)
 */
public class Excellence {

  private static File input;
  private static PrintStream out = System.out;
  private static String view;
  private static int speed = 1;

  /**
   * Our main method for running the Animator. Takes command line arguments: -in (filepath)
   * (required) -out (filepath) -view (text, visual, svg)  (required) -speed (integer)
   *
   * @param args string of arguments, detailed above
   */
  public static void main(String[] args) throws IOException {

    //parse the command line arguments
    for (int i = 0; i < args.length; i++) {
      if (args[i].startsWith("-")) {
        try {
          switch (args[i]) {
            case "-in":
              input = new File(args[i + 1]);
              break;
            case "-out":
              out = new PrintStream(args[i + 1]);
              break;
            case "-view":
              view = args[i + 1];
              break;
            case "-speed":
              speed = Integer.parseInt(args[i + 1]);
              break;
            default: //do nothing
          }
        } catch (NullPointerException e) {
          //throw error for missing an argument
          throwErrorWindow("Arguments Uneven",
              "There's an odd number of command line arguments, something is wrong!");
        } catch (NumberFormatException e) {
          //throw error for speed not being a number
          throwErrorWindow("NaN", "Speed must be a number!");
        } catch (IllegalArgumentException e) {
          //throw error for an invalid input
          throwErrorWindow("Input Error", "You put in something I can't read!");
        } catch (FileNotFoundException e) {
          //throw an error for not finding the file
          throwErrorWindow("File Not Found", "Can't find the specified input file!");
        }
      }
    }

    if (input == null || view == null) {
      //throw error for missing input or view!
      throwErrorWindow("Missing Argument", "You didn't provide either an input or a view!");
    }

    //Make the input file into something readable
    BufferedReader inputBR = new BufferedReader(new FileReader(input));

    //returns our model, with the input file imported
    ExcellenceOperations ourModel = new AnimationReader().parseFile(inputBR, new Builder());

    //Set up the view we are using with the factory and our constructed model
    ExcellenceView ourView = new ViewFactory().getView(view, ourModel);

    //Set the tickrate for the view
    ourView.setTickRate(speed);

    //Give the view the output, and set it going! The view takes over from here.
    //ourView.startView(out);

    ExcellenceController ourController = new EditController(ourModel, ourView);
    ourController.beginControl();
  }

  /**
   * Helper used to show error windows when command line parameters are missing.
   *
   * @param title   The title of the error window
   * @param message The message shown in the error window
   */
  public static void throwErrorWindow(String title, String message) {

    //Set up the error window
    JFrame frame = new JFrame();
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.pack();
    frame.setVisible(true);

    //Show the error, then exit
    JOptionPane.showMessageDialog(frame, message, title, JOptionPane.ERROR_MESSAGE);
    System.exit(0);
  }
}
