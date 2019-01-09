package koredotai.botkit.sdk.handler;

import koredotai.botkit.sdk.payload.Payload;

public interface BotHandler {

    public String getBotId();
    
    public String getBotName();
    
    public Payload onUserMessage(String requestId, Payload payload, Object callback) throws Exception;
    
    public Payload onBotMessage(String requestId, Payload payload, Object callback) throws Exception;
    
    public Payload onWebhook(String requestId, Payload payload, Object callback) throws Exception;
    
    public Payload onAgentTransfer(String requestId, Payload payload, Object callback) throws Exception;
    
    public Payload onEvent(String requestId, Payload payload, Object callback) throws Exception;
    
    public Payload onAlert(String requestId, Payload payload, Object callback) throws Exception;
}
