package core;

import java.sql.Date;
import java.util.List;

public interface ReizigerDAO {
    List<Reiziger> findAll();
    List<Reiziger> findByGBdatum(String gbdatum);
    Reiziger save(Reiziger reiziger);
    Reiziger update(Reiziger reiziger, String newNaam, Date newGBdatum);
    boolean delete(Reiziger reiziger);
}
