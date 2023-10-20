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
      throw Activity.wrap(e);
    }

    // TODO Modify the return value to use your output class
	String translation = content.toString();
    return translation;
  }
}
