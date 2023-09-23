package niqzart.lab3;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.v114.network.Network;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public abstract class BaseTest {
  public ArrayList<WebDriver> drivers;

  @BeforeAll
  public static void loadProperties() {
    Properties.getInstance().load();
  }

  @BeforeEach
  public void setUp() {
    drivers = new ArrayList<>();

    if (Properties.getInstance().doFirefox()) drivers.add(new FirefoxDriver());

    if (Properties.getInstance().doChrome()) {
      ChromeDriver chromeDriver = new ChromeDriver();
      chromeDriver.getDevTools().createSession();
      chromeDriver.getDevTools().send(Network.enable(Optional.empty(), Optional.empty(), Optional.empty()));
      chromeDriver.getDevTools().send(Network.setBlockedURLs(List.of(
        "https://px.ads.linkedin.com/*",
        "https://b.6sc.co/*",
        "https://graph.facebook.com/*",
        "https://www.google-analytics.com/*",
        "https://pagead2.googlesyndication.com/*"
      )));
      drivers.add(chromeDriver);
    }
  }

  @AfterEach
  public void tearDown() {
    drivers.forEach(WebDriver::quit);
  }
}
