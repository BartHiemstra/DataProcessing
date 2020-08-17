package core;

import domain.OVchipkaart;
import domain.Product;

import domain.Reiziger;
import persistency.OVchipkaartOracleDaoImpl;
import persistency.ProductOracleDaoImpl;
import persistency.ReizigerOracleDaoImpl;

import java.sql.SQLException;

public class Main {

    public static ReizigerOracleDaoImpl reizigerDao;
    public static OVchipkaartOracleDaoImpl chipkaartDao;
    public static ProductOracleDaoImpl productDao;

    public static void main(String[] args) throws SQLException {

        //Instantiate DAO's.
        reizigerDao = new ReizigerOracleDaoImpl();
        chipkaartDao = new OVchipkaartOracleDaoImpl();
        productDao = new ProductOracleDaoImpl();

        //Haal Product met productNummer 1 op.
        Product p1 = productDao.find(1, true);
        System.out.println(p1.getProductNaam() + ": " + p1.getBeschrijving() + " (€" + p1.getPrijs() + ")");

        //Update p1.
        p1.setPrijs(p1.getPrijs() + 1.0);
        productDao.update(p1);
        System.out.println("Nieuwe prijs van Product met productnummer 1: €" + productDao.find(1, true).getPrijs());

        //Voeg nieuw Product toe.
        Product p2 = new Product(8, "Nieuw Product", "Dit is een gloednieuw product.", 15.2);
        productDao.save(p2);

        //Print all Producten.
        System.out.println("\n--- Alle producten ---");
        for(Product p : productDao.findAll(true)) {
            System.out.println(p.getProductNaam());
        }

        //Delete the product from the database.
        productDao.delete(p2);

        //Print all Producten.
        System.out.println("\n--- Alle producten ---");
        for(Product p : productDao.findAll(true)) {
            System.out.println(p.getProductNaam());
        }

        // To demonstrate the bi-directional many-to-many association between OV-chipkaart and Product,
        // iterate through all Reizigers, then iterate through all their OV-chipkaarten, then iterate through all their Products, then display their product name.
        System.out.println("\n\n--- Alle Producten, binnen alle OV-chipkaarten ---");
        for(Reiziger r : reizigerDao.findAll(true)) {
            for(OVchipkaart ov : r.getOVchipkaarten()) {
                for(Product p : ov.getGekoppeldeProducten()) {
                    System.out.println("Product: [" + p.getProductNaam() + "] is gekoppeld aan OV-chipkaart: [" + ov.getKaartnummer() + "] en staat op naam van: "  + ov.getReiziger().getNaam());
                }
            }
        }
        System.out.println("---");

        // To demonstrate the other way around,
        // get the first product from the first OV-chipkaart from Reiziger (reizigerid=2), and display all associated OV-chipkaarten.
        Reiziger r = reizigerDao.find(2, true);
        OVchipkaart ov = r.getOVchipkaarten().get(0);
        Product p = ov.getGekoppeldeProducten().get(0);
        for(OVchipkaart kaart : p.getGekoppeldeOVchipkaarten()) {
            System.out.println("Gevonden OV-kaart voor Product " + p.getProductNummer() + ": [" + kaart.getKaartnummer() + "]");
        }


        reizigerDao.closeConnection();
        chipkaartDao.closeConnection();
        productDao.closeConnection();
    }
}
