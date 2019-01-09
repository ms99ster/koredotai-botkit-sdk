package koredotai.botkit.sdk.lib;

import java.net.URI;
import java.net.URISyntaxException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import koredotai.botkit.sdk.configuration.Credential;
import koredotai.botkit.sdk.configuration.Credentials;


@Component
public class InvokePlatformAPI {

    private static final Logger LOG = LoggerFactory.getLogger(InvokePlatformAPI.class);

    RestTemplate restTemplate = new RestTemplate();

    @Autowired
    Credentials credentials;

    public <T> ResponseEntity<T> post(String url, Object body, Class<T> responseType, Object callback) {
        return this.makeRequest(url, HttpMethod.POST, body, responseType, null);
    }

    public <T> ResponseEntity<T> get(String url, Class<T> responseType, Object callback) {
        return this.makeRequest(url, HttpMethod.GET, null, responseType, null);
    }

    public <T> ResponseEntity<T> getWithOptions(String url, Class<T> responseType, Object opts, Object callback) {
        return this.makeRequest(url, HttpMethod.GET, null, responseType, opts);
    }

    private <T> ResponseEntity<T> makeRequest(String url, HttpMethod method, Object body, Class<T> responseType, Object opts) {
        String botId = url.split("/")[6];
        Credential credential = credentials.getBots().get(botId);

        String apiKey = credential == null ? credentials.getApikey() : credential.getApikey();
        String appId = credential == null ? credentials.getAppId() : credential.getAppId();

        String jwtToken = getSignedJWTToken(apiKey, appId);

        HttpHeaders headers = new HttpHeaders();// TODO should get headers from opts if opts not null
        headers.set(HttpHeaders.CONTENT_TYPE, "application/json");
        headers.set("auth", jwtToken);

        URI uri = null;
        try {
            uri = new URI(url);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        LOG.debug("body: " + body);
        LOG.debug("headers: " + headers);
        LOG.debug("uri: " + uri);

        RequestEntity<Object> requestEntity = new RequestEntity<Object>(body, headers, method, uri);
        return restTemplate.exchange(requestEntity, responseType);
    }

    private String getSignedJWTToken(String apiKey, String appId) {

        String token = JWT.create().withClaim("appId", appId).sign(Algorithm.HMAC256(apiKey));
        LOG.debug("getSignedJWTToken: " + token);
        return token;
    }
}
