package br.com.reclameaqui.complainsapi.service.impl;

import br.com.reclameaqui.complainsapi.domain.Complaint;
import br.com.reclameaqui.complainsapi.repository.ComplaintRepository;
import br.com.reclameaqui.complainsapi.service.ComplaintService;
import br.com.reclameaqui.complainsapi.shared.exception.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static br.com.reclameaqui.complainsapi.shared.Constants.Messages.MSG_NO_LOCATIONS_FOUND;

@Service
@Transactional
public class ComplaintServiceImpl implements ComplaintService {

    private final ComplaintRepository complaintRepository;

    public ComplaintServiceImpl(ComplaintRepository complaintRepository) {
        this.complaintRepository = complaintRepository;
    }

    @Override
    public Complaint find(String id) {
        final Optional<Complaint> optionalComplaint = complaintRepository.findById(id);
        return optionalComplaint.orElseThrow(() -> new NotFoundException(MSG_NO_LOCATIONS_FOUND));
    }

    @Override
    public Complaint create(Complaint complaint) {
        return complaintRepository.save(complaint);
    }

    @Override
    public Complaint edit(Complaint modifiedComplaint) {
        final Complaint complaint = checkIfExistsComplaint(modifiedComplaint);
        return modifyingAttributesAllowed(modifiedComplaint, complaint);
    }

    private Complaint modifyingAttributesAllowed(Complaint modifiedComplaint, Complaint complaint) {
        return complaint.title(modifiedComplaint.getTitle())
                        .description(modifiedComplaint.getDescription())
                        .locale(modifiedComplaint.getLocale())
                        .company(modifiedComplaint.getCompany());
    }

    private Complaint checkIfExistsComplaint(Complaint modifiedComplaint) {
        return find(modifiedComplaint.getId());
    }
}
