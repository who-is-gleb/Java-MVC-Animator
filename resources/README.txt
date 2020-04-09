------------------
Changes from last assignment:

- Fixed Java style for large portions of the code, notably in declareShape in the Builder by
  removing a switch statement

- Added toggleLooping(), togglePlayback(), restart(), increaseRate(), decreaseRate(), forwardTick(),
  backTick(), setListener(), getNameField(), and getKeyframeArguments() to the view interface.
  These are needed by the new view and controller to make interacting with the view possible.

- Added a second movement storage system to shapes, based on keyframes

  - Added two versions of addKeyframeToShape() and one version of removeKeyframeFromShape() to our
    model and model interface. This is so that we can store and modify keyframes in the editor view.

  - Also rewrote the Animation view's panel to use the new keyframe system so that the edit view can
    reuse the same panel with no issues.

- Finished the SVG view that was unfinished last time

------------------
Our design:

- We have a controller that handles inputs on the edit view, and also sets the given view going.

- The controller implements ActionListener and ListSelectionListener and waits for actionCommands

  - We only use buttons and text fields, so we don't have to bother with a keyboard or mouse handler
    since we were pressed for time

- Users can select a shape from a list, navigate to the frame they want to edit, then input new
  values. Confirming an edit without adding all needed values creates a new keyframe based on
  tweening between the current ones.

