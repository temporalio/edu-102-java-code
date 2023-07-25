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
    public TranslationActivityOutput translateTerm(TranslationActivityInput input) {
        String term = input.getTerm();
        String languageCode = input.getLanguageCode();

        StringBuilder builder = new StringBuilder();

        String baseUrl = "http://localhost:9999/translate?term=%s&lang=%s";

        URL url = null;
        try {
            url = new URL(String.format(baseUrl, term, languageCode, URLEncoder.encode(term, "UTF-8")));
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

        return new TranslationActivityOutput(builder.toString());
    }
}
