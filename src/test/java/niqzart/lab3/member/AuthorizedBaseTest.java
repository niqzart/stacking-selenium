package niqzart.lab3.member;

import niqzart.lab3.BaseTest;
import niqzart.lab3.visitor.LoginPage;
import org.junit.jupiter.api.BeforeEach;

public class AuthorizedBaseTest extends BaseTest {
  @BeforeEach
  void setupAuth() {
    drivers.forEach(driver -> new LoginPage(driver).login());
  }
}
