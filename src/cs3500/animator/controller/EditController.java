package cs3500.animator.controller;

import cs3500.animator.view.ExcellenceView;
import cs3500.excellence.hw05.ExcellenceOperations;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditController implements ExcellenceController, ActionListener {

  private ExcellenceOperations model;
  private ExcellenceView view;

  public EditController (ExcellenceOperations model, ExcellenceView view) {
    this.model = model;
    this.view = view;
  }

  @Override
  public void beginControl() {
    this.view.setListener(this);
    this.view.startView(System.out);
  }

  @Override
  public void actionPerformed(ActionEvent event) {
    switch (event.getActionCommand()) {
      case "restart": view.restart();
      case "playPause": view.togglePlayback();
      case "toggleLoop": view.toggleLooping();
      case "speedDown": view.decreaseRate();
      case "speedUp": view.increaseRate();
    }
  }
}
