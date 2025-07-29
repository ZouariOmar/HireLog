/**
 * SignUpController.java
 *
 * 'SignUp.fxml' Controller Class
 *
 * <p>NONE</p>
 *
 * @author @ZouariOmar (zouariomar20@gmail.com)
 * @version 1.0
 * @since 29/07/2025
 * @see https://github.com/ZouariOmar/HireLog/tree/main/project/src/test/java/com/mycompany/HireLog/controller/SignUpController.java
 */

package com.mycompany.HireLog.controller;

// Java core imports
import java.io.IOException;

// JavaFx imports
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class SignUpController {

  @FXML // fx:id="banner"
  private ImageView banner; // Value injected by FXMLLoader
  //
  @FXML // fx:id="email"
  private TextField email; // Value injected by FXMLLoader

  @FXML // fx:id="logo"
  private ImageView logo; // Value injected by FXMLLoader

  @FXML // fx:id="name"
  private TextField name; // Value injected by FXMLLoader

  @FXML // fx:id="password"
  private PasswordField password; // Value injected by FXMLLoader

  @FXML // fx:id="prename"
  private TextField prename; // Value injected by FXMLLoader

  @FXML // fx:id="signUpBox"
  private AnchorPane signUpBox; // Value injected by FXMLLoader

  @FXML // fx:id="signUpBtn"
  private Button signUpBtn; // Value injected by FXMLLoader

  /**
   * Slot connected to `sginInHyperlink` signals
   *
   * <p>
   * This method is marked with &#64;FXML so it can be invoked by the FXML loader.
   * </p>
   *
   * @throws IOException For `FXMLLoader.load()` method
   *
   * @param event {@code ActionEvent}
   */
  @FXML
  private void onSginInHyperlinkAction(ActionEvent event) throws IOException {
    Parent root = FXMLLoader.load(getClass().getResource("/fxml/Login.fxml"));
    // Get the current stage (window) from the event source and set the new scene
    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    stage.setTitle("HireLog - Login");
    stage.setScene(new Scene(root));
    stage.show();
  }

  @FXML
  void onSignUpAction(ActionEvent event) {

  }

  @FXML // This method is called by the FXMLLoader when initialization is complete
  private void initialize() {
    assert banner != null : "fx:id=\"banner\" was not injected: check your FXML file 'SignUp.fxml'.";
    assert email != null : "fx:id=\"email\" was not injected: check your FXML file 'SignUp.fxml'.";
    assert logo != null : "fx:id=\"logo\" was not injected: check your FXML file 'SignUp.fxml'.";
    assert name != null : "fx:id=\"name\" was not injected: check your FXML file 'SignUp.fxml'.";
    assert password != null : "fx:id=\"password\" was not injected: check your FXML file 'SignUp.fxml'.";
    assert prename != null : "fx:id=\"prename\" was not injected: check your FXML file 'SignUp.fxml'.";
    assert signUpBox != null : "fx:id=\"signUpBox\" was not injected: check your FXML file 'SignUp.fxml'.";
    assert signUpBtn != null : "fx:id=\"signUpBtn\" was not injected: check your FXML file 'SignUp.fxml'.";

    // Set images
    logo.setImage(new Image(getClass().getResource("/assets/logo-1.png").toExternalForm()));
    banner.setImage(new Image(getClass().getResource("/assets/banner.png").toExternalForm()));
  }
}
