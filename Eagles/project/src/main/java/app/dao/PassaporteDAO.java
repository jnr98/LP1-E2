package app.dao;

import app.model.Passaporte;
import app.util.ConnectionFactory;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PassaporteDAO {

    // CREATE (Salvar)
    public void salvar(Passaporte p) throws SQLException {
        String sql = "INSERT INTO passaporte (nome, numero, pais) VALUES (?, ?, ?)";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, p.getNome());
            stmt.setString(2, p.getNumero());
            stmt.setString(3, p.getPais());

            stmt.execute();
        }
    }

    // READ (Listar)
    public List<Passaporte> listar() throws SQLException {
        List<Passaporte> lista = new ArrayList<>();
        String sql = "SELECT * FROM passaporte";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Passaporte p = new Passaporte(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("numero"),
                        rs.getString("pais")
                );
                lista.add(p);
            }
        }
        return lista;
    }

    // UPDATE (Atualizar)
    public void atualizar(Passaporte p) throws SQLException {
        String sql = "UPDATE passaporte SET nome=?, numero=?, pais=? WHERE id=?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, p.getNome());
            stmt.setString(2, p.getNumero());
            stmt.setString(3, p.getPais());
            stmt.setInt(4, p.getId());

            stmt.execute();
        }
    }

    // DELETE (Excluir)
    public void deletar(int id) throws SQLException {
        String sql = "DELETE FROM passaporte WHERE id=?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            stmt.execute();
        }
    }
}