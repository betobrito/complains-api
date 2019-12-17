package br.com.reclameaqui.complainsapi.shared;

public interface Constants {

    String BAR = "/";
    String API_COMPLAINT = "/complaint";

    interface Messages {
        String MSG_NO_LOCATIONS_FOUND = "No locations found with this id.";
    }

    interface Jwt {
        String KEY_USER = "user";
        String KEY_ROLES = "roles";
    }
}
