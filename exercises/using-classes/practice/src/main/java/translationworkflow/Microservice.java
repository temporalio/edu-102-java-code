package translationworkflow;

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

        final HashMap<String, HashMap<String, String>> translations = setTranslations();

        // Define the service endpoints and handlers
        On.get("/translate").plain(new TranslationHandler(translations));

        // Also define a catch-all to return an HTTP 404 Not Found error if the URL
        // path in the request didn't match an endpoint defined above. It's essential
        // that this code remains at the end.
        On.req((req, resp) -> {
            String message = String.format("Error: Invalid endpoint address '%s'", req.path());
            return req.response().result(message).code(404);
        });
    }

    private static class TranslationHandler implements ReqRespHandler {

        private HashMap<String, HashMap<String, String>> translations;

        public TranslationHandler(HashMap<String, HashMap<String, String>> translations) {
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
            return U.str(response);
        }

    }

    private static HashMap<String, HashMap<String, String>> setTranslations() {

        HashMap<String, HashMap<String, String>> translations = new HashMap<String, HashMap<String, String>>();
        // German translations
        HashMap<String, String> germanTranslations = new HashMap<String, String>();
        germanTranslations.put("hello", "hallo");
        germanTranslations.put("goodbye", "auf wiedersehen");
        germanTranslations.put("thanks", "danke schön");
        translations.put("de", germanTranslations);

        // Spanish translations
        HashMap<String, String> spanishTranslations = new HashMap<String, String>();
        spanishTranslations.put("hello", "hola");
        spanishTranslations.put("goodbye", "adiós");
        spanishTranslations.put("thanks", "gracias");
        translations.put("es", spanishTranslations);

        // French translations
        HashMap<String, String> frenchTranslations = new HashMap<String, String>();
        frenchTranslations.put("hello", "bonjour");
        frenchTranslations.put("goodbye", "au revoir");
        frenchTranslations.put("thanks", "merci");
        translations.put("fr", frenchTranslations);

        // Latvian translations
        HashMap<String, String> latvianTranslations = new HashMap<String, String>();
        latvianTranslations.put("hello", "sveiks");
        latvianTranslations.put("goodbye", "ardievu");
        latvianTranslations.put("thanks", "paldies");
        translations.put("lv", latvianTranslations);

        // Maori translations
        HashMap<String, String> maoriTranslations = new HashMap<String, String>();
        maoriTranslations.put("hello", "kia ora");
        maoriTranslations.put("goodbye", "poroporoaki");
        maoriTranslations.put("thanks", "whakawhetai koe");
        translations.put("mi", maoriTranslations);

        // Slovak translations
        HashMap<String, String> slovakTranslations = new HashMap<String, String>();
        slovakTranslations.put("hello", "ahoj");
        slovakTranslations.put("goodbye", "zbohom");
        slovakTranslations.put("thanks", "ďakujem koe");
        translations.put("sk", slovakTranslations);

        // Turkish translations
        HashMap<String, String> turkishTranslations = new HashMap<String, String>();
        turkishTranslations.put("hello", "merhaba");
        turkishTranslations.put("goodbye", "güle güle");
        turkishTranslations.put("thanks", "teşekkür ederim");
        translations.put("tr", turkishTranslations);

        // Zulu translations
        HashMap<String, String> zuluTranslations = new HashMap<String, String>();
        zuluTranslations.put("hello", "hamba kahle");
        zuluTranslations.put("goodbye", "sawubona");
        zuluTranslations.put("thanks", "ngiyabonga");
        translations.put("zu", zuluTranslations);

        return translations;
    }

}
