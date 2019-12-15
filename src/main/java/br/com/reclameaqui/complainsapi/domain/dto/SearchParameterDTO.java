package br.com.reclameaqui.complainsapi.domain.dto;

public class SearchParameterDTO {

    private String locale;
    private String company;

    public SearchParameterDTO() {
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
}
