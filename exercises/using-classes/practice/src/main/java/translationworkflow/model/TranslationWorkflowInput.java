package translationworkflow.model;

public class TranslationWorkflowInput {
  private final String name;
  private final String languageCode;

  public TranslationWorkflowInput(String name, String languageCode) {
    this.name = name;
    this.languageCode = languageCode;
  }

  public String getName() {
    return name;
  }

  public String getLanguageCode() {
    return languageCode;
  }
}
