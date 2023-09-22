package niqzart.lab3;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.ArrayList;

public abstract class BaseTest {
  public ArrayList<WebDriver> drivers;

  @BeforeEach
  public void setUp() {
    drivers = new ArrayList<>();
    drivers.add(new FirefoxDriver());
    drivers.add(new ChromeDriver());
  }

  @AfterEach
  public void tearDown() {
    drivers.forEach(WebDriver::quit);
  }
}
