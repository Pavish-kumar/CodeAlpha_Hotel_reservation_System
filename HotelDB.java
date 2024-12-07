import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class HotelDB {
    private static final String DB_URL = "jdbc:sqlite:hotel.db";
    public static void displayAvailableRooms() {
        try (Connection conn = DriverManager.getConnection(DB_URL);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM rooms WHERE is_available = 1")) {
             
            StringBuilder rooms = new StringBuilder("Available Rooms:\n");
            while (rs.next()) {
                rooms.append("Room ID: ").append(rs.getInt("id"))
                     .append(", Category: ").append(rs.getString("category"))
                     .append(", Price: $").append(rs.getDouble("price"))
                     .append("\n");
            }

            if (rooms.length() == "Available Rooms:\n".length()) {
                rooms.append("No rooms available.");
            }
            javax.swing.JOptionPane.showMessageDialog(null, rooms.toString());
        } catch (Exception e) {
            e.printStackTrace();
            javax.swing.JOptionPane.showMessageDialog(null, "Error retrieving rooms: " + e.getMessage());
        }
    }
    public static void bookRoom(int roomId, String guestName, String checkIn, String checkOut) {
        String insertReservation = "INSERT INTO reservations (room_id, guest_name, check_in_date, check_out_date) VALUES (?, ?, ?, ?)";
        String updateRoomAvailability = "UPDATE rooms SET is_available = 0 WHERE id = ?";

        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmtReservation = conn.prepareStatement(insertReservation);
             PreparedStatement pstmtUpdateRoom = conn.prepareStatement(updateRoomAvailability)) {
            pstmtReservation.setInt(1, roomId);
            pstmtReservation.setString(2, guestName);
            pstmtReservation.setString(3, checkIn);
            pstmtReservation.setString(4, checkOut);
            pstmtReservation.executeUpdate();
            pstmtUpdateRoom.setInt(1, roomId);
            pstmtUpdateRoom.executeUpdate();
            javax.swing.JOptionPane.showMessageDialog(null, "Room booked successfully!");
        } catch (Exception e) {
            e.printStackTrace();
            javax.swing.JOptionPane.showMessageDialog(null, "Error booking room: " + e.getMessage());
        }
    }
}
