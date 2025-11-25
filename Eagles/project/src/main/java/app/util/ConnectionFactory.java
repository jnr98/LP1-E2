package app.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

    // Configurações do Banco
    private static final String URL = "jdbc:mysql://localhost:3306/projeto_java?useTimezone=true&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASS = "123456"; // <--- Verifique se sua senha é essa mesmo

    // Este é o método que estava faltando!
    public static Connection getConnection() throws SQLException {
        try {
            return DriverManager.getConnection(URL, USER, PASS);
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao conectar com o banco de dados: " + e.getMessage(), e);
        }
    }
}