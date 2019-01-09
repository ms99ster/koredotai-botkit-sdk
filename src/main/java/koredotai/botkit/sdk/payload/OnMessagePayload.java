package koredotai.botkit.sdk.payload;

import com.fasterxml.jackson.databind.JsonNode;

public class OnMessagePayload extends BasePayload {
    private boolean ackMessage;
    private boolean agent_transfer;

    public OnMessagePayload(String requestId, String botId, String componentId, JsonNode originalPayload) {
        super(requestId, botId, componentId, originalPayload);
        this.setPayloadClassName(this.getClass().getName());
        this.agent_transfer = originalPayload.get("agent_transfer") == null ? false : originalPayload.get("agent_transfer").asBoolean();
        this.ackMessage = true;
    }

    public boolean isAgent_transfer() {
        return agent_transfer;
    }

    public void setAgent_transfer(boolean agent_transfer) {
        this.agent_transfer = agent_transfer;
    }

    public boolean isAckMessage() {
        return ackMessage;
    }

    public void setAckMessage(boolean ackMessage) {
        this.ackMessage = ackMessage;
    }

}
