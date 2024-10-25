package approve;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/approve")
public class approve extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String softwareName = request.getParameter("softwareName");
        String accessType = request.getParameter("accessType");
        String reason = request.getParameter("reason");

        try {
            Class.forName("org.postgresql.Driver");
            Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/access_mgmt", "user", "password");

            String sql = "INSERT INTO requests (software_id, access_type, reason, status) VALUES (?, ?, ?, 'Pending')";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, softwareName); // You need to convert this to ID using a query
            ps.setString(2, accessType);
            ps.setString(3, reason);

            ps.executeUpdate();
            ps.close();
            conn.close();

            response.sendRedirect("employeeDashboard.jsp");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp");
        }
    }
}
