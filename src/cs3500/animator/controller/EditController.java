package cs3500.animator.controller;

import cs3500.animator.view.ExcellenceView;
import cs3500.excellence.hw05.ExcellenceOperations;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.PrintStream;

public class EditController implements ExcellenceController, ActionListener {

  private ExcellenceView view;

  public EditController(ExcellenceOperations model, ExcellenceView view) {
    this.view = view;
  }

  @Override
  public void beginControl(PrintStream out) {
    this.view.setListener(this);
    this.view.startView(out);
  }

  @Override
  public void actionPerformed(ActionEvent event) {
    switch (event.getActionCommand()) {
      case "restart":
        view.restart();
        break;
      case "playPause":
        view.togglePlayback();
        break;
      case "toggleLoop":
        view.toggleLooping();
        break;
      case "speedDown":
        view.decreaseRate();
        break;
      case "speedUp":
        view.increaseRate();
        break;
      case "forwardFrame":
        view.forwardTick();
        break;
      case "backFrame":
        view.backTick();
        break;
    }
  }
}
