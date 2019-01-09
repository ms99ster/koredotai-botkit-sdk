package koredotai.botkit.sdk;

import koredotai.botkit.sdk.payload.OnAgentTransferPayload;
import koredotai.botkit.sdk.payload.OnAlertPayload;
import koredotai.botkit.sdk.payload.OnEventPayload;
import koredotai.botkit.sdk.payload.OnHookPayload;
import koredotai.botkit.sdk.payload.OnMessagePayload;
import koredotai.botkit.sdk.payload.OnVariableUpdate;
import koredotai.botkit.sdk.payload.Payload;

public enum EventMapping {
    /**
     * 
     */
    on_user_message("on_user_message", OnMessagePayload.class, "onUserMessage", false, true, false, String.class, Payload.class, Object.class),
    /**
     * 
     */
    on_bot_message("on_bot_message", OnMessagePayload.class, "onBotMessage", false, true, false, String.class, Payload.class, Object.class),
    /**
     * 
     */
    on_webhook("on_webhook", OnHookPayload.class, "onWebhook", true, false, true, String.class, Payload.class, Object.class),
    /**
     * 
     */
    on_agent_transfer("on_agent_transfer", OnAgentTransferPayload.class, "onAgentTransfer", false, true, true, String.class, Payload.class, Object.class),
    /**
     * 
     */
    on_event("on_event", OnEventPayload.class, "onEvent", false, true, false, String.class, Payload.class, Object.class),
    /**
     * 
     */
    on_alert("on_alert", OnAlertPayload.class, "onAlert", false, true, false, String.class, Payload.class, Object.class),
    /**
     * Ø
     * 
     */
    on_variable_update("on_variable_update", OnVariableUpdate.class, "on_variable_update", false, true, true);

    public static EventMapping resolve(String eventName) {
        for (EventMapping eventMapping : values()) {
            if (eventMapping.eventName.equals(eventName)) {
                return eventMapping;
            }
        }
        return null;
    }

    private final String eventName;

    private final Class<?> payloadClass;
    private final String callbackName;
    private final Class<?>[] callbackArgs;

    private final boolean sendComponentNameInArgs;
    private final boolean ackImmediately;
    private final boolean processResponse;

    private EventMapping(String eventName, Class<?> payloadClass, String callbackName, boolean sendComponentNameInArgs, boolean ackImmediately, boolean processResponse, Class<?>... callbackArgs) {
        this.eventName = eventName;
        this.payloadClass = payloadClass;
        this.callbackName = callbackName;
        this.sendComponentNameInArgs = sendComponentNameInArgs;
        this.ackImmediately = ackImmediately;
        this.processResponse = processResponse;
        this.callbackArgs = callbackArgs;
    }

    public String getEventName() {
        return eventName;
    }

    public Class<?>[] getCallbackArgs() {
        return callbackArgs;
    }

    public Class<?> getPayloadClass() {
        return payloadClass;
    }

    public String getCallbackName() {
        return callbackName;
    }

    public boolean isSendComponentNameInArgs() {
        return sendComponentNameInArgs;
    }

    public boolean isAckImmediately() {
        return ackImmediately;
    }

    public boolean isProcessResponse() {
        return processResponse;
    }

}
