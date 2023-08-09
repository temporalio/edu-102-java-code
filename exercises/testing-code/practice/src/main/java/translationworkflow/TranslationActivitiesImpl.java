package translationworkflow;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.temporal.activity.Activity;
import translationworkflow.model.TranslationActivityInput;
import translationworkflow.model.TranslationActivityOutput;

public class TranslationActivitiesImpl implements TranslationActivities {

    private static final Logger logger = LoggerFactory.getLogger(TranslationActivitiesImpl.class);

    @Override
    public TranslationActivityOutput translateTerm(TranslationActivityInput input) {
        String term = input.getTerm();
        String lang = input.getLanguageCode();

        logger.info("[ACTIVITY INVOKED] translateTerm invoked with input term: {} language code: {}", term, lang);

        StringBuilder builder = new StringBuilder();

        String baseUrl = "http://localhost:9999/translate?term=%s&lang=%s";

        URL url = null;
        try {
            url = new URL(String.format(baseUrl, term, lang, URLEncoder.encode(term, "UTF-8")));
        } catch (IOException e) {
            logger.error("An IOException occurred when trying to craft the URL: {}", e.toString());
            throw Activity.wrap(e);
        }

        HttpURLConnection connection;
        try {
            connection = (HttpURLConnection) url.openConnection();
        } catch (IOException e) {
            logger.error("An IOException occurred when trying to establish connection to the microservice: {}",
                    e.toString());
            throw Activity.wrap(e);
        }

        try (BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
            String line;
            while ((line = in.readLine()) != null) {
                builder.append(line);
            }
        } catch (IOException e) {
            try (BufferedReader in = new BufferedReader(new InputStreamReader(connection.getErrorStream()))) {
                String error;
                while ((error = in.readLine()) != null) {
                    builder.append(error);
                }
                String errorMessage = "An error was caught attempting to call the microservice: " + builder.toString();
                logger.error("errorMessage");
                throw Activity.wrap(new IOException(errorMessage));
            } catch (IOException ex) {
                logger.error(
                        "An IOException occurred when trying to establish connection to the microservice error stream: {}",
                        e.toString());
                throw Activity.wrap(ex);
            }
        }

        TranslationActivityOutput translation = new TranslationActivityOutput(builder.toString());
        logger.debug("Translation successful: {}", translation.getTranslation());
        return translation;
    }
}
