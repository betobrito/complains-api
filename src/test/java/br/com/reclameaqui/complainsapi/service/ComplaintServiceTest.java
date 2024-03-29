package br.com.reclameaqui.complainsapi.service;

import br.com.reclameaqui.complainsapi.domain.Complaint;
import br.com.reclameaqui.complainsapi.repository.ComplaintRepository;
import br.com.reclameaqui.complainsapi.service.impl.ComplaintServiceImpl;
import br.com.reclameaqui.complainsapi.shared.exception.NotFoundException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static br.com.reclameaqui.complainsapi.shared.Constants.Messages.MSG_NO_COMPLAINTS_FOUND;
import static br.com.reclameaqui.complainsapi.shared.TestConstants.Messages.MSG_THIS_METHOD_SHOULD_NOT_BE_CALLED;
import static br.com.reclameaqui.complainsapi.util.TestConstant.ID_ONE;
import static br.com.reclameaqui.complainsapi.util.TestConstant.TEST;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ComplaintServiceTest {

    @Mock
    private ComplaintRepository complaintRepositoryMock;
    private ComplaintService complaintService;

    private Complaint complaint;
    private Optional<Complaint> optionalComplaint;
    private List<Complaint> complains;

    @Before
    public void context() {
        this.complaintService = new ComplaintServiceImpl(complaintRepositoryMock);
        this.complaint = new Complaint();
        this.optionalComplaint = Optional.of(complaint);
        this.complains = new ArrayList<>();
    }

    @Test
    public void shouldCallMethodFindDelegatingToTheRepository() {
        when(complaintRepositoryMock.findById(ID_ONE)).thenReturn(this.optionalComplaint);

        final Complaint complaintReturning = complaintService.find(ID_ONE);

        verify(complaintRepositoryMock).findById(ID_ONE);
        assertEquals(complaintReturning, this.complaint);
    }

    @Test
    public void shouldCallMethodGetSpecificThrowingExceptionNotFound() {
        when(complaintRepositoryMock.findById(ID_ONE)).thenReturn(Optional.empty());

        try{
            complaintService.find(ID_ONE);
            fail(MSG_THIS_METHOD_SHOULD_NOT_BE_CALLED);
        } catch (NotFoundException e) {
            assertEquals(MSG_NO_COMPLAINTS_FOUND, e.getMessage());
        }
    }

    @Test
    public void shouldCallMethodCreateDelegatingToTheRepository() {
        when(complaintRepositoryMock.save(this.complaint)).thenReturn(this.complaint);

        final Complaint returnComplaint = complaintService.create(this.complaint);

        verify(complaintRepositoryMock).save(this.complaint);
        assertEquals(returnComplaint, this.complaint);
    }

    @Test
    public void shouldCallMethodEditDelegatingToTheRepository() {
        when(complaintRepositoryMock.findById(ID_ONE)).thenReturn(optionalComplaint);
        when(complaintRepositoryMock.save(complaint)).thenReturn(complaint);

        final Complaint editedComplaint = complaintService.edit(ID_ONE, this.complaint);

        verify(complaintRepositoryMock).findById(ID_ONE);
        assertEquals(this.complaint, editedComplaint);
    }

    @Test
    public void shouldCallMethodEditWithComplaintNotFoundThrowingExceptionNotFound() {
        try{
            complaintService.edit(ID_ONE, this.complaint);
            fail(MSG_THIS_METHOD_SHOULD_NOT_BE_CALLED);
        } catch (NotFoundException e) {
            assertEquals(MSG_NO_COMPLAINTS_FOUND, e.getMessage());
        }
    }

    @Test
    public void shouldCallMethodListByLocaleDelegatingToTheRepository() {
        when(complaintRepositoryMock.findComplaintsByLocale(TEST)).thenReturn(complains);

        List<Complaint> resultado = complaintService.listByLocale(TEST);

        verify(complaintRepositoryMock).findComplaintsByLocale(TEST);
        assertEquals(this.complains, resultado);
    }

    @Test
    public void shouldCallMethodListByLocaleAndCompanyDelegatingToTheRepository() {
        when(complaintRepositoryMock.findComplaintsByLocaleAndCompany(TEST,TEST)).thenReturn(complains);

        List<Complaint> resultado = complaintService.listByLocaleAndCompany(TEST, TEST);

        verify(complaintRepositoryMock).findComplaintsByLocaleAndCompany(TEST, TEST);
        assertEquals(this.complains, resultado);
    }

    @Test
    public void shouldCallMethodDeleteDelegatingToTheRepository() {
        when(complaintRepositoryMock.findById(ID_ONE)).thenReturn(this.optionalComplaint);

        complaintService.delete(ID_ONE);

        verify(complaintRepositoryMock).findById(ID_ONE);
        verify(complaintRepositoryMock).delete(complaint);
    }

    @Test
    public void shouldCallMethodDeleteThrowingExceptionNotFound() {
        when(complaintRepositoryMock.findById(ID_ONE)).thenReturn(Optional.empty());

        try{
            complaintService.delete(ID_ONE);
            fail(MSG_THIS_METHOD_SHOULD_NOT_BE_CALLED);
        } catch (NotFoundException e) {
            assertEquals(MSG_NO_COMPLAINTS_FOUND, e.getMessage());
        }
    }
}
