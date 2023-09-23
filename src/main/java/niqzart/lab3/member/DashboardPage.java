package niqzart.lab3.member;

import niqzart.lab3.LandingPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class DashboardPage extends LandingPage {
  public DashboardPage(WebDriver driver) {
    super(driver);
  }

  private static final String tabsXPath = "//div[contains(@class, 's-btn-group')]/a[@data-value='%s']";

  public void selectTab(String tabName) {
    driver.findElement(By.xpath(String.format(tabsXPath, tabName))).click();
  }

  private static final String navLinksXPath = "//ol[contains(@class, 'nav-links')]/li/a[contains(@href, '%s')]";

  public void clickNavLink(String ref) {
    driver.findElement(By.xpath(String.format(navLinksXPath, ref))).click();
  }

  public void clickUserButton() {
    driver.findElement(By.xpath(userButtonXPath)).click();
  }

  private static final String siteSwitcherButtonXPath = "//nav//ol/li/a[contains(@class, 'js-site-switcher-button')]";
  private static final String siteSwitcherDialogXPath = "//div[contains(@class, 'siteSwitcher-dialog')]";

  public void openSiteSwitcher() {
    driver.findElement(By.xpath(siteSwitcherButtonXPath)).click();
    new WebDriverWait(driver, Duration.ofMillis(100)).until(driver1 ->
      driver1.findElements(By.xpath(siteSwitcherDialogXPath)).size() > 0
    );
  }

  private static final String stackMetaXPath = siteSwitcherDialogXPath + "//a[contains(@href, 'meta.stackoverflow.com')]";

  public void clickStackMetaButton() {
    driver.findElement(By.xpath(stackMetaXPath)).click();
  }

  private static final String logoutButtonXPath = siteSwitcherDialogXPath + "//ul[contains(@class, 'current-site')]//a[contains(@href, '/logout')]";

  public void clickLogoutButton() {
    driver.findElement(By.xpath(logoutButtonXPath)).click();
  }
}
