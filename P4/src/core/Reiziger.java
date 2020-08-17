package core;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "REIZIGER")
public class Reiziger {

    @Id
    @Column(name = "REIZIGERID")
    private int id;

    @Column(name = "VOORLETTERS")
    private String voorletters;

    @Column(name = "TUSSENVOEGSEL")
    private String tussenvoegsel;

    @Column(name = "ACHTERNAAM")
    private String achternaam;

    @Column(name = "GEBORTEDATUM")
    private Date gbdatum;

    public Reiziger() { }

    public Reiziger(int id, String voorletters, String tussenvoegsel, String achternaam, Date gbdatum) {
        this.id = id;
        this.voorletters = voorletters;
        this.tussenvoegsel = tussenvoegsel;
        this.achternaam = achternaam;
        this.gbdatum = gbdatum;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNaam() {
        return voorletters + "." + (tussenvoegsel == null ? "" : " " + tussenvoegsel) + " " + achternaam;
    }

    public String getVoorletters() {
        return voorletters;
    }

    public void setVoorletters(String voorletters) {
        this.voorletters = voorletters;
    }

    public String getTussenvoegsel() {
        return tussenvoegsel;
    }

    public void setTussenvoegsel(String tussenvoegsel) {
        this.tussenvoegsel = tussenvoegsel;
    }

    public String getAchternaam() {
        return achternaam;
    }

    public void setAchternaam(String achternaam) {
        this.achternaam = achternaam;
    }

    public Date getGBdatum() {
        return gbdatum;
    }

    public void setGBdatum(Date gbdatum) {
        this.gbdatum = gbdatum;
    }
}
