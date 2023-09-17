package com.fwutech.oss.middleware.core.configs;

import com.fwutech.oss.middleware.commons.beans.base.Agent;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@EnableAutoConfiguration
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "agents")
public class AgentConfig {

    List<Agent> allowedAgents;

    public List<Agent> getAllowedAgents() {
        return allowedAgents;
    }

    public void setAllowedAgents(List<Agent> allowedAgents) {
        this.allowedAgents = allowedAgents;
    }
}
