package niqzart.lab3;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

public class SignupPageTest extends OAuthPageTest {
  @Override
  protected OAuthPage constructPage(WebDriver driver) {
    return new SignupPage(driver);
  }

  @Test
  void testNoErrorsBeforeInput() {
    drivers.forEach(driver -> {
      SignupPage signupPage = new SignupPage(driver);
      assertTrue(signupPage.getErrorMessage() == null || Objects.equals(signupPage.getErrorMessage(), ""));
    });
  }

  @Test
  void testEmptyEmailError() {
    drivers.forEach(driver -> {
      SignupPage signupPage = new SignupPage(driver);

      signupPage.enterPassword("test");
      signupPage.submitSignup();

      String errorMessage = signupPage.getErrorMessage();
      assertNotNull(errorMessage);
      assertTrue(errorMessage.contains("Email cannot be empty"));
    });
  }

  @Test
  void testEmptyPasswordError() {
    drivers.forEach(driver -> {
      SignupPage signupPage = new SignupPage(driver);

      signupPage.enterEmail("test");
      signupPage.submitSignup();

      String errorMessage = signupPage.getErrorMessage();
      assertNotNull(errorMessage);
      assertTrue(errorMessage.contains("Password cannot be empty"));
    });
  }

  @Test
  void testWeakPasswordError() {
    drivers.forEach(driver -> {
      SignupPage signupPage = new SignupPage(driver);

      signupPage.enterEmail("test");
      signupPage.enterPassword("test");
      signupPage.submitSignup();

      String errorMessage = signupPage.getErrorMessage();
      assertNotNull(errorMessage);
      assertTrue(errorMessage.contains("make your password stronger"));
    });
  }

  @Test
  void testCaptchaRequired() {
    drivers.forEach(driver -> {
      SignupPage signupPage = new SignupPage(driver);

      signupPage.enterDisplayName("test");
      signupPage.enterEmail("test@yandex.ru");
      signupPage.enterPassword("test1234");
      signupPage.submitSignup();

      String errorMessage = signupPage.getErrorMessage();
      assertNotNull(errorMessage);
      assertTrue(errorMessage.contains("CAPTCHA response required"));
    });
  }
}
