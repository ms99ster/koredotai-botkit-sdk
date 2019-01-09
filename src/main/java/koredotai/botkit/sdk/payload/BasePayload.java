package koredotai.botkit.sdk.payload;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class BasePayload implements Payload {
    private String requestId;
    private String botId;
    private String componentId;

    private JsonNode _originalPayload;
    private String baseUrl;
    private JsonNode channel;
    private JsonNode context;
    private String getBotVariableUrl;
    private String message;
    private JsonNode metaInfo;
    private String payloadClassName;
    private String resetBotUrl;
    private String sendBotMessageUrl;
    private String sendUserMessageUrl;

    public BasePayload(String requestId, String botId, String componentId, JsonNode originalPayload) {
        super();
        this.requestId = requestId;
        this.botId = botId;
        this.componentId = componentId;
        this._originalPayload = originalPayload;
        this.baseUrl = jsonNode2String(originalPayload.get("baseUrl"));

        this.channel = originalPayload.get("channel");
        this.context = originalPayload.get("context");
        this.getBotVariableUrl = jsonNode2String(originalPayload.get("getBotVariableUrl"));
        this.message = jsonNode2String(originalPayload.get("message"));
        this.metaInfo = originalPayload.get("metaInfo");
        this.resetBotUrl = jsonNode2String(originalPayload.get("resetBotUrl"));
        this.sendBotMessageUrl = jsonNode2String(originalPayload.get("sendBotMessageUrl"));
        this.sendUserMessageUrl = jsonNode2String(originalPayload.get("sendUserMessageUrl"));
    }

    private String jsonNode2String(JsonNode jsonNode) {
        if (null == jsonNode) {
            return null;
        }
        return jsonNode.asText();
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getBotId() {
        return botId;
    }

    public void setBotId(String botId) {
        this.botId = botId;
    }

    public String getComponentId() {
        return componentId;
    }

    public void setComponentId(String componentId) {
        this.componentId = componentId;
    }

    @JsonIgnore
    public JsonNode get_originalPayload() {
        return _originalPayload;
    }

    public void set_originalPayload(JsonNode _originalPayload) {
        this._originalPayload = _originalPayload;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public JsonNode getChannel() {
        return channel;
    }

    public void setChannel(JsonNode channel) {
        this.channel = channel;
    }

    public JsonNode getContext() {
        return context;
    }

    public void setContext(JsonNode context) {
        this.context = context;
    }

    public String getGetBotVariableUrl() {
        return getBotVariableUrl;
    }

    public void setGetBotVariableUrl(String getBotVariableUrl) {
        this.getBotVariableUrl = getBotVariableUrl;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @JsonIgnore
    public JsonNode getMetaInfo() {
        return metaInfo;
    }

    public void setMetaInfo(JsonNode metaInfo) {
        this.metaInfo = metaInfo;
    }

    @JsonProperty("__payloadClass")
    public String getPayloadClassName() {
        return this.payloadClassName;
    }

    public void setPayloadClassName(String payloadClassName) {
        this.payloadClassName = payloadClassName;
    }

    @JsonIgnore
    public String getResetBotUrl() {
        return resetBotUrl;
    }

    public void setResetBotUrl(String resetBotUrl) {
        this.resetBotUrl = resetBotUrl;
    }

    public String getSendBotMessageUrl() {
        return sendBotMessageUrl;
    }

    public void setSendBotMessageUrl(String sendBotMessageUrl) {
        this.sendBotMessageUrl = sendBotMessageUrl;
    }

    public String getSendUserMessageUrl() {
        return sendUserMessageUrl;
    }

    public void setSendUserMessageUrl(String sendUserMessageUrl) {
        this.sendUserMessageUrl = sendUserMessageUrl;
    }

    public String toString() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;

    }

}
