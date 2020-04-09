package cs3500.animator.controller;

import cs3500.animator.view.ExcellenceView;
import cs3500.excellence.hw05.Ellipse;
import cs3500.excellence.hw05.ExcellenceOperations;
import cs3500.excellence.hw05.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.PrintStream;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class EditController implements ExcellenceController, ActionListener, ListSelectionListener {

  private ExcellenceView view;
  private ExcellenceOperations model;
  private String currentShape;

  public EditController(ExcellenceOperations model, ExcellenceView view) {
    this.model = model;
    this.view = view;
  }

  @Override
  public void beginControl(PrintStream out) {
    this.view.setListener(this);
    this.view.startView(out);
    this.currentShape = "";
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
      case "addRect":
        model.addShape(new Rectangle(view.getNameField()));
        view.updateShapeNames();
        break;
      case "addEllipse":
        model.addShape(new Ellipse(view.getNameField()));
        view.updateShapeNames();
        break;
      case "removeShape":
        model.removeShape(currentShape);
        view.updateShapeNames();
        break;
      case "addKey":
        try {
          int[] args = view.getKeyframeArguments();
          model.addKeyframeToShape(currentShape, view.getTick(), args[0], args[1], args[2], args[3],
              args[4], args[5], args[6]);
        } catch (Exception e) {
        model.addKeyframeToShape(currentShape, view.getTick());
      }
      break;
      case "removeKey":
        model.removeKeyframeFromShape(currentShape, view.getTick());
        break;
    }
  }

  @Override
  public void valueChanged(ListSelectionEvent e) {
    currentShape = model.returnShapeList().get(e.getFirstIndex()).name;
  }
}
