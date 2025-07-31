/**
 * User.java
 *
 * `User` model describer
 *
 * <p>NONE</p>
 *
 * @author @ZouariOmar (zouariomar20@gmail.com)
 * @version 1.0
 * @since 28/07/2025
 * @see <a href="https://github.com/ZouariOmar/HireLog/tree/main/project/src/main/java/com/mycompany/HireLog/model/User.java"></a>
 */

// `User` pkg name
package com.mycompany.HireLog.model;

public record User(String username, String email, String password) {
}
