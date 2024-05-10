package demo;

import java.sql.*;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

@WebService(serviceName = "NewWebService")
public class NewWebService {

    @WebMethod(operationName = "hello")
    public String hello(@WebParam(name = "name") String txt) {
        return "Hello " + txt + "!";
    }

    @WebMethod(operationName = "display")
    public String display(@WebParam(name = "id") String id) {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection("jdbc:derby://localhost:1527/ip14");
            int productId = Integer.parseInt(id);
            PreparedStatement stmt = connection.prepareStatement("SELECT name,description FROM product WHERE product_id=?");
            stmt.setInt(1, productId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                String productName = rs.getString("name");
                String productDescription = rs.getString("description");
                rs.close();
                stmt.close();
                return productName + ": " + productDescription;
            } else {
                return "No data found for ID: " + id;
            }
        } catch (SQLException | NumberFormatException e) {
            e.printStackTrace();
            return "An error occurred while processing the request";
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}