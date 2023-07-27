package translationworkflow;

import java.net.URL;
import java.net.URLEncoder;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import io.temporal.activity.Activity;
import translationworkflow.model.TranslationActivityInput;
import translationworkflow.model.TranslationActivityOutput;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TranslationActivitiesImpl implements TranslationActivities {

    private static final Logger logger = LoggerFactory.getLogger(TranslationActivitiesImpl.class);

    @Override
    public TranslationActivityOutput translateTerm(TranslationActivityInput input) {
        String term = input.getTerm();
        String lang = input.getLanguageCode();

        logger.info("translateTerm Activity invoked with input term: {} language code: {}", term, lang);

        StringBuilder builder = new StringBuilder();

        String baseUrl = "http://localhost:9999/translate?term=%s&lang=%s";

        URL url = null;
        try {
            url = new URL(String.format(baseUrl, term, lang, URLEncoder.encode(term, "UTF-8")));
        } catch (IOException e) {
            logger.error("An IOException occurred when trying to craft the URL: {}", e.toString());
            throw Activity.wrap(e);
        }

        try (BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()))) {
            String line;
            while ((line = in.readLine()) != null) {
                builder.append(line);
            }
        } catch (IOException e) {
            logger.error("An IOException was caught while attempting to connect to the microservice: {}", e.toString());
            throw Activity.wrap(e);
        }

        TranslationActivityOutput translation = new TranslationActivityOutput(builder.toString());
        logger.debug("Translation successful: {}", translation.getTranslation());
        return translation;
    }
}
