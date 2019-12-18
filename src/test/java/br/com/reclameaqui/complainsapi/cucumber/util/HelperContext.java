package br.com.reclameaqui.complainsapi.cucumber.util;

import br.com.reclameaqui.complainsapi.domain.Complaint;

public interface HelperContext {

    void clearTestData();

    void insertComplaint(Complaint complaint);

    Complaint findByTitle(String title);

    Complaint find(String id);
}
