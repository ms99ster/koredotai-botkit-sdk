package koredotai.botkit.sdk.payload;

public interface Payload {
    public String getRequestId();

    public void setRequestId(String requestId);

    public String getBotId();

    public void setBotId(String botId);

    public String getComponentId();

    public void setComponentId(String componentId);
}
