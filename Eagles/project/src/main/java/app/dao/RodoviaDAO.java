package app.dao;

import app.model.Rodovia;
import app.util.ConnectionFactory;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RodoviaDAO {

    // Salvar (CREATE)
    public void salvar(Rodovia rodovia) throws SQLException {
        String sql = "INSERT INTO rodovia (nome, cidade, extensao) VALUES (?, ?, ?)";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, rodovia.getNome());
            stmt.setString(2, rodovia.getCidade());
            stmt.setInt(3, rodovia.getExtensao());

            stmt.execute();
        }
    }

    // Listar Todos (READ)
    public List<Rodovia> listar() throws SQLException {
        List<Rodovia> rodovias = new ArrayList<>();
        String sql = "SELECT * FROM rodovia";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Rodovia r = new Rodovia(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("cidade"),
                        rs.getInt("extensao")
                );
                rodovias.add(r);
            }
        }
        return rodovias;
    }

    // Atualizar (UPDATE)
    public void atualizar(Rodovia rodovia) throws SQLException {
        String sql = "UPDATE rodovia SET nome=?, cidade=?, extensao=? WHERE id=?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, rodovia.getNome());
            stmt.setString(2, rodovia.getCidade());
            stmt.setInt(3, rodovia.getExtensao());
            stmt.setInt(4, rodovia.getId());

            stmt.execute();
        }
    }

    // Deletar (DELETE)
    public void deletar(int id) throws SQLException {
        String sql = "DELETE FROM rodovia WHERE id=?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            stmt.execute();
        }
    }
}