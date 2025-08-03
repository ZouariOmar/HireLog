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
 * @see https://github.com/ZouariOmar/HireLog/tree/main/project/src/test/java/com/mycompany/HireLog/util/UiHelper.java
 */

// UiHelper pkg name
package com.mycompany.HireLog.ui;

// Java javafx imports
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.animation.PauseTransition;
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
 * @see com.mycompany.HireLog.controller.LoginController
 * @see com.mycompany.HireLog.controller.SignUpController
 *
 *      <pre>
 * {@code
 * UiHelper.showStatusMsg(status, "Username or password incoreect!");
 * }</pre>
 */
public class UiHelper {
  public final static void showStatusMsg(final Label status, final String msg, final Color color, final int seconds) {
    PauseTransition visiblePause = new PauseTransition(Duration.seconds(seconds));
    status.setText(msg);
    status.setTextFill(color);
    status.setVisible(true);
    visiblePause.setOnFinished(e -> {
      status.setVisible(false);
    });
    visiblePause.play();
  }

  public final static void showStatusMsg(final Label status, final String msg) {
    showStatusMsg(status, msg, Color.RED, 3);
  }

  public final static void showStatusMsg(final Label status, final String msg, final Color color) {
    showStatusMsg(status, msg, color, 3);
  }
}
