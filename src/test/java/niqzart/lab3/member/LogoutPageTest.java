package niqzart.lab3.member;

import niqzart.lab3.BaseTest;
import niqzart.lab3.visitor.LoginPage;
import niqzart.lab3.Utils;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LogoutPageTest extends BaseTest {
  @Test
  void testAuthorizedLogout() {
    drivers.forEach(driver -> {
      new LoginPage(driver).login();

      LogoutPage logoutPage = new LogoutPage(driver);

      logoutPage.clickLogoutButton();

      assertEquals(driver.getCurrentUrl(), Utils.formatUrl("/"));
    });
  }

  @Test
  void testUnauthorizedLogout() {
    drivers.forEach(driver -> {
      new LogoutPage(driver);
      assertEquals(driver.getCurrentUrl(), Utils.formatUrl("/"));
    });
  }
}
