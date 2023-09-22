package niqzart.lab3;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LandingPageTest extends BaseTest {
  @Test
  public void testNavigateToLoginPage() {
    drivers.forEach(driver -> {
      LandingPage page = new LandingPage(driver);
      page.clickLogin();
      assertTrue(driver.getCurrentUrl().startsWith(Utils.formatUrl("/users/login")));
    });
  }

  @Test
  public void testNavigateToSignupPage() {
    drivers.forEach(driver -> {
      LandingPage page = new LandingPage(driver);
      page.clickSignup();
      assertTrue(driver.getCurrentUrl().startsWith(Utils.formatUrl("/users/signup")));
    });
  }

  @Test
  public void testNavigateToSignupPageFromCommunity() {
    drivers.forEach(driver -> {
      LandingPage page = new LandingPage(driver);
      page.clickJoinCommunity();
      assertTrue(driver.getCurrentUrl().startsWith(Utils.formatUrl("/users/signup?ssrc=product_home")));
    });
  }

  @Test
  public void testNavigateToContentSearch() {
    drivers.forEach(driver -> {
      LandingPage page = new LandingPage(driver);
      page.clickSearchContent();
      assertEquals(driver.getCurrentUrl(), Utils.formatUrl("/questions"));
    });
  }

  @Test
  public void testNavigateToTeamsDiscovery() {
    drivers.forEach(driver -> {
      LandingPage page = new LandingPage(driver);
      page.clickDiscoverTeams();
      assertEquals(driver.getCurrentUrl(), "https://stackoverflow.co/teams/");
    });
  }
}
