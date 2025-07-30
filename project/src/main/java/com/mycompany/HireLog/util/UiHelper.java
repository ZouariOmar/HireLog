
package com.mycompany.HireLog.util;

import javafx.scene.control.Label;
import javafx.animation.PauseTransition;
import javafx.util.Duration;

/**
 * `UiHelper` helper class
 *
 * <p>
 * Note: This class used only for ui purposes, this why we seperated from any
 * `*Util` class
 * </p>
 *
 * @see LoginController
 * @see SignUpController
 *
 *      <pre>
 * {@code
 * UiHelper.showStatusMsg(status, "Username or password incoreect!");
 * }</pre>
 */
public class UiHelper {
  public final static void showStatusMsg(final Label status, final String msg, final int seconds) {
    PauseTransition visiblePause = new PauseTransition(Duration.seconds(seconds));
    status.setText(msg);
    status.setVisible(true);
    visiblePause.setOnFinished(e -> status.setVisible(false));
    visiblePause.play();
  }

  public final static void showStatusMsg(final Label status, final String msg) {
    showStatusMsg(status, msg, 3);
  }
}
