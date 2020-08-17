package domain;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "ov_chipkaart")
public class OVchipkaart {

    @Id
    @Column(name = "KAARTNUMMER")
    private int kaartnummer;

    @Column(name = "GELDIGTOT")
    private Date geldigTot;

    @Column(name = "KLASSE")
    private int klasse;

    @Column(name = "SALDO")
    private double saldo;

    @ManyToOne
    @JoinColumn(name="reizigerid")
    private Reiziger reiziger;

    public OVchipkaart() { }

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
