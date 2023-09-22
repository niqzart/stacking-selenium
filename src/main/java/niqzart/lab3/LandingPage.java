package niqzart.lab3;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LandingPage extends BasePage {
  @Override
  protected String getPath() {
    return "/";
  }

  public LandingPage(WebDriver driver) {
    super(driver);
  }

  private static final String loginButtonXPath = "//header//li/a[@href='https://stackoverflow.com/users/login?ssrc=head&returnurl=https%3a%2f%2fstackoverflow.com%2f']";

  public void clickLogin() {
    driver.findElement(By.xpath(loginButtonXPath)).click();
  }

  private static final String signupButtonXPath = "//header//li/a[@href='https://stackoverflow.com/users/signup?ssrc=head&returnurl=https%3a%2f%2fstackoverflow.com%2f']";

  public void clickSignup() {
    driver.findElement(By.xpath(signupButtonXPath)).click();
  }

  private static final String communityJoinButtonXPath = "//div/a[@href='/users/signup?ssrc=product_home']";

  public void clickJoinCommunity() {
    driver.findElement(By.xpath(communityJoinButtonXPath)).click();
  }

  private static final String searchContentLinkXPath = "//p/a[@href='/questions']";

  public void clickSearchContent() {
    driver.findElement(By.xpath(searchContentLinkXPath)).click();
  }

  private static final String discoverTeamsButtonXPath = "//li/a[@href='https://stackoverflow.co/teams/']";

  public void clickDiscoverTeams() {
    driver.findElement(By.xpath(discoverTeamsButtonXPath)).click();
  }
}
