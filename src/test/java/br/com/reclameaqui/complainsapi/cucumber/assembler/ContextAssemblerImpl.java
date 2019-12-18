package br.com.reclameaqui.complainsapi.cucumber.assembler;

import br.com.reclameaqui.complainsapi.cucumber.util.HelperContext;
import br.com.reclameaqui.complainsapi.domain.Complaint;
import br.com.reclameaqui.complainsapi.domain.dto.ComplaintDTO;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import static br.com.reclameaqui.complainsapi.shared.TestConstants.*;
import static br.com.reclameaqui.complainsapi.util.TestConstant.ID_ONE;

@Component
@Transactional(propagation = Propagation.REQUIRES_NEW)
public class ContextAssemblerImpl implements ContextAssembler {

    private HelperContext helperContext;

    public ContextAssemblerImpl(HelperContext helperContext) {
        this.helperContext = helperContext;
    }

    @Override
    public void clearTestData() {
        helperContext.clearTestData();
    }

    @Override
    public void addComplaints() {
        helperContext.insertComplaint(new Complaint()
                                            .id(ID_ONE)
                                            .title(TESTE)
                                            .description(TESTE)
                                            .locale(MACEIO)
                                            .company(COMPANY_MACEIO_SHOPPING));
    }

    @Override
    public ComplaintDTO findByTitle(String title) {
        final Complaint complaint = helperContext.findByTitle(title);
        return ComplaintDTO.of(complaint);
    }

    @Override
    public ComplaintDTO find(String id) {
        final Complaint complaint = helperContext.find(id);
        return null;
    }
}
