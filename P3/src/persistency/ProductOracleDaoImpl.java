package persistency;

import core.Main;
import domain.OVchipkaart;
import domain.Product;

import java.util.List;
import java.util.ArrayList;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductOracleDaoImpl extends OracleBaseDAO implements ProductDAO {

    //Open connection when instanced.
    public ProductOracleDaoImpl() throws SQLException {
        openConnection();
    }

    //Finds a single Product by productNummer. If nothing found, reteurns null.
    public Product find(int productNummer, boolean koppelOV) throws SQLException {
        String query = "SELECT * FROM product WHERE productnummer = ?";

        try {
            statement = connection.prepareStatement(query);
            statement.setInt(1, productNummer);
            ResultSet rs = statement.executeQuery();
            if(rs.next()) {
                //Create new Product based on the resultset.
                Product p = new Product(rs.getInt("productnummer"), rs.getString("productnaam"), rs.getString("beschrijving"), rs.getDouble("prijs"));

                //If asked for, add Producten to OV-chipkaart object.
                if(koppelOV)
                    p = koppelOVchipkaarten(p);

                return p;
            }
            rs.close();
            statement.close();
            return null;

        } catch (SQLException e) {
            throw e;
        }
    }

    //Returns all Producten.
    public List<Product> findAll(boolean koppelOV) throws SQLException {
        List<Product> producten = new ArrayList<>();
        String query = "SELECT * FROM product";

        try {
            statement = connection.prepareStatement(query);
            ResultSet rs = statement.executeQuery();
            while(rs.next()) {
                //Create new Product based on the resultset.
                Product p = new Product(rs.getInt("productnummer"), rs.getString("productnaam"), rs.getString("beschrijving"), rs.getDouble("prijs"));

                //If asked for, add Producten to OV-chipkaart object.
                if(koppelOV)
                    p = koppelOVchipkaarten(p);

                //Add to list of returning Producten.
                producten.add(p);
            }
            rs.close();
            statement.close();
            return producten;

        } catch (SQLException e) {
            throw e;
        }
    }

    //Inserts new Product.
    public void save(Product product) throws SQLException {
        String query = "INSERT INTO product VALUES (?, ?, ?, ?)";

        try {
            statement = connection.prepareStatement(query);
            statement.setInt(1, product.getProductNummer());
            statement.setString(2, product.getProductNaam());
            statement.setString(3, product.getBeschrijving());
            statement.setDouble(4, product.getPrijs());
            statement.execute();
            statement.close();
            return;
        } catch (SQLException e) {
            throw e;
        }
    }

    //Update Product based on productNummer.
    public void update(Product product) throws SQLException {
        String query = "UPDATE product SET productnaam = ?, beschrijving = ?, prijs = ? WHERE productnummer = ?";

        try {
            statement = connection.prepareStatement(query);
            statement.setString(1, product.getProductNaam());
            statement.setString(2, product.getBeschrijving());
            statement.setDouble(3, product.getPrijs());
            statement.setInt(4, product.getProductNummer());
            statement.execute();
            statement.close();
        } catch (SQLException e) {
            throw e;
        }
    }

    //Delete Product based on productNummer.
    public void delete(Product product) throws SQLException {
        String query = "DELETE FROM product WHERE productnummer = ?";
        try {
            statement = connection.prepareStatement(query);
            statement.setInt(1, product.getProductNummer());
            statement.execute();
            statement.close();
        } catch (SQLException e) {
            throw e;
        }
    }

    //Koppel any OV-chipkaarten that are associated with specified Product.
    //We do this by looking at koppeltabel OV_CHIPKAART_PRODUCT.
    private Product koppelOVchipkaarten(Product p) throws SQLException {
        String query = "SELECT * FROM ov_chipkaart_product WHERE productnummer = ?";

        try {
            statement = connection.prepareStatement(query);
            statement.setInt(1, p.getProductNummer());
            ResultSet rs = statement.executeQuery();
            while(rs.next()) {
                //Create OV-chipkaart by finding it based on kaartnummer from table OV_CHIPKAART_PRODUCT.
                OVchipkaart ov = Main.chipkaartDao.find(rs.getInt("kaartnummer"), false);

                //Koppel that OV-chipkaart to Product.
                p.addOVchipkaart(ov);
            }
            rs.close();
            statement.close();
            return p;

        } catch (SQLException e) {
            throw e;
        }
    }
}
