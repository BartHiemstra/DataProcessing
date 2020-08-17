package persistency;

import domain.OVchipkaart;
import domain.Reiziger;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class SessionFactoryBuilder {
    private static SessionFactory oracleFactory;

    //Build new SessionFactory.
    public static void buildOracleFactory() {
        try {
            oracleFactory = new Configuration().configure("/hibernate-oracle.cfg.xml").addAnnotatedClass(Reiziger.class).addAnnotatedClass(OVchipkaart.class).buildSessionFactory();
        }
        catch (Exception e) {
            System.err.println("Failed to create sessionFactory object." + e);
            throw new ExceptionInInitializerError(e);
        }
    }

    //Return the built SessionFactory.
    public static SessionFactory getOracleFactory() {
        //If not built yet, build from within this class.
        if(oracleFactory == null)
            buildOracleFactory();

        return oracleFactory;
    }

    //Close SessionFactory.
    public static void closeOracleFactory() {
        oracleFactory.close();
    }
}
