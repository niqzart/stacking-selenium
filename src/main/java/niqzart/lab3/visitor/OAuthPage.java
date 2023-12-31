package niqzart.lab3.visitor;

import niqzart.lab3.BasePage;
import niqzart.lab3.Utils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public abstract class OAuthPage extends BasePage {
  private static final String loginWithProviderButtonXPath = "//button[@data-provider='%s']";

  public OAuthPage(WebDriver driver) {
    super(driver);
  }

  public void clickLoginWithProvider(String provider) {
    driver.findElement(By.xpath(String.format(loginWithProviderButtonXPath, provider))).click();
    Utils.checkCaptcha(driver);
  }
}
