package core;

import java.sql.Date;

public class Main {

    public static void main(String[] args) {
        //Instantiate ReizigerDaoImpl
        ReizigerOracleDaoImpl dao = new ReizigerOracleDaoImpl();

        //Add new Reizigers
        Reiziger r1 = new Reiziger("Bart", Date.valueOf("1996-08-06"));
        dao.save(r1);
        Reiziger r2 = new Reiziger("Johan", Date.valueOf("2000-01-01"));
        dao.save(r2);
        Reiziger r3 = new Reiziger("André", Date.valueOf("2000-01-01"));
        dao.save(r3);
        Reiziger r4 = new Reiziger("Stan", Date.valueOf("2003-05-10"));
        dao.save(r4);

        //Print all Reizigers
        for(Reiziger r : dao.findAll()){
            System.out.println("Naam: " + r.getNaam() + ", Geboren: " + r.getGBdatum());
        }

        //Print all Reizigers where gbdatum = '2000-01-01'
        System.out.println("---");
        for(Reiziger r : dao.findByGBdatum("2000-01-01")) {
            System.out.println("Geboren op 2000-01-01: " + r.getNaam());
        }

        //Update Reiziger r2
        dao.update(r2, r2.getNaam() + " Junior", r2.getGBdatum());

        //Delete Reiziger r1.
        dao.delete(r1);

        //Select (print) all reizigers
        System.out.println("---");
        for(Reiziger r : dao.findAll()) {
            System.out.println("Naam: " + r.getNaam() + ", Geboren: " + r.getGBdatum());
        }

        dao.closeConnection();
    }
}
