package Util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Matheus Montenegro
 */
public class Conexao {

    private static Connection connection = null;
    private static String url;
    private static String usuario;
    private static String senha;

    public Conexao() {
        //url = "jdbc:postgresql://localhost:5432/sizeapp";
        //usuario = "postgres";
        //senha = "postgres";
        url = "postgres://wrbzffsg:QchHZSJV2fUJostXRr3yxCV-xLAvhucK@elmer.db.elephantsql.com:5432/wrbzffsg";
        usuario = "wrbzffsg";
        senha = "QchHZSJV2fUJostXRr3yxCV-xLAvhucK";
    }

    private Connection getConnection() {
        try {
            if (connection == null || connection.isClosed()) {
                Class.forName("org.postgresql.Driver");
                connection = DriverManager.getConnection(url, usuario, senha);
                return connection;
            }
            return connection;
        } catch (ClassNotFoundException ex) {
            System.out.println("O driver não foi encontrado.");
            return null;
        } catch (SQLException ex) {
            System.out.println("Problemas com a conexão\n" + ex.getMessage());
            return null;
        }
    }

    public static void closeConnection() throws SQLException {
        if (connection instanceof Connection) {
            connection.close();
        }
    }
}
