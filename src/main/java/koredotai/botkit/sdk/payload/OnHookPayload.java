package koredotai.botkit.sdk.payload;

import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.JsonNode;

import koredotai.botkit.sdk.lib.InvokePlatformAPI;

public class OnHookPayload extends BasePayload {

    private String callbackUrl;

    private String componentName;

    private String contextId;

    public OnHookPayload(String requestId, String botId, String componentId, JsonNode originalPayload) {
        super(requestId, botId, componentId, originalPayload);
        this.setPayloadClassName(this.getClass().getSimpleName());
        this.callbackUrl = originalPayload.get("callbackUrl").asText();
        this.componentName = originalPayload.get("dialogComponentName").asText();
        this.contextId = originalPayload.get("contextId").asText();
    }

    private JsonNode dialog;
    
    
    @Autowired
    InvokePlatformAPI invokePlatformAPI;

    @JsonIgnore
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

    public String getComponentName() {
        return componentName;
    }

    public void setComponentName(String componentName) {
        this.componentName = componentName;
    }

    public String getContextId() {
        return contextId;
    }

    public void setContextId(String contextId) {
        this.contextId = contextId;
    }
    
    @JsonIgnore
    public JsonNode getDialog() {
        return dialog;
    }

    public void setDialog(JsonNode dialog) {
        this.dialog = dialog;
    }

    @JsonIgnore
    public InvokePlatformAPI getInvokePlatformAPI() {
        return invokePlatformAPI;
    }

    public void setInvokePlatformAPI(InvokePlatformAPI invokePlatformAPI) {
        this.invokePlatformAPI = invokePlatformAPI;
    }
    
    @JsonIgnore
    public String getBaseUrl() {
        return super.getBaseUrl();
    }

}
