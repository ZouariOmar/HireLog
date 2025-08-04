/**
 * HireLogApp.java
 *
 * The HireLog entry point
 *
 * <p>By default, it will open the `HireLog - Login` interface</p>
 *
 * @author @ZouariOmar (zouariomar20@gmail.com)
 * @version 1.0
 * @since 27/07/2025
 * @serial [bc0b8d5]
 * 
 * <a href="https://github.com/ZouariOmar/HireLog/blob/main/project/src/main/java/com/mycompany/HireLog/HireLogApp.java">
 *  HireLogApp.java
 * </a>
 */

// `App` pkg name
package com.mycompany.hirelog;

// JavaFx imports
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class HireLogApp extends Application {

  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage primaryStage) throws Exception {
    Parent root = FXMLLoader.load(getClass().getResource("/fxml/Login.fxml"));
    primaryStage.setTitle("HireLog - Login");
    primaryStage.setScene(new Scene(root));
    primaryStage.show();
  }
}
