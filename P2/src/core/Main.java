package core;

import java.sql.Date;
import java.sql.SQLException;

public class Main {

    public static ReizigerOracleDaoImpl reizigerDao;
    public static OVchipkaartDaoImpl chipkaartDao;

    public static void main(String[] args) throws SQLException {
        //Instantiate DAO's.
        reizigerDao = new ReizigerOracleDaoImpl();
        chipkaartDao = new OVchipkaartDaoImpl();

        //Add a new Reiziger.
        Reiziger r1 = new Reiziger(6,"B", null, "Hiemstra", Date.valueOf("2000-01-01"));
        reizigerDao.save(r1);


        //Select all Reizigers where gbdatum = '2000-01-01'
        for(Reiziger r : reizigerDao.findByGBdatum("2000-01-01", false)) {
            System.out.println("Geboren op 2000-01-01: " + r.getNaam());
        }

        //Print all Reizigers.
        System.out.println("\n--- Reizigers ---");
        for(Reiziger r : reizigerDao.findAll(true)){
            System.out.println("Naam: " + r.getNaam() + ", Geboren: " + r.getGBdatum());
        }
        System.out.println("---\n");

        //Update Reiziger r1
        System.out.println("Naam van reiziger nu: " + r1.getNaam());
        r1.setAchternaam("Anders");
        reizigerDao.update(r1);
        System.out.println("Naam van reiziger na UPDATE: " + reizigerDao.find(r1.getId(), true).getNaam());

        //Delete this reiziger.
        reizigerDao.delete(r1);

        //Print OV chipkaart with kaartnummer = 3.
        System.out.println("\n--- OV chipkaart ---");
        for(OVchipkaart ov : chipkaartDao.findByReiziger(3)) {
            System.out.println(ov.getKaartnummer());
        }

        // To demonstrate bi-directional one-to-many association,
        // iterate through all Reizigers and show their OV-chipkaarten, which in turn show their Reiziger naam.
        System.out.println("\n--- All OV-chipkaarten ---");
        for(Reiziger r : reizigerDao.findAll(true)) {
            for(OVchipkaart ov : r.getOVchipkaarten()) {
                System.out.println("Kaart: " + ov.getKaartnummer() + "(Geldig tot " + ov.getGeldigTot() + ") is van: " + ov.getReiziger().getNaam());
            }
        }
        System.out.println("---");


        reizigerDao.closeConnection();
        chipkaartDao.closeConnection();
    }
}
