package translationworkflow.model;

public class TranslationActivityOutput {
    private String translation;

    public TranslationActivityOutput() {
    }

    public TranslationActivityOutput(String translation) {
        this.translation = translation;
    }

    public String getTranslation() {
        return translation;
    }

    public void setTranslation(String translation) {
        this.translation = translation;
    }
}
