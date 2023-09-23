package niqzart.lab3.visitor;

import niqzart.lab3.BaseTest;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public abstract class OAuthPageTest extends BaseTest {
  private static Stream<Arguments> oauthParams() {
    return Stream.of(
      Arguments.of("google", "https://accounts.google.com/"),
      Arguments.of("github", "https://github.com/"),
      Arguments.of("facebook", "https://www.facebook.com/")
    );
  }

  protected abstract OAuthPage constructPage(WebDriver driver);

  @ParameterizedTest
  @MethodSource("oauthParams")
  public void testNavigateToOauth(String provider, String url) {
    drivers.forEach(driver -> {
      OAuthPage oAuthPage = constructPage(driver);

      try {
        oAuthPage.clickLoginWithProvider(provider);
      } catch (WebDriverException e) {
        assertTrue(e.getMessage().contains("stackauth.com"), e.getMessage());
        return;
      }

      assertTrue(driver.getCurrentUrl().startsWith(url), driver.getCurrentUrl());
      assertTrue(driver.getCurrentUrl().contains("stackauth.com"), driver.getCurrentUrl());
    });
  }
}
