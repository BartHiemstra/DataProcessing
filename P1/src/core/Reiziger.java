package core;

import java.sql.Date;

//POJO
public class Reiziger {

    private String naam;
    private Date gbdatum;

    public Reiziger(String naam, Date gbdatum) {
        this.naam = naam;
        this.gbdatum = gbdatum;
    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public Date getGBdatum() {
        return gbdatum;
    }

    public void setGBdatum(Date gbdatum) {
        this.gbdatum = gbdatum;
    }
}
