package persistency;

import domain.OVchipkaart;

import java.util.List;

public interface OVchipkaartDAO {
    OVchipkaart find(int kaartnummer);
    List<OVchipkaart> findAll();
    List<OVchipkaart> findByReiziger(int reizigerID);
    void save (OVchipkaart ov);
    void update(OVchipkaart ov);
    void delete(OVchipkaart ov);
}
