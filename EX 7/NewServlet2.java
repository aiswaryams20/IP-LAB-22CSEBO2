<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Employee Salary Calculator</title>
</head>
<body>
    <h1>Employee Salary Calculator</h1>
    <form action="CalculateSalaryServlet" method="post">
        <label for="name">Name:</label>
        <input type="text" id="name" name="name" required><br><br>
         <label for="empid">Employee ID:</label>
        <input type="text" id="empid" name="empid" required><br><br>
       <label for="basicpay">Basic Pay:</label>
        <input type="number" id="basicpay" name="basicpay" required><br><br>
       <label for="hra">HRA:</label>
        <input type="number" id="hra" name="hra" required><br><br>
       
        <input type="submit" value="Calculate Salary">
    </form>
</body>
</html>
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
@WebServlet("/CalculateSalaryServlet")
public class CalculateSalaryServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
       String name = request.getParameter("name");
        String empId = request.getParameter("empid");
        double basicPay = Double.parseDouble(request.getParameter("basicpay"));
        double hra = Double.parseDouble(request.getParameter("hra"));
        double da = 0.5 * basicPay;
       double grossPay = basicPay + hra + da;
       out.println("<html><head><title>Salary Calculation Result</title></head><body>");
        out.println("<h1>Salary Calculation Result</h1>");
        out.println("<p>Name: " + name + "</p>");
        out.println("<p>Employee ID: " + empId + "</p>");
        out.println("<p>Basic Pay: $" + basicPay + "</p>");
        out.println("<p>HRA: $" + hra + "</p>");
        out.println("<p>DA: $" + da + "</p>");
        out.println("<p>Gross Pay: $" + grossPay + "</p>");
        out.println("</body></html>");
    }
}

