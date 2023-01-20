import java.io.FileNotFoundException;
import java.sql.*;
import java.util.List;

public class main {
    public static void main(String[] args) throws SQLException, FileNotFoundException {
        selectAll("PEOPLE");
        //createTable("CREATE TABLE EMAIL(Id integer primary key autoincrement, Mail text not null);");
        MailListReader mlr = new MailListReader();
        List<String> list = mlr.read("email.txt");
        insertIntoTable(list);
    }
    private static Connection connect() {
        String url = "jdbc:sqlite:Kata5.db";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    private static void selectAll(String table) throws SQLException {
        String sql = "SELECT * FROM " + table;
        Connection conn = connect();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);

        // Bucle sobre el conjunto de registros.
        while (rs.next()) {
            System.out.println(rs.getInt("id") + "\t" +
                rs.getString("Nombre") + "\t" +
                rs.getString("Apellidos") + "\t" +
                rs.getString("Departamento") + "\t");
        }
    }

    public static void createTable(String query) throws SQLException {
        Connection conn = connect();
        Statement stmt = conn.createStatement();
        stmt.execute(query);
        System.out.println("Tabla creada.");
    }

    public static void insertIntoTable(List<String> list) throws SQLException {
        String sql = "insert into EMAIL(Mail) values(?)";
        Connection conn = connect();
        PreparedStatement pS = conn.prepareStatement(sql);
        for (String email : list) {
            pS.setString(1, email);
            pS.executeUpdate();
        }
        System.out.println("Se han insertado " + list.size() + " elementos.");
    }

}
