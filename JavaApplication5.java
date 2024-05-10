/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication5;
import demo.*;
/**
 *
 * @author AISWARYA.MS
 */
public class JavaApplication5 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        System.out.println(display("101"));
    }

    private static String display(java.lang.String id) {
        demo.NewWebService_Service service = new demo.NewWebService_Service();
        demo.NewWebService port = service.getNewWebServicePort();
        return port.display(id);
    }
}
