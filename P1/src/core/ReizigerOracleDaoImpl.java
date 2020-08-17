package core;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class ReizigerOracleDaoImpl extends OracleBaseDAO implements ReizigerDAO {

    private List<Reiziger> reizigers = new ArrayList<>();

    public List<Reiziger> findAll() {
        return reizigers;
    }

    public List<Reiziger> findByGBdatum(String gbdatum) {
        List<Reiziger> lReizigers = new ArrayList<Reiziger>();

        for(Reiziger r : reizigers) {
            if(r.getGBdatum().equals(Date.valueOf(gbdatum)))
                lReizigers.add(r);
        }

        return lReizigers;
    }

    public Reiziger save(Reiziger reiziger) {
        reizigers.add(reiziger);
        return reiziger;
    }

    public Reiziger update(Reiziger reiziger, String newNaam, Date newGBdatum) {
        reizigers.get(reizigers.indexOf(reiziger)).setNaam(newNaam);
        reizigers.get(reizigers.indexOf(reiziger)).setGBdatum(newGBdatum);
        return reiziger;
    }

    public boolean delete(Reiziger reiziger) {
        return reizigers.remove(reiziger);
    }
}
