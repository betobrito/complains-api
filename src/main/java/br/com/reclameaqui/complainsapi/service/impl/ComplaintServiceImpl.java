package br.com.reclameaqui.complainsapi.service.impl;

import br.com.reclameaqui.complainsapi.domain.Complaint;
import br.com.reclameaqui.complainsapi.service.ComplaintService;
import org.springframework.stereotype.Service;

@Service
public class ComplaintServiceImpl implements ComplaintService {
    @Override
    public Complaint find(String id) {
        throw new UnsupportedOperationException("Not implemented.");
    }
}
