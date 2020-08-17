package persistency;

import core.Main;
import domain.OVchipkaart;
import domain.Product;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OVchipkaartOracleDaoImpl extends OracleBaseDAO implements OVchipkaartDAO {

    //Open connection when instanced.
    public OVchipkaartOracleDaoImpl() throws SQLException {
        openConnection();
    }

    //Finds a single OV-chipkaart by kaartnummer. If nothing found, reteurns null.
    public OVchipkaart find(int kaartnummer, boolean koppelProducten) throws SQLException {
        String query = "SELECT * FROM ov_chipkaart WHERE kaartnummer = ?";

        try {
            statement = connection.prepareStatement(query);
            statement.setInt(1, kaartnummer);
            ResultSet rs = statement.executeQuery();
            if(rs.next()) {
                //Create new OV-chipkaart based on the resultset.
                OVchipkaart ov = new OVchipkaart(rs.getInt("kaartnummer"), rs.getDate("geldigtot"), rs.getInt("klasse"), rs.getDouble("saldo"));

                //Set the Reiziger associated with OV-chipkaart.
                ov.setReiziger(Main.reizigerDao.find(rs.getInt("reizigerid"), false));

                //If asked for, add Producten to OV-chipkaart object.
                if(koppelProducten)
                    ov = koppelProducten(ov);

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
    public List<OVchipkaart> findByReiziger(int reizigerID, boolean koppelProducten) throws SQLException {
        List<OVchipkaart> OVchipkaarten = new ArrayList<>();
        String query = "SELECT * FROM ov_chipkaart WHERE reizigerid = ?";

        try {
            statement = connection.prepareStatement(query);
            statement.setInt(1, reizigerID);
            ResultSet rs = statement.executeQuery();
            while(rs.next()) {
                //Create new OV-chipkaart based on the resultset.
                OVchipkaart ov = new OVchipkaart(rs.getInt("kaartnummer"), rs.getDate("geldigtot"), rs.getInt("klasse"), rs.getDouble("saldo"));

                //Set the Reiziger associated with OV-chipkaart.
                ov.setReiziger(Main.reizigerDao.find(rs.getInt("reizigerid"), false));

                //If asked for, add Producten to OV-chipkaart object.
                if(koppelProducten)
                    ov = koppelProducten(ov);

                //Add to the list of returning OV-chipkaarten.
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
    public List<OVchipkaart> findAll(boolean koppelProducten) throws SQLException {
        List<OVchipkaart> OVchipkaarten = new ArrayList<>();
        String query = "SELECT * FROM ov_chipkaart";

        try {
            statement = connection.prepareStatement(query);
            ResultSet rs = statement.executeQuery();
            while(rs.next()) {
                //Create new OV-chipkaart based on the resultset.
                OVchipkaart ov = new OVchipkaart(rs.getInt("kaartnummer"), rs.getDate("geldigtot"), rs.getInt("klasse"), rs.getDouble("saldo"));

                //Set the Reiziger associated with OV-chipkaart.
                ov.setReiziger(Main.reizigerDao.find(rs.getInt("reizigerid"), false));

                //If asked for, add Producten to OV-chipkaart object.
                if(koppelProducten)
                    ov = koppelProducten(ov);

                //Add to the list of returning OV-chipkaarten.
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

    //Koppel any Producten that are associated with specified OV-chipkaart.
    //We do this by looking at Join Table OV_CHIPKAART_PRODUCT.
    private OVchipkaart koppelProducten(OVchipkaart ov) throws SQLException {
        String query = "SELECT * FROM ov_chipkaart_product WHERE kaartnummer = ?";

        try {
            statement = connection.prepareStatement(query);
            statement.setInt(1, ov.getKaartnummer());
            ResultSet rs = statement.executeQuery();
            while(rs.next()) {
                //Create Product by finding it based on Productnummer from table OV_CHIPKAART_PRODUCT.
                Product p = Main.productDao.find(rs.getInt("productnummer"), true);

                //Koppel that product to OV-chipkaart.
                ov.addProduct(p);
            }
            rs.close();
            statement.close();
            return ov;

        } catch (SQLException e) {
            throw e;
        }
    }
}
