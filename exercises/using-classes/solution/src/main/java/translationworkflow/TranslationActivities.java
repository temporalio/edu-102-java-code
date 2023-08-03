package translationworkflow;

import io.temporal.activity.ActivityInterface;
import translationworkflow.model.TranslationActivityInput;
import translationworkflow.model.TranslationActivityOutput;

@ActivityInterface
public interface TranslationActivities {

    TranslationActivityOutput translateTerm(TranslationActivityInput input);

}
