/**
 * LoginController.java
 *
 * The controller of `Login.fxml` view
 *
 * @author @ZouariOmar (zouariomar20@gmail.com)
 * @version 1.0
 * @since 27/07/2025
 * @see https://github.com/ZouariOmar/HireLog/tree/main/project/src/test/java/com/mycompany/HireLog/controller/LoginController.java
 */

// `LoginController` pkg name
package com.mycompany.HireLog.controller;

// Java core imports
import java.io.IOException;

// JavaFx imports
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Hyperlink;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.event.ActionEvent;

// `log4j` java imports
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

// `util` Cutom imports
import com.mycompany.HireLog.util.*;

public class LoginController {
  private static final Logger _LOGGER = LogManager.getLogger();

  @FXML // fx:id="banner"
  private ImageView banner; // Value injected by FXMLLoader

  @FXML // fx:id="login"
  private Button login; // Value injected by FXMLLoader

  @FXML // fx:id="logo"
  private ImageView logo; // Value injected by FXMLLoader

  @FXML // fx:id="password"
  private PasswordField password; // Value injected by FXMLLoader

  @FXML // fx:id="sginUpHyperlink"
  private Hyperlink sginUpHyperlink; // Value injected by FXMLLoader

  @FXML // fx:id="username"
  private TextField username; // Value injected by FXMLLoader

  @FXML // fx:id="status"
  private Label status; // Value injected by FXMLLoader

  /**
   * Slot connected to `login` signals
   *
   * <p>
   * This method is marked with &#64;FXML so it can be invoked by the FXML loader.
   * </p>
   *
   * @param event {@code ActionEvent}
   *
   * @see https://openjfx.io/javadoc/22/javafx.fxml/javafx/fxml/doc-files/introduction_to_fxml.html
   */
  @FXML
  private void onLoginAction(ActionEvent event) throws IOException {
    final String enterdUsername = username.getText(), eneterdPassword = password.getText();

    if (enterdUsername.isEmpty()
        || enterdUsername.isBlank()) { // Display "Username field is empty/blank!" for 3s
      UiHelper.showStatusMsg(status, "Username field is empty/blank!");
      _LOGGER.warn("Login Access Failed: `enterdUsername` isEmpty/isBlank!");

    } else if (eneterdPassword.isEmpty()
        || enterdUsername.isBlank()) { // Display "Password field is empty/blank!" for 3s
      UiHelper.showStatusMsg(status, "Password field is empty/blank!");
      _LOGGER.warn("Login Access Failed: `eneterdPassword` isEmpty/isBlank!");

    } else if (UserHelper.isUser(enterdUsername, eneterdPassword)) { // Login success!
      Parent root = FXMLLoader.load(getClass().getResource("/fxml/Dashboard.fxml"));
      Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
      stage.setTitle("HireLog - Sign up");
      stage.setScene(new Scene(root));
      stage.show();
      _LOGGER.info("Login Access Success: `{}` accessed!", enterdUsername);

    } else { // Display "Username or password incoreect!" for 3s
      UiHelper.showStatusMsg(status, "Username or password incoreect!");
      _LOGGER.warn("Login Access Failed: `{}` want to access!", enterdUsername);
    }
  }

  /**
   * Slot connected to `sginUpHyperlink` signals
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
  private void onSginUpHyperlinkAction(ActionEvent event) throws IOException {
    Parent root = FXMLLoader.load(getClass().getResource("/fxml/SignUp.fxml"));
    // Get the current stage (window) from the event source and set the new scene
    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    stage.setTitle("HireLog - Sign up");
    stage.setScene(new Scene(root));
    stage.show();
  }

  @FXML // This method is called by the FXMLLoader when initialization is complete
  private void initialize() {
    assert logo != null : "fx:id=\"logo\" was not injected: check your FXML file 'SignUp.fxml'.";
    assert password != null : "fx:id=\"password\" was not injected: check your FXML file 'SignUp.fxml'.";

    // Set images
    logo.setImage(new Image(getClass().getResource("/assets/logo-1.png").toExternalForm()));
    banner.setImage(new Image(getClass().getResource("/assets/banner.png").toExternalForm()));
  }
}

// TODO: Make all resources paths in `LoginControllerPaths`
enum LoginControllerPaths {

}

// TODO: Make all login status in `LoginControllerStatus`
enum LoginControllerStatus {

}
