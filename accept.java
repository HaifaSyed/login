package Accept;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ApprovalServlet")
public class accept extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // JDBC URL, username, and password for PostgreSQL
    private static final String JDBC_URL = "jdbc:postgresql://localhost:5432/access_mgmt";
    private static final String JDBC_USER = "your_db_username"; // Replace with your DB username
    private static final String JDBC_PASSWORD = "your_db_password"; // Replace with your DB password

    // The doPost method to handle approval or rejection of requests
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve the request parameters (request ID and action)
        String requestId = request.getParameter("requestId");
        String action = request.getParameter("action"); // "Approve" or "Reject"

        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            // Load PostgreSQL JDBC Driver
            Class.forName("org.postgresql.Driver");

            // Establish a connection
            conn = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);

            // SQL query to update the status of the request
            String sql = "UPDATE requests SET status = ? WHERE id = ?";

            // Create a prepared statement
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, action.equals("Approve") ? "Approved" : "Rejected"); // Set status based on action
            pstmt.setInt(2, Integer.parseInt(requestId)); // Request ID as an integer

            // Execute the update
            int result = pstmt.executeUpdate();

            // Generate response based on the outcome
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();

            if (result > 0) {
                out.println("<h2>Request has been " + (action.equals("Approve") ? "approved" : "rejected") + " successfully!</h2>");
                out.println("<a href='pendingRequests.jsp'>Go back to pending requests</a>");
            } else {
                out.println("<h2>Failed to process the request.</h2>");
            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            out.println("<h2>Error occurred while processing the request.</h2>");
        } finally {
            // Close the database resources
            try {
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
}
