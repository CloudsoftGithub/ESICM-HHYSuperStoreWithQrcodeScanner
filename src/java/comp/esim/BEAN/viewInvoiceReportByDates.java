package comp.esim.BEAN;

import comp.esimbarcode.DAO.DAO;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;

@ManagedBean
@SessionScoped

public class viewInvoiceReportByDates extends DAO {

    //Connection con;
    PreparedStatement ps;
    ResultSet rs;

    private String customerName;
    private String orderDate;
    private String order_amount;
    private String TotalAmountDescp;
    private String orderTransactionID;
    private String startDate;
    private String toDate;

    private String transactionID;
    private String productID;
    private String productDescp;
    private String unitQty;
    private int unitQtySum;

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

    public String getUnitQty() {
        return unitQty;
    }

    public void setUnitQty(String unitQty) {
        this.unitQty = unitQty;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getOrder_amount() {
        return order_amount;
    }

    public void setOrder_amount(String order_amount) {
        this.order_amount = order_amount;
    }

    public String getTotalAmountDescp() {
        return TotalAmountDescp;
    }

    public void setTotalAmountDescp(String TotalAmountDescp) {
        this.TotalAmountDescp = TotalAmountDescp;
    }

    public String getOrderTransactionID() {
        return orderTransactionID;
    }

    public void setOrderTransactionID(String orderTransactionID) {
        this.orderTransactionID = orderTransactionID;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

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

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getToDate() {
        return toDate;
    }

    public void setToDate(String toDate) {
        this.toDate = toDate;
    }

    public int getUnitQtySum() {
        return unitQtySum;
    }

    public void setUnitQtySum(int unitQtySum) {
        this.unitQtySum = unitQtySum;
    }

    public void retriveProduct_TitleFromUI() {//get the current productTitle on the UI 
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        Product_Title = ec.getRequestParameterMap().get("stockUpdateForm:productTitle");
    }//end of method

    public void enableGoButton() throws Exception {
        getInvoiceAmountInfoByDates();

    }//end of the method

    public List<viewInvoiceReportByDates> getNoOfPurchasedItemsInfoByDates() throws Exception {

        this.connector();//establishes connection from the DAO Class (i.e the super class)
        List<viewInvoiceReportByDates> invoiceAmountInfo = new ArrayList<viewInvoiceReportByDates>();

        try {
            PreparedStatement ps = this.getCn().prepareStatement("SELECT  productDescp,sum(unitQty) Qty_Purchased FROM permantodertr WHERE order_Date BETWEEN ? AND  ? group by productDescp order by sum(unitQty) Desc ");
            ps.setString(1, startDate + " 00:00:00");//time instance was addeded on the inform of " 00:00:00"
            ps.setString(2, toDate + " 23:59:00"); /////time instance was addeded on the inform of " 23:59:00"

            rs = ps.executeQuery();

            RequestContext.getCurrentInstance().update("OrderInvoiceForm:salesTransactionDetailsPanelByDates");

            while (rs.next()) {
                System.out.println("The Transaction ID: " + transactionID);
                viewInvoiceReportByDates tbl = new viewInvoiceReportByDates();
                tbl.setOrderTransactionID(rs.getString(1));
                tbl.setProductDescp(rs.getString(1));
                tbl.setUnitQtySum(rs.getInt(2));

                invoiceAmountInfo.add(tbl);
            }//end of while-block

        } catch (Exception e) {
            throw e;
        } finally {

            if (this.getCn() != null) {
                this.Close();
            }//end of
        }//end of finally-block

        return invoiceAmountInfo;
    }//end of method

    public List<viewInvoiceReportByDates> getInvoiceAmountInfoByDates() throws Exception {
        getAggregateInvoiceAmountInfoByDates();//invoked

        this.connector();//establishes connection from the DAO Class (i.e the super class)
        List<viewInvoiceReportByDates> invoiceAmountInfo = new ArrayList<viewInvoiceReportByDates>();

        try {
            PreparedStatement ps = this.getCn().prepareStatement("SELECT orderTransactId,CONCAT('N', FORMAT(sum(TotalPerProduct) , 2) ) order_Amount,order_Date, customerName  FROM permantodertr WHERE order_Date between ? and ? GROUP BY orderTransactId Asc ");
            System.out.println("Testing Start Date now: " + startDate);

            ps.setString(1, startDate + " 00:00:00");//time instance was addeded on the inform of " 00:00:00"
            ps.setString(2, toDate + " 23:59:00"); /////time instance was addeded on the inform of " 23:59:00"

            rs = ps.executeQuery();

            RequestContext.getCurrentInstance().update("OrderInvoiceForm:salesTransactionDetailsPanelByDates");

            while (rs.next()) {
                System.out.println("The Transaction ID: " + transactionID);
                viewInvoiceReportByDates tbl = new viewInvoiceReportByDates();
                tbl.setOrderTransactionID(rs.getString(1));
                tbl.setOrder_amount(rs.getString(2));
                tbl.setOrderDate(rs.getString(3));
                tbl.setCustomerName(rs.getString(4));

                invoiceAmountInfo.add(tbl);
            }//end of while-block

        } catch (Exception e) {
            throw e;
        } finally {

            if (this.getCn() != null) {
                this.Close();
            }//end of
        }//end of finally-block

        return invoiceAmountInfo;
    }//end of method

    public List<viewInvoiceReportByDates> getAggregateInvoiceAmountInfoByDates() throws Exception {

        this.connector();//establishes connectdion from the DAO Class (i.e the super class)

        List<viewInvoiceReportByDates> invoiceAmountInfo = new ArrayList<viewInvoiceReportByDates>();

        try {
            PreparedStatement ps = this.getCn().prepareStatement("SELECT CONCAT('N', FORMAT(sum(TotalPerProduct) , 2) ) order_Amount  FROM permantodertr WHERE order_Date between ? and ?  ");
            ps.setString(1, startDate + " 00:00:00");//time instance was addeded on the inform of " 00:00:00"
            ps.setString(2, toDate + " 23:59:00"); /////time instance was addeded on the inform of " 23:59:00"

            rs = ps.executeQuery();

            while (rs.next()) {
                viewInvoiceReportByDates tbl = new viewInvoiceReportByDates();
                tbl.setOrder_amount(rs.getString(1));
                tbl.setStartDate(startDate);
                tbl.setToDate(toDate);
                invoiceAmountInfo.add(tbl);
            }//end of while-block

        } catch (Exception e) {
            throw e;
        } finally {

            if (this.getCn() != null) {
                this.Close();
            }//end of
        }//end of finally-block

        return invoiceAmountInfo;
    }//end of method

    public List<viewInvoiceReportByDates> getAggregateInvoiceAmountInfoForEntireTransactions() throws Exception {

        this.connector();//establishes connectdion from the DAO Class (i.e the super class)

        List<viewInvoiceReportByDates> invoiceAmountInfo = new ArrayList<viewInvoiceReportByDates>();

        try {
            PreparedStatement ps = this.getCn().prepareStatement("SELECT CONCAT('N', FORMAT(sum(TotalPerProduct) , 2) ) order_Amount  FROM permantodertr ");
            rs = ps.executeQuery();

            while (rs.next()) {
                viewInvoiceReportByDates tbl = new viewInvoiceReportByDates();
                tbl.setOrder_amount(rs.getString(1));
                invoiceAmountInfo.add(tbl);
            }//end of while-block

        } catch (Exception e) {
            throw e;
        } finally {

            if (this.getCn() != null) {
                this.Close();
            }//end of
        }//end of finally-block

        return invoiceAmountInfo;
    }//end of method
}//end of vla 
