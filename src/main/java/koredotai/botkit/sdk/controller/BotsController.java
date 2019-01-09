package koredotai.botkit.sdk.controller;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import koredotai.botkit.sdk.EventMapping;
import koredotai.botkit.sdk.configuration.Credentials;
import koredotai.botkit.sdk.handler.BotHandler;
import koredotai.botkit.sdk.payload.Payload;

@RestController()
public class BotsController {

    private static final Logger log = LoggerFactory.getLogger(BotsController.class);

    @Autowired(required = false)
    List<BotHandler> botHandlers;

    @Autowired
    Credentials credentials;

    @RequestMapping(value = "/test", method = { RequestMethod.GET, RequestMethod.POST })
    public ResponseEntity<String> justTest() {
        String resultString ="ssssssssssssssssss";
        HttpHeaders headers = new HttpHeaders();
        headers.set("supportsMessageAck", Boolean.toString(true));
        headers.set("content-type", "application/json");
        ResponseEntity<String> response = new ResponseEntity<String>(resultString, headers, HttpStatus.OK);// new ResponseEntity<String>(resultString, HttpStatus.OK);
        return response;
        
    }
    
    @RequestMapping(value = "/test1", method = { RequestMethod.GET, RequestMethod.POST })
    public String justTest1() {
       return "ssssssssssssssss";
        
    }

    @RequestMapping(value = "/sdk/bots/{botId}/{eventName}", method = { RequestMethod.GET, RequestMethod.POST })
    public ResponseEntity<String> onComponentEvent(HttpServletRequest req, HttpServletResponse res, @PathVariable String botId, @PathVariable String eventName, @RequestBody JsonNode body,
            @RequestHeader(value = "apikey", required = false) String apikeyHeader) throws Exception {
        return this.onComponentEvent(req, res, botId, "default", eventName, body, apikeyHeader);
    }

    @RequestMapping(value = "/sdk/bots/{botId}/components/{componentId}/{eventName}", method = RequestMethod.POST)
    public ResponseEntity<String> onComponentEvent(HttpServletRequest req, HttpServletResponse res, @PathVariable String botId, @PathVariable String componentId, @PathVariable String eventName,
            @RequestBody JsonNode body, @RequestHeader(value = "apikey", required = false) String apikeyHeader) throws Exception {
        log.warn("qqqqqqqqqqqqqqq" + res.getHeaderNames().size());
        res.getHeaderNames().stream().forEach(log::warn);
        ;
        /*
         * log.warn(credentials.getApikey() + "========="+credentials.getAppId());
         * Map<String, Credential> bots = credentials.getBots(); for (Iterator iterator
         * = bots.keySet().iterator(); iterator.hasNext();) { String key = (String)
         * iterator.next(); log.error(key); log.error(bots.get(key).getApikey() + "===="
         * +bots.get(key).getAppId()); }
         */

        log.debug(">>>>>>>>>>>>>>>>>" + botHandlers);
        log.debug(">>>>>>>>>>>>>>>>>" + botHandlers.size());
        /*
         * for (Iterator iterator = botHandlers.keySet().iterator();
         * iterator.hasNext();) { String type = (String) iterator.next();
         * log.debug(">>>>>>>>>>>>>>>>>>>>>>>>" + type);
         * 
         * }
         */
        Payload payload = this.runComponentHandler(botId, componentId, eventName, body);

        ObjectMapper mapper = new ObjectMapper();
        String resultString = mapper.writeValueAsString(payload);
        HttpHeaders headers = new HttpHeaders();
        headers.set("supportsMessageAck", Boolean.toString(true));
        headers.set("content-type", "application/json");
        ResponseEntity<String> response = new ResponseEntity<String>(resultString, headers, HttpStatus.OK);// new ResponseEntity<String>(resultString, HttpStatus.OK);
        return response;
    }

    private BotHandler getBotHanlerByBotId(String botId) {
        BotHandler defaultHandler = null;
        for (BotHandler botHandler : botHandlers) {
            if (botHandler.getBotId().equals(botId)) {
                return botHandler;
            } else if (botHandler.getBotId().equals("default")) {
                defaultHandler = botHandler;
            }
        }
        return defaultHandler;
    }

    public Payload runComponentHandler(String botId, String componentId, String eventName, JsonNode reqPayload) throws Exception {

        BotHandler botHandler = getBotHanlerByBotId(botId);
        log.debug("find bothandlar with botId: " + botHandler.getBotId());
        EventMapping em = EventMapping.resolve(eventName);
        if (null == em) {
            return null;// TODO should be error
        }

        if (em.isSendComponentNameInArgs()) {
            // TODO call the handler function with componentName in arguments
        } else {
            // TODO call the handler function without componentName in arguments
        }

        String requestId;
        Payload payload;
        requestId = reqPayload.get("requestId").asText();
        log.debug("EventMapping: " + em.getEventName() + "  Payload Class: " + em.getPayloadClass().getName());
        @SuppressWarnings("unchecked")
        Constructor<Payload> constructor = (Constructor<Payload>) em.getPayloadClass().getConstructor(String.class, String.class, String.class, JsonNode.class);
        payload = constructor.newInstance(requestId, botId, componentId, reqPayload);
        Method callBackMethod = botHandler.getClass().getMethod(em.getCallbackName(), em.getCallbackArgs());
        Object response = em.isSendComponentNameInArgs() ? callBackMethod.invoke(botHandler, requestId, payload, null) : callBackMethod.invoke(botHandler, requestId, payload, null);

        if (em.getPayloadClass().isInstance(response)) {
            payload = (Payload) response;
            log.debug("|||||||||||||||||||||||||||||||||||||||||||||||||||||||| " + payload.toString());
        } else {
            log.debug("|| || || || || || || || || || || || || || || || || || || " + response);
        }

        return payload;
    }
}
