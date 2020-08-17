package core;

import java.sql.Date;
import java.util.List;
import java.util.ArrayList;

public class Reiziger {

    private int id;
    private String voorletters;
    private String tussenvoegsel;
    private String achternaam;
    private Date gbdatum;
    private List<OVchipkaart> OVchipkaarten = new ArrayList<>();

    public Reiziger(String voorletters, String tussenvoegsel, String achternaam, Date gbdatum) {
        this.id = 0;
        this.voorletters = voorletters;
        this.tussenvoegsel = tussenvoegsel;
        this.achternaam = achternaam;
        this.gbdatum = gbdatum;
    }

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

    public void addOVchipkaart(OVchipkaart ov) {
         OVchipkaarten.add(ov);
    }

    public List<OVchipkaart> getOVchipkaarten() {
        return OVchipkaarten;
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
