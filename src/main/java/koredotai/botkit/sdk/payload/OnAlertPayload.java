package koredotai.botkit.sdk.payload;

import com.fasterxml.jackson.databind.JsonNode;

public class OnAlertPayload extends BasePayload {

    private String sendAlertMessageUrl;

    public OnAlertPayload(String requestId, String botId, String componentId, JsonNode originalPayload) {
        super(requestId, botId, componentId, originalPayload);
        this.setPayloadClassName(this.getClass().getName());
        this.sendAlertMessageUrl = originalPayload.get("sendAlertMessageUrl").asText();
    }

    public String getSendAlertMessageUrl() {
        return sendAlertMessageUrl;
    }

    public void setSendAlertMessageUrl(String sendAlertMessageUrl) {
        this.sendAlertMessageUrl = sendAlertMessageUrl;
    }

}
