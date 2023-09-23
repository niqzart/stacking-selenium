package niqzart.lab3.member;

import niqzart.lab3.LandingPageTest;
import niqzart.lab3.Utils;
import niqzart.lab3.visitor.LoginPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

public class DashboardPageTest extends LandingPageTest {
  @BeforeEach
  void setupAuth() {
    drivers.forEach(driver -> {
      new LoginPage(driver).login();
      assertTrue(new DashboardPage(driver).isAuthorized());
    });
  }

  @ParameterizedTest
  @ValueSource(strings = {"interesting", "bounties", "hot", "week", "month"})
  void testQuestionTabNavigation(String tabName) {
    drivers.forEach(driver -> {
      DashboardPage dashboardPage = new DashboardPage(driver);

      dashboardPage.selectTab(tabName);

      assertEquals(driver.getCurrentUrl(), Utils.formatUrl(String.format("/?tab=%s", tabName)));
    });
  }

  @ParameterizedTest
  @ValueSource(strings = {"/questions", "/tags", "/users", "/jobs/companies", "/collectives"})
  void testNavLinkNavigation(String ref) {
    drivers.forEach(driver -> {
      DashboardPage dashboardPage = new DashboardPage(driver);

      dashboardPage.clickNavLink(ref);

      assertEquals(driver.getCurrentUrl(), Utils.formatUrl(ref));
    });
  }

  @Test
  void testNavigateToMeta() {
    drivers.forEach(driver -> {
      DashboardPage dashboardPage = new DashboardPage(driver);

      dashboardPage.openSiteSwitcher();
      dashboardPage.clickStackMetaButton();

      assertEquals(driver.getCurrentUrl(), "https://meta.stackoverflow.com/");
    });
  }

  @Test
  void testNavigateToLogout() {
    drivers.forEach(driver -> {
      DashboardPage dashboardPage = new DashboardPage(driver);

      dashboardPage.openSiteSwitcher();
      dashboardPage.clickLogoutButton();

      assertEquals(driver.getCurrentUrl(), Utils.formatUrl("/users/logout"));
    });
  }

  @Test
  void testNavigateToUser() {
    drivers.forEach(driver -> {
      DashboardPage dashboardPage = new DashboardPage(driver);

      dashboardPage.clickUserButton();

      assertTrue(driver.getCurrentUrl().matches(Utils.formatUrl("/users/\\d+/[^/]+")));
    });
  }
}
