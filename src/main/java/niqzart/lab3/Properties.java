package niqzart.lab3;

import java.io.FileInputStream;
import java.io.IOException;

public class Properties {
  private static Properties instance;

  private boolean chrome;
  private boolean firefox;

  public static Properties getInstance() {
    if (instance == null) {
      instance = new Properties();
    }
    return instance;
  }
  public void load() {
    var props = new java.util.Properties();
    try {
      props.load(new FileInputStream("browsers.properties"));
      chrome = props.getProperty("chrome") != null;
      firefox = props.getProperty("firefox") != null;
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public boolean doChrome() {
    return chrome;
  }

  public boolean doFirefox() {
    return firefox;
  }
}
