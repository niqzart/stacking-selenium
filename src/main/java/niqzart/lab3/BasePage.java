package niqzart.lab3;

import org.openqa.selenium.WebDriver;

public abstract class BasePage {
  protected abstract String getPath();
  protected final WebDriver driver;
  public BasePage(WebDriver driver) {
    this.driver = driver;
    this.driver.get(Utils.formatUrl(getPath()));
  }
}
