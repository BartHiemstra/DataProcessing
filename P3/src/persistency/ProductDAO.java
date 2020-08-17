package persistency;

import domain.OVchipkaart;
import domain.Product;

import java.util.List;
import java.sql.SQLException;

public interface ProductDAO {
    Product find(int productNummer, boolean koppelOV) throws SQLException;
    void save (Product product) throws SQLException;
    void update(Product product) throws SQLException;
    void delete(Product product) throws SQLException;
}
