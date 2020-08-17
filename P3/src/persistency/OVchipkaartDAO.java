package persistency;

import domain.OVchipkaart;

import java.sql.SQLException;
import java.util.List;

public interface OVchipkaartDAO {
    OVchipkaart find(int kaartnummer, boolean koppelProducten) throws SQLException;
    List<OVchipkaart> findByReiziger(int reizigerID, boolean koppelProducten) throws SQLException;
    List<OVchipkaart> findAll(boolean koppelProducten) throws SQLException;
    void save (OVchipkaart ov) throws SQLException;
    void update(OVchipkaart ov) throws SQLException;
    void delete(OVchipkaart ov) throws SQLException;
}
