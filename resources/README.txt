------------------
Changes from last assignment:

- Fixed Java style for large portions of the code, notably in declareShape in the Builder by
  removing a switch statement

- Added toggleLooping(), togglePlayback(), restart(), increaseRate(), decreaseRate(), and
  setListener() to the view interface. These are needed by the new view and controller to make
  interacting with the view possible.

- Added two versions of addKeyframeToShape() and removeKeyframeFromShape() to our model and model
  interface. This is so that we can store and modify keyframes in the editor view.

- Also reworked Animation view's panel to use the new keyframe system so that the edit view can
  reuse the same panel with no issues.

------------------
