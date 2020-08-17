package persistency;

import org.hibernate.SessionFactory;

public class OracleBaseDAO {

    protected SessionFactory factory;

    protected void openConnection() {
        factory = SessionFactoryBuilder.getOracleFactory();
    }
}
