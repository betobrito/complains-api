package br.com.reclameaqui.complainsapi.web.rest;

import br.com.reclameaqui.complainsapi.domain.Complaint;
import br.com.reclameaqui.complainsapi.domain.ComplaintDTO;
import br.com.reclameaqui.complainsapi.service.ComplaintService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ComplaintResourceTest {

  public static final String ID_ONE = "1";

  @Mock
  private ComplaintService complaintServiceMock;

  private ComplaintResource complaintResource;
  private ComplaintDTO complaintDTO;
  private Complaint complaint;
  private Optional<Complaint> optionalComplaint;
  private List<Complaint> complains;

  @Before
  public void context() {
    this.complaintResource = new ComplaintResource(complaintServiceMock);
    this.complaint = new Complaint();
    this.complaintDTO = new ComplaintDTO(complaint);
    this.optionalComplaint = Optional.of(complaint);
    this.complains = new ArrayList<>();
  }

  @Test
  public void shouldCallMethodGetSpecificPlaceDelegatingToTheService() {
    when(complaintServiceMock.find(ID_ONE)).thenReturn(complaint);

    ResponseEntity resultado = complaintResource.find(ID_ONE);

    verify(complaintServiceMock).find(ID_ONE);
    assertEquals(HttpStatus.OK, resultado.getStatusCode());
    assertEquals(complaintDTO, resultado.getBody());
  }

}
