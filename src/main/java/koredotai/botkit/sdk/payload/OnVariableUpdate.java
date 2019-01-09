package koredotai.botkit.sdk.payload;

import com.fasterxml.jackson.databind.JsonNode;

public class OnVariableUpdate extends BasePayload {

    private boolean ackMessage;
    private boolean agentTransfer;
    private String eventType;

    private String language;
    private JsonNode variable;

    public OnVariableUpdate(String requestId, String botId, String componentId, JsonNode originalPayload) {
        super(requestId, botId, componentId, originalPayload);
        this.setPayloadClassName(this.getClass().getName());
        this.ackMessage = true;
        this.agentTransfer = originalPayload.get("agent_transfer") == null ? false : originalPayload.get("agent_transfer").asBoolean();
        this.eventType = originalPayload.get("data").get("eventType").asText();
        this.language = originalPayload.get("data").get("language").asText();
        this.variable = originalPayload.get("data").get("variable");
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public JsonNode getVariable() {
        return variable;
    }

    public void setVariable(JsonNode variable) {
        this.variable = variable;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public boolean isAgentTransfer() {
        return agentTransfer;
    }

    public void setAgentTransfer(boolean agentTransfer) {
        this.agentTransfer = agentTransfer;
    }

    public boolean isAckMessage() {
        return ackMessage;
    }

    public void setAckMessage(boolean ackMessage) {
        this.ackMessage = ackMessage;
    }

}
