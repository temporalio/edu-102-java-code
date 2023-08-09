package translationworkflow.model;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        TranslationActivityInput other = (TranslationActivityInput) o;
        return this.term.equals(other.term) &&
                this.languageCode.equals(other.languageCode);
    }

    @Override
    public int hashCode() {
        return new Objects.hash(term, languageCode);
    }
}
