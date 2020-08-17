package core;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OVchipkaartDaoImpl extends OracleBaseDAO implements OVchipkaartDAO {

    public OVchipkaartDaoImpl() throws SQLException {
        openConnection();
    }

    //Finds a single OV-chipkaart by kaartnummer. If nothing found, reteurns null.
    public OVchipkaart find(int kaartnummer) throws SQLException {
        String query = "SELECT * FROM ov_chipkaart WHERE kaartnummer = ?";

        try {
            statement = connection.prepareStatement(query);
            statement.setInt(1, kaartnummer);
            ResultSet rs = statement.executeQuery();
            if(rs.next()) {
                OVchipkaart ov = new OVchipkaart(rs.getInt("kaartnummer"), rs.getDate("geldigtot"), rs.getInt("klasse"), rs.getDouble("saldo"));
                ov.setReiziger(Main.reizigerDao.find(rs.getInt("reizigerid"), false));
                return ov;
            }
            rs.close();
            statement.close();
            return null;

        } catch (SQLException e) {
            throw e;
        }
    }

    //Returns all OV-chipkaarten that belong to specific reizigerID.
    public List<OVchipkaart> findByReiziger(int reizigerID) throws SQLException {
        List<OVchipkaart> OVchipkaarten = new ArrayList<>();
        String query = "SELECT * FROM ov_chipkaart WHERE reizigerid = ?";

        try {
            statement = connection.prepareStatement(query);
            statement.setInt(1, reizigerID);
            ResultSet rs = statement.executeQuery();
            while(rs.next()) {
                OVchipkaart ov = new OVchipkaart(rs.getInt("kaartnummer"), rs.getDate("geldigtot"), rs.getInt("klasse"), rs.getDouble("saldo"));
                ov.setReiziger(Main.reizigerDao.find(rs.getInt("reizigerid"), false));
                OVchipkaarten.add(ov);
            }
            rs.close();
            statement.close();
            return OVchipkaarten;

        } catch (SQLException e) {
            throw e;
        }
    }

    //Returns all OV-chipkaarten.
    public List<OVchipkaart> findAll() throws SQLException {
        List<OVchipkaart> OVchipkaarten = new ArrayList<>();
        String query = "SELECT * FROM ov_chipkaart";

        try {
            statement = connection.prepareStatement(query);
            ResultSet rs = statement.executeQuery();
            while(rs.next()) {
                OVchipkaart ov = new OVchipkaart(rs.getInt("kaartnummer"), rs.getDate("geldigtot"), rs.getInt("klasse"), rs.getDouble("saldo"));
                ov.setReiziger(Main.reizigerDao.find(rs.getInt("reizigerid"), false));
                OVchipkaarten.add(ov);
            }
            rs.close();
            statement.close();
            return OVchipkaarten;

        } catch (SQLException e) {
            throw e;
        }
    }

    //Inserts OV-chipkaart.
    public void save(OVchipkaart ov) throws SQLException {
        String query = "INSERT INTO ov_chipkaart VALUES (?, to_date(? ,'yyyy-mm-dd'), ?, ?, ?)";

        try {
            statement = connection.prepareStatement(query);
            statement.setInt(1, ov.getKaartnummer());
            statement.setDate(2, ov.getGeldigTot());
            statement.setInt(3, ov.getKlasse());
            statement.setDouble(4, ov.getSaldo());
            statement.setInt(5, ov.getReiziger().getId());
            statement.execute();
            statement.close();
        } catch (SQLException e) {
            throw e;
        }
    }

    //Update OVchipkaart based on kaartNummer.
    public void update(OVchipkaart ov) throws SQLException {
        String query = "UPDATE ov_chipkaart SET geldigtot = ?, klasse = ?, saldo = ?, reizigerid = ? WHERE kaartnummer = ?";

        try {
            statement = connection.prepareStatement(query);
            statement.setDate(1, ov.getGeldigTot());
            statement.setInt(2, ov.getKlasse());
            statement.setDouble(3, ov.getSaldo());
            statement.setInt(4, ov.getReiziger().getId());
            statement.setInt(5, ov.getKaartnummer());
            statement.execute();
            statement.close();
        } catch (SQLException e) {
            throw e;
        }
    }

    //Delete OVchipkaart based on kaartNummer.
    public void delete(OVchipkaart ov) throws SQLException {
        String query = "DELETE FROM ov_chipkaart WHERE kaartnummer = ?";
        try {
            statement = connection.prepareStatement(query);
            statement.setInt(1, ov.getKaartnummer());
            statement.execute();
            statement.close();
        } catch (SQLException e) {
            throw e;
        }
    }
}
