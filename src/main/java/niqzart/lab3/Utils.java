package niqzart.lab3;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Utils {
  public final static String baseUrl = "https://stackoverflow.com";

  public static String formatUrl(String path) {
    return String.format("%s%s", baseUrl, path);
  }

  public static void checkCaptcha(WebDriver driver) {
    try {
      new WebDriverWait(driver, Duration.ofMillis(100)).until(
        driver1 -> driver1.getCurrentUrl().startsWith(Utils.formatUrl("/nocaptcha"))
      );
      System.err.println("Captcha!");
      new WebDriverWait(driver, Duration.ofMinutes(1)).until(
        driver1 -> !driver1.getCurrentUrl().startsWith(Utils.formatUrl("/nocaptcha"))
      );
    } catch (TimeoutException ignored) {}
  }
}
