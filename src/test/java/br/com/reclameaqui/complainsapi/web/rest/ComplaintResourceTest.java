package br.com.reclameaqui.complainsapi.web.rest;

import br.com.reclameaqui.complainsapi.domain.Complaint;
import br.com.reclameaqui.complainsapi.domain.dto.ComplaintDTO;
import br.com.reclameaqui.complainsapi.domain.dto.SearchParameterDTO;
import br.com.reclameaqui.complainsapi.service.ComplaintService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ComplaintResourceTest {

  public static final String ID_ONE = "1";
  public static final String TEST = "Test";

  @Mock
  private ComplaintService complaintServiceMock;

  private ComplaintResource complaintResource;
  private ComplaintDTO complaintDTO;
  private Complaint complaint;
  private List<Complaint> complains;
  private SearchParameterDTO searchParameter;

  @Before
  public void context() {
    this.complaintResource = new ComplaintResource(complaintServiceMock);
    this.complaint = new Complaint();
    this.complaintDTO = new ComplaintDTO(complaint);
    this.complains = new ArrayList<>();
    this.searchParameter = new SearchParameterDTO();
    this.searchParameter.setLocale(TEST);
    this.searchParameter.setCompany(TEST);
  }

  @Test
  public void shouldCallMethodGetSpecificComplaintDelegatingToTheService() {
    when(complaintServiceMock.find(ID_ONE)).thenReturn(complaint);

    ResponseEntity resultado = complaintResource.find(ID_ONE);

    verify(complaintServiceMock).find(ID_ONE);
    assertEquals(HttpStatus.OK, resultado.getStatusCode());
    assertEquals(complaintDTO, resultado.getBody());
  }

  @Test
  public void shouldCallMethodCreateDelegatingToTheConvertAndCreate() throws URISyntaxException {
    when(complaintServiceMock.create(complaint)).thenReturn(complaint);

    ResponseEntity resultado = complaintResource.create(complaintDTO);

    verify(complaintServiceMock).create(complaint);
    assertEquals(HttpStatus.CREATED, resultado.getStatusCode());
    assertEquals(complaintDTO, resultado.getBody());
  }

  @Test
  public void shouldCallMethodEditDelegatingToTheConvertAndEdit() throws URISyntaxException {
    when(complaintServiceMock.edit(ID_ONE, complaint)).thenReturn(complaint);

    ResponseEntity resultado = complaintResource.edit(ID_ONE, complaintDTO);

    verify(complaintServiceMock).edit(ID_ONE, complaint);
    assertEquals(HttpStatus.CREATED, resultado.getStatusCode());
    assertEquals(complaintDTO, resultado.getBody());
  }

  @Test
  public void shouldCallMethodListByLocaleDelegatingToTheRepository() {
    when(complaintServiceMock.listByLocale(TEST)).thenReturn(complains);

    ResponseEntity resultado = complaintResource.listByLocale(searchParameter);

    verify(complaintServiceMock).listByLocale(TEST);
    assertEquals(HttpStatus.OK, resultado.getStatusCode());
    assertEquals(this.complains, resultado.getBody());
  }

  @Test
  public void shouldCallMethodListByLocaleAndCompanyDelegatingToTheRepository() {
    when(complaintServiceMock.listByLocaleAndCompany(TEST, TEST)).thenReturn(complains);

    ResponseEntity resultado = complaintResource.listByLocaleAndCompany(searchParameter);

    verify(complaintServiceMock).listByLocaleAndCompany(TEST, TEST);
    assertEquals(HttpStatus.OK, resultado.getStatusCode());
    assertEquals(this.complains, resultado.getBody());
  }

  @Test
  public void shouldCallMethodDeleteComplaintDelegatingToTheService() {
    ResponseEntity resultado = complaintResource.delete(ID_ONE);

    verify(complaintServiceMock).delete(ID_ONE);
    assertEquals(HttpStatus.OK, resultado.getStatusCode());
  }
}
