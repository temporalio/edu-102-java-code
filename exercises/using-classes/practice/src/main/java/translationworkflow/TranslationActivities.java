package translationworkflow;

import io.temporal.activity.ActivityInterface;
import translationworkflow.model.TranslationActivityInput;
import translationworkflow.model.TranslationActivityOutput;

@ActivityInterface
public interface TranslationActivities {

  // TODO Replace the two input parameters with the class you defined as input
  // TODO Replace the return value (String) with the name of the class you defined
  // as output
  String translateTerm(String term, String languageCode);

}
