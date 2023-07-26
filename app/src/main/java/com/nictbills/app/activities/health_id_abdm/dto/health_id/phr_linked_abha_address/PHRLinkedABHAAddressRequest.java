package com.nictbills.app.activities.health_id_abdm.dto.health_id.phr_linked_abha_address;

public class PHRLinkedABHAAddressRequest {

    private String phrAddress;
    private boolean preferred;

    public String getPhrAddress() {
        return phrAddress;
    }

    public void setPhrAddress(String phrAddress) {
        this.phrAddress = phrAddress;
    }


    public boolean isPreferred() {
        return preferred;
    }

    public void setPreferred(boolean preferred) {
        this.preferred = preferred;
    }
}
