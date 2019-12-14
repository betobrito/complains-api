package br.com.reclameaqui.complainsapi.domain;

import javax.persistence.Id;
import java.util.Objects;

public class Complaint {

    @Id
    public String id;

    public String title;
    public String description;
    public String locale;
    public String company;

    public Complaint() {
    }

    public Complaint(String title, String description, String locale, String company) {
        this.title = title;
        this.description = description;
        this.locale = locale;
        this.company = company;
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
