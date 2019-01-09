package koredotai.botkit.sdk.lib;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.JsonNode;

import koredotai.botkit.sdk.payload.OnMessagePayload;

@Component
public class InvokeSendMessageAPI {

    private static final Logger log = LoggerFactory.getLogger(InvokeSendMessageAPI.class);

    @Autowired
    InvokePlatformAPI invokePlatformAPI;

    public void sendUserMessage(OnMessagePayload payload, Object callback) {
        String url = payload.getSendUserMessageUrl();
        ResponseEntity<JsonNode> res = invokePlatformAPI.post(url, payload, JsonNode.class, callback);
        log.debug("sendUserMessage result::: status: " + res.getStatusCode() + "body: " + res.getBody());
    }

    public void sendBotMessage(OnMessagePayload payload, Object callback) {
        String url = payload.getSendBotMessageUrl();
        ResponseEntity<JsonNode> res = invokePlatformAPI.post(url, payload, JsonNode.class, callback);
        log.debug("sendBotMessage result::: status: " + res.getStatusCode() + "body: " + res.getBody());
    }
}
