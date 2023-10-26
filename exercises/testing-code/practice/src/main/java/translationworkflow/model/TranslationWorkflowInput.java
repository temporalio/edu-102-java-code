package translationworkflow.model;

public class TranslationWorkflowInput {

  private String name;
  private String languageCode;

  public TranslationWorkflowInput() {
  }

  public TranslationWorkflowInput(String name, String languageCode) {
    this.name = name;
    this.languageCode = languageCode;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getLanguageCode() {
    return languageCode;
  }

  public void setLanguageCode(String languageCode) {
    this.languageCode = languageCode;
  }
}
