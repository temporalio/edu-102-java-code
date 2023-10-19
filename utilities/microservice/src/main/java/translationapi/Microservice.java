package translationapi;

import java.io.IOException;

import org.rapidoid.http.Req;
import org.rapidoid.http.ReqRespHandler;
import org.rapidoid.http.Resp;
import org.rapidoid.setup.On;
import org.rapidoid.u.U;

import java.util.Map;
import java.util.HashMap;

public class Microservice {

  // port number where this service will listen for incoming HTTP requests
  public static final int PORT_NUMBER = 9999;

  // IP address to which the service will be bound. Using a value of 0.0.0.0
  // will make it available on all available interfaces, but you could use
  // 127.0.0.1 to restrict it to the loopback interface
  public static final String SERVER_IP = "0.0.0.0";

  public static void main(String[] args) throws IOException {
    // Start the service on the specified IP address and port
    On.address(SERVER_IP).port(PORT_NUMBER);

    final Map<String, Map<String, String>> translations = loadTranslations();

    // Define the service endpoints and handlers
    On.get("/translate").plain(new TranslationHandler(translations));

    // Also define a catch-all to return an HTTP 404 Not Found error if the URL
    // path in the request didn't match an endpoint defined above. It's essential
    // that this code remains at the end.
    On.req(
        (req, resp) -> {
          String message = String.format("Error: Invalid endpoint address '%s'", req.path());
          return req.response().result(message).code(404);
        });
  }

  private static class TranslationHandler implements ReqRespHandler {

    private final Map<String, Map<String, String>> translations;

    public TranslationHandler(Map<String, Map<String, String>> translations) {
      super();
      this.translations = translations;
    }

    @Override
    public Object execute(Req req, Resp resp) throws Exception {
      Map<String, String> params = req.params();

      if (!params.containsKey("term")) {
        String message = "Error: Missing required 'term' parameter!";
        return req.response().result(message).code(500);
      }

      if (!params.containsKey("lang")) {
        String message = "Error: Missing required 'lang' parameter!";
        return req.response().result(message).code(500);
      }

      String languageCode = params.get("lang");
      String term = params.get("term");

      if (!translations.containsKey(languageCode)) {
        String message = "Error: Invalid language code '" + languageCode + "'";
        return req.response().result(message).code(500);
      }

      if (!translations.get(languageCode).containsKey(term)) {
        String message = "Error: Invalid translation term '" + term + "'";
        return req.response().result(message).code(500);
      }

      String message = translations.get(languageCode).get(term);
      String capMessage = message.substring(0, 1).toUpperCase() + message.substring(1);
      String response = String.format("%s", capMessage);
      System.out.println(response);

      return U.str(response);
    }
  }

  private static Map<String, Map<String, String>> loadTranslations() {
    Map<String, Map<String, String>> translations = new HashMap<>();

    // German translations
    Map<String, String> germanTranslations = new HashMap<>();
    germanTranslations.put("hello", "hallo");
    germanTranslations.put("goodbye", "auf wiedersehen");
    germanTranslations.put("thanks", "danke schön");
    translations.put("de", germanTranslations);

    // Spanish translations
    Map<String, String> spanishTranslations = new HashMap<>();
    spanishTranslations.put("hello", "hola");
    spanishTranslations.put("goodbye", "adiós");
    spanishTranslations.put("thanks", "gracias");
    translations.put("es", spanishTranslations);

    // French translations
    Map<String, String> frenchTranslations = new HashMap<>();
    frenchTranslations.put("hello", "bonjour");
    frenchTranslations.put("goodbye", "au revoir");
    frenchTranslations.put("thanks", "merci");
    translations.put("fr", frenchTranslations);

    // Latvian translations
    Map<String, String> latvianTranslations = new HashMap<>();
    latvianTranslations.put("hello", "sveiks");
    latvianTranslations.put("goodbye", "ardievu");
    latvianTranslations.put("thanks", "paldies");
    translations.put("lv", latvianTranslations);

    // Maori translations
    Map<String, String> maoriTranslations = new HashMap<>();
    maoriTranslations.put("hello", "kia ora");
    maoriTranslations.put("goodbye", "poroporoaki");
    maoriTranslations.put("thanks", "whakawhetai koe");
    translations.put("mi", maoriTranslations);

    // Slovak translations
    Map<String, String> slovakTranslations = new HashMap<>();
    slovakTranslations.put("hello", "ahoj");
    slovakTranslations.put("goodbye", "zbohom");
    slovakTranslations.put("thanks", "ďakujem koe");
    translations.put("sk", slovakTranslations);

    // Turkish translations
    Map<String, String> turkishTranslations = new HashMap<>();
    turkishTranslations.put("hello", "merhaba");
    turkishTranslations.put("goodbye", "güle güle");
    turkishTranslations.put("thanks", "teşekkür ederim");
    translations.put("tr", turkishTranslations);

    // Zulu translations
    Map<String, String> zuluTranslations = new HashMap<>();
    zuluTranslations.put("hello", "hamba kahle");
    zuluTranslations.put("goodbye", "sawubona");
    zuluTranslations.put("thanks", "ngiyabonga");
    translations.put("zu", zuluTranslations);

    return translations;
  }
}
