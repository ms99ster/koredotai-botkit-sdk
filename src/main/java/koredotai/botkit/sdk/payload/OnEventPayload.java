package koredotai.botkit.sdk.payload;

import com.fasterxml.jackson.databind.JsonNode;

public class OnEventPayload extends BasePayload {
    private boolean agentTransfer;
    private JsonNode event;

    public OnEventPayload(String requestId, String botId, String componentId, JsonNode originalPayload) {
        super(requestId, botId, componentId, originalPayload);
        this.setPayloadClassName(this.getClass().getName());
        JsonNode agent_transfer = originalPayload.get("agent_transfer");
        this.agentTransfer = agent_transfer == null ? false : agent_transfer.asBoolean();
        this.event = originalPayload.get("event");
    }

    public JsonNode getEvent() {
        return event;
    }

    public void setEvent(JsonNode event) {
        this.event = event;
    }

    public boolean isAgentTransfer() {
        return agentTransfer;
    }

    public void setAgentTransfer(boolean agentTransfer) {
        this.agentTransfer = agentTransfer;
    }

}
