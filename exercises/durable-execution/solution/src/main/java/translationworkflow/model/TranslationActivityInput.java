package translationworkflow.model;

public class TranslationActivityInput {

    private String term;
    private String languageCode;

    public TranslationActivityInput() {

    }

    public TranslationActivityInput(String term, String languageCode) {
        this.term = term;
        this.languageCode = languageCode;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public String getLanguageCode() {
        return languageCode;
    }

    public void setLanguageCode(String languageCode) {
        this.languageCode = languageCode;
    }

}
