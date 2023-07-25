package translationworkflow;

import java.net.URL;
import java.net.URLEncoder;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import io.temporal.activity.Activity;
import translationworkflow.model.TranslationActivityInput;
import translationworkflow.model.TranslationActivityOutput;

public class TranslationActivitiesImpl implements TranslationActivities {

    @Override
    // TODO Replace the two input parameters with the class you defined as input
    // TODO Replace the return value (String) with the name of the class you defined
    // as output
    public String translateTerm(String inputTerm, String languageCode) {
        // TODO Change the parameters used in these two calls to
        // use the getters from the class you defined to retrieve the input
        String term = inputTerm;
        String lang = languageCode;

        StringBuilder builder = new StringBuilder();

        String baseUrl = "http://localhost:9999/translate?term=%s&lang=%s";

        URL url = null;
        try {
            url = new URL(String.format(baseUrl, term, lang, URLEncoder.encode(term, "UTF-8")));
        } catch (IOException e) {
            throw Activity.wrap(e);
        }

        try (BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()))) {
            String line;
            while ((line = in.readLine()) != null) {
                builder.append(line);
            }
        } catch (IOException e) {
            throw Activity.wrap(e);
        }

        // TODO Replace
        String translation = builder.toString();
        return translation;
    }
}
