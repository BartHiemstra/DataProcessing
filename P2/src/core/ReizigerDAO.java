package core;

import java.sql.SQLException;
import java.util.List;

public interface ReizigerDAO {
    Reiziger find(int id, boolean addChipkaarten) throws SQLException;
    List<Reiziger> findAll(boolean addChipkaarten) throws SQLException;
    List<Reiziger> findByGBdatum(String gbdatum, boolean addChipkaarten) throws SQLException;
    void save (Reiziger reiziger) throws SQLException;
    void update(Reiziger reiziger) throws SQLException;
    void delete(Reiziger reiziger) throws SQLException;
}
