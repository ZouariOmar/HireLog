/**
 * PasswordGeneratorService.java
 *
 * Generate Secure Password
 *
 * <p>None</p>
 *
 * @author @ZouariOmar (zouariomar20@gmail.com)
 * @version 1.0
 * @since 08/08/2025
 *
 * <a href="https://www.passay.org">Passay</a>
 */

// `PasswordGeneratorService` pkg name
package com.mycompany.hirelog.service;

// `passay` imports
import org.passay.CharacterRule;
import org.passay.EnglishCharacterData;
import org.passay.PasswordGenerator;
import org.passay.Rule;

public class PasswordGeneratorService {
  public static final String generate(int length) {
    return new PasswordGenerator().generatePassword(length,
        (Rule) new CharacterRule(EnglishCharacterData.LowerCase, 1),
        (Rule) new CharacterRule(EnglishCharacterData.UpperCase, 1),
        (Rule) new CharacterRule(EnglishCharacterData.Digit, 1),
        (Rule) new CharacterRule(EnglishCharacterData.SpecialAscii, 1));
  }

  /**
   * Generate a random secure password of 10 charaters;
   *
   * <p>
   * None
   * </p>
   *
   * @return {@code String}
   * @see #generate(int)
   *
   *      <pre>
   * {@code
   * System.out.println("Your Password: " + PasswordGeneratorService.generate());
   * }</pre>
   */
  public static final String generate() {
    return generate(10);
  }
} // PasswordGeneratorService class
