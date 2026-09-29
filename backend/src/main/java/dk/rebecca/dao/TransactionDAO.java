package dk.rebecca.dao;

import dk.rebecca.Database;
import dk.rebecca.model.Transaction;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class TransactionDAO {

    public void insert(Transaction t) {
        String sql = "INSERT INTO transactions (amount, category, description, type, date) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = Database.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setDouble(1, t.getAmount());
            stmt.setString(2, t.getCategory());
            stmt.setString(3, t.getDescription());
            stmt.setString(4, t.getType());
            stmt.setString(5, t.getDate());

            stmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Fejl ved indsættelse: " + e.getMessage());
        }
    }

    public List<Transaction> getAll() {
        List<Transaction> transactions = new ArrayList<>();
        String sql = "SELECT * FROM transactions ORDER BY date DESC";

        try (Connection conn = Database.connect();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Transaction t = new Transaction(
                        rs.getInt("id"),
                        rs.getDouble("amount"),
                        rs.getString("category"),
                        rs.getString("description"),
                        rs.getString("type"),
                        rs.getString("date")
                );
                transactions.add(t);
            }

        } catch (SQLException e) {
            System.out.println("Fejl ved hentning: " + e.getMessage());
        }

        return transactions;
    }

    public void delete(int id){
        String sql = "DELETE FROM transactions WHERE id = ?";

        try (Connection conn = Database.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            stmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Fejl ved slettelse: " + e.getMessage());
        }
    }

    public void update(Transaction t, int id) {
        String sql = "UPDATE transactions SET amount = ?, category = ?, description = ?, type = ?, date = ? WHERE id = ?";

        try (Connection conn = Database.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setDouble(1, t.getAmount());
            stmt.setString(2, t.getCategory());
            stmt.setString(3, t.getDescription());
            stmt.setString(4, t.getType());
            stmt.setString(5, t.getDate());
            stmt.setInt(6, id);

            stmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Fejl ved opdatering: " + e.getMessage());
        }
    }
}