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
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    final TranslationActivityInput other = (TranslationActivityInput) obj;
    if (!Objects.equals(this.term, other.term)) {
      return false;
    }
    return Objects.equals(this.languageCode, other.languageCode);
  }

  @Override
  public int hashCode() {
    int hash = 3;
    hash = 53 * hash + Objects.hashCode(this.term);
    hash = 53 * hash + Objects.hashCode(this.languageCode);
    return hash;
  }  
}
