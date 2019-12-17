package br.com.reclameaqui.complainsapi.repository;

import br.com.reclameaqui.complainsapi.domain.Complaint;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ComplaintRepository extends MongoRepository<Complaint, String> {

    List<Complaint> findComplaintsByLocale(String locale);
    Complaint findByTitle(String title);
    List<Complaint> findComplaintsByLocaleAndCompany(String locale, String company);
}
