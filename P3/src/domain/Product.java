package domain;

import java.util.ArrayList;
import java.util.List;

public class Product {

    private int productNummer;

    private String productNaam;
    private String beschrijving;
    private double prijs;

    private List<OVchipkaart> gekoppeldeOVchipkaarten = new ArrayList<>();

    public Product(int productNummer, String productNaam, String beschrijving, double prijs) {
        this.productNummer = productNummer;
        this.productNaam = productNaam;
        this.beschrijving = beschrijving;
        this.prijs = prijs;
    }

    public int getProductNummer() {
        return productNummer;
    }

    public void setProductNummer(int productNummer) {
        this.productNummer = productNummer;
    }

    public void addOVchipkaart(OVchipkaart ov) {
        gekoppeldeOVchipkaarten.add(ov);
    }

    public List<OVchipkaart> getGekoppeldeOVchipkaarten() {
        return gekoppeldeOVchipkaarten;
    }

    public String getProductNaam() {
        return productNaam;
    }

    public void setProductNaam() {
        this.productNaam = productNaam;
    }

    public String getBeschrijving() {
        return beschrijving;
    }

    public void setBeschrijving(String beschrijving) {
        this.beschrijving = beschrijving;
    }

    public double getPrijs() {
        return prijs;
    }

    public void setPrijs(double prijs) {
        this.prijs = prijs;
    }
}
