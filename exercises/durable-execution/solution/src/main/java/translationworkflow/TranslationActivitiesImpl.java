package translationworkflow;

import java.net.URI;
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
    
    logger.info("[ACTIVITY INVOKED] translateTerm invoked with input term: {} language code: {}",
        term, lang);

    // construct the URL, with supplied input parameters, for accessing the microservice
    URL url = null;
    try {
      String baseUrl = "http://localhost:9999/translate?term=%s&lang=%s";
      url = URI.create(
              String.format(baseUrl, 
                URLEncoder.encode(term, "UTF-8"), 
                URLEncoder.encode(lang, "UTF-8")))
              .toURL();
    } catch (IOException e) {
      logger.error("An IOException occurred when creating the URL: {}", e.toString());
      throw Activity.wrap(e);
    }

    // Make the HTTP request, extract the translation from the response, and return it
    StringBuilder content = new StringBuilder();
    try (BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()))) {
      String line;
      while ((line = in.readLine()) != null) {
        content.append(line);
      }
    } catch (IOException e) {
      logger.error("An IOException occurred when accessing the microservice: {}", e.toString());
      throw Activity.wrap(e);
    }

    TranslationActivityOutput translation = new TranslationActivityOutput(content.toString());
    logger.debug("Translation successful: {}", translation.getTranslation());
    return translation;
  }
}
