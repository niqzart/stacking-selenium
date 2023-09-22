package niqzart.lab3;

public class Utils {
  public final static String baseUrl = "https://stackoverflow.com";

  public static String formatUrl(String path) {
    return String.format("%s%s", baseUrl, path);
  }
}
