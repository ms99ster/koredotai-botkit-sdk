package koredotai.botkit.sdk.configuration;

import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "credentials")
public class Credentials extends Credential {

    private Map<String, Credential> bots;

    public Map<String, Credential> getBots() {
        return bots;
    }

    public void setBots(Map<String, Credential> bots) {
        this.bots = bots;
    }

}