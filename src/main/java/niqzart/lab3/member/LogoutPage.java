package niqzart.lab3.member;

import niqzart.lab3.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LogoutPage extends BasePage {
  @Override
  protected String getPath() {
    return "/users/logout";
  }

  public LogoutPage(WebDriver driver) {
    super(driver);
    waitAndAcceptCookies();
  }

  private static final String logoutButtonXPath = "//form[@action='/users/logout']//button[contains(text(), 'Log out')]";

  public void clickLogoutButton() {
    driver.findElement(By.xpath(logoutButtonXPath)).click();
  }
}
