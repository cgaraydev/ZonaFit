package zona_fit.conexion;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexion {
    public static Connection getConnection() {
        Connection conexion = null;
        var db = "zona_fit_db";
        var url = "jdbc:mysql://localhost:3306/" + db;
        var user = "root";
        var password = "leia2024";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conexion = DriverManager.getConnection(url, user, password);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return conexion;
    }

    public static void main(String[] args) {
        var conexion = Conexion.getConnection();
        if (conexion != null) System.out.println(conexion);
        else System.out.println("No se pudo conectar a la base de datos");

    }
}


