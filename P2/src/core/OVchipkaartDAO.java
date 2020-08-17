package core;

import java.sql.SQLException;
import java.util.List;

public interface OVchipkaartDAO {
    OVchipkaart find(int kaartnummer) throws SQLException;
    List<OVchipkaart> findByReiziger(int reizigerID) throws SQLException;
    List<OVchipkaart> findAll() throws SQLException;
    void save (OVchipkaart ov) throws SQLException;
    void update(OVchipkaart ov) throws SQLException;
    void delete(OVchipkaart ov) throws SQLException;
}
