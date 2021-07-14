package comp.esimbarcode.BEAN;

import comp.esimbarcode.DAO.DAO;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

@ManagedBean
@SessionScoped
public class orderInvoiceBEAN extends DAO {

    PreparedStatement ps;
    ResultSet rs;
    Connection con;
    private String tranctionID;
    private String customerID;

    private String productID;
    private String productDescp;
     private String TransactionType;
    private String orderDate;
    
    private int unitQty;

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    
    
    public int getUnitQty() {
        return unitQty;
    }

    public void setUnitQty(int unitQty) {
        this.unitQty = unitQty;
    }
    
    

    public String getTranctionID() {
        return tranctionID;
    }

    public void setTranctionID(String tranctionID) {
        this.tranctionID = tranctionID;
    }

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public String getProductDescp() {
        return productDescp;
    }

    public void setProductDescp(String productDescp) {
        this.productDescp = productDescp;
    }

    

    public String getTransactionType() {
        return TransactionType;
    }

    public void setTransactionType(String TransactionType) {
        this.TransactionType = TransactionType;
    }

     public void retriveCustomerIDFromUI() {//get the current tranctionID  on the UI 
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        tranctionID = ec.getRequestParameterMap().get("OrderInvoiceForm:TransactionID");
    }//end of method

    ////Data table implementation --FOR the permanenctOrder info use for 'order-invoice'
    public List<orderInvoiceBEAN> getInvoiceInfo() throws Exception {
        List<orderInvoiceBEAN> invoiceinfo = new ArrayList<orderInvoiceBEAN>();

        try {

            //Mysql Connection 
            con = (com.mysql.jdbc.Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/esimbarcode_hhy_store?user=root&password=ash123");

            // Jealastic connection on the Jealastic Cloud
           //  con = (com.mysql.jdbc.Connection) DriverManager.getConnection("jdbc:mysql://node12525-env-esimbarcode.cloud.interhostsolutions.be/esimbarcode", "root", "HHBoea11492");
            PreparedStatement ps = con.prepareStatement("SELECT * FROM  permantodertr WHERE orderTransactId=? ");
            ps.setString(1, tranctionID);
            rs = ps.executeQuery();

            while (rs.next()) {
                orderInvoiceBEAN tbl = new orderInvoiceBEAN();
                tbl.setCustomerID(rs.getString("customerID"));
                tbl.setProductID(rs.getString("productID"));
                tbl.setProductDescp(rs.getString("productDescp"));
                tbl.setUnitQty(rs.getInt("unitQty"));
                // tbl.setPrice(rs.getDouble("Price"));
                //tbl.setTotalPerProduct(rs.getDouble("TotalPerProduct"));
                tbl.setTransactionType(rs.getString("TransactionType"));
                tbl.setOrderDate(rs.getString("order_Date"));
                invoiceinfo.add(tbl);
            }//end of while-block

        } catch (Exception e) {
            throw e;
        } finally {

            if (con != null) {
                con.close();
            }//end of
        }//end of finally-block

        return invoiceinfo;
    }//end of the method 
}//end of class
