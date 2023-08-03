package translationworkflow.model;

public class TranslationWorkflowOutput {
    private final String helloMessage;
    private final String goodbyeMessage;

    public TranslationWorkflowOutput(String helloMessage, String goodbyeMessage) {
        this.helloMessage = helloMessage;
        this.goodbyeMessage = goodbyeMessage;
    }

    public String getHelloMessage() {
        return helloMessage;
    }

    public String getGoodbyeMessage() {
        return goodbyeMessage;
    }

    public String toString() {
        return this.helloMessage + "\n" + this.goodbyeMessage;
    }
}
