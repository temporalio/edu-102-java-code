package translationworkflow.model;

public class TranslationWorkflowOutput {
  private String helloMessage;
  private String goodbyeMessage;

  public TranslationWorkflowOutput() {

  }

  public TranslationWorkflowOutput(String helloMessage, String goodbyeMessage) {
    this.helloMessage = helloMessage;
    this.goodbyeMessage = goodbyeMessage;
  }

  public String getHelloMessage() {
    return helloMessage;
  }

  public void setHelloMessage(String helloMessage) {
    this.helloMessage = helloMessage;
  }

  public String getGoodbyeMessage() {
    return goodbyeMessage;
  }

  public void setGoodbyeMessage(String goodbyeMessage) {
    this.goodbyeMessage = goodbyeMessage;
  }

  public String toString() {
    return this.helloMessage + "\n" + this.goodbyeMessage;
  }
}
