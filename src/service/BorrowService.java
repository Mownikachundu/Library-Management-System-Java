package service;

import model.BorrowRecord;
import util.DBConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BorrowService {

    public void borrowBook(int userId, int bookId) {
        String sql = "INSERT INTO borrow_records (user_id, book_id, borrow_date) VALUES (?, ?, CURDATE())";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, userId);
            stmt.setInt(2, bookId);
            stmt.executeUpdate();
            System.out.println("Book borrowed");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void returnBook(int recordId) {
        String sql = "UPDATE borrow_records SET return_date = CURDATE() WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, recordId);
            stmt.executeUpdate();
            System.out.println("Book returned");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<BorrowRecord> getAllBorrowRecords() {
        List<BorrowRecord> records = new ArrayList<>();
        String sql = "SELECT * FROM borrow_records";
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                records.add(new BorrowRecord(
                        rs.getInt("id"),
                        rs.getInt("user_id"),
                        rs.getInt("book_id"),
                        rs.getDate("borrow_date"),
                        rs.getDate("return_date")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return records;
    }
}
