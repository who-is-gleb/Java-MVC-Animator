------------------
Changes from last assignment:

- Added a name field to all shapes, since the AnimationBuilder expects shapes to be named.

- Changed model to look for names of shapes, rather than look for shapes based on index. To do this,
we also needed to change how we constructed shapes to avoid errors.

- Updated the model interface and model implementation to store information on the canvas.

- Added a setCanvas method to the model interface and model that sets up the canvas.

- Added a returnShapeList() method to the model interface and model so that the views can get access
to a copy of the shapeArrayList that the model stores in order to display its contents.

- Modified Shapes to check for time properly, as they were making sure time in movements didn't
overlap at all, rather than making sure movements had shared start and end points.

- Modified and removed some tests to reflect those changes

- Added the Builder class inside of our model, since that's where the assignment told us to put it

------------------
Our design for the views:

- We have a central Interface that all views share, called ExcellenceView

  - It has three methods: void setTickRate(int rate), void refresh(), and void go(PrintStream out)

    - void setTickRate(int rate) is called from the main method after constructing a view. It's used
      to set the framerate for the animation. It is not used in the text or svg views, since the
      speed is not needed.

    - void refresh() is called to refresh the view, which is only used in the visual view to refresh
      the panel.

    - void go(PrintStream out) is called from the main method after constructing a view. Used to
      start the actual process of viewing the view. In the text and SVG views this is where the
      building of the output happens, in the visual view it's where the clock is started and where
      refresh() is called.

- All our views take a ExcellenceOperations (the interface of out model) in their constructor

  - In these constructors, the views call returnShapeList() and getCanvasInfo() in order to get
    information about what to draw from the model they have been passed.

- Our main Excellence program does several things, in this order:

  - We read and parse the command line arguments, and throw errors and set defaults as needed.

  - Then, we convert the input file into something that we can pass to the AnimationReader

  - We then use the AnimationReader to parse the input file using our builder, which returns a model

  - We then construct a View of the appropriate type by passing the ViewFactory the view string from
    the command line, as well as the model we just built from the AnimationReader.

  - Finally, we pass the newly constructed view the tickrate from the command line, and then call
    go() with the output specified in the command line to start the view. The views handle the rest
    of the program's functions.