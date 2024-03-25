<!DOCTYPE html>
<html>
    <head>
        <title>Product Selection</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>
    <body>
        <div>
            <h1>Product Selection</h1>
            <form action="cart.html" method="post">
                <input type="checkbox" id="product1" name="products" value="product1">
                <label for ="product1">Product1</label>
                <input type="checkbox" id="product2" name="products" value="product2">
                <label for ="product2">Product2</label>
                <input type="checkbox" id="product3" name="products" value="product3">
                <label for ="product3">Product3</label>
                <input type="checkbox" id="product4" name="products" value="product4">
                <label for ="product4">Product4</label>
                <input type="submit" value="Submit to cart">
            </form>
        </div>
    </body>
</html>
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
@WebServlet("/AddToCartServlet")
public class AddToCartServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String[] selectedProducts = request.getParameterValues("product");
             if (selectedProducts != null && selectedProducts.length > 0) {
            StringBuilder cartItems = new StringBuilder();
            for (String product : selectedProducts) {
                cartItems.append(product).append(",");
            }
            cartItems.deleteCharAt(cartItems.length() - 1);
            Cookie cookie = new Cookie("cartItems", cartItems.toString());
            cookie.setMaxAge(24 * 60 * 60); 
            response.addCookie(cookie);
        }
        response.sendRedirect("ShoppingCartServlet");
    }
}

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
@WebServlet("/ShoppingCartServlet")
public class ShoppingCartServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Cookie[] cookies = request.getCookies();
        String cartItems = "";
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("cartItems")) {
                    cartItems = cookie.getValue();
                    break;
                }
            }
        }
        response.setContentType("text/html");
        response.getWriter().println("<h2>Shopping Cart</h2>");
        if (!cartItems.isEmpty()) {
            String[] items = cartItems.split(",");
            response.getWriter().println("<ul>");
            for (String item : items) {
                response.getWriter().println("<li>" + item + "</li>");
            }
            response.getWriter().println("</ul>");
        } else {
            response.getWriter().println("<p>Your cart is empty.</p>");
        }
    }
}
