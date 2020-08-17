package core;

public class OracleBaseDAO {
    protected boolean getCondnection() {
        return false;
    }

    public void closeConnection() {
        System.out.println("Closing connection.");
    }
}
