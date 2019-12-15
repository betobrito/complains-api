package br.com.reclameaqui.complainsapi.service;

import br.com.reclameaqui.complainsapi.domain.Complaint;

public interface ComplaintService {

    Complaint find(String id);

    Complaint create(Complaint complaint);

    Complaint edit(String id, Complaint complaint);
}
