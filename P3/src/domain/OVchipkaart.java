package domain;

import java.sql.Date;
import java.util.List;
import java.util.ArrayList;

public class OVchipkaart {

    private int kaartnummer;

    private Date geldigTot;
    private int klasse;
    private double saldo;

    private Reiziger reiziger;
    private List<Product> gekoppeldeProducten = new ArrayList<>();

    public OVchipkaart(int kaartnummer, Date geldigTot, int klasse, double saldo) {
        this.kaartnummer = kaartnummer;
        this.geldigTot = geldigTot;
        this.klasse = klasse;
        this.saldo = saldo;
        this.reiziger = reiziger;
    }

    public int getKaartnummer() {
        return kaartnummer;
    }

    public Reiziger getReiziger() {
        return reiziger;
    }

    public void setReiziger(Reiziger reiziger) {
        this.reiziger = reiziger;
    }

    public void addProduct(Product p) {
        gekoppeldeProducten.add(p);
    }

    public List<Product> getGekoppeldeProducten() {
        return gekoppeldeProducten;
    }

    public int getKlasse() {
        return klasse;
    }

    public void setKlasse(int klasse) {
        this.klasse = klasse;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public Date getGeldigTot() {
        return geldigTot;
    }

    public void setGeldigTot(Date geldigTot) {
        this.geldigTot = geldigTot;
    }
}
