package persistency;

import core.Main;
import domain.OVchipkaart;
import domain.Reiziger;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReizigerOracleDaoImpl extends OracleBaseDAO implements ReizigerDAO {

    public ReizigerOracleDaoImpl() throws SQLException {
        openConnection();
    }

    //Finds a single Reiziger by reizigerID. If nothing found, reteurns null.
    public Reiziger find(int id, boolean addChipkaarten) throws SQLException {
        String query = "SELECT * FROM reiziger WHERE reizigerid = ?";

        try {
            statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            //If we get a result back, create a new instance of Reiziger.
            if(rs.next()) {
                //Create new Reiziger based on resultset.
                Reiziger r = new Reiziger(rs.getInt("reizigerID"), rs.getString("voorletters"), rs.getString("tussenvoegsel"), rs.getString("achternaam"), rs.getDate("gebortedatum"));

                //Iterate through all OVchipkaart with this Reiziger ID, in OVchipkaartDAO and add them to Reizigers OVChipkaart List.
                //But only if specified to do so through boolean addChipKaarten.
                if(addChipkaarten) {
                    for (OVchipkaart ov : Main.chipkaartDao.findByReiziger(r.getId(), true)) {
                        r.addOVchipkaart(ov);
                    }
                }
                return r;
            }
            rs.close();
            statement.close();
            return null;

        } catch (SQLException e) {
            throw e;
        }
    }

    //Returns all Reizigers.
    public List<Reiziger> findAll(boolean addChipkaarten) throws SQLException {
        List<Reiziger> reizigers = new ArrayList<>();
        String query = "SELECT * FROM reiziger";

        try {
            statement = connection.prepareStatement(query);
            ResultSet rs = statement.executeQuery();
            while(rs.next()) {
                //Create new Reiziger based on resultset.
                Reiziger r = new Reiziger(rs.getInt("reizigerID"), rs.getString("voorletters"), rs.getString("tussenvoegsel"), rs.getString("achternaam"), rs.getDate("gebortedatum"));

                //Iterate through all OVchipkaart with this Reiziger ID, in OVchipkaartDAO and add them to Reizigers OVChipkaart List.
                //But only if specified to do so through boolean addChipKaarten.
                if(addChipkaarten) {
                    for (OVchipkaart ov : Main.chipkaartDao.findByReiziger(r.getId(), true)) {
                        r.addOVchipkaart(ov);
                    }
                }
                reizigers.add(r);
            }
            rs.close();
            statement.close();
            return reizigers;

        } catch (SQLException e) {
            throw e;
        }
    }

    //Returns Reizigers by specific geboortedatum.
    public List<Reiziger> findByGBdatum(String gbdatum, boolean addChipkaarten) throws SQLException {
        List<Reiziger> reizigers = new ArrayList<>();
        String query = "SELECT * FROM reiziger WHERE gebortedatum = to_date(?, 'yyyy-mm-dd')";

        try {
            statement = connection.prepareStatement(query);
            statement.setString(1, gbdatum);
            ResultSet rs = statement.executeQuery();
            Reiziger r = new Reiziger(rs.getInt("reizigerID"), rs.getString("voorletters"), rs.getString("tussenvoegsel"), rs.getString("achternaam"), rs.getDate("gebortedatum"));
            if(addChipkaarten) {
                for (OVchipkaart ov : Main.chipkaartDao.findByReiziger(r.getId(), true)) {
                    r.addOVchipkaart(ov);
                }
            }
            reizigers.add(r);
            rs.close();
            statement.close();
            return reizigers;

        } catch (SQLException e) {
            throw e;
        }
    }

    //Inserts Reiziger.
    public void save(Reiziger reiziger) throws SQLException {
        String query = "INSERT INTO reiziger VALUES (?, ?, ?, ?, to_date(? ,'yyyy-mm-dd'))";

        try {
            statement = connection.prepareStatement(query);
            statement.setInt(1, reiziger.getId());
            statement.setString(2, reiziger.getVoorletters());
            statement.setString(3, (reiziger.getTussenvoegsel() == null ? "" : reiziger.getTussenvoegsel()));
            statement.setString(4, reiziger.getAchternaam());
            statement.setString(5, reiziger.getGBdatum().toString());
            statement.execute();
            statement.close();
        } catch (SQLException e) {
            throw e;
        }
    }

    //Updates Reiziger.
    public void update(Reiziger reiziger) throws SQLException {
        String query = "UPDATE reiziger SET voorletters = ?, tussenvoegsel = ?, achternaam = ?, gebortedatum = to_date(? ,'yyyy-mm-dd') WHERE reizigerid = ?";

        try {
            statement = connection.prepareStatement(query);
            statement.setString(1, reiziger.getVoorletters());
            statement.setString(2, (reiziger.getTussenvoegsel() == null ? "" : reiziger.getTussenvoegsel()));
            statement.setString(3, reiziger.getAchternaam());
            statement.setString(4, reiziger.getGBdatum().toString());
            statement.setInt(5, reiziger.getId());
            statement.execute();
            statement.close();
        } catch (SQLException e) {
            throw e;
        }
    }

    //Deletes Reiziger.
    public void delete(Reiziger reiziger) throws SQLException {
        String query = "DELETE FROM reiziger WHERE reizigerid = ?";
        try {
            statement = connection.prepareStatement(query);
            statement.setInt(1, reiziger.getId());
            statement.execute();
            statement.close();
        } catch (SQLException e) {
            throw e;
        }
    }
}
