import java.sql.*;
import java.util.Scanner;

public class OnlineVotingSystem {

    // Database connection
    static Connection connect() throws Exception {
        return DriverManager.getConnection(
            "jdbc:mysql://localhost:3306/voting_system",
            "root",
            "password"   // change if needed
        );
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        try {
            Connection con = connect();

            System.out.println("=== Online Voting System ===");
            System.out.print("Enter Voter ID: ");
            int voterId = sc.nextInt();

            // Check if voter already voted
            PreparedStatement check = con.prepareStatement(
                "SELECT has_voted FROM voters WHERE id=?"
            );
            check.setInt(1, voterId);
            ResultSet rs = check.executeQuery();

            if (rs.next() && rs.getBoolean("has_voted")) {
                System.out.println("You have already voted!");
                return;
            }

            System.out.println("1. Candidate A");
            System.out.println("2. Candidate B");
            System.out.print("Choose candidate: ");
            int choice = sc.nextInt();

            String candidate = (choice == 1) ? "Candidate A" : "Candidate B";

            // Update vote count
            PreparedStatement vote = con.prepareStatement(
                "UPDATE candidates SET votes = votes + 1 WHERE name=?"
            );
            vote.setString(1, candidate);
            vote.executeUpdate();

            // Mark voter as voted
            PreparedStatement updateVoter = con.prepareStatement(
                "UPDATE voters SET has_voted=TRUE WHERE id=?"
            );
            updateVoter.setInt(1, voterId);
            updateVoter.executeUpdate();

            System.out.println("Vote cast successfully!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
