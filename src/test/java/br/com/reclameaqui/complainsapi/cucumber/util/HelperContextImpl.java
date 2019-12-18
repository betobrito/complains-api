package br.com.reclameaqui.complainsapi.cucumber.util;

import br.com.reclameaqui.complainsapi.domain.Complaint;
import br.com.reclameaqui.complainsapi.repository.ComplaintRepository;
import br.com.reclameaqui.complainsapi.shared.Constants;
import org.springframework.stereotype.Component;

@Component
public class HelperContextImpl implements HelperContext {

    private final ComplaintRepository complaintRepository;

    public HelperContextImpl(ComplaintRepository complaintRepository) {
        this.complaintRepository = complaintRepository;
    }

    @Override
    public void clearTestData() {
        complaintRepository.deleteAll();
    }

    @Override
    public void insertComplaint(Complaint complaint) {
        complaintRepository.save(complaint);
    }

    @Override
    public Complaint findByTitle(String title) {
        return complaintRepository.findByTitle(title);
    }

    @Override
    public Complaint find(String id) {
        return complaintRepository.findById(id).orElseThrow(() -> new RuntimeException(Constants.Messages.MSG_NO_COMPLAINTS_FOUND));
    }

}
