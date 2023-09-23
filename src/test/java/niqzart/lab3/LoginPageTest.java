package niqzart.lab3;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;

import static org.junit.jupiter.api.Assertions.*;

public class LoginPageTest extends OAuthPageTest {
  @Override
  protected OAuthPage constructPage(WebDriver driver) {
    return new LoginPage(driver);
  }

  @Test
  void testNoErrorsBeforeInput() {
    drivers.forEach(driver -> {
      LoginPage loginPage = new LoginPage(driver);
      assertNull(loginPage.getErrorMessage());
    });
  }

  @Test
  void testEmptyEmailError() {
    drivers.forEach(driver -> {
      LoginPage loginPage = new LoginPage(driver);

      loginPage.enterPassword("test");
      loginPage.submitLogin();

      String errorMessage = loginPage.getErrorMessage();
      assertNotNull(errorMessage);
      assertTrue(errorMessage.contains("Email cannot be empty"));
    });
  }

  @Test
  void testEmptyPasswordError() {
    drivers.forEach(driver -> {
      LoginPage loginPage = new LoginPage(driver);

      loginPage.enterEmail("test");
      loginPage.submitLogin();

      String errorMessage = loginPage.getErrorMessage();
      assertNotNull(errorMessage);
      assertTrue(errorMessage.contains("Password cannot be empty"));
    });
  }

  @Test
  void testWrongEmailError() {
    drivers.forEach(driver -> {
      LoginPage loginPage = new LoginPage(driver);

      loginPage.enterEmail("test");
      loginPage.enterPassword("test");
      loginPage.submitLogin();

      String errorMessage = loginPage.getErrorMessage();
      assertNotNull(errorMessage);
      assertTrue(errorMessage.contains("The email is not a valid email address"));
    });
  }

  @Test
  void testWrongPasswordError() {
    drivers.forEach(driver -> {
      LoginPage loginPage = new LoginPage(driver);

      loginPage.enterEmail(System.getenv().get("SO-EMAIL"));
      loginPage.enterPassword("test");
      loginPage.submitLogin();

      String errorMessage = loginPage.getErrorMessage();
      assertNotNull(errorMessage);
      assertTrue(errorMessage.contains("The email or password is incorrect"));
    });
  }

  @Test
  void testEmailLogin() {
    drivers.forEach(driver -> {
      LoginPage loginPage = new LoginPage(driver);

      loginPage.enterEmail(System.getenv().get("SO-EMAIL"));
      loginPage.enterPassword(System.getenv().get("SO-PASSWORD"));
      loginPage.submitLogin(true);

      assertEquals(driver.getCurrentUrl(), Utils.formatUrl("/"));
    });
  }
}
