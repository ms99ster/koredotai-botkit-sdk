package koredotai.botkit.sdk.payload;

import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.databind.JsonNode;

import koredotai.botkit.sdk.lib.InvokePlatformAPI;

public class OnAgentTransferPayload extends BasePayload {

    private String callbackUrl;

    private JsonNode dialog;
    @Autowired
    InvokePlatformAPI invokePlatformAPI;

    public OnAgentTransferPayload(String requestId, String botId, String componentId, JsonNode originalPayload) {
        super(requestId, botId, componentId, originalPayload);
        this.setPayloadClassName(this.getClass().getName());
        this.callbackUrl = originalPayload.get("callbackUrl").asText();
    }

    public JsonNode getDialog(Object callback) throws Exception {
        if (this.get_originalPayload().get("getDialogUrl") == null) {
            throw new Exception();
        }
        if (this.dialog == null) {
            this.dialog = invokePlatformAPI.get(this.get_originalPayload().get("getDialogUrl").asText(), JsonNode.class, callback).getBody();
        }
        return this.dialog;
    }

    public String getCallbackUrl() {
        return callbackUrl;
    }

    public void setCallbackUrl(String callbackUrl) {
        this.callbackUrl = callbackUrl;
    }

}
