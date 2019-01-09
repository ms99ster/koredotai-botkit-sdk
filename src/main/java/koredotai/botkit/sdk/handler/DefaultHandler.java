package koredotai.botkit.sdk.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import koredotai.botkit.sdk.SDK;
import koredotai.botkit.sdk.payload.Payload;

@Component
public class DefaultHandler implements BotHandler {

    private static final Logger log = LoggerFactory.getLogger(DefaultHandler.class);
    
    @Autowired
    SDK sdk;
    
    @Override
    public Payload onUserMessage(String requestId, Payload payload, Object callback) throws Exception {
        log.debug("requestId: "+ requestId + "   payload:"+payload + "   callback"+callback);
        sdk.sendBotMessage(payload, callback);
        //sdk.sendUserMessage(payload, callback);
        return null;
    }

    @Override
    public Payload onBotMessage(String requestId, Payload payload, Object callback) throws Exception {
        log.debug("requestId"+ requestId + "   payload:"+payload + "   callback"+callback);
        sdk.sendUserMessage(payload, callback);
        return null;
    }

    @Override
    public Payload onWebhook(String requestId, Payload payload, Object callback) {
        String componentName = null;
        log.debug("requestId"+ requestId + "   payload:"+payload + "  componentName "+ componentName + "   callback"+callback);
        return null;
    }

    @Override
    public Payload onAgentTransfer(String requestId, Payload payload, Object callback) {
        log.debug("requestId"+ requestId + "   payload:"+payload + "   callback"+callback);
        return null;
    }
    @Override
    public Payload onEvent(String requestId, Payload payload, Object callback) throws Exception {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Payload onAlert(String requestId, Payload payload, Object callback) throws Exception {
        // TODO Auto-generated method stub
        return null;
    }
    @Override
    public String getBotId() {
        // TODO Auto-generated method stub
        return "default";
    }

    @Override
    public String getBotName() {
        // TODO Auto-generated method stub
        return "default";
    }



}
