package br.com.reclameaqui.complainsapi.domain;

import org.springframework.data.annotation.Id;

import java.util.Objects;

public class Complaint {

    @Id
    private String id;

    private String title;
    private String description;
    private String locale;
    private String company;

    public Complaint() {
    }

    public Complaint(String title, String description, String locale, String company) {
        this.title = title;
        this.description = description;
        this.locale = locale;
        this.company = company;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getLocale() {
        return locale;
    }

    public String getCompany() {
        return company;
    }

    public Complaint title(String title){
        this.title = title;
        return this;
    }

    public Complaint description(String description){
        this.description = description;
        return this;
    }

    public Complaint locale(String locale){
        this.locale = locale;
        return this;
    }


    public Complaint company(String company){
        this.company = company;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Complaint complaint = (Complaint) o;
        return Objects.equals(title, complaint.title) &&
                Objects.equals(description, complaint.description) &&
                Objects.equals(locale, complaint.locale) &&
                Objects.equals(company, complaint.company);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, description, locale, company);
    }

    @Override
    public String toString() {
        return "Complains{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", locale='" + locale + '\'' +
                ", company='" + company + '\'' +
                '}';
    }
}
