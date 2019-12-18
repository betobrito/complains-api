package br.com.reclameaqui.complainsapi.shared;

public interface Constants {

    String BAR = "/";
    String API_COMPLAINT = "/api/complaint";

    interface Messages {
        String MSG_NO_COMPLAINTS_FOUND = "No complaints found with this id.";
    }

    interface Jwt {
        String KEY_USER = "user";
        String KEY_ROLES = "roles";
    }
}
