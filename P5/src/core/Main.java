package core;

import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;

import domain.OVchipkaart;
import domain.Reiziger;
import persistency.OVchipkaartOracleDaoImpl;
import persistency.ReizigerOracleDaoImpl;
import persistency.SessionFactoryBuilder;

public class Main {

    private static ReizigerOracleDaoImpl reizigerDao;
    private static OVchipkaartOracleDaoImpl chipkaartDao;

    public static void main(String[] args) throws SQLException, ParseException {
        //Build new Oracle-based SessionFactory.
        SessionFactoryBuilder.buildOracleFactory();

        //Instantiate DAO's.
        reizigerDao = new ReizigerOracleDaoImpl();
        chipkaartDao = new OVchipkaartOracleDaoImpl();

        //READ (reizigerid=1)
        Reiziger r1 = reizigerDao.find(1);
        System.out.println("Reiziger (id=1): " + r1.getNaam());

        //CREATE
        Reiziger r2 = new Reiziger(20, "H", null, "Ibernate", Date.valueOf("2000-01-01"));
        reizigerDao.save(r2);

        //UPDATE
        r2.setAchternaam("Anders");
        reizigerDao.update(r2);
        System.out.println("\nNieuwe naam van reiziger 20: " + reizigerDao.find(20).getNaam());

        //DELETE
        reizigerDao.delete(r2);

        //Find all Reizigers with geboortedatum = '2002-12-03'
        System.out.println("\n--- Reizigers geboren op 03-12-2002 ---");
        for (Reiziger r : reizigerDao.findByGBdatum("2002-12-03")) {
            System.out.println(r.getNaam());
        }


        // --- Below are methods to demonstrate bi-directional one-to-many association between Reiziger and OVchipkaart

        //Print out which Reiziger is associated with a specified OV-chipkaart kaartnummer.
        OVchipkaart ov1 = chipkaartDao.find(35283);
        System.out.println("OV-chipkaart " + ov1.getKaartnummer() + " is gekoppeld aan: " + ov1.getReiziger().getNaam());

        //Find all Reizigers in database, and print their linked OV-Chipkaarten by iterating through them.
        System.out.println("\n--- Alle OV-chipkaarten van alle Reizigers ---");
        for (Reiziger r : reizigerDao.findAll()) {
            for(OVchipkaart ov : r.getOVchipkaarten()) {
                System.out.println(ov.getKaartnummer() + " staat op naam van: " + r.getNaam());
            }
        }

        //Close SessionFactory when done.
        SessionFactoryBuilder.closeOracleFactory();
    }
}