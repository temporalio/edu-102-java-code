package ageestimationworkflow;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLEncoder;
import java.time.Instant;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.JsonProcessingException;

import ageestimationworkflow.model.EstimatorResponse;

import io.temporal.activity.Activity;


public class AgeEstimationActivitiesImpl implements AgeEstimationActivities {

  @Override
  public int retrieveEstimate(String name) {

    StringBuilder builder = new StringBuilder();
    ObjectMapper objectMapper = new ObjectMapper();

    String baseUrl = "https://api.agify.io/?name=%s";

    URL url = null;
    try {
      url = new URL(String.format(baseUrl, name, URLEncoder.encode(name, "UTF-8")));
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

    EstimatorResponse response;
    try {
      response = objectMapper.readValue(builder.toString(), EstimatorResponse.class);
    } catch (JsonProcessingException e) {
      throw Activity.wrap(e);
    }

    return response.getAge();
  }
}
