package br.com.reclameaqui.complainsapi.domain;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class ComplaintDTO {

    public String title;
    public String description;
    public String locale;
    public String company;

    public ComplaintDTO() {
    }

    public ComplaintDTO(Complaint complaint) {
        this.title = complaint.getTitle();
        this.description = complaint.getDescription();
        this.locale = complaint.getLocale();
        this.company = complaint.getCompany();
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
