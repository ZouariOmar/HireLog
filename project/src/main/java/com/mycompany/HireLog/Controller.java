package com.mycompany.HireLog;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class Controller {

  @FXML
  private Button btn;

  @FXML
  public void onBtnClicked(ActionEvent event) {
    btn.setText("Button Clicked!");
  }
}
