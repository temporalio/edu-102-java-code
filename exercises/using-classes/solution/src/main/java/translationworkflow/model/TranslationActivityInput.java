package translationworkflow.model;

public class TranslationActivityInput {
    private final String term;
    private final String languageCode;

    public TranslationActivityInput(String term, String languageCode) {
        this.term = term;
        this.languageCode = languageCode;
    }

    public String getTerm() {
        return term;
    }

    public String getLanguageCode() {
        return languageCode;
    }

}
