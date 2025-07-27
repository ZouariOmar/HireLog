/**
 * LoginController.java
 *
 * the controller of `Login.fxml` view
 *
 * @author @ZouariOmar (zouariomar20@gmail.com)
 * @version 1.0
 * @since 27/07/2025
 * @see https://github.com/ZouariOmar/HireLog/tree/main/project/src/test/java/com/mycompany/HireLog/controller/LoginController.java
 */
package com.mycompany.HireLog.controller;

// JavaFx imports
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

// Java core imports
import java.io.FileNotFoundException;
import java.net.URISyntaxException;

// Cutom imports
import com.mycompany.HireLog.util.*;

public class LoginController {
  @FXML
  private ImageView logo;

  @FXML
  private Button login;

  @FXML
  private PasswordField password;

  @FXML
  private TextField username;

  @FXML
  public void initialize() {
    logo.setImage(new Image(getClass().getResource("/assets/logo.png").toExternalForm()));
  }

  /**
   * Slot connected to `Mouse` click signal
   *
   * <p>
   * This method is marked with &#64;FXML so it can be invoked by the FXML loader.
   * </p>
   * <p>
   * This method catches the following exceptions:
   * </p>
   * <ul>
   * <li><code>URISyntaxException</code> exception</li>
   * <li><code>FileNotFoundException</code> exception</li>
   * </ul>
   *
   * @param event {@code MouseEvent}
   *
   * @see https://openjfx.io/javadoc/22/javafx.fxml/javafx/fxml/doc-files/introduction_to_fxml.html
   */
  @FXML
  private void onMouseClicked(MouseEvent event) {
    final String enterdUsername = username.getText();
    final String eneterdPassword = password.getText();
    try {
      if (UserUtil.isUser(enterdUsername, eneterdPassword))
        login.setText("Passed!");
      else
        login.setText("Fuck Me!");
    } catch (URISyntaxException e) {
      e.printStackTrace();
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
  }
}
