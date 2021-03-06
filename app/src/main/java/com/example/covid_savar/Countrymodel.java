package com.example.covid_savar;

public class Countrymodel {
    private  String flag,country,cases,todaycases,deaths,todaydeaths,recovered,active,critical,asia,updated;

    public Countrymodel() {
    }

    public Countrymodel(String flag, String country, String cases, String todaycases, String deaths, String todaydeaths, String recovered, String active, String critical,String asia,String updated) {
        this.flag = flag;
        this.country = country;
        this.cases = cases;
        this.todaycases = todaycases;
        this.deaths = deaths;
        this.todaydeaths = todaydeaths;
        this.recovered = recovered;
        this.active = active;
        this.critical = critical;
        this.asia=asia;
        this.updated=updated;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getUpdated() {
        return updated;
    }

    public void setUpdated(String updated) {
        this.updated = updated;
    }

    public String getCountry() {
        return country;
    }

    public String getAsia() {
        return asia;
    }

    public void setAsia(String asia) {
        this.asia = asia;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCases() {
        return cases;
    }

    public void setCases(String cases) {
        this.cases = cases;
    }

    public String getTodaycases() {
        return todaycases;
    }

    public void setTodaycases(String todaycases) {
        this.todaycases = todaycases;
    }

    public String getDeaths() {
        return deaths;
    }

    public void setDeaths(String deaths) {
        this.deaths = deaths;
    }

    public String getTodaydeaths() {
        return todaydeaths;
    }

    public void setTodaydeaths(String todaydeaths) {
        this.todaydeaths = todaydeaths;
    }

    public String getRecovered() {
        return recovered;
    }

    public void setRecovered(String recovered) {
        this.recovered = recovered;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public String getCritical() {
        return critical;
    }

    public void setCritical(String critical) {
        this.critical = critical;
    }
}
