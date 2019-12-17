package br.com.reclameaqui.complainsapi.cucumber.assembler;

import br.com.reclameaqui.complainsapi.domain.dto.ComplaintDTO;

public interface ContextAssembler {

    void clearTestData();
    void addComplaints();
    ComplaintDTO findByTitle(String id);
}
