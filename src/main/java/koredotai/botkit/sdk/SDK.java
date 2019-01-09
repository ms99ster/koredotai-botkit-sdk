package koredotai.botkit.sdk;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import koredotai.botkit.sdk.lib.InvokeSendMessageAPI;
import koredotai.botkit.sdk.payload.OnMessagePayload;
import koredotai.botkit.sdk.payload.Payload;

@Component
public class SDK {

    @Autowired
    InvokeSendMessageAPI sendMessageAPI;
    
    public void sendUserMessage(Payload payload, Object callback) throws Exception {
        OnMessagePayload onMessagePayload = (OnMessagePayload)payload;
        if(null == onMessagePayload.getSendUserMessageUrl()) {
            throw new Exception("send user message url is missing");
        }
        sendMessageAPI.sendUserMessage(onMessagePayload, callback);

    }
    
    public void sendBotMessage(Payload payload, Object callback) throws Exception {
        OnMessagePayload onMessagePayload = (OnMessagePayload)payload;
        if(null == onMessagePayload.getSendBotMessageUrl()) {
            throw new Exception("send bot message url is missing");
        }
        sendMessageAPI.sendBotMessage(onMessagePayload, callback);
    }
}
