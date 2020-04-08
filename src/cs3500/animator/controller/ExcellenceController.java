package cs3500.animator.controller;

import java.io.PrintStream;

/**
 * The controller interface for the Excellence animation program.
 */
public interface ExcellenceController {

    /**
     * Start the program by giving the controller control.
     *
     * @param out the PrintStream we are sending the output to
     */
    void beginControl(PrintStream out);

}
