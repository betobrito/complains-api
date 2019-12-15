package br.com.reclameaqui.complainsapi.service;

import br.com.reclameaqui.complainsapi.domain.Complaint;

import java.util.List;

public interface ComplaintService {

    Complaint find(String id);

    Complaint create(Complaint complaint);

    Complaint edit(String id, Complaint complaint);

    List<Complaint> listByLocale(String locale);

    List<Complaint> listByLocaleAndCompany(String locale, String company);

    void delete(String id);
}
