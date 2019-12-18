package br.com.reclameaqui.complainsapi.cucumber.stepdefs;

import br.com.reclameaqui.complainsapi.domain.dto.ComplaintDTO;
import br.com.reclameaqui.complainsapi.shared.ApiErrorDTO;
import br.com.reclameaqui.complainsapi.shared.Constants;
import br.com.reclameaqui.complainsapi.util.JsonConverter;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.io.UnsupportedEncodingException;
import java.util.List;

import static br.com.reclameaqui.complainsapi.shared.Constants.API_COMPLAINT;
import static br.com.reclameaqui.complainsapi.shared.Constants.BAR;
import static br.com.reclameaqui.complainsapi.shared.Constants.Messages.MSG_NO_COMPLAINTS_FOUND;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class FunctionalityStepDefs extends StepDefs {

    @Before
    public void context() {
        contextAssembler.clearTestData();
    }

    @Given("^that there is a registered complaint$")
    public void thatThereIsAhRegisteredComplaint() {
        contextAssembler.addComplaints();
    }

    @Given("that the complaint entered has id {string}")
    public void thatTheComplaintEnteredHasId(String id) throws Exception {
        this.actions = mockGet(API_COMPLAINT+"/{id}", id);
    }

    @Then("should return a complaint title {string} and locale {string}")
    public void shouldReturnAComplaintTitleAndLocale(String title, String locale) throws Exception {
        this.actions.andExpect(status().isOk());
        this.actions.andExpect(MockMvcResultMatchers.jsonPath("$.title").value(title));
        this.actions.andExpect(MockMvcResultMatchers.jsonPath("$.locale").value(locale));
    }

    @Then("should return an error with status not found")
    public void shouldReturnAnErrorWithStatusNotFound() throws Exception {
        this.actions.andExpect(status().isNotFound());
    }

    @Then("should return an error with status bad request")
    public void shouldReturnAnErrorWithStatusBadRequest() throws Exception {
        this.actions.andExpect(status().isBadRequest());
    }

    @And("should return a error message {string}")
    public void shouldReturnAErrorMessage(String message) throws Exception {
        this.actions.andExpect(MockMvcResultMatchers.jsonPath("$.message").value(message));
    }

    @Given("Since you should register a complaint with the following information: title {string}, locale {string}, company {string}")
    public void sinceYouShouldRegisterAComplaintWithTheFollowingInformationTitleLocaleCompany(String title, String locale, String company) throws Exception {
        ComplaintDTO complaintDTO = new ComplaintDTO().title(title).locale(locale).company(company);
        mockPost(API_COMPLAINT, complaintDTO);
    }

    @Then("should return a complaint with title {string}, locale {string}, company {string}")
    public void shouldReturnAComplaintWithTitleLocaleCompany(String title, String locale, String company) throws Exception {
        this.actions.andExpect(status().isCreated());
        final ComplaintDTO complaint = contextAssembler.findByTitle(title);
        assertEquals(title, complaint.getTitle());
        assertEquals(locale, complaint.getLocale());
        assertEquals(company, complaint.getCompany());
    }

    @And("should return a error message list {string}")
    public void shouldReturnAErrorMessageList(String message) throws UnsupportedEncodingException {
        List<ApiErrorDTO> erros = JsonConverter.asJsonToClassList(this.actions.andReturn().getResponse().getContentAsString(), ApiErrorDTO.class);
        assertFalse(erros.isEmpty());
        assertEquals(message, erros.get(0).getMessage());
    }

    @Given("Since you should edited a complaint with the following information: id {string} title {string}, locale {string}, company {string}")
    public void sinceYouShouldEditedAComplaintWithTheFollowingInformationIdTitleLocaleCompany(String id, String title, String locale, String company) throws Exception {
        ComplaintDTO complaintDTO = new ComplaintDTO().id(id).title(title).locale(locale).company(company);
        mockPut(API_COMPLAINT + BAR + id, complaintDTO);
    }

    @Given("that the complaint to be deleted has id {string}")
    public void thatTheComplaintToBeDeletedHasId(String id) throws Exception {
        mockDelete(API_COMPLAINT+"/{id}", id);
    }

    @Then("should return status code ok and no objects found with id {string}")
    public void shouldReturnStatusCodeOkAndNoObjectsFoundWithId(String id) throws Exception {
        try{
            contextAssembler.find(id);
        }catch (RuntimeException e){
            this.actions.andExpect(status().isOk());
            assertEquals(MSG_NO_COMPLAINTS_FOUND, e.getMessage());
        }
    }
}
