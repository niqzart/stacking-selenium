package niqzart.lab3;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public abstract class BasePage {
  protected String getPath() {
    return null;
  }

  protected final WebDriver driver;

  public BasePage(WebDriver driver) {
    this.driver = driver;
    String path = getPath();
    if (path != null) this.driver.get(Utils.formatUrl(getPath()));
  }

  protected static String cookiesPopupXPath = "//div[contains(@class, 'js-consent-banner') and not(contains(@class, 'd-none'))]";

  protected static String acceptCookiesButtonXPath = cookiesPopupXPath + "//button[contains(text(), 'Accept')]";

  public void clickAcceptCookies() {
    driver.findElement(By.xpath(acceptCookiesButtonXPath)).click();
  }

  public void waitAndAcceptCookies() {
    try {
      new WebDriverWait(driver, Duration.ofMillis(100)).until(driver1 ->
        driver1.findElements(By.xpath(cookiesPopupXPath)).size() > 0);
      clickAcceptCookies();
    } catch (TimeoutException ignored) {}
  }
}
