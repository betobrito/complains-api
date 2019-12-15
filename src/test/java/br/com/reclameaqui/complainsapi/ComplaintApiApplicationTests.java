package br.com.reclameaqui.complainsapi;

import br.com.reclameaqui.complainsapi.domain.Complaint;
import br.com.reclameaqui.complainsapi.repository.ComplaintRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static br.com.reclameaqui.complainsapi.shared.TestConstants.*;
import static org.junit.Assert.assertTrue;

@SpringBootTest
class ComplaintApiApplicationTests {

	private ComplaintRepository repository;
	private Complaint complaintOne;
	private Complaint complaintTwo;

	@Autowired
	public ComplaintApiApplicationTests(ComplaintRepository repository) {
		this.repository = repository;
	}

	@BeforeEach
	public void context() {
		repository.deleteAll();
		this.complaintOne = new Complaint(TESTE, TESTE, MACEIO, COMPANY_MACEIO_SHOPPING);
		repository.save(complaintOne);
		this.complaintTwo = new Complaint(TESTE_2, TESTE_2, MACEIO, COMPANY_PARQUE_SHOPPING);
		repository.save(complaintTwo);
	}

	@Test
	public void checkIfTestObjectHasBeenInserted(){
		final List<Complaint> complaints = repository.findComplaintsByCompany(COMPANY_MACEIO_SHOPPING);
		assertTrue(complaints.contains(this.complaintOne));
	}
}
