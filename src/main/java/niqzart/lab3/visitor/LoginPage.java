package niqzart.lab3.visitor;

import niqzart.lab3.Utils;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Objects;

public class LoginPage extends OAuthPage {
  @Override
  protected String getPath() {
    return "/users/login";
  }

  public LoginPage(WebDriver driver) {
    super(driver);
  }

  private static final String emailInputXPath = "//input[@type='email']";

  public void enterEmail(String email) {
    driver.findElement(By.xpath(emailInputXPath)).sendKeys(email);
  }

  private static final String passwordInputXPath = "//input[@type='password']";

  public void enterPassword(String password) {
    driver.findElement(By.xpath(passwordInputXPath)).sendKeys(password);
  }

  private static final String formErrorXPath = "//p[contains(@class, 'js-error-message') and not(contains(@class, 'd-none'))]";

  public String getErrorMessage() {
    try {
      new WebDriverWait(driver, Duration.ofMillis(100)).until(driver1 -> driver.findElements(By.xpath(formErrorXPath)).size() > 0);
    } catch (TimeoutException e) {
      return null;
    }
    for (var element : driver.findElements(By.xpath(formErrorXPath))) {
      if (element.isEnabled()) return element.getText();
    }
    return null;
  }

  private static final String loginButtonXPath = "//button[@name='submit-button']";

  public void submitLogin(boolean waitSuccess) {
    driver.findElement(By.xpath(loginButtonXPath)).click();
    Utils.checkCaptcha(driver);
    if (waitSuccess) new WebDriverWait(driver, Duration.ofMillis(100)).until(
      driver1 -> Objects.equals(driver1.getCurrentUrl(), Utils.formatUrl("/"))
    );
  }

  public void submitLogin() {
    submitLogin(false);
  }

  public void login() {
    enterEmail(System.getenv().get("SO-EMAIL"));
    enterPassword(System.getenv().get("SO-PASSWORD"));
    try {
      submitLogin(true);
    } catch (TimeoutException ignore) {
      login();
    }
  }
}
