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
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

@ManagedBean
@SessionScoped

public class viewstockUpdateTable extends DAO {

    //Connection con;
    PreparedStatement ps;
    ResultSet rs;

    private String transactionID;
    private String productID;
    private String productDescp;
    private String partner_name;
    private Date date_purchased;
    private int qty_added;
    private int previous_qty;
    private int total_qty;
    private String store_title;
    private double costPrice;

    private double salePrice;

    private String Product_Title;

    private double newPrice;

    public double getNewPrice() {
        return newPrice;
    }

    public void setNewPrice(double newPrice) {
        this.newPrice = newPrice;
    }
 
    public double getCostPrice() {
        return costPrice;
    }

    public void setCostPrice(double costPrice) {
        this.costPrice = costPrice;
    }

    public double getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(double salePrice) {
        this.salePrice = salePrice;
    }

    public String getProduct_Title() {
        return Product_Title;
    }

    public void setProduct_Title(String Product_Title) {
        this.Product_Title = Product_Title;
    }

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

    public int getPrevious_qty() {
        return previous_qty;
    }

    public void setPrevious_qty(int previous_qty) {
        this.previous_qty = previous_qty;
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

    public void retriveProduct_TitleFromUI() {//get the current productTitle on the UI 
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        Product_Title = ec.getRequestParameterMap().get("stockUpdateForm:productTitle");
    }//end of method

    public List<viewstockUpdateTable> getStockUpdatedInfo() throws Exception {
        this.connector();

        List<viewstockUpdateTable> stockupdatInfo = new ArrayList<viewstockUpdateTable>();

        try {

            ps = this.getCn().prepareStatement("select * from stockupdatetransc order by  date_purchased Desc ");
            rs = ps.executeQuery();

            while (rs.next()) {
                viewstockUpdateTable tbl = new viewstockUpdateTable();
                tbl.setTransactionID(rs.getString("transactionID"));
                tbl.setProductID(rs.getString("productID"));
                tbl.setProductDescp(rs.getString("productDescp"));
                tbl.setPartner_name(rs.getString("partner_name"));
                tbl.setDate_purchased(rs.getDate("date_purchased"));
                tbl.setStore_title(rs.getString("store_title"));
                tbl.setQty_added(rs.getInt("qty_added"));
                // tbl.setPrevious_qty(rs.getInt("previous_qty"));
                //tbl.setTotal_qty(rs.getInt("total_qty"));
                stockupdatInfo.add(tbl);
            }//end of while-block

        } catch (Exception e) {
            throw e;
        } finally {

            try {
                rs.close();
                ps.close();
                //con.close();
                this.Close();
            } catch (Exception e) {
                e.printStackTrace();
                System.err.println("Error Message: " + e.getMessage());
            }
        }//end of finally-block

        return stockupdatInfo;
    }//end of method

    public List<viewstockUpdateTable> getStockOldPriceInfo() throws Exception {
        this.connector();
        retriveProduct_TitleFromUI();//invoked

        List<viewstockUpdateTable> stockupdatInfo = new ArrayList<viewstockUpdateTable>();

        try {

            ps = this.getCn().prepareStatement("select * from products WHERE ProductDescp = ? ");
            ps.setString(1, Product_Title);
            rs = ps.executeQuery();

            while (rs.next()) {
                viewstockUpdateTable tbl = new viewstockUpdateTable();
                tbl.setProductID(rs.getString("productID"));
                tbl.setProductDescp(rs.getString("productDescp"));

                tbl.setPartner_name(rs.getString("CompanyName"));
                tbl.setSalePrice(rs.getDouble("SalesPrice"));
                 tbl.setDate_purchased(rs.getDate("date_of_registration"));
                //Product_Title = "";
                stockupdatInfo.add(tbl);
            }//end of while-block

        } catch (Exception e) {
            throw e;
        } finally {

            try {
                rs.close();
                ps.close();
                //con.close();
                this.Close();
            } catch (Exception e) {
                e.printStackTrace();
                System.err.println("Error Message: " + e.getMessage());
            }
        }//end of finally-block

        return stockupdatInfo;
    }//end of method
   
    public List<viewstockUpdateTable> getStockModifiedPriceUpdatedInfo() throws Exception {
        this.connector();
        retriveProduct_TitleFromUI();//invoked

        List<viewstockUpdateTable> stockupdatInfo = new ArrayList<viewstockUpdateTable>();

        try {

            ps = this.getCn().prepareStatement("select * from products_new_price order by date_of_registration desc");
            rs = ps.executeQuery();

            while (rs.next()) {
                viewstockUpdateTable tbl = new viewstockUpdateTable();
                tbl.setProductID(rs.getString("productID"));
                tbl.setProductDescp(rs.getString("productDescp"));

                tbl.setPartner_name(rs.getString("CompanyName"));
                tbl.setSalePrice(rs.getDouble("OldSalesPrice"));
                tbl.setNewPrice(rs.getDouble("NewSalesPrice"));
                tbl.setDate_purchased(rs.getDate("date_of_registration"));
                //Product_Title = "";
                stockupdatInfo.add(tbl);
            }//end of while-block

        } catch (Exception e) {
            throw e;
        } finally {

            try {
                rs.close();
                ps.close();
                //con.close();
                this.Close();
            } catch (Exception e) {
                e.printStackTrace();
                System.err.println("Error Message: " + e.getMessage());
            }
        }//end of finally-block

        return stockupdatInfo;
    }//end of method

}//end of vla 
