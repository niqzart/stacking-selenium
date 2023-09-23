package niqzart.lab3.visitor;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SignupPage extends LoginPage {
  @Override
  protected String getPath() {
    return "/users/signup";
  }

  public SignupPage(WebDriver driver) {
    super(driver);
  }

  private static final String displayNameInputXPath = "//input[@type='text' and @name='display-name']";

  public void enterDisplayName(String displayName) {
    driver.findElement(By.xpath(displayNameInputXPath)).sendKeys(displayName);
  }

  public void submitSignup() {
    submitLogin();
  }
}
