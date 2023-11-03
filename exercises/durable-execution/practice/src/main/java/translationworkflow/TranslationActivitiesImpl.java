package translationworkflow;

import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URI;
import java.net.URL;
import java.net.URLEncoder;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import io.temporal.activity.Activity;
import io.temporal.failure.ApplicationFailure;
import java.net.HttpURLConnection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import translationworkflow.model.TranslationActivityInput;
import translationworkflow.model.TranslationActivityOutput;

public class TranslationActivitiesImpl implements TranslationActivities {

  private static final Logger logger = LoggerFactory.getLogger(TranslationActivitiesImpl.class);

  @Override
  public TranslationActivityOutput translateTerm(TranslationActivityInput input) {
    String term = input.getTerm();
    String lang = input.getLanguageCode();

    // TODO: Add an info log statement here with the string [ACTIVITY INVOKED]
    // at the beginning along with the name of the Activity and parameters passed

    // construct the URL, with supplied input parameters, for accessing the
    // microservice
    URL url = null;
    try {
      String baseUrl = "http://localhost:9999/translate?term=%s&lang=%s";
      url = URI.create(
          String.format(baseUrl,
              URLEncoder.encode(term, "UTF-8"),
              URLEncoder.encode(lang, "UTF-8")))
          .toURL();
    } catch (IOException e) {
      // TODO: Add an error log statement here at the error level about the exception
      throw Activity.wrap(e);
    }

    TranslationActivityOutput result = new TranslationActivityOutput();

    try {
      // Open a connection to the URL
      HttpURLConnection connection = (HttpURLConnection) url.openConnection();

      // Set the HTTP request method (GET, POST, etc.)
      connection.setRequestMethod("GET");

      // Get the response code
      int responseCode = connection.getResponseCode();

      if (responseCode == HttpURLConnection.HTTP_OK) {
        // If the response code is 200 (HTTP OK), the request was successful
        BufferedReader reader = new BufferedReader(
            new InputStreamReader(connection.getInputStream()));
        String line;
        StringBuilder response = new StringBuilder();

        while ((line = reader.readLine()) != null) {
          response.append(line);
        }

        reader.close();

        connection.disconnect();
        result.setTranslation(response.toString());

      } else {
        // If the response code is not 200, there was an error
        BufferedReader errorReader = new BufferedReader(
            new InputStreamReader(connection.getErrorStream()));
        String line;
        StringBuilder errorResponse = new StringBuilder();

        while ((line = errorReader.readLine()) != null) {
          errorResponse.append(line);
        }

        errorReader.close();

        connection.disconnect();

        // TODO: Add an error log statement here detailing the exception
        
        throw ApplicationFailure.newFailure(errorResponse.toString(), IOException.class.getName());
      }

    } catch (IOException e) {
      e.printStackTrace();
    }
    // TODO: Add a debug log statement here stating the Translation was successful
    // include the output
    
    return result;
  }

}
