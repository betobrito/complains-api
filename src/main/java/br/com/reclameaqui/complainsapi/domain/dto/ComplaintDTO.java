package br.com.reclameaqui.complainsapi.domain.dto;

import br.com.reclameaqui.complainsapi.domain.Complaint;
import br.com.reclameaqui.complainsapi.shared.validation.Create;
import br.com.reclameaqui.complainsapi.shared.validation.Edit;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Null;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class ComplaintDTO {

    @Null(groups = Create.class)
    public String id;

    @NotBlank(groups = {Create.class, Edit.class})
    public String title;

    public String description;

    @NotBlank(groups = {Create.class, Edit.class})
    public String locale;

    @NotBlank(groups = {Create.class, Edit.class})
    public String company;

    public ComplaintDTO() {
    }

    public ComplaintDTO(Complaint complaint) {
        this.id = complaint.getId();
        this.title = complaint.getTitle();
        this.description = complaint.getDescription();
        this.locale = complaint.getLocale();
        this.company = complaint.getCompany();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public static ComplaintDTO of(Complaint lei){
        return new ComplaintDTO(lei);
    }

    public Complaint toEntity(){
        return new Complaint(this.title, this.description, this.locale, this.company);
    }

    public static List<ComplaintDTO> convert(List<Complaint> complains){
        return complains.stream().map(ComplaintDTO::new).collect(Collectors.toList());
    }

    public ComplaintDTO id(String id){
        this.id = id;
        return this;
    }

    public ComplaintDTO title(String title){
        this.title = title;
        return this;
    }

    public ComplaintDTO description(String description){
        this.description = description;
        return this;
    }

    public ComplaintDTO locale(String locale){
        this.locale = locale;
        return this;
    }


    public ComplaintDTO company(String company){
        this.company = company;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ComplaintDTO that = (ComplaintDTO) o;
        return Objects.equals(title, that.title) &&
                Objects.equals(description, that.description) &&
                Objects.equals(locale, that.locale) &&
                Objects.equals(company, that.company);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, description, locale, company);
    }

    @Override
    public String toString() {
        return "ComplaintDTO{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", locale='" + locale + '\'' +
                ", company='" + company + '\'' +
                '}';
    }
}
