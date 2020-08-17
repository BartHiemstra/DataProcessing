package core;

import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;  
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class Main {

  private static SessionFactory factory;

  public static void main(String[] args) throws SQLException, ParseException {
      try {
          //Don't forget to remove 'Reiziger.hbm.xml' from mapping in config file to demonstrate annotation method.
          factory = new Configuration().configure().buildSessionFactory();
          //factory = new Configuration().configure().addAnnotatedClass(Reiziger.class).buildSessionFactory();
      }
      catch (Throwable ex) {
          System.err.println("Failed to create sessionFactory object." + ex);
          throw new ExceptionInInitializerError(ex);
      }

      //READ (reizigerid=1)
      Reiziger r1 = findReiziger(1);
      System.out.println("Reiziger (id=1): "  + r1.getNaam() + ", geboren op " + r1.getGBdatum());

      //CREATE
      Reiziger r2 = new Reiziger(20, "H", null, "Ibernate", Date.valueOf("2000-01-01"));
      addReiziger(r2);
      System.out.println("\nNieuwe reiziger (id=20): " + findReiziger(20).getNaam() + ", geboren op " + findReiziger(20).getGBdatum());

      //UPDATE
      r2.setAchternaam("Anders");
      updateReiziger(r2);
      System.out.println("\nNaam van reiziger (id=20) na update: " + findReiziger(20).getNaam());

      //DELETE
      deleteReiziger(r2);

      //Close factory when done.
      factory.close();
  }

  //CREATE
  public static int addReiziger(Reiziger r) {
      int reizigerID;
      Session session = factory.openSession();
      Transaction t = session.beginTransaction();
      reizigerID = (int)session.save(r);
      t.commit();
      session.close();

      return reizigerID;
  }

  //READ
  public static Reiziger findReiziger(int reizigerID) {
      Session session = factory.openSession();
      Reiziger r = session.get(Reiziger.class, reizigerID);
      session.close();

      return r;
  }

  //UPDATE
  public static void updateReiziger(Reiziger r) {
      Session session = factory.openSession();
      Transaction t = session.beginTransaction();
      session.update(r);
      t.commit();
      session.close();
  }

  //DELETE
  public static void deleteReiziger(Reiziger r) {
      Session session = factory.openSession();
      Transaction t = session.beginTransaction();
      session.delete(r);
      t.commit();
      session.close();
  }
}
