package app.dao;

import app.model.Prisoneiro;
import app.util.ConnectionFactory;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PrisoneiroDAO {

    // CREATE (Salvar)
    public void salvar(Prisoneiro p) throws SQLException {
        String sql = "INSERT INTO prisoneiro (lugar_prisao, nome, arma) VALUES (?, ?, ?)";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, p.getLugar());
            stmt.setString(2, p.getNome());
            stmt.setString(3, p.getArma());

            stmt.execute();
        }
    }

    // READ (Listar)
    public List<Prisoneiro> listar() throws SQLException {
        List<Prisoneiro> lista = new ArrayList<>();
        String sql = "SELECT * FROM prisoneiro";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Prisoneiro p = new Prisoneiro(
                        rs.getInt("id"),
                        rs.getString("lugar_prisao"),
                        rs.getString("nome"),
                        rs.getString("arma")
                );
                lista.add(p);
            }
        }
        return lista;
    }

    // UPDATE (Atualizar)
    public void atualizar(Prisoneiro p) throws SQLException {
        String sql = "UPDATE prisoneiro SET lugar_prisao=?, nome=?, arma=? WHERE id=?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, p.getLugar());
            stmt.setString(2, p.getNome());
            stmt.setString(3, p.getArma());
            stmt.setInt(4, p.getId());

            stmt.execute();
        }
    }

    // DELETE (Excluir)
    public void deletar(int id) throws SQLException {
        String sql = "DELETE FROM prisoneiro WHERE id=?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            stmt.execute();
        }
    }
}