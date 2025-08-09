/**
 * UiHelper.java
 *
 * HireLog ui helper
 *
 * <p>None</p>
 *
 * @author @ZouariOmar (zouariomar20@gmail.com)
 * @version 1.0
 * @since 31/07/2025
 * @see https://github.com/ZouariOmar/HireLog/tree/main/project/src/test/java/com.mycompany.hirelog/util/UiHelper.java
 */

// UiHelper pkg name
package com.mycompany.hirelog.view;

// Java javafx imports
import javafx.animation.PauseTransition;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.util.Duration;

/**
 * `UiHelper` helper class
 *
 * <p>
 * Note: This class used only for ui purposes, this why we seperated from any
 * `*Util` class
 * </p>
 * <ul>
 * <li>Default text color: RED</li>
 * </ul>
 *
 * @see com.mycompany.hirelog.controller.LoginController
 * @see com.mycompany.hirelog.controller.SignUpController
 *
 *      <pre>
 * {@code
 * UiHelper.showStatusMsg(status, "Username or password incoreect!");
 * }</pre>
 */
public class ViewUtils {

  /**
   * @param status
   * @param msg
   * @param color
   * @param seconds
   */
  public final static void showStatusMsg(final Label status, final String msg, final Color color, final int seconds) {
    final PauseTransition visiblePause = new PauseTransition(Duration.seconds(seconds));
    status.setText(msg);
    status.setTextFill(color);
    status.setVisible(true);
    visiblePause.setOnFinished(_ -> {
      status.setVisible(false);
    });
    visiblePause.play();
  }

  /**
   * @param status
   * @param msg
   */
  public final static void showStatusMsg(final Label status, final String msg) {
    showStatusMsg(status, msg, Color.RED, 3);
  }

  /**
   * @param status
   * @param msg
   * @param color
   */
  public final static void showStatusMsg(final Label status, final String msg, final Color color) {
    showStatusMsg(status, msg, color, 3);
  }

  /**
   * @param imageView
   * @param gifPath
   * @param seconds
   */
  public final static void playGifAnimation(final ImageView imageView, final String gifPath, final double seconds) {
    final Image initialImage = imageView.getImage();
    imageView.setImage(new Image(gifPath));
    final PauseTransition visiblePause = new PauseTransition(Duration.seconds(seconds));
    visiblePause.setOnFinished(_ -> imageView.setImage(initialImage));
    visiblePause.play();
  }

  public final static void playGifAnimation(final ImageView imageView, final String gifPath) {
    playGifAnimation(imageView, gifPath, 1d);
  }

  public final static void disableButton(final Button button, double seconds) {
    button.setDisable(true);
    final PauseTransition visiblePause = new PauseTransition(Duration.seconds(seconds));
    visiblePause.setOnFinished(_ -> button.setDisable(false));
    visiblePause.play();
  }

  public final static void updateButtonStates(ObservableList<LogTableUi> items, Button editBtn, Button deleteBtn) {
    for (LogTableUi item : items) {
      item.selectedProperty().addListener((_, _, _) -> {
        long selectedCount = items.stream().filter(LogTableUi::isSelected).count();
        editBtn.setDisable(selectedCount == 0 || selectedCount > 1);
        deleteBtn.setDisable(selectedCount == 0);
      });
    }
  }
}
