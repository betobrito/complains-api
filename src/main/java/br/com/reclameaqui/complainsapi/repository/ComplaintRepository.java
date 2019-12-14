package br.com.reclameaqui.complainsapi.repository;

import br.com.reclameaqui.complainsapi.domain.Complaint;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ComplaintRepository extends MongoRepository<Complaint, String> {


    Complaint findByTitle(String title);
    List<Complaint> findComplaintsByCompany(String company);
    List<Complaint> findComplaintsByLocale(String locale);

}