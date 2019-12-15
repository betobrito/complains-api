package br.com.reclameaqui.complainsapi.web.rest;

import br.com.reclameaqui.complainsapi.domain.Complaint;
import br.com.reclameaqui.complainsapi.domain.dto.ComplaintDTO;
import br.com.reclameaqui.complainsapi.domain.dto.SearchParameterDTO;
import br.com.reclameaqui.complainsapi.service.ComplaintService;
import br.com.reclameaqui.complainsapi.shared.validation.Create;
import br.com.reclameaqui.complainsapi.shared.validation.Edit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import static br.com.reclameaqui.complainsapi.domain.dto.ComplaintDTO.convert;
import static br.com.reclameaqui.complainsapi.shared.Constants.API_COMPLAINT;

@RestController
@RequestMapping(API_COMPLAINT)
public class ComplaintResource {

    public static final String BAR = "/";
    private final Logger log = LoggerFactory.getLogger(ComplaintResource.class);

    private final ComplaintService complaintService;

    public ComplaintResource(ComplaintService complaintService) {
        this.complaintService = complaintService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ComplaintDTO> find(@PathVariable String id) {
        log.debug("Rest call method to get specific complaint by id: {}", id);
        Complaint complaint = complaintService.find(id);
        return ResponseEntity.ok().body(ComplaintDTO.of(complaint));
    }

    @PostMapping
    public ResponseEntity<ComplaintDTO> create(@Validated(Create.class) @RequestBody ComplaintDTO complaintDTO) throws URISyntaxException {
        log.debug("Rest call method create complaint: {}", complaintDTO);
        final Complaint insertedComplaint = complaintService.create(complaintDTO.toEntity());
        return ResponseEntity
                .created(new URI(API_COMPLAINT + BAR + insertedComplaint.getId()))
                .body(ComplaintDTO.of(insertedComplaint));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ComplaintDTO> edit(@PathVariable String id, @Validated(Edit.class) @RequestBody ComplaintDTO complaint) throws URISyntaxException {
        log.debug("Rest call method edit complaint: {}", complaint);
        final Complaint editedComplaint = complaintService.edit(id, complaint.toEntity());
        return ResponseEntity
                .created(new URI(API_COMPLAINT + BAR  + editedComplaint.getId()))
                .body(ComplaintDTO.of(editedComplaint));
    }

    @PostMapping("/locale")
    public ResponseEntity<List<ComplaintDTO>> listByLocale(@RequestBody SearchParameterDTO searchParameter) {
        log.debug("Rest call method to list complaints by locale: {}", searchParameter.getLocale());
        List<Complaint> complaints = complaintService.listByLocale(searchParameter.getLocale());
        return ResponseEntity.ok().body(convert(complaints));
    }

    @PostMapping("/locale/company")
    public ResponseEntity<List<ComplaintDTO>> listByLocaleAndCompany(@RequestBody SearchParameterDTO searchParameter) {
        log.debug("Rest call method to list complains by locale and company: {} - {}", searchParameter.getLocale(), searchParameter.getCompany());
        List<Complaint> complaints = complaintService.listByLocaleAndCompany(searchParameter.getLocale(), searchParameter.getCompany());
        return ResponseEntity.ok().body(convert(complaints));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ComplaintDTO> delete(@PathVariable String id) {
        log.debug("Rest call method to delete complaint by id: {}", id);
        complaintService.delete(id);
        return ResponseEntity.ok().build();
    }
}
