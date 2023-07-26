package com.nictbills.app.activities.health_id_abdm.dto.health_id.health_id_init;

public class HealthIdInitRequest {

    private String authMethod,healthid;

    public String getAuthMethod() {
        return authMethod;
    }

    public void setAuthMethod(String authMethod) {
        this.authMethod = authMethod;
    }

    public String getHealthid() {
        return healthid;
    }

    public void setHealthid(String healthid) {
        this.healthid = healthid;
    }
}
