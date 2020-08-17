package persistency;

import domain.Reiziger;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.sql.Date;
import java.util.List;

public class ReizigerOracleDaoImpl extends OracleBaseDAO implements ReizigerDAO {

    public ReizigerOracleDaoImpl() {
        openConnection();
    }

    //Find a specific Reiziger based on ReizigerID.
    public Reiziger find(int reizigerID) {
        Session session = factory.openSession();
        Reiziger r = session.get(Reiziger.class, reizigerID);
        session.close();

        return r;
    }

    //Select all Reizigers in database.
    public List<Reiziger> findAll() {
        Session session = factory.openSession();

        //Instantiate a criteriabuilder, set the criterium to be the Reiziger class.
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Reiziger> criterium = builder.createQuery(Reiziger.class);
        criterium.from(Reiziger.class);

        //Create query based on above criterium, and return the result set as a list.
        List<Reiziger> reizigers = session.createQuery(criterium).getResultList();
        session.close();

        return reizigers;
    }

    //Find all reizigers with specified geboorteDatum.
    public List<Reiziger> findByGBdatum(String gbdatum) {
        Session session = factory.openSession();

        //Use CriteriaBuilder to create a new criteria object, set the criterium to be Reiziger.
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Reiziger> criteria = builder.createQuery(Reiziger.class);

        //Add a restriction based on geboorteDatum.
        Root<Reiziger> root = criteria.from(Reiziger.class);
        criteria.select(root).where(builder.equal(root.get("gbdatum"), Date.valueOf(gbdatum)));

        //Create query based on above criterium, and return the result set as a list.
        List<Reiziger> reizigers = session.createQuery(criteria).getResultList();
        session.close();

        return reizigers;
    }

    //Save new Reiziger.
    public void save(Reiziger r) {
        Session session = factory.openSession();
        Transaction t = session.beginTransaction();
        session.save(r);
        t.commit();
        session.close();
    }

    //Update specified Reiziger.
    public void update(Reiziger r) {
        Session session = factory.openSession();
        Transaction t = session.beginTransaction();
        session.update(r);
        t.commit();
        session.close();
    }

    //Delete specified Reiziger.
    public void delete(Reiziger r) {
        Session session = factory.openSession();
        Transaction t = session.beginTransaction();
        session.delete(r);
        t.commit();
        session.close();
    }
}
