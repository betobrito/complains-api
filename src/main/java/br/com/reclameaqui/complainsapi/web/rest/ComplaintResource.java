package br.com.reclameaqui.complainsapi.web.rest;

import br.com.reclameaqui.complainsapi.domain.Complaint;
import br.com.reclameaqui.complainsapi.domain.ComplaintDTO;
import br.com.reclameaqui.complainsapi.service.ComplaintService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static br.com.reclameaqui.complainsapi.shared.Constantes.API_COMPLAINT;

@RestController
@RequestMapping(API_COMPLAINT)
public class ComplaintResource {

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
}
