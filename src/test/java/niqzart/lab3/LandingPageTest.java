package niqzart.lab3;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class LandingPageTest extends BaseTest {
  private static Stream<Arguments> searchWordsParams() {
    return Stream.of(
      Arguments.of("python performance", "python+performance"),
      Arguments.of("\"python is bad\"", "%22python+is+bad%22"),
      Arguments.of("// in xpath", "%2F%2F+in+xpath"),
      Arguments.of("hello score:4", "hello+score%3A4"),
      Arguments.of("is:question", "is%3Aquestion"),
      Arguments.of("[]", "%5B%5D")  // checking if [] is not recognized as a tag
    );
  }

  @ParameterizedTest
  @MethodSource("searchWordsParams")
  void testSearchByWords(String query, String encoded) {
    drivers.forEach(driver -> {
      LandingPage page = new LandingPage(driver);

      page.enterSearchQuery(query);
      page.runSearch();

      assertTrue(driver.getCurrentUrl().startsWith(Utils.formatUrl(String.format("/search?q=%s", encoded))));
    });
  }
  private static Stream<Arguments> searchTagsParams() {
    return Stream.of(
      Arguments.of("[kotlin]", "kotlin"),
      Arguments.of("[kotlin] [java]", "kotlin+java")
    );
  }

  @ParameterizedTest
  @MethodSource("searchTagsParams")
  void testSearchByTag(String tags, String tagged) {
    drivers.forEach(driver -> {
      LandingPage page = new LandingPage(driver);

      page.enterSearchQuery(tags);
      page.runSearch();

      assertTrue(driver.getCurrentUrl().startsWith(Utils.formatUrl(String.format("/questions/tagged/%s", tagged))));
    });
  }
}
