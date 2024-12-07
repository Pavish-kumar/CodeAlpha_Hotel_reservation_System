import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class HotelApp {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Hotel Reservation System");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JButton viewRoomsBtn = new JButton("View Available Rooms");
        JButton bookRoomBtn = new JButton("Book a Room");

        viewRoomsBtn.setBounds(100, 50, 200, 30);
        bookRoomBtn.setBounds(100, 100, 200, 30);

        frame.add(viewRoomsBtn);
        frame.add(bookRoomBtn);

        viewRoomsBtn.addActionListener(e -> HotelDB.displayAvailableRooms());

        bookRoomBtn.addActionListener(e -> {
            String roomId = JOptionPane.showInputDialog("Enter Room ID:");
            String guestName = JOptionPane.showInputDialog("Enter Guest Name:");
            String checkIn = JOptionPane.showInputDialog("Enter Check-In Date (YYYY-MM-DD):");
            String checkOut = JOptionPane.showInputDialog("Enter Check-Out Date (YYYY-MM-DD):");

            HotelDB.bookRoom(Integer.parseInt(roomId), guestName, checkIn, checkOut);
        });

        frame.setLayout(null);
        frame.setVisible(true);
    }
}
