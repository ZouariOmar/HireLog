/**
 * App.java
 *
 * The HireLog entry point
 *
 * <p>By default, it will open the `HireLog - Login` interface</p>
 *
 * @author @ZouariOmar (zouariomar20@gmail.com)
 * @version 1.0
 * @since 27/07/2025
 * @see https://github.com/ZouariOmar/HireLog/blob/main/project/src/main/java/com/mycompany/HireLog/App.java
 * @serial [bc0b8d5]
 */

// `App` pkg name
package com.mycompany.HireLog;

// JavaFx imports
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {
  @Override
  public void start(Stage primaryStage) throws Exception {
    Parent root = FXMLLoader.load(getClass().getResource("/fxml/Login.fxml"));
    primaryStage.setTitle("HireLog - Login");
    primaryStage.setScene(new Scene(root));
    primaryStage.show();
  }

  public static void main(String[] args) {
    launch(args);
  }
}
