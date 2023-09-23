package niqzart.lab3;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class LoginPageTest extends BaseTest {
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

  private static Stream<Arguments> oauthParams() {
    return Stream.of(
      Arguments.of("google", "https://accounts.google.com/"),
      Arguments.of("github", "https://github.com/"),
      Arguments.of("facebook", "https://www.facebook.com/")
    );
  }

  @ParameterizedTest
  @MethodSource("oauthParams")
  public void testNavigateToOauth(String provider, String url) {
    drivers.forEach(driver -> {
      LoginPage loginPage = new LoginPage(driver);

      loginPage.clickLoginWithProvider(provider);

      assertTrue(driver.getCurrentUrl().startsWith(url));
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
