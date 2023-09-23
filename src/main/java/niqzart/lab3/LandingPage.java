package niqzart.lab3;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LandingPage extends BasePage {
  @Override
  protected String getPath() {
    return "/";
  }

  protected static final String userButtonXPath = "//li/a[contains(@class, 's-user-card')]";

  public boolean isAuthorized() {
    return driver.findElements(By.xpath(userButtonXPath)).size() > 0;
  }

  public LandingPage(WebDriver driver) {
    super(driver);
  }

  protected static final String searchInputXPath = "//input[@type='text' and @aria-label='Search']";

  protected WebElement getSearchInput() {
    return driver.findElement(By.xpath(searchInputXPath));
  }

  public void enterSearchQuery(String query) {
    getSearchInput().sendKeys(query);
  }

  public void runSearch() {
    getSearchInput().sendKeys(Keys.ENTER);
    Utils.checkCaptcha(driver);
  }
}
