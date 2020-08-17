package persistency;

import domain.OVchipkaart;

import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

public class OVchipkaartOracleDaoImpl extends OracleBaseDAO implements OVchipkaartDAO {

    public OVchipkaartOracleDaoImpl() {
        openConnection();
    }

    //Find a specific OVchipkaart based on kaartnummer.
    public OVchipkaart find(int kaartnummer) {
        Session session = factory.openSession();
        OVchipkaart ov = session.get(OVchipkaart.class, kaartnummer);
        session.close();

        return ov;
    }

    //Select all OVchipkaarten in database.
    public List<OVchipkaart> findAll() {
        Session session = factory.openSession();

        //Instantiate a criteriabuilder, set the criterium to be the OVchipkaart class.
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<OVchipkaart> criterium = builder.createQuery(OVchipkaart.class);
        criterium.from(OVchipkaart.class);

        //Create query based on above criterium, and return the result set as a list.
        List<OVchipkaart> ov_chipkaarten = session.createQuery(criterium).getResultList();
        session.close();

        return ov_chipkaarten;
    }

    //Find all OVchipkaarten linked to specified ReizigerID.
    public List<OVchipkaart> findByReiziger(int reizigerID) {
        return null;
    }

    //Save new OVchipkaart.
    public void save(OVchipkaart ov) {
        Session session = factory.openSession();
        Transaction t = session.beginTransaction();
        session.save(ov);
        t.commit();
        session.close();
    }

    //Update specified OVchipkaart.
    public void update(OVchipkaart ov) {
        Session session = factory.openSession();
        Transaction t = session.beginTransaction();
        session.update(ov);
        t.commit();
        session.close();
    }

    //Delete specified OVchipkaart.
    public void delete(OVchipkaart ov) {
        Session session = factory.openSession();
        Transaction t = session.beginTransaction();
        session.delete(ov);
        t.commit();
        session.close();
    }
}
