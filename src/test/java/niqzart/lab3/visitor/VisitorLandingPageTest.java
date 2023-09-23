package niqzart.lab3.visitor;

import niqzart.lab3.LandingPageTest;
import niqzart.lab3.Utils;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class VisitorLandingPageTest extends LandingPageTest {
  @Test
  public void testNavigateToLoginPage() {
    drivers.forEach(driver -> {
      VisitorLandingPage page = new VisitorLandingPage(driver);
      assertFalse(page.isAuthorized());
      page.clickLogin();
      assertTrue(driver.getCurrentUrl().startsWith(Utils.formatUrl("/users/login")), driver.getCurrentUrl());
    });
  }

  @Test
  public void testNavigateToSignupPage() {
    drivers.forEach(driver -> {
      VisitorLandingPage page = new VisitorLandingPage(driver);
      assertFalse(page.isAuthorized());
      page.clickSignup();
      assertTrue(driver.getCurrentUrl().startsWith(Utils.formatUrl("/users/signup")), driver.getCurrentUrl());
    });
  }

  @Test
  public void testNavigateToSignupPageFromCommunity() {
    drivers.forEach(driver -> {
      VisitorLandingPage page = new VisitorLandingPage(driver);
      assertFalse(page.isAuthorized());
      page.clickJoinCommunity();
      assertTrue(driver.getCurrentUrl().startsWith(Utils.formatUrl("/users/signup?ssrc=product_home")), driver.getCurrentUrl());
    });
  }

  @Test
  public void testNavigateToContentSearch() {
    drivers.forEach(driver -> {
      VisitorLandingPage page = new VisitorLandingPage(driver);
      assertFalse(page.isAuthorized());
      page.clickSearchContent();
      assertEquals(driver.getCurrentUrl(), Utils.formatUrl("/questions"));
    });
  }

  @Test
  public void testNavigateToTeamsDiscovery() {
    drivers.forEach(driver -> {
      VisitorLandingPage page = new VisitorLandingPage(driver);
      assertFalse(page.isAuthorized());
      page.clickDiscoverTeams();
      assertEquals(driver.getCurrentUrl(), "https://stackoverflow.co/teams/");
    });
  }
}
