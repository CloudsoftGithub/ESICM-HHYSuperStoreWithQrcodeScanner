package comp.esimbarcode.BEAN;

import comp.esimbarcode.DAO.DAO;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped

public class viewStock extends DAO {

    PreparedStatement ps;
    ResultSet rs;
   // Connection con;

    private String transactionID;
    private String productID;
    private String productDescp;
    private String partner_name;
    private Date date_purchased;
    private int qty_added;
    private int tqty_StoreA;
    private int tqty_StoreB;
    private int tqty_StoreC;
    private int total_qty;
    private String store_title;

    public String getTransactionID() {
        return transactionID;
    }

    public void setTransactionID(String transactionID) {
        this.transactionID = transactionID;
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

    public String getPartner_name() {
        return partner_name;
    }

    public void setPartner_name(String partner_name) {
        this.partner_name = partner_name;
    }

    public Date getDate_purchased() {
        return date_purchased;
    }

    public void setDate_purchased(Date date_purchased) {
        this.date_purchased = date_purchased;
    }

    public int getQty_added() {
        return qty_added;
    }

    public void setQty_added(int qty_added) {
        this.qty_added = qty_added;
    }

    public int getTotal_qty() {
        return total_qty;
    }

    public void setTotal_qty(int total_qty) {
        this.total_qty = total_qty;
    }

    public String getStore_title() {
        return store_title;
    }

    public void setStore_title(String store_title) {
        this.store_title = store_title;
    }

    public int getTqty_StoreA() {
        return tqty_StoreA;
    }

    public void setTqty_StoreA(int tqty_StoreA) {
        this.tqty_StoreA = tqty_StoreA;
    }

    public int getTqty_StoreB() {
        return tqty_StoreB;
    }

    public void setTqty_StoreB(int tqty_StoreB) {
        this.tqty_StoreB = tqty_StoreB;
    }

    public int getTqty_StoreC() {
        return tqty_StoreC;
    }

    public void setTqty_StoreC(int tqty_StoreC) {
        this.tqty_StoreC = tqty_StoreC;
    }

    public List<viewStock> getStoreAviewInfo() throws Exception {
        this.connector();  
        
      //  con = (com.mysql.jdbc.Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/esimbarcode_hhy_store?user=root&password=ash123");

        List<viewStock> stockviewInfo = new ArrayList<viewStock>();

        try {

            PreparedStatement ps = this.getCn().prepareStatement("SELECT productID,ProductDescp,partner_name,store_title,Tqty_StoreA,Ttotal_qty FROM stockdetails  WHERE store_title='STORE A' order by Ttotal_qty Desc ");
            rs = ps.executeQuery();

            while (rs.next()) {
                viewStock tbl = new viewStock();
                tbl.setProductID(rs.getString("productID"));
                tbl.setProductDescp(rs.getString("productDescp"));
                tbl.setPartner_name(rs.getString("partner_name"));
                tbl.setStore_title(rs.getString("store_title"));
                tbl.setTqty_StoreA(rs.getInt("tqty_StoreA"));
                tbl.setTotal_qty(rs.getInt("Ttotal_qty"));
                stockviewInfo.add(tbl);
            }//end of while-block

        } catch (Exception e) {
            throw e;
        } finally {

            try {
                rs.close();
                ps.close();
                this.Close();
            } catch (Exception e) {
                e.printStackTrace();
                System.err.println("Error while viewing stock: " + e.getMessage());
            }
        }//end of finally-block

        return stockviewInfo;
    }//end of method
    
        public List<viewStock> getZeroQtyStoreAviewInfo() throws Exception {
        this.connector();  
        
      //  con = (com.mysql.jdbc.Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/esimbarcode_hhy_store?user=root&password=ash123");

        List<viewStock> stockviewInfo = new ArrayList<viewStock>();

        try {

            PreparedStatement ps = this.getCn().prepareStatement("SELECT productID,ProductDescp,partner_name,store_title,Tqty_StoreA,Ttotal_qty FROM stockdetails  WHERE store_title='STORE A' AND Ttotal_qty=0 ");
            rs = ps.executeQuery();

            while (rs.next()) {
                viewStock tbl = new viewStock();
                tbl.setProductID(rs.getString("productID"));
                tbl.setProductDescp(rs.getString("productDescp"));
                tbl.setPartner_name(rs.getString("partner_name"));
                tbl.setStore_title(rs.getString("store_title"));
                tbl.setTqty_StoreA(rs.getInt("tqty_StoreA"));
                tbl.setTotal_qty(rs.getInt("Ttotal_qty"));
                stockviewInfo.add(tbl);
            }//end of while-block

        } catch (Exception e) {
            throw e;
        } finally {

            try {
                rs.close();
                ps.close();
                this.Close();
            } catch (Exception e) {
                e.printStackTrace();
                System.err.println("Error while viewing stock: " + e.getMessage());
            }
        }//end of finally-block

        return stockviewInfo;
    }//end of method


    public List<viewStock> getStoreBviewInfo() throws Exception {
        this.connector();

        List<viewStock> stockviewInfo = new ArrayList<viewStock>();

        try {
            PreparedStatement ps = this.getCn().prepareStatement("SELECT productID,ProductDescp,partner_name,store_title,Tqty_StoreB,Ttotal_qty FROM stockdetails  WHERE store_title='STORE B'");
            rs = ps.executeQuery();

            while (rs.next()) {
                viewStock tbl = new viewStock();
                tbl.setProductID(rs.getString("productID"));
                tbl.setProductDescp(rs.getString("productDescp"));
                tbl.setPartner_name(rs.getString("partner_name"));
                tbl.setStore_title(rs.getString("store_title"));
                tbl.setTqty_StoreA(rs.getInt("tqty_StoreB"));
                tbl.setTotal_qty(rs.getInt("Ttotal_qty"));
                stockviewInfo.add(tbl);
            }//end of while-block

        } catch (Exception e) {
            throw e;
        } finally {

            try {
                rs.close();
                ps.close();
                this.Close();
            } catch (Exception e) {
                e.printStackTrace();
                System.err.println("Error while viewing stock: " + e.getMessage());
            }
        }//end of finally-block

        return stockviewInfo;
    }//end of method

    public List<viewStock> getStoreCviewInfo() throws Exception {
        this.connector();

        List<viewStock> stockviewInfo = new ArrayList<viewStock>();

        try {
            PreparedStatement ps = this.getCn().prepareStatement("SELECT productID,ProductDescp,partner_name,store_title,Tqty_StoreC,Ttotal_qty FROM stockdetails  WHERE store_title='STORE C'");
            rs = ps.executeQuery();

            while (rs.next()) {
                viewStock tbl = new viewStock();
                tbl.setProductID(rs.getString("productID"));
                tbl.setProductDescp(rs.getString("productDescp"));
                tbl.setPartner_name(rs.getString("partner_name"));
                tbl.setStore_title(rs.getString("store_title"));
                tbl.setTqty_StoreA(rs.getInt("tqty_StoreC"));
                tbl.setTotal_qty(rs.getInt("Ttotal_qty"));
                stockviewInfo.add(tbl);
            }//end of while-block

        } catch (Exception e) {
            throw e;
        } finally {

            try {
                rs.close();
                ps.close();
                this.Close();
            } catch (Exception e) {
                e.printStackTrace();
                System.err.println("Error while viewing stock: " + e.getMessage());
            }
        }//end of finally-block

        return stockviewInfo;
    }//end of method
}//end of vla 
