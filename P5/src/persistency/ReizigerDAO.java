package persistency;

import domain.Reiziger;

import java.util.List;

public interface ReizigerDAO {
    Reiziger find(int id);
    List<Reiziger> findAll();
    List<Reiziger> findByGBdatum(String gbdatum);
    void save (Reiziger r);
    void update(Reiziger r);
    void delete(Reiziger r);
}