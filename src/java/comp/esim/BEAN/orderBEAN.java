package comp.esimbarcode.BEAN;

import comp.esimbarcode.DAO.DAO;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.event.ValueChangeEvent;
import javax.persistence.criteria.Order;
import org.primefaces.context.RequestContext;

@ManagedBean
@RequestScoped
public class orderBEAN extends DAO {

    int counter = 0;
    int checkAvailabilityCountter = 0;
    int checksIfPre_OrderifAdded = 0;
    int RowCount = 0;

    private int Ttotal_Qty;
    private int transactionCounter = 0;

    String myProdDescp;
    String myProdID;
    int myQtyinStock;

    int r = 0;
    int h = 0;
    int myunitQty = 0;
    int rstock = 0;

    int myTqty_StoreA = 0;
    int myTqty_StoreB = 0;
    int myTqty_StoreC = 0;
    int myTtotal_qty = 0;

    int myTqty_StoreA2 = 0;
    int myTqty_StoreB2 = 0;
    int myTqty_StoreC2 = 0;
    int myTtotal_qty2 = 0;
    String store_title2 = "";

    PreparedStatement ps;
    ResultSet rs;
    ResultSet dupChecksRS;

    // Connection con;
    Connection con2;
    PreparedStatement ps2;

    private String acceptance;
    private String delivery;

    private String customerID;
    private String customerName;
    private String BussinessName;
    private String phoneNumber;
    private String address;
    private String userName;

    private String productID;
    private String productDescp;
    private int unitQty = 1;//initial value of one products
    private double price;
    private double totalPerProduct;
    private String transactionType;
    private String orderDate;
    private String order_amount;
    private String TotalAmountDescp;
    private String orderTransactionID;
    private Date startDate;

    private String custName;

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getTotalAmountDescp() {
        return TotalAmountDescp;
    }

    public void setTotalAmountDescp(String TotalAmountDescp) {
        this.TotalAmountDescp = TotalAmountDescp;
    }

    private String Product_Title;

    private String transactionID;

    public String getAcceptance() {
        return acceptance;
    }

    public void setAcceptance(String acceptance) {
        this.acceptance = acceptance;
    }

    public String getDelivery() {
        return delivery;
    }

    public void setDelivery(String delivery) {
        this.delivery = delivery;
    }

    public int getTransactionCounter() {
        return transactionCounter;
    }

    public void setTransactionCounter(int transactionCounter) {
        this.transactionCounter = transactionCounter;
    }

    public int getMyTqty_StoreA2() {
        return myTqty_StoreA2;
    }

    public void setMyTqty_StoreA2(int myTqty_StoreA2) {
        this.myTqty_StoreA2 = myTqty_StoreA2;
    }

    public int getMyTqty_StoreB2() {
        return myTqty_StoreB2;
    }

    public void setMyTqty_StoreB2(int myTqty_StoreB2) {
        this.myTqty_StoreB2 = myTqty_StoreB2;
    }

    public int getMyTqty_StoreC2() {
        return myTqty_StoreC2;
    }

    public void setMyTqty_StoreC2(int myTqty_StoreC2) {
        this.myTqty_StoreC2 = myTqty_StoreC2;
    }

    public String getStore_title2() {
        return store_title2;
    }

    public int getMyTtotal_qty2() {
        return myTtotal_qty2;
    }

    public void setMyTtotal_qty2(int myTtotal_qty2) {
        this.myTtotal_qty2 = myTtotal_qty2;
    }

    public void setStore_title2(String store_title2) {
        this.store_title2 = store_title2;
    }

    public int getMyTqty_StoreA() {
        return myTqty_StoreA;
    }

    public void setMyTqty_StoreA(int myTqty_StoreA) {
        this.myTqty_StoreA = myTqty_StoreA;
    }

    public int getMyTqty_StoreB() {
        return myTqty_StoreB;
    }

    public void setMyTqty_StoreB(int myTqty_StoreB) {
        this.myTqty_StoreB = myTqty_StoreB;
    }

    public int getMyTqty_StoreC() {
        return myTqty_StoreC;
    }

    public void setMyTqty_StoreC(int myTqty_StoreC) {
        this.myTqty_StoreC = myTqty_StoreC;
    }

    public int getMyTtotal_qty() {
        return myTtotal_qty;
    }

    public void setMyTtotal_qty(int myTtotal_qty) {
        this.myTtotal_qty = myTtotal_qty;
    }

    public int getRstock() {
        return rstock;
    }

    public void setRstock(int rstock) {
        this.rstock = rstock;
    }

    public int getR() {
        return r;
    }

    public void setR(int r) {
        this.r = r;
    }

    public int getH() {
        return h;
    }

    public void setH(int h) {
        this.h = h;
    }

    public int getMyunitQty() {
        return myunitQty;
    }

    public void setMyunitQty(int myunitQty) {
        this.myunitQty = myunitQty;
    }

    public int getTtotal_Qty() {
        return Ttotal_Qty;
    }

    public void setTtotal_Qty(int Ttotal_Qty) {
        this.Ttotal_Qty = Ttotal_Qty;
    }

    public String getProduct_Title() {
        return Product_Title;
    }

    public void setProduct_Title(String Product_Title) {
        this.Product_Title = Product_Title;
    }

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getBussinessName() {
        return BussinessName;
    }

    public void setBussinessName(String BussinessName) {
        this.BussinessName = BussinessName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public int getUnitQty() {
        return unitQty;
    }

    public void setUnitQty(int unitQty) {
        this.unitQty = unitQty;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getTotalPerProduct() {
        return totalPerProduct;
    }

    public void setTotalPerProduct(double totalPerProduct) {
        this.totalPerProduct = totalPerProduct;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
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

    public String getTransactionID() {
        return transactionID;
    }

    public void setTransactionID(String transactionID) {
        this.transactionID = transactionID;
    }

    public String getOrderTransactionID() {
        return orderTransactionID;
    }

    public void setOrderTransactionID(String orderTransactionID) {
        this.orderTransactionID = orderTransactionID;
    }

    public void retriveTransactionIDFromUI() {//get the current tranctionID  on the UI 
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        transactionID = ec.getRequestParameterMap().get("OrderInvoiceForm:TransactionID");
    }//end of method

    public void retriveUserNameFromUI() {//get the current Username  on the UI 
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        userName = ec.getRequestParameterMap().get("CustomerOrderForm:userName");
    }//end of method  

    public void retriveCustomerIDFromUI() {//get the current CustomerID  on the UI 
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        customerID = ec.getRequestParameterMap().get("CustomerOrderForm:CustomerID");
    }//end of method

    public void retriveProductIDFromUI() {//get the current Product ID  on the UI 
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        productID = ec.getRequestParameterMap().get("CustomerOrderForm:productID");
    }//end of method

    public void retriveUnitQtyFromUI() {//get the current unitQtyon the UI 
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        String myunitQty = ec.getRequestParameterMap().get("CustomerOrderForm:unitQty");
        unitQty = Integer.parseInt(myunitQty);//converted to integer
    }//end of method

    public void retriveProductTitleFromUI() {//get the current Product Title  on the UI 
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        productDescp = ec.getRequestParameterMap().get("CustomerOrderForm:productTitle");
    }//end of method

    public void retriveCustomerName() {//get the current Product Title  on the UI 
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        customerName = ec.getRequestParameterMap().get("CustomerOrderForm:CustomerName");
    }//end of method

    public void retrieveCustomerInforMthd() throws Exception {
        //  clearCustomerInfo();//clears customer info prio-searching for the new customer

        this.connector();//establishes connectdion from the DAO Class (i.e the super class)

        try {

            //Msyql Local Conncetion
            //con = (com.mysql.jdbc.Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/esimbarcode?user=root&password=ash123");
            ps = this.getCn().prepareStatement("SELECT customerName,Buss_name,Phone,address FROM customer WHERE customerID=?");
            ps.setString(1, customerID);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                customerName = rs.getString(1);
                BussinessName = rs.getString(2);
                phoneNumber = rs.getString(3);
                address = rs.getString(4);

                this.Close();
                ps.close();
                rs.close();

            } else {

                // FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Customer Search Error", "The Customer ID is invalid. Pls, check and try again. ");
                // RequestContext.getCurrentInstance().showMessageInDialog(message);
            }//end of else-block

            preOrderMthd();//invokes the method, so Transaction ID will be creaed in the re-order transation table with the customerID as a foreign key

        } catch (Exception e) {
            // FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Customer Search Error", "The Customer ID is invalid. Pls, check and try again. ");
            // RequestContext.getCurrentInstance().showMessageInDialog(message);
            //end of catch-block
        }

    }//end of retrieveCustomerInforMthd method

    public void preOrderMthd() throws Exception {

        this.connector();//establishes connectdion from the DAO Class (i.e the super class)

        ps = this.getCn().prepareStatement("SELECT * FROM preodertransac ");
        //ps.setString(1, customerID);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {//if found 

            //Do NOTHING : this prevents duplicates in the pre-order table 
        } else {

            try {//does the insertion 
                ps = this.getCn().prepareStatement("INSERT INTO preodertransac VALUES(?)");
                ps.setString(1, null);
                //ps.setString(2, customerID);
                ps.executeUpdate();

            } catch (Exception e) {
                throw e;
            } finally {
                this.Close();
                ps.close();
                rs.close();
            }//end of finally-block

        }//end of else-block

    }//end of method 

    /*
    /////Retrieving the Product Description from DB
    public void viewAndComputeDescp() throws Exception {
      //  retrieveCustomerInforMthd();//invkes the view info button

        totalPerProduct = price * unitQty;

        try {
            //Mysql Local connection 
            con = (com.mysql.jdbc.Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/esimbarcode_hhy_store?user=root&password=ash123");

            // Jealastic connection on the Jealastic Cloud
            //  con = (com.mysql.jdbc.Connection) DriverManager.getConnection("jdbc:mysql://node12525-env-esimbarcode.cloud.interhostsolutions.be/esimbarcode", "root", "HHBoea11492");
            ps = con.prepareStatement("SELECT ProductDescp,productID FROM products WHERE productDescp=?");
            ps.setString(1, Product_Title);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                productDescp = rs.getString(1);
                productID = rs.getString(2);
            } else {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Product Search Error", "The Product Title is invalid. Pls, check and try again. ");
                RequestContext.getCurrentInstance().showMessageInDialog(message);
            }

        } catch (Exception e) {
            //FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Customer Search Error", "The Customer ID is invalid. Pls, check and try again. " + e.getMessage());
            //RequestContext.getCurrentInstance().showMessageInDialog(message);
        } finally {
            con.close();
            ps.close();
            rs.close();
        }//end of finally-block

    }//end of method
    
    
     */
    public void productCheck() throws SQLException, Exception {
        //checks the AVAILABILITY Of the Quantity for a PRODUCT needed

        this.connector();//establishes connectdion from the DAO Class (i.e the super class)

        try {
            //Mysql connection
            //con = (com.mysql.jdbc.Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/esimbarcode?user=root&password=ash123");

            // Jealastic connection on the Jealastic Cloud
            //  con = (com.mysql.jdbc.Connection) DriverManager.getConnection("jdbc:mysql://node12525-env-esimbarcode.cloud.interhostsolutions.be/esimbarcode", "root", "HHBoea11492");
            ps = this.getCn().prepareStatement("SELECT SUM(Ttotal_qty) FROM stockdetails WHERE productID=? GROUP BY ?");
            ps.setString(1, productID);
            ps.setString(2, productID);
            rs = ps.executeQuery();

            while (rs.next()) {
                Ttotal_Qty = rs.getInt(1);//getting the Total_Qty of products in the whole stores
                System.out.println("Qty: " + Ttotal_Qty);

            }//end of bigger if-block

        } catch (Exception e) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Product Availability Error", "The requested QTY for the product is NOT AVAILABLE! " + e.getMessage());
            RequestContext.getCurrentInstance().showMessageInDialog(message);
        } finally {
            this.Close();
            ps.close();
        }//end of finally-block

    }//end of method

    public void preOrderDetail() throws Exception {

        //productDescp = evt.getNewValue().toString();
        RowCount = 0;//clears the counter 

        this.connector();//establishes connectdion from the DAO Class (i.e the super class)

        retriveUserNameFromUI();//invokes the method so 'userName' variable get captured from the BEAN

        try {

            ps = this.getCn().prepareStatement("SELECT  productID,ProductDescp,SalesPrice FROM products WHERE ProductDescp=?");
            ps.setString(1, productDescp);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                productID = rs.getString(1);
                productDescp = rs.getString(2);
                price = rs.getDouble(3);
            }
        } catch (Exception e) {
            throw e;
        }

               try {//INSERTING THE PRE-ORDER TUPLES

            //  con = (com.mysql.jdbc.Connection) DriverManager.getConnection("jdbc:mysql://node12525-env-esimbarcode.cloud.interhostsolutions.be/esimbarcode", "root", "HHBoea11492");
            ps = this.getCn().prepareStatement("INSERT INTO preoderdetails VALUES(?,?,?,?,?, unitQty*Price, Now(), ?) ON DUPLICATE KEY UPDATE unitQty=unitQty+1,Price=VALUES(Price),TotalPerProduct=unitQty*Price, order_Date=VALUES(order_Date) ");

            ps.setString(1, productID);
            ps.setString(2, customerName);
            ps.setString(3, productDescp);
            ps.setInt(4, unitQty);
            ps.setDouble(5, price);
            ps.setString(6, userName);

            RowCount = ps.executeUpdate();

            if (RowCount > 0) {
                System.out.println("Testign row Counter: " + RowCount);
                FacesContext.getCurrentInstance().addMessage(
                        null,
                        new FacesMessage(FacesMessage.SEVERITY_INFO,
                                "Congrats! " + productDescp + " Added for the Customer Order successfully.",
                                "Thank you."));
            } else {
                //Do nothing 
            }

            productDescp = "";//clears the former productDescp for the previous transaction before the current transaction 

        } catch (Exception e) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Pre-Orderdetails Error", "Error Occured. Pls, check and try again. " + e.getMessage());
            RequestContext.getCurrentInstance().showMessageInDialog(message);
        } finally {
            this.Close();
            ps.close();
        }//end of finally-block
        
        
     



        checkQTYfromSTOCK();//invoked the method

        //RequestContext.getCurrentInstance().scrollTo("CustomerOrderForm:productTitle");
    }//end of orderDetailMthd

    public void confirmOrder() throws Exception {

        this.connector();//establishes connectdion from the DAO Class (i.e the super class)

        retriveUserNameFromUI();//get invoked

        checksIFPre_orderTableContainsTuple();// invokes the method 

        if (userName.equalsIgnoreCase("")) {

            FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(FacesMessage.SEVERITY_FATAL,
                            "Order-Confirmation Error. " + "Username NOT DISPLAYED. Pls, login again!",
                            "Thank you."));

        } else if (customerName.equalsIgnoreCase("")) {
            FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            "Order-Confirmation Error. " + "Pls, enter customer name or GSM no.",
                            "Thank you."));
        } else if (checksIfPre_OrderifAdded <= 0) {

            FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            "Product Adding Error" + "Pls, add some products first before you CONFIRM THE ORDER! ",
                            "Thank you."));

        } else {

            this.connector();//establishes connectdion from the DAO Class (i.e the super class)

            ////Step1: CLEARING THE 'ordertransact' TABLE BY DELETING ALL RAOWS WITH SAME USERNAME
            ////------->DELETING THE 'orderTransactId' FROM THE table: confirmedoder AT THE BEGINING OF THE operations
            ///////////////////////
            try {//

                ps = this.getCn().prepareStatement("DELETE FROM ordertransact WHERE userName=?");
                ps.setString(1, userName);
                ps.executeUpdate();

            } catch (Exception e) {
                throw e;
            }

            ////Step2: CLEARING THE 'confirmedOder' TABLE BY DELETING ALL ROWS WITH SAME USERNAME
            ////------->DELETING THE userName ROWS FROM THE table: confirmedoder AT THE BEGINING OF THE operations
            ///////////////////////
            try {//
                // con = (com.mysql.jdbc.Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/esimbarcode_hhy_store?user=root&password=ash123");

                ps = this.getCn().prepareStatement("DELETE FROM confirmedOder WHERE userName=?");
                ps.setString(1, userName);
                ps.executeUpdate();

            } catch (Exception e) {
                throw e;
            }

            try {//Step3: copying the pre-order details into the confirmedOrder table

                ps = this.getCn().prepareStatement("INSERT INTO confirmedOder (orderTransactId, productID, productDescp, unitQty, Price, TotalPerProduct, order_Date, username, customerName) SELECT null,productID, productDescp, unitQty, Price, TotalPerProduct, order_Date, username,customerName FROM preoderdetails WHERE username=?");
                ps.setString(1, userName);
                ps.executeUpdate();

                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "The requested  Order for: " + customerName + " has been confirmed successfully.", "Thank you."));
                clearPreorderDetails();
                transactionCounter += 1;// INCREMENTING transactionCounter (i.e to know if order has been confirmed successfully)

            } catch (Exception e) {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Order-Confirmation Error", "The requested order is NOT CONFIRMED! " + e.getMessage());
                RequestContext.getCurrentInstance().showMessageInDialog(message);
            }//end of catch block 

            ////Step4: DEDUCTING THE ORDERED QTY FROM THE AVAIALABLE STORE i.e 'STORE A' 
            if (transactionCounter > 0) {//checking if the transaction is successful

                /////COMPUTING AND DEDUCTING THE ORDER LIST FROM THE AVAILABLE STOCK, OUT OF THE THREE STORES A,B,C
                ///updating the Tqty_StoreA in the stockdetails with the value of r
                ps = this.getCn().prepareStatement(" UPDATE stockdetails AS b INNER JOIN preoderdetails AS x ON  x.productID=b.productID  SET b.Tqty_StoreA = Tqty_StoreA - x.unitQty, b.Ttotal_qty = Ttotal_qty - x.unitQty WHERE x.productID=b.productID AND store_title='STORE A' AND x.username= ?");
                ps.setString(1, userName);
                ps.executeUpdate();
            }

            if (transactionCounter > 0) {//checking if the transaction is successful

                //Z1..........this occurs AFTER confirming Order and updating the 'stockdetails' table
                try {//  BY userName

                    ps = this.getCn().prepareStatement("DELETE FROM preoderdetails WHERE userName=?");
                    ps.setString(1, userName);
                    ps.executeUpdate();

                    //the content fo compiledOderTransac ORDER get deleted after the successful copying and update      
                } catch (Exception e) {
                    throw e;
                }

                try {//A: Creating transaction ID by inserting tuples into orderTransact

                    ps = this.getCn().prepareStatement("INSERT INTO orderTransact VALUES (null, CONCAT('T',ID),?) ");
                    ps.setString(1, userName);
                    ps.executeUpdate();

                } catch (Exception e) {
                    throw e;
                }

                ////------->UPDATING THE TransactionID FROM THE "T" into 'T'+ID (e.g T000001)
                ///////////////////////
                try {//B
                    ps = this.getCn().prepareStatement("UPDATE orderTransact SET orderTransactId = CONCAT('T',ID) WHERE userName=?");
                    ps.setString(1, userName);
                    ps.executeUpdate();

                } catch (Exception e) {
                    throw e;
                }

                ////------->UPDATING THE 'orderTransactId' FROM THE table: 'orderTransact'
                ///////////////////////
                try {//B
                    ps = this.getCn().prepareStatement("UPDATE orderTransact SET orderTransactId = CONCAT('T',ID) WHERE userName=?");
                    ps.setString(1, userName);
                    ps.executeUpdate();

                } catch (Exception e) {
                    throw e;
                }

                ///////////// RETRIEVING THE orderTransactionID from the table 'ordertransact'
                //con = (com.mysql.jdbc.Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/esimbarcode?user=root&password=ash123");
                ps = this.getCn().prepareStatement("SELECT orderTransactId FROM ordertransact WHERE userName=?");
                ps.setString(1, userName);
                ResultSet rs = ps.executeQuery();

                while (rs.next()) {

                    transactionID = rs.getString(1);
                    //con.close();
                    //ps.close();
                    // rs.close();
                }

                ////------->UPDATING THE 'orderTransactId' FROM THE table: 'confirmedoder'. AFTER THE ORDER CONFIRMATION. This is because the the column orderTransactId was null and now got updated
                ///////////////////////
                try {//C:
                    ps = this.getCn().prepareStatement("UPDATE confirmedoder SET orderTransactId = ? WHERE userName=?");
                    ps.setString(1, transactionID);
                    ps.setString(2, userName);
                    ps.executeUpdate();

                } catch (Exception e) {
                    throw e;
                }

                //D....COPYING THE CONTENT OF confirmedoder INTO A TABLE CALLED ...permantodertr ...
                try {//copying the CONTENT OF confirmedoder into the permantodertr TABLE

                    //Mysql connection
                    //con = (com.mysql.jdbc.Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/esimbarcode?user=root&password=ash123");
                    // Jealastic connection on the Jealastic Cloud
                    ps = this.getCn().prepareStatement("INSERT INTO permantodertr (orderTransactId, productID, productDescp, unitQty, Price, TotalPerProduct, order_Date, acceptance, delivery, customerName) "
                            + "SELECT orderTransactId, productID, productDescp, unitQty, Price, TotalPerProduct, order_Date,null,null,customerName FROM confirmedoder WHERE username=?");
                    ps.setString(1, userName);
                    ps.executeUpdate();

                } catch (Exception e) {
                    throw e;
                } finally {
                    this.Close();
                }

            }//end of if-block

        }//end of else block 

        this.Close();

    }//end of method 

    ///Confirmin the ORDER 
    /*
    
      public void confirmOrder() throws Exception {

        if (customerName.equalsIgnoreCase("")) {

            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Order-Confirmation Error", "Pls, make sure that 'Cutomer ID is populated'");
            RequestContext.getCurrentInstance().showMessageInDialog(message);

        } else {

            try {//copying the pre-order details into the confirmedOrder table

                //Mysql connection
                con = (com.mysql.jdbc.Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/esimbarcode_hhy_store?user=root&password=ash123");

                // Jealastic connection on the Jealastic Cloud
                //  con = (com.mysql.jdbc.Connection) DriverManager.getConnection("jdbc:mysql://node12525-env-esimbarcode.cloud.interhostsolutions.be/esimbarcode", "root", "HHBoea11492");
                ps = con.prepareStatement("INSERT INTO confirmedOder (customerName, productID, productDescp, unitQty, Price, TotalPerProduct, TransactionType, order_Date) SELECT customerName, productID, productDescp, unitQty, Price, TotalPerProduct, TransactionType, order_Date FROM preoderdetails WHERE customerName=?");
                ps.setString(1, customerName);

                ps.executeUpdate();

                FacesContext.getCurrentInstance().addMessage(
                        null,
                        new FacesMessage(FacesMessage.SEVERITY_INFO,
                                "The requested  Order has been confirmed successfully.",
                                "Thank you."));

                clearPreorderDetails();
                transactionCounter += 1;// INCREMENTING transactionCounter (i.e to know is order has been confirmed successfully)

                System.out.println("my counter = " + transactionCounter);

            } catch (Exception e) {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Order-Confirmation Error", "The requested order is NOT CONFIRMED! " + e.getMessage());
                RequestContext.getCurrentInstance().showMessageInDialog(message);
            }

            if (transactionCounter > 0) { //order confirmation begins HERE!

                //Z1..........this occurs AFTER copying the compiled order and updating the orderTransactId
                try {//DELETING THE DETAILES IN THE compiledOderTransac BY customerName

                    //Mysql Connection 
                    con = (com.mysql.jdbc.Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/esimbarcode_hhy_store?user=root&password=ash123");

                    // Jealastic connection on the Jealastic Cloud
                    //  con = (com.mysql.jdbc.Connection) DriverManager.getConnection("jdbc:mysql://node12525-env-esimbarcode.cloud.interhostsolutions.be/esimbarcode", "root", "HHBoea11492");
                    ps = con.prepareStatement("DELETE FROM compiledOderTransac WHERE customerName=?");
                    ps.setString(1, customerName);
                    ps.executeUpdate();

                    //the content fo compiledOderTransac ORDER get deleted after the successful copying and update      
                } catch (Exception e) {
                    throw e;
                }

                //Z2..........this occurs AFTER copying the compiled order and updating the orderTransactId
                try {//DELETING THE DETAILES IN THE ordertransact BY customerID

                    //Mysql Connection 
                    con = (com.mysql.jdbc.Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/esimbarcode_hhy_store?user=root&password=ash123");

                    // Jealastic connection on the Jealastic Cloud
                    //  con = (com.mysql.jdbc.Connection) DriverManager.getConnection("jdbc:mysql://node12525-env-esimbarcode.cloud.interhostsolutions.be/esimbarcode", "root", "HHBoea11492");
                    ps = con.prepareStatement("DELETE FROM ordertransact WHERE customerName=?");
                    ps.setString(1, customerName);
                    ps.executeUpdate();

                    //the content of ordertransact ORDER get deleted after the successful copying and update      
                } catch (Exception e) {
                    throw e;
                }

                //z5..........this occurs AFTER the confirmation of ORDER and after all dedcuctionand calculations 
                try {//DELETING THE StockANDorder table's content 
                    //Mysql Connection 
                    con = (com.mysql.jdbc.Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/esimbarcode_hhy_store?user=root&password=ash123");

                    // Jealastic connection on the Jealastic Cloud
                    //  con = (com.mysql.jdbc.Connection) DriverManager.getConnection("jdbc:mysql://node12525-env-esimbarcode.cloud.interhostsolutions.be/esimbarcode", "root", "HHBoea11492");
                    ps = con.prepareStatement("DELETE FROM StockANDorder WHERE customerName=?");
                    ps.setString(1, customerName);
                    ps.executeUpdate();

                    //the StockANDorder contents get deleted after the successful order       
                } catch (Exception e) {
                    throw e;
                }

                //z6:
                ///TRUNCATING THE CONTENT OF THE TABLES 'rnegprod' BY the customer ID
                //At starting in order to clear it up
                try {

                    //Mysql Connection 
                    con = (com.mysql.jdbc.Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/esimbarcode_hhy_store?user=root&password=ash123");

                    // Jealastic connection on the Jealastic Cloud
                    //  con = (com.mysql.jdbc.Connection) DriverManager.getConnection("jdbc:mysql://node12525-env-esimbarcode.cloud.interhostsolutions.be/esimbarcode", "root", "HHBoea11492");
                    ps = con.prepareStatement("DELETE FROM rnegprod WHERE customerName=?");
                    ps.setString(1, customerName);
                    ps.executeUpdate();

                    //the 'rnegprod' table content get deleted after the successful order       
                } catch (Exception e) {
                    throw e;
                }

                try {//A
                    //Mysql connection
                    con = (com.mysql.jdbc.Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/esimbarcode_hhy_store?user=root&password=ash123");

                    // Jealastic connection on the Jealastic Cloud
                    //  con = (com.mysql.jdbc.Connection) DriverManager.getConnection("jdbc:mysql://node12525-env-esimbarcode.cloud.interhostsolutions.be/esimbarcode", "root", "HHBoea11492");
                    ps = con.prepareStatement("INSERT INTO orderTransact VALUES (null, CONCAT('NT',ID),?)");
                    ps.setString(1, customerName);

                    ps.executeUpdate();

                } catch (Exception e) {
                    throw e;
                }

                ////------->UPDATING THE TransactionID FROM THE "NT" into 'NT'+ID (e.g NT000001)
                ///////////////////////
                try {//B
                    ps = con.prepareStatement("UPDATE orderTransact SET orderTransactId = CONCAT('NT',ID) WHERE customerName=?");
                    ps.setString(1, customerName);
                    ps.executeUpdate();

                } catch (Exception e) {
                    throw e;
                }

                //////////A1
                try {//copying the confirmed order into the COMPILEDORDER TABLE

                    //Mysql connection
                    con = (com.mysql.jdbc.Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/esimbarcode_hhy_store?user=root&password=ash123");

                    // Jealastic connection on the Jealastic Cloud
                    //  con = (com.mysql.jdbc.Connection) DriverManager.getConnection("jdbc:mysql://node12525-env-esimbarcode.cloud.interhostsolutions.be/esimbarcode", "root", "HHBoea11492");
                    ps = con.prepareStatement("INSERT INTO compiledOderTransac (customerName,productID, productDescp, unitQty, Price, TotalPerProduct, TransactionType, order_Date) SELECT customerName, productID, productDescp, unitQty, Price, TotalPerProduct, TransactionType, order_Date FROM confirmedOder WHERE customerName=?");
                    ps.setString(1, customerName);
                    ps.executeUpdate();

                } catch (Exception e) {
                    throw e;
                }

                ///A2
                ////------->UPDATING THE orderTransactId in  compiledOderTransac TABLE
                ///////////////////////
                try {//
                    ps = con.prepareStatement("UPDATE compiledOderTransac SET orderTransactId=(SELECT orderTransactId FROM ordertransact WHERE customerName=?) WHERE customerName=?");
                    ps.setString(1, customerName);
                    ps.setString(2, customerName);
                    ps.executeUpdate();

                    // transactionCounter = 0;
                } catch (Exception e) {
                    throw e;
                }

                //A3....COPYING THE CONTENT OF compiledOderTransac INTO A TABLE CALLED ...permantodertr ...
                try {//copying the CONTENT OF compiledOderTransac into the permantodertr TABLE

                    //Mysql connection
                    con = (com.mysql.jdbc.Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/esimbarcode_hhy_store?user=root&password=ash123");
                    // Jealastic connection on the Jealastic Cloud
                    //  con = (com.mysql.jdbc.Connection) DriverManager.getConnection("jdbc:mysql://node12525-env-esimbarcode.cloud.interhostsolutions.be/esimbarcode", "root", "HHBoea11492");
                    ps = con.prepareStatement("INSERT INTO permantodertr (orderTransactId,customerName, productID, productDescp, unitQty, Price, TotalPerProduct, TransactionType, order_Date, acceptance, delivery) SELECT orderTransactId,customerName, productID, productDescp, unitQty, Price, TotalPerProduct, TransactionType, order_Date,null,null FROM compiledOderTransac WHERE customerName=?");
                    ps.setString(1, customerName);
                    ps.executeUpdate();

                } catch (Exception e) {
                    throw e;
                }

                /////////////////R1
                //RETIEVING THE TRANSACTION ID FROM THE ID AFTER a successfull order operation (and displying the ID on the screen)
                try {
                    //Msyql Local Conncetion
                    con = (com.mysql.jdbc.Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/esimbarcode_hhy_store?user=root&password=ash123");

                    // Jealastic connection on the Jealastic Cloud
                    //  con = (com.mysql.jdbc.Connection) DriverManager.getConnection("jdbc:mysql://node12525-env-esimbarcode.cloud.interhostsolutions.be/esimbarcode", "root", "HHBoea11492");
                    ps = con.prepareStatement("SELECT orderTransactId FROM compiledOderTransac WHERE customerName=?");
                    ps.setString(1, customerName);
                    ResultSet rs = ps.executeQuery();

                    while (rs.next()) {
                        String myOrderTransaction = rs.getString(1);
                        transactionID = myOrderTransaction;
                    }

                } catch (Exception e) {
                    throw e;
                }
 
                ////ys
                /////// Deducting the ordered Qty from the STOCK I.E STORES A, B, C
                //Y1/// STEP1: COPY THE PRE-ORDER DETAIL table + STOCKUPDTE table into a table called StockANDorder
                ///Y2// DEDUCT THE Tqty_StoreA, Tqty_StoreB OR Tqty_StoreC FROM THE unitQty with respect to the product and customer from the three stores A, B, C
                
                /////////Y1 - STEP1: 
                try {
                    //Msyql Local Conncetion
                    con = (com.mysql.jdbc.Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/esimbarcode_hhy_store?user=root&password=ash123");

                    // Jealastic connection on the Jealastic Cloud
                    //  con = (com.mysql.jdbc.Connection) DriverManager.getConnection("jdbc:mysql://node12525-env-esimbarcode.cloud.interhostsolutions.be/esimbarcode", "root", "HHBoea11492");
                    //ps = con.prepareStatement("INSERT INTO StockANDorder(customerName,productID, productDescp, unitQty, Price, TotalPerProduct, TransactionType, order_Date, store_title, Tqty_StoreA, Tqty_StoreB, Tqty_StoreC,Rmd,Ttotal_qty)   SELECT customerID,preoderdetails.productID, preoderdetails.productDescp, preoderdetails.unitQty, preoderdetails.Price, preoderdetails.TotalPerProduct, preoderdetails.TransactionType, preoderdetails.order_Date,store_title,Tqty_StoreA,Tqty_StoreB,Tqty_StoreC,NULL,Ttotal_qty  FROM preoderdetails INNER JOIN stockdetails on preoderdetails.productID=stockdetails.productID  WHERE preoderdetails.customerID=?");
                    ps = con.prepareStatement("INSERT INTO StockANDorder(customerName,productID, productDescp, unitQty, Price, TotalPerProduct, TransactionType, order_Date, store_title, Tqty_StoreA, Tqty_StoreB, Tqty_StoreC,Rmd,Ttotal_qty)   SELECT customerName,preoderdetails.productID, preoderdetails.productDescp, preoderdetails.unitQty, preoderdetails.Price, preoderdetails.TotalPerProduct, preoderdetails.TransactionType, preoderdetails.order_Date,store_title,Tqty_StoreA,Tqty_StoreB,Tqty_StoreC,NULL,Ttotal_qty  FROM preoderdetails INNER JOIN stockdetails on preoderdetails.productID=stockdetails.productID  WHERE store_title='STORE A' AND  preoderdetails.customerName=?");
                    ps.setString(1, customerName);
                    ps.executeUpdate();

                } catch (Exception e) {
                    throw e;
                }

                /////////Y2 - STEP2: 
                try {
                    //Msyql Local Conncetion
                    con = (com.mysql.jdbc.Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/esimbarcode_hhy_store?user=root&password=ash123");

                    // Jealastic connection on the Jealastic Cloud
                    //  con = (com.mysql.jdbc.Connection) DriverManager.getConnection("jdbc:mysql://node12525-env-esimbarcode.cloud.interhostsolutions.be/esimbarcode", "root", "HHBoea11492");
                    ps = con.prepareStatement(" SELECT StockANDorder.unitQty, StockANDorder.Tqty_StoreA, StockANDorder.Ttotal_qty FROM StockANDorder,preoderdetails  WHERE preoderdetails.productID=StockANDorder.productID AND store_title='STORE A' AND preoderdetails.customerName=?");
                    ps.setString(1, customerName);
                    ResultSet rs = ps.executeQuery();

                    while (rs.next()) {
                        r = 0;
                        h = 0;
                        myunitQty = rs.getInt(1);
                        myTqty_StoreA = rs.getInt(2);
                        myTtotal_qty = rs.getInt(3);
                        r = myTqty_StoreA - myunitQty;
                    }//end of while-block

                } catch (Exception e) {
                    throw e;
                }

                /////COMPUTING AND DEDUCTING THE ORDER LIST FROM THE AVAILABLE STOCK, OUT OF THE THREE STORES A,B,C
                if (r <= 0) {
                    if (r == 0) {
                        ///updating the Tqty_StoreA in the stockdetails with the value of r
                        //ps = con.prepareStatement("UPDATE stockdetails SET Tqty_StoreA=? , Ttotal_qty= ? WHERE productID='N001HFL' AND store_title='STORE A' ");
                        //ps = con.prepareStatement("UPDATE stockdetails AS b INNER JOIN preoderdetails AS x ON x.productID=b.productID  SET Tqty_StoreA=? , Ttotal_qty= ? WHERE store_title='STORE A' AND x.customerID=? ");
                        ps = con.prepareStatement(" UPDATE stockdetails AS b INNER JOIN preoderdetails AS x ON  x.productID=b.productID  SET b.Tqty_StoreA = Tqty_StoreA - x.unitQty, b.Ttotal_qty = Ttotal_qty - x.unitQty WHERE x.productID=b.productID AND store_title='STORE A' AND x.customerName=?");

                        //ps.setInt(1, r);
                        //ps.setInt(2, rstock);
                        ps.setString(1, customerName);
                        ps.executeUpdate();

                        ///updating the 'Rmd' in the StockANDorder with the new value of r
                        //ps = con.prepareStatement("UPDATE StockANDorder SET Rmd=? , Tqty_StoreA = Tqty_StoreA - unitQty WHERE productID='N001HFL' AND store_title='STORE A' ");
                        //ps = con.prepareStatement("UPDATE StockANDorder AS b INNER JOIN preoderdetails AS x ON x.productID=b.productID  SET Rmd=? , Tqty_StoreA = Tqty_StoreA - b.unitQty, Ttotal_qty = ? WHERE store_title='STORE A' AND x.customerID=? ");
                        ps = con.prepareStatement("UPDATE StockANDorder AS b INNER JOIN preoderdetails AS x ON  x.productID=b.productID  SET b.Rmd = Tqty_StoreA - x.unitQty, b.Tqty_StoreA = Tqty_StoreA - x.unitQty, b.Ttotal_qty = Ttotal_qty - x.unitQty WHERE x.productID=b.productID AND store_title='STORE A' AND x.customerName=? ");

                        // ps.setInt(1, r);
                        // ps.setInt(2, r);
                        ps.setString(1, customerName);
                        ps.executeUpdate();

                    } else if (r < 0) {
                        //STEP1: Checking for any Product in 'Store A ' whose Qty is enough to serve the requested no.
                        //IF found, The product(s) should be copied to a table called 'rnegprod' for some adjustments   
                        //STEP2: Update the 'stockdetails'  and 'stockandorder' tables with new values of   Tqty_StoreB & Ttotal_qty and Rmd for Store B
                        //STEP3:  Update the 'stockdetails'  and 'stockandorder' tables with  'zero' values of   Tqty_StoreA & Ttotal_qty and Rmd fror 'Store A'
                        //SETP4: iNSERT THE REMAINING PREORDER DETAILS into StockAndOrder with regards to 'Store B'
                        //SETP5: SELECTING THE REMAINING PREORDER DETAILS FROM StockAndOrder with regards to 'Store B'
                        ///TRUNCATING THE CONTENT OF THE TABLES 'rnegprod' BY the customer ID                        
                        //STEP7: continue to test r in 'STORE B' THEN 'STORE C' (if not available in Store B)

                        //checking IF there is any requested product is not avialable in 'Store A' 
                        //COPYING THE PRODUCT WHOSE 'Tqty_StoreA - preoderdetails.unitQty <0 ' (i.e th eproduct is not available in Store A, or its not enough to serve the requested no.) IS NEGATIVE TO A TABLE CALLED 'rnegprod' 
                        try {
                            //Msyql Local Conncetion
                            con = (com.mysql.jdbc.Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/esimbarcode_hhy_store?user=root&password=ash123");

                            // Jealastic connection on the Jealastic Cloud
                            //  con = (com.mysql.jdbc.Connection) DriverManager.getConnection("jdbc:mysql://node12525-env-esimbarcode.cloud.interhostsolutions.be/esimbarcode", "root", "HHBoea11492");
                            ps = con.prepareStatement("INSERT INTO rnegprod(customerName,productID, productDescp, unitQty, Price, TotalPerProduct, TransactionType, order_Date, store_title, Tqty_StoreA, Tqty_StoreB, Tqty_StoreC,Rmd,Ttotal_qty)   SELECT customerName,preoderdetails.productID, preoderdetails.productDescp, preoderdetails.unitQty, preoderdetails.Price, preoderdetails.TotalPerProduct, preoderdetails.TransactionType, preoderdetails.order_Date,store_title,Tqty_StoreA,Tqty_StoreB,Tqty_StoreC,NULL,Ttotal_qty  FROM preoderdetails INNER JOIN stockdetails on preoderdetails.productID=stockdetails.productID  WHERE store_title='STORE A' AND Tqty_StoreA - preoderdetails.unitQty <0 AND preoderdetails.customerName=? ");
                            ps.setString(1, customerName);
                            ps.executeUpdate();

                        } catch (Exception e) {
                            throw e;
                        }

                        //STEP2: (a)
                        //update1: UPDATING THE the 'Store B' of 'stockdetails' with the value of 'Store A' same product since Store A doesn't has enough qty of product needed OR has Qty=0, (This enables the system to addup the Store A qty into B for same product and deduct from B)
                        try {
                            //Mysql
                            con = (com.mysql.jdbc.Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/esimbarcode_hhy_store?user=root&password=ash123");

                            // Jealastic connection on the Jealastic Cloud
                            //  con = (com.mysql.jdbc.Connection) DriverManager.getConnection("jdbc:mysql://node12525-env-esimbarcode.cloud.interhostsolutions.be/esimbarcode", "root", "HHBoea11492");
                            ps = con.prepareStatement(" UPDATE stockdetails AS b INNER JOIN  rnegprod AS x ON  x.productID=b.productID  SET  b.Tqty_StoreB = b.Tqty_StoreB + x.Tqty_StoreA, b.Ttotal_qty= b.Ttotal_qty + x.Ttotal_qty  WHERE x.productID=b.productID AND b.store_title='STORE B' AND x.customerName=? ");
                            ps.setString(1, customerName);
                            ps.executeUpdate();

                        } catch (Exception e) {
                            throw e;
                        }

                        //STEP3: (a)
                        //updating  the Tqty_StoreA and Ttotal_qty in the stockdetails with value '0' i.e because its qtys have been added to Store B same produts same tables
                        //Mysql                        
                        con = (com.mysql.jdbc.Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/esimbarcode_hhy_store?user=root&password=ash123");

                        // Jealastic connection on the Jealastic Cloud
                        //  con = (com.mysql.jdbc.Connection) DriverManager.getConnection("jdbc:mysql://node12525-env-esimbarcode.cloud.interhostsolutions.be/esimbarcode", "root", "HHBoea11492");
                        ps = con.prepareStatement("UPDATE stockdetails AS b INNER JOIN  rnegprod AS x ON  b.productID=x.productID  SET  b.Tqty_StoreA = 0, b.Ttotal_qty= 0  WHERE b.productID=x.productID AND b.store_title='STORE A' AND x.customerName=?");
                        ps.setString(1, customerName);
                        ps.executeUpdate();

                        //STEP3: (a)
                        //updating  the Tqty_StoreA and Ttotal_qty in the StockANDorder with value '0' i.e because its qtys have been added to Store B same produts same tables
                        //Mysql                        
                        con = (com.mysql.jdbc.Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/esimbarcode_hhy_store?user=root&password=ash123");

                        // Jealastic connection on the Jealastic Cloud
                        //  con = (com.mysql.jdbc.Connection) DriverManager.getConnection("jdbc:mysql://node12525-env-esimbarcode.cloud.interhostsolutions.be/esimbarcode", "root", "HHBoea11492");
                        ps = con.prepareStatement("UPDATE StockANDorder AS b INNER JOIN  rnegprod AS x ON  b.productID=x.productID  SET b.Rmd = 0, b.Tqty_StoreA = 0, b.Ttotal_qty = 0  WHERE b.productID=x.productID AND b.store_title='STORE A' AND x.customerName=?");
                        ps.setString(1, customerName);
                        ps.executeUpdate();

                        try {
                            //Mysql                        
                            con = (com.mysql.jdbc.Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/esimbarcode_hhy_store?user=root&password=ash123");

                            // Jealastic connection on the Jealastic Cloud
                            //  con = (com.mysql.jdbc.Connection) DriverManager.getConnection("jdbc:mysql://node12525-env-esimbarcode.cloud.interhostsolutions.be/esimbarcode", "root", "HHBoea11492");
                            //STEP4:
                            //Inserting the pre-order details product into StockAndorder with regards to store 'Store B' While 
                            ps = con.prepareStatement("INSERT INTO StockANDorder(customerName,productID, productDescp, unitQty, Price, TotalPerProduct, TransactionType, order_Date, store_title, Tqty_StoreA, Tqty_StoreB, Tqty_StoreC,Rmd,Ttotal_qty)   SELECT customerName,preoderdetails.productID, preoderdetails.productDescp, preoderdetails.unitQty, preoderdetails.Price, preoderdetails.TotalPerProduct, preoderdetails.TransactionType, preoderdetails.order_Date,store_title,Tqty_StoreA,Tqty_StoreB,Tqty_StoreC,NULL,Ttotal_qty  FROM preoderdetails INNER JOIN stockdetails on preoderdetails.productID=stockdetails.productID  WHERE store_title='STORE B' AND preoderdetails.customerName=?");
                            ps.setString(1, customerName);
                            ps.executeUpdate();

                            //STEP5:
                            //Selecting the pre-order details product FROM StockAndorder with regards to store 'Store B', Tqy needed can be deducted from the total
                            //Mysql                        
                            con = (com.mysql.jdbc.Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/esimbarcode_hhy_store?user=root&password=ash123");

                            // Jealastic connection on the Jealastic Cloud
                            //  con = (com.mysql.jdbc.Connection) DriverManager.getConnection("jdbc:mysql://node12525-env-esimbarcode.cloud.interhostsolutions.be/esimbarcode", "root", "HHBoea11492");
                            ps = con.prepareStatement(" SELECT StockANDorder.unitQty, StockANDorder.Tqty_StoreB, StockANDorder.Ttotal_qty FROM StockANDorder,preoderdetails  WHERE preoderdetails.productID=StockANDorder.productID AND store_title='STORE B' AND preoderdetails.customerName=?");
                            ps.setString(1, customerName);
                            ResultSet rs = ps.executeQuery();

                            while (rs.next()) {
                                r = 0;
                                h = 0;
                                myunitQty = rs.getInt(1);
                                myTqty_StoreB = rs.getInt(2);
                                myTtotal_qty = rs.getInt(3);
                                r = myTqty_StoreB - myunitQty;///
                            }//end of while-block

                        } catch (Exception e) {
                            throw e;
                        }

                        //STEP6:
                        ///TRUNCATING THE CONTENT OF THE TABLES 'rnegprod' BY the customer ID
                        //This is beacuse its work has elapsed and we may use it again for Store C
                        try {

                            //Mysql Connection 
                            con = (com.mysql.jdbc.Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/esimbarcode_hhy_store?user=root&password=ash123");

                            // Jealastic connection on the Jealastic Cloud
                            //  con = (com.mysql.jdbc.Connection) DriverManager.getConnection("jdbc:mysql://node12525-env-esimbarcode.cloud.interhostsolutions.be/esimbarcode", "root", "HHBoea11492");
                            ps = con.prepareStatement("DELETE FROM rnegprod WHERE customerName=?");
                            ps.setString(1, customerName);
                            ps.executeUpdate();

                            //the 'rnegprod' table content get deleted after the successful order       
                        } catch (Exception e) {
                            throw e;
                        }

                        //STEP7:                        
                        ///TESTING FOR r IN 'STORE B' for those products not 
                        if (r <= 0) {

                            if (r == 0) {
                                ///updating the Tqty_StoreB in the stockdetails with the value of r
                                //Mysql                        
                                con = (com.mysql.jdbc.Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/esimbarcode_hhy_store?user=root&password=ash123");

                                // Jealastic connection on the Jealastic Cloud
                                //  con = (com.mysql.jdbc.Connection) DriverManager.getConnection("jdbc:mysql://node12525-env-esimbarcode.cloud.interhostsolutions.be/esimbarcode", "root", "HHBoea11492");
                                ps = con.prepareStatement(" UPDATE stockdetails AS b INNER JOIN preoderdetails AS x ON  x.productID=b.productID  SET b.Tqty_StoreB = Tqty_StoreB - x.unitQty, b.Ttotal_qty = Ttotal_qty - x.unitQty WHERE x.productID=b.productID AND store_title='STORE B' AND x.customerName=?");
                                ps.setString(1, customerName);
                                ps.executeUpdate();

                                ///updating the 'Rmd' in the StockANDorder with the new value of r
                                //Mysql                        
                                con = (com.mysql.jdbc.Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/esimbarcode_hhy_store?user=root&password=ash123");

                                // Jealastic connection on the Jealastic Cloud
                                //  con = (com.mysql.jdbc.Connection) DriverManager.getConnection("jdbc:mysql://node12525-env-esimbarcode.cloud.interhostsolutions.be/esimbarcode", "root", "HHBoea11492");
                                ps = con.prepareStatement("UPDATE StockANDorder AS b INNER JOIN preoderdetails AS x ON  x.productID=b.productID  SET b.Rmd = Tqty_StoreB - x.unitQty, b.Tqty_StoreB = Tqty_StoreB - x.unitQty, b.Ttotal_qty = Ttotal_qty - x.unitQty WHERE x.productID=b.productID AND store_title='STORE B' AND x.customerName=? ");
                                ps.setString(1, customerName);
                                ps.executeUpdate();

                            } else if (r < 0) {//checking if r <0 (i.e the Qty of product needed is not available in both Stores A, B or its not enough)

                                //STEP1: Checking for any Product in 'Store B' whose Qty is enough to serve the requested no.
                                //IF found, The product(s) should be copied to a table called 'rnegprod' for some adjustments   
                                //STEP2: Update the 'stockdetails'  table with new values of   Tqty_StoreC & Ttotal_qty and Rmd for 'Store C'
                                //STEP3:  Update the 'stockdetails'  and 'stockandorder' tables with  'zero' values of   Tqty_StoreA & Ttotal_qty and Rmd fror 'Store A'
                                //SETP4: iNSERT THE REMAINING PREORDER DETAILS into StockAndOrder with regards to 'Store B'
                                //SETP5: SELECTING THE REMAINING PREORDER DETAILS FROM StockAndOrder with regards to 'Store B'
                                ///TRUNCATING THE CONTENT OF THE TABLES 'rnegprod' BY the customer ID                        
                                //STEP7: continue to test r in 'STORE B' THEN 'STORE C' (if not available in Store B)
                                try {//checking IF there is any requested product is not avialable in 'Store B' 
                                    //COPYING THE PRODUCT WHOSE 'Tqty_StoreA - preoderdetails.unitQty <0 ' (i.e th eproduct is not available in Store A, or its not enough to serve the requested no.) IS NEGATIVE TO A TABLE CALLED 'rnegprod' 
                                    //Msyql Local Conncetion
                                    con = (com.mysql.jdbc.Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/esimbarcode_hhy_store?user=root&password=ash123");

                                    // Jealastic connection on the Jealastic Cloud
                                    //  con = (com.mysql.jdbc.Connection) DriverManager.getConnection("jdbc:mysql://node12525-env-esimbarcode.cloud.interhostsolutions.be/esimbarcode", "root", "HHBoea11492");
                                    ps = con.prepareStatement("INSERT INTO rnegprod(customerName,productID, productDescp, unitQty, Price, TotalPerProduct, TransactionType, order_Date, store_title, Tqty_StoreA, Tqty_StoreB, Tqty_StoreC,Rmd,Ttotal_qty)   SELECT customerName,preoderdetails.productID, preoderdetails.productDescp, preoderdetails.unitQty, preoderdetails.Price, preoderdetails.TotalPerProduct, preoderdetails.TransactionType, preoderdetails.order_Date,store_title,Tqty_StoreA,Tqty_StoreB,Tqty_StoreC,NULL,Ttotal_qty  FROM preoderdetails INNER JOIN stockdetails on preoderdetails.productID=stockdetails.productID  WHERE store_title='STORE B' AND Tqty_StoreA - preoderdetails.unitQty <0 AND preoderdetails.customerName=? ");
                                    ps.setString(1, customerName);
                                    ps.executeUpdate();

                                } catch (Exception e) {
                                    throw e;
                                }

                                //STEP2: (a)
                                //update1: UPDATING THE the 'Store C' of 'stockdetails' with the value of 'Store B' same product since 'Store B' doesn't has enough qty of product needed OR has Qty=0, (This enables the system to addup the Store B qty into 'Store C' for same product and deduct from 'Store C')
                                try {
                                    //Mysql                        
                                    con = (com.mysql.jdbc.Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/esimbarcode_hhy_store?user=root&password=ash123");

                                    // Jealastic connection on the Jealastic Cloud
                                    //  con = (com.mysql.jdbc.Connection) DriverManager.getConnection("jdbc:mysql://node12525-env-esimbarcode.cloud.interhostsolutions.be/esimbarcode", "root", "HHBoea11492");
                                    ps = con.prepareStatement("  UPDATE stockdetails AS b INNER JOIN  rnegprod AS x ON  x.productID=b.productID  SET  b.Tqty_StoreC = b.Tqty_StoreC + x.Tqty_StoreB, b.Ttotal_qty= b.Ttotal_qty + x.Ttotal_qty  WHERE x.productID=b.productID AND b.store_title='STORE C' AND x.customerName=? ");
                                    ps.setString(1, customerName);
                                    ps.executeUpdate();

                                } catch (Exception e) {
                                    throw e;
                                }

                                //STEP3: (a)  
                                //updating  the Tqty_StoreB and Ttotal_qty in the stockdetails with value '0' i.e because its qtys have been added to 'Store C' same produts same tables
                                //Mysql                        
                                con = (com.mysql.jdbc.Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/esimbarcode_hhy_store?user=root&password=ash123");

                                // Jealastic connection on the Jealastic Cloud
                                //  con = (com.mysql.jdbc.Connection) DriverManager.getConnection("jdbc:mysql://node12525-env-esimbarcode.cloud.interhostsolutions.be/esimbarcode", "root", "HHBoea11492");
                                ps = con.prepareStatement(" UPDATE stockdetails AS b INNER JOIN  rnegprod AS x ON  b.productID=x.productID  SET  b.Tqty_StoreB = 0, b.Ttotal_qty= 0  WHERE b.productID=x.productID AND b.store_title='STORE B' AND x.customerName=?");
                                ps.setString(1, customerName);
                                ps.executeUpdate();

                                //STEP3: (a)
                                //updating  the Tqty_StoreB and Ttotal_qty in the StockANDorder with value '0' i.e because its qtys have been added to 'Store C' same produts same tables
                                //Mysql                        
                                con = (com.mysql.jdbc.Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/esimbarcode_hhy_store?user=root&password=ash123");

                                // Jealastic connection on the Jealastic Cloud
                                //  con = (com.mysql.jdbc.Connection) DriverManager.getConnection("jdbc:mysql://node12525-env-esimbarcode.cloud.interhostsolutions.be/esimbarcode", "root", "HHBoea11492");
                                ps = con.prepareStatement("UPDATE StockANDorder AS b INNER JOIN  rnegprod AS x ON  b.productID=x.productID  SET b.Rmd = 0, b.Tqty_StoreB = 0, b.Ttotal_qty = 0  WHERE b.productID=x.productID AND b.store_title='STORE B' AND x.customerName=?");
                                ps.setString(1, customerName);
                                ps.executeUpdate();

                                try {
                                    //Mysql                        
                                    con = (com.mysql.jdbc.Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/esimbarcode_hhy_store?user=root&password=ash123");

                                    // Jealastic connection on the Jealastic Cloud
                                    //  con = (com.mysql.jdbc.Connection) DriverManager.getConnection("jdbc:mysql://node12525-env-esimbarcode.cloud.interhostsolutions.be/esimbarcode", "root", "HHBoea11492");
                                    //STEP4:
                                    //Inserting the pre-order details product into StockAndorder with regards to store 'Store C' While 
                                    ps = con.prepareStatement("INSERT INTO StockANDorder(customerName,productID, productDescp, unitQty, Price, TotalPerProduct, TransactionType, order_Date, store_title, Tqty_StoreA, Tqty_StoreB, Tqty_StoreC,Rmd,Ttotal_qty)   SELECT customerName,preoderdetails.productID, preoderdetails.productDescp, preoderdetails.unitQty, preoderdetails.Price, preoderdetails.TotalPerProduct, preoderdetails.TransactionType, preoderdetails.order_Date,store_title,Tqty_StoreA,Tqty_StoreB,Tqty_StoreC,NULL,Ttotal_qty  FROM preoderdetails INNER JOIN stockdetails on preoderdetails.productID=stockdetails.productID  WHERE store_title='STORE C' AND preoderdetails.customerName=?");
                                    ps.setString(1, customerName);
                                    ps.executeUpdate();

                                    //STEP5:
                                    //Selecting the pre-order details product FROM StockAndorder with regards to store 'Store B', Tqy needed can be deducted from the total
                                    //Mysql                        
                                    con = (com.mysql.jdbc.Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/esimbarcode_hhy_store?user=root&password=ash123");

                                    // Jealastic connection on the Jealastic Cloud
                                    //  con = (com.mysql.jdbc.Connection) DriverManager.getConnection("jdbc:mysql://node12525-env-esimbarcode.cloud.interhostsolutions.be/esimbarcode", "root", "HHBoea11492");
                                    ps = con.prepareStatement(" SELECT StockANDorder.unitQty, StockANDorder.Tqty_StoreC, StockANDorder.Ttotal_qty FROM StockANDorder,preoderdetails  WHERE preoderdetails.productID=StockANDorder.productID AND store_title='STORE C' AND preoderdetails.customerName=?");
                                    ps.setString(1, customerName);
                                    ResultSet rs = ps.executeQuery();

                                    while (rs.next()) {
                                        r = 0;
                                        h = 0;
                                        myunitQty = rs.getInt(1);
                                        myTqty_StoreB = rs.getInt(2);
                                        myTtotal_qty = rs.getInt(3);
                                        r = myTqty_StoreB - myunitQty;///
                                    }//end of while-block

                                } catch (Exception e) {
                                    throw e;
                                }

                                //STEP6:
                                ///TRUNCATING THE CONTENT OF THE TABLES 'rnegprod' BY the customer ID
                                //This is beacuse its work has elapsed and we may use it again for Store C
                                try {

                                    //Mysql Connection 
                                    con = (com.mysql.jdbc.Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/esimbarcode_hhy_store?user=root&password=ash123");

                                    // Jealastic connection on the Jealastic Cloud
                                    //  con = (com.mysql.jdbc.Connection) DriverManager.getConnection("jdbc:mysql://node12525-env-esimbarcode.cloud.interhostsolutions.be/esimbarcode", "root", "HHBoea11492");
                                    ps = con.prepareStatement("DELETE FROM rnegprod WHERE customerName=?");
                                    ps.setString(1, customerName);
                                    ps.executeUpdate();

                                    //the 'rnegprod' table content get deleted after the successful order       
                                } catch (Exception e) {
                                    throw e;
                                }

                                if (r >= 0) {

                                    ///TESTING FOR r IN 'STORE C' for those products not in 'Store B'
                                    if (r == 0) {
                                        ///updating the Tqty_StoreC in the stockdetails with the value of r
                                        //Mysql                        
                                        con = (com.mysql.jdbc.Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/esimbarcode_hhy_store?user=root&password=ash123");

                                        // Jealastic connection on the Jealastic Cloud
                                        //  con = (com.mysql.jdbc.Connection) DriverManager.getConnection("jdbc:mysql://node12525-env-esimbarcode.cloud.interhostsolutions.be/esimbarcode", "root", "HHBoea11492");
                                        ps = con.prepareStatement(" UPDATE stockdetails AS b INNER JOIN preoderdetails AS x ON  x.productID=b.productID  SET b.Tqty_StoreC = Tqty_StoreC - x.unitQty, b.Ttotal_qty = Ttotal_qty - x.unitQty WHERE x.productID=b.productID AND store_title='STORE C' AND x.customerName=?");
                                        ps.setString(1, customerName);
                                        ps.executeUpdate();

                                        ///updating the 'Rmd' in the StockANDorder with the new value of r
                                        //Mysql                        
                                        con = (com.mysql.jdbc.Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/esimbarcode_hhy_store?user=root&password=ash123");

                                        // Jealastic connection on the Jealastic Cloud
                                        //  con = (com.mysql.jdbc.Connection) DriverManager.getConnection("jdbc:mysql://node12525-env-esimbarcode.cloud.interhostsolutions.be/esimbarcode", "root", "HHBoea11492");
                                        ps = con.prepareStatement("UPDATE StockANDorder AS b INNER JOIN preoderdetails AS x ON  x.productID=b.productID  SET b.Rmd = b.Tqty_StoreC - x.unitQty, b.Tqty_StoreC = b.Tqty_StoreC - x.unitQty, b.Ttotal_qty = b.Ttotal_qty - x.unitQty WHERE x.productID=b.productID AND store_title='STORE C' AND x.customerName=? ");
                                        ps.setString(1, customerName);
                                        ps.executeUpdate();

                                    } else if (r > 0) {
                                        ///updating the Tqty_StoreC in the stockdetails with the value of r
                                        con = (com.mysql.jdbc.Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/esimbarcode_hhy_store?user=root&password=ash123");
                                        ps = con.prepareStatement(" UPDATE stockdetails AS b INNER JOIN preoderdetails AS x ON  x.productID=b.productID  SET b.Tqty_StoreC = Tqty_StoreC - x.unitQty, b.Ttotal_qty = Ttotal_qty - x.unitQty WHERE x.productID=b.productID AND store_title='STORE C' AND x.customerName=?");
                                        ps.setString(1, customerName);
                                        ps.executeUpdate();

                                        ///updating the 'Rmd' in the StockANDorder with the new value of r
                                        ps = con.prepareStatement(" UPDATE StockANDorder AS b INNER JOIN preoderdetails AS x ON  x.productID=b.productID  SET b.Rmd = Tqty_StoreC - x.unitQty, b.Tqty_StoreC = Tqty_StoreC - x.unitQty, b.Ttotal_qty = Ttotal_qty - x.unitQty WHERE x.productID=b.productID AND store_title='STORE C' AND x.customerName=? ");
                                        ps.setString(1, customerName);
                                        ps.executeUpdate();

                                    }///

                                }//

                            }

                        } else if (r > 0) {
                            ///updating the Tqty_StoreB in the stockdetails with the value of r
                            //Mysql                        
                            con = (com.mysql.jdbc.Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/esimbarcode_hhy_store?user=root&password=ash123");

                            // Jealastic connection on the Jealastic Cloud
                            //  con = (com.mysql.jdbc.Connection) DriverManager.getConnection("jdbc:mysql://node12525-env-esimbarcode.cloud.interhostsolutions.be/esimbarcode", "root", "HHBoea11492");
                            ps = con.prepareStatement(" UPDATE stockdetails AS b INNER JOIN preoderdetails AS x ON  x.productID=b.productID  SET b.Tqty_StoreB = Tqty_StoreB - x.unitQty, b.Ttotal_qty = Ttotal_qty - x.unitQty WHERE x.productID=b.productID AND store_title='STORE B' AND x.customerName=?");
                            ps.setString(1, customerName);
                            ps.executeUpdate();

                            ///updating the 'Rmd' in the StockANDorder with the new value of r
                            ps = con.prepareStatement(" UPDATE StockANDorder AS b INNER JOIN preoderdetails AS x ON  x.productID=b.productID  SET b.Rmd = Tqty_StoreB - x.unitQty, b.Tqty_StoreB = Tqty_StoreB - x.unitQty, b.Ttotal_qty = Ttotal_qty - x.unitQty WHERE x.productID=b.productID AND store_title='STORE B' AND x.customerName=? ");
                            ps.setString(1, customerName);
                            ps.executeUpdate();

                        }

                    }

                } else if (r > 0) {
                    ///updating the Tqty_StoreA in the stockdetails with the value of r
                    //ps = con.prepareStatement("UPDATE stockdetails SET Tqty_StoreA=? , Ttotal_qty =  ? WHERE productID='N001HFL' AND store_title='STORE A' ");
                    // ps = con.prepareStatement("UPDATE stockdetails AS b INNER JOIN preoderdetails AS x ON x.productID = b.productID  SET Tqty_StoreA = ? , Ttotal_qty =  ?  WHERE store_title='STORE A' AND x.customerName=?  ");
                    ps = con.prepareStatement(" UPDATE stockdetails AS b INNER JOIN preoderdetails AS x ON  x.productID=b.productID  SET b.Tqty_StoreA = Tqty_StoreA - x.unitQty, b.Ttotal_qty = Ttotal_qty - x.unitQty WHERE x.productID=b.productID AND store_title='STORE A' AND x.customerName=?");
                    //ps.setInt(1, r);
                    // ps.setInt(2, r);  //pls, try r here. thanks
                    ps.setString(1, customerName);
                    ps.executeUpdate();

                    ///updating the 'Rmd' in the StockANDorder with the new value of r
                    //ps = con.prepareStatement("UPDATE StockANDorder SET Rmd=? , Tqty_StoreA = ? WHERE productID='N001HFL' AND store_title='STORE A'");
                    //ps = con.prepareStatement(" UPDATE StockANDorder AS b INNER JOIN preoderdetails AS x ON x.productID=b.productID  SET Rmd=? , Tqty_StoreA = ?,Ttotal_qty=? WHERE  store_title='STORE A' AND x.customerName=? ");
                    ps = con.prepareStatement(" UPDATE StockANDorder AS b INNER JOIN preoderdetails AS x ON  x.productID=b.productID  SET b.Rmd = Tqty_StoreA - x.unitQty, b.Tqty_StoreA = Tqty_StoreA - x.unitQty, b.Ttotal_qty = Ttotal_qty - x.unitQty WHERE x.productID=b.productID AND store_title='STORE A' AND x.customerName=? ");
                    //ps.setInt(1, r);
                    //ps.setInt(2, r);
                    //ps.setInt(3, r);
                    ps.setString(1, customerName);
                    ps.executeUpdate();

                }//end of the bigger if-block 

                //Z3
                //Z3..........this occurs AFTER the confirmation of ORDER
                try {//DELETING THE ALREADY PROCESSDED ORDER IN THE preorderDetails

                    //Mysql Connection 
                    con = (com.mysql.jdbc.Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/esimbarcode_hhy_store?user=root&password=ash123");

                    // Jealastic connection on the Jealastic Cloud
                    //  con = (com.mysql.jdbc.Connection) DriverManager.getConnection("jdbc:mysql://node12525-env-esimbarcode.cloud.interhostsolutions.be/esimbarcode", "root", "HHBoea11492");
                    ps = con.prepareStatement("DELETE FROM preoderdetails WHERE customerName=?");
                    ps.setString(1, customerName);
                    ps.executeUpdate();

                    //the PROCESSED ORDER get deleted after the successful order       
                } catch (Exception e) {
                    throw e;
                }

                //z4..........this occurs AFTER the confirmation of ORDER
                try {//DELETING THE ALREADY PROCESSDED ORDER IN THE confirmedOderDETAILS

                    //Mysql Connection 
                    con = (com.mysql.jdbc.Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/esimbarcode_hhy_store?user=root&password=ash123");

                    // Jealastic connection on the Jealastic Cloud
                    //  con = (com.mysql.jdbc.Connection) DriverManager.getConnection("jdbc:mysql://node12525-env-esimbarcode.cloud.interhostsolutions.be/esimbarcode", "root", "HHBoea11492");
                    ps = con.prepareStatement("DELETE FROM confirmedOder WHERE customerName=?");
                    ps.setString(1, customerName);
                    ps.executeUpdate();

                    ExternalContext redcontext = FacesContext.getCurrentInstance().getExternalContext();
                    redcontext.redirect("acceptOrderBySalesManager.xhtml");   /// redirecting to  the 

                    //the PROCESSED ORDER get deleted after the successful order       
                } catch (Exception e) {
                    throw e;
                }

            }//end of if-block

        }//end of else-block

    }//end of method
    
     */
    private void stockDeductOrderQty() {

    }//en of method 

    public void clearCustomerInfo() {
        customerName = "";
        BussinessName = "";
        phoneNumber = "";
        address = "";

    }//end of method 

    public void clearPreorderDetails() {
        productDescp = "";//clears the vabraible for new input
        productID = "";
        //transactionType = "";
        totalPerProduct = 0;
        unitQty = 0;
        price = 0;
        Product_Title = "";

    }//end of method

    ////DELETING/REMOVING THE PRODUCTS FROM THE PRE-ORDER LIST (Customer may deside to do some adjustments for some reasons)
    public void removeProductFromOrderList() throws Exception {

        this.connector();//establishes connectdion from the DAO Class (i.e the super class)

        retriveUserNameFromUI();//invokes the method so 'userName' variable get captured from the BEAN

        try {

            ps = this.getCn().prepareStatement("DELETE FROM preoderdetails WHERE productDescp=? AND username=?");
            ps.setString(1, productDescp);
            ps.setString(2, userName);

            ps.executeUpdate();

            FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO,
                            "Product with product ID; " + productID + " has been removed successfully from " + " Order-List .",
                            "Thank you."));

        } catch (Exception e) {
            throw e;
        } finally {
            this.Close();
            ps.close();
        }//end of finally-block

    }//end of method

    ////DELETING/REMOVING THE PRODUCTS FROM THE PRE-ORDER LIST (Customer may deside to do some adjustments for some reasons)
    public void removeProductFromOrderListWithCommandLink(String productDescp) throws Exception {

        this.connector();//establishes connectdion from the DAO Class (i.e the super class)

        retriveUserNameFromUI();//invokes the method so 'userName' variable get captured from the BEAN

        try {

            ps = this.getCn().prepareStatement("DELETE FROM preoderdetails WHERE productDescp =? ");
            ps.setString(1, productDescp);
            // ps.setString(2, userName);

            ps.executeUpdate();

            FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO,
                            "Product with product ID; " + productID + " has been removed successfully from " + " Order-List .",
                            "Thank you."));

        } catch (Exception e) {
            throw e;
        } finally {
            this.Close();
            ps.close();
        }//end of finally-block

    }//end of method

    ////DELETING/remove all products from the preorder list, by 'username'
    public void deleteAllProductFromPreOrderList() throws Exception {

        this.connector();//establishes connectdion from the DAO Class (i.e the super class)

        retriveUserNameFromUI();//invokes the method so 'userName' variable get captured from the BEAN

        try {

            ps = this.getCn().prepareStatement("DELETE FROM preoderdetails WHERE username=?");
            ps.setString(1, userName);

            ps.executeUpdate();

            FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO,
                            "All Products " + " have been removed/Cleared successfully from " + " Order-List .",
                            "Thank you."));

        } catch (Exception e) {
            throw e;
        } finally {
            this.Close();
            ps.close();
        }//end of finally-block

    }//end of method

    ////Data table implementation -- for fre-order info 
    public List<orderBEAN> getPreOrderDetailsInfo() throws Exception {

        this.connector();//establishes connectdion from the DAO Class (i.e the super class)
        retriveUserNameFromUI();

        List<orderBEAN> preOrderInfo = new ArrayList<orderBEAN>();

        try {

            PreparedStatement ps = this.getCn().prepareStatement("SELECT * FROM  preoderdetails WHERE username = ? ORDER BY  order_Date Desc");
            ps.setString(1, userName);
            rs = ps.executeQuery();

            RequestContext.getCurrentInstance().update("CustomerOrderForm:CustomerRegPanel");

            while (rs.next()) {
                orderBEAN tbl = new orderBEAN();
                //tbl.setCustomerName(rs.getString("customerName"));
                //  tbl.setCustomerID(rs.getString("customerID"));
                tbl.setProductID(rs.getString("productID"));
                tbl.setProductDescp(rs.getString("productDescp"));
                tbl.setUnitQty(rs.getInt("unitQty"));
                tbl.setPrice(rs.getDouble("Price"));
                tbl.setTotalPerProduct(rs.getDouble("TotalPerProduct"));
                //tbl.setTransactionType(rs.getString("TransactionType"));
                tbl.setOrderDate(rs.getString("order_Date"));
                tbl.setUserName(rs.getString("username"));
                preOrderInfo.add(tbl);
            }//end of while-block

        } catch (Exception e) {
            throw e;
        } finally {

            if (this.getCn() != null) {
                this.Close();
            }//end of
        }//end of finally-block

        return preOrderInfo;
    }//end of method

    ////Data table implementation --FOR the permanenctOrder info use for 'order-invoice'
    public List<orderBEAN> getPermanentOrderInfo() throws Exception {

        this.connector();//establishes connectdion from the DAO Class (i.e the super class)

        List<orderBEAN> permanentOrderInfo = new ArrayList<orderBEAN>();

        try {

            //Mysql Connection 
            // con = (com.mysql.jdbc.Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/esimbarcode_hhy_store?user=root&password=ash123");
            // Jealastic connection on the Jealastic Cloud
            //  con = (com.mysql.jdbc.Connection) DriverManager.getConnection("jdbc:mysql://node12525-env-esimbarcode.cloud.interhostsolutions.be/esimbarcode", "root", "HHBoea11492");
            PreparedStatement ps = this.getCn().prepareStatement("SELECT * FROM  permantodertr WHERE orderTransactId=? ");
            ps.setString(1, transactionID);
            rs = ps.executeQuery();

            while (rs.next()) {
                orderBEAN tbl = new orderBEAN();
                tbl.setCustomerName(rs.getString("customerName"));
                //tbl.setCustomerID(rs.getString("customerID"));
                tbl.setProductID(rs.getString("productID"));
                tbl.setProductDescp(rs.getString("productDescp"));
                tbl.setUnitQty(rs.getInt("unitQty"));
                tbl.setPrice(rs.getDouble("Price"));
                tbl.setTotalPerProduct(rs.getDouble("TotalPerProduct"));
                tbl.setTransactionType(rs.getString("TransactionType"));
                tbl.setOrderDate(rs.getString("order_Date"));
                tbl.setOrderDate(rs.getString("acceptance"));
                tbl.setOrderDate(rs.getString("delivery"));
                permanentOrderInfo.add(tbl);
            }//end of while-block

        } catch (Exception e) {
            throw e;
        } finally {

            if (this.getCn() != null) {
                this.Close();
            }//end of
        }//end of finally-block

        return permanentOrderInfo;
    }//end of the method 

    public List<orderBEAN> getPreOrderAmountInfo() throws Exception {

        this.connector();//establishes connectdion from the DAO Class (i.e the super class)

        List<orderBEAN> preOrderInfo = new ArrayList<orderBEAN>();

        try {
            //Mysql Connection 
            //con = (com.mysql.jdbc.Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/esimbarcode?user=root&password=ash123");

            // Jealastic connection on the Jealastic Cloud
            //  con = (com.mysql.jdbc.Connection) DriverManager.getConnection("jdbc:mysql://node12525-env-esimbarcode.cloud.interhostsolutions.be/esimbarcode", "root", "HHBoea11492");
            PreparedStatement ps = this.getCn().prepareStatement("SELECT CONCAT('N', FORMAT(sum(TotalPerProduct) , 2) )   Order_Amount FROM preoderdetails ");
            // ps.setString(1, customerName);
            rs = ps.executeQuery();

            while (rs.next()) {
                orderBEAN tbl = new orderBEAN();
                // tbl.setCustomerName(rs.getString("customerName"));
                tbl.setOrder_amount(rs.getString(1));
                preOrderInfo.add(tbl);
            }//end of while-block

        } catch (Exception e) {
            throw e;
        } finally {

            if (this.getCn() != null) {
                this.Close();
            }//end of
        }//end of finally-block

        return preOrderInfo;
    }//end of method

    public void invoiceCallingmthd() throws Exception {

        this.connector();//establishes connectdion from the DAO Class (i.e the super class)

        //RETIEVING THE TRANSACTION ID FROM THE  'permantodertr' table 
        try {

            ps = this.getCn().prepareStatement("SELECT orderTransactId FROM permantodertr WHERE orderTransactId=?");
            ps.setString(1, transactionID);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                transactionID = rs.getString(1);
            }

        } catch (Exception e) {
            throw e;
        } finally {
            this.Close();
        }
    }//end of method

    /////GENERATING INVOICE REPORT
    public List<orderBEAN> getInvoiceReportInfoPerDate() throws Exception {

        this.connector();//establishes connectdion from the DAO Class (i.e the super class)

        List<orderBEAN> invoiceinfo = new ArrayList<orderBEAN>();

        try {
            //transactionID="";//clears
            PreparedStatement ps = this.getCn().prepareStatement("SELECT * FROM  permantodertr WHERE orderTransactId=?   ");
            ps.setString(1, transactionID);
            rs = ps.executeQuery();

            while (rs.next()) {
                orderBEAN tbl = new orderBEAN();
                tbl.setProductID(rs.getString("productID"));
                tbl.setProductDescp(rs.getString("productDescp"));
                tbl.setUnitQty(rs.getInt("unitQty"));
                tbl.setPrice(rs.getDouble("Price"));
                tbl.setTotalPerProduct(rs.getDouble("TotalPerProduct"));
                tbl.setOrderDate(rs.getString(7));
                tbl.setAcceptance(rs.getString("acceptance"));
                tbl.setDelivery(rs.getString("delivery"));
                invoiceinfo.add(tbl);
            }

        } catch (Exception e) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "transaction Id error", "invalid transaction ID, pls enter a valid id  " + e.getMessage());
            RequestContext.getCurrentInstance().showMessageInDialog(message);
        } finally {

            if (this.getCn() != null) {
                this.Close();
            }//end of
        }//end of finally-block

        return invoiceinfo;

    }//end of the method 

    ///calling the transaction details FROM THE invoice page 
    ////Data table implementation --FOR the permanenctOrder info use for 'order-invoice'
    public List<orderBEAN> getInvoiceInfo() throws Exception {

        this.connector();//establishes connectdion from the DAO Class (i.e the super class)

        List<orderBEAN> invoiceinfo = new ArrayList<orderBEAN>();

        try {
            //transactionID="";//clears
            PreparedStatement ps = this.getCn().prepareStatement("SELECT * FROM  permantodertr WHERE orderTransactId=? ");
            ps.setString(1, transactionID);
            rs = ps.executeQuery();

            while (rs.next()) {
                orderBEAN tbl = new orderBEAN();
                tbl.setProductID(rs.getString("productID"));
                tbl.setProductDescp(rs.getString("productDescp"));
                tbl.setUnitQty(rs.getInt("unitQty"));
                tbl.setPrice(rs.getDouble("Price"));
                tbl.setTotalPerProduct(rs.getDouble("TotalPerProduct"));
                tbl.setOrderDate(rs.getString(7));
                tbl.setAcceptance(rs.getString("acceptance"));
                tbl.setDelivery(rs.getString("delivery"));
                invoiceinfo.add(tbl);
            }

        } catch (Exception e) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "transaction Id error", "invalid transaction ID, pls enter a valid id  " + e.getMessage());
            RequestContext.getCurrentInstance().showMessageInDialog(message);
        } finally {

            if (this.getCn() != null) {
                this.Close();
            }//end of
        }//end of finally-block

        return invoiceinfo;

    }//end of the method 

    public List<orderBEAN> getInvoiceAmountInfo() throws Exception {

        this.connector();//establishes connectdion from the DAO Class (i.e the super class)

        List<orderBEAN> invoiceAmountInfo = new ArrayList<orderBEAN>();

        try {
            PreparedStatement ps = this.getCn().prepareStatement("SELECT orderTransactId,CONCAT('N', FORMAT(sum(TotalPerProduct) , 2) ) order_Amount,order_Date, customerName  FROM permantodertr WHERE orderTransactId=? GROUP BY orderTransactId");
            ps.setString(1, transactionID);
            rs = ps.executeQuery();

            while (rs.next()) {
                System.out.println("The Transaction ID: " + transactionID);
                orderBEAN tbl = new orderBEAN();
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

    public List<orderBEAN> getPrintableInvoiceAmountInfo() throws Exception {

        this.connector();//establishes connectdion from the DAO Class (i.e the super class)

        List<orderBEAN> invoiceAmountInfo = new ArrayList<orderBEAN>();

        try {
            PreparedStatement ps = this.getCn().prepareStatement("SELECT orderTransactId,CONCAT('N', FORMAT(sum(TotalPerProduct) , 2) ) order_Amount,order_Date, customerName  FROM permantodertr WHERE orderTransactId=? GROUP BY orderTransactId");
            ps.setString(1, transactionID);
            rs = ps.executeQuery();

            while (rs.next()) {
                System.out.println("The Transaction ID: " + transactionID);
                orderBEAN tbl = new orderBEAN();
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

    public List<orderBEAN> getEntireInvoiceAmountInfo() throws Exception {

        this.connector();//establishes connectdion from the DAO Class (i.e the super class)

        List<orderBEAN> invoiceAmountInfo = new ArrayList<orderBEAN>();

        try {
            PreparedStatement ps = this.getCn().prepareStatement("SELECT orderTransactId,CONCAT('N', FORMAT(sum(TotalPerProduct) , 2) ) order_Amount,order_Date, customerName  FROM permantodertr GROUP BY orderTransactId desc");
            // ps.setString(1, transactionID);

            rs = ps.executeQuery();

            while (rs.next()) {
                System.out.println("The Transaction ID: " + transactionID);
                orderBEAN tbl = new orderBEAN();
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

    //CHECKS if the required order QTY is AVAILABLE in the stock
    public void checkQTYfromSTOCK() throws Exception {

        this.connector();//establishes connectdion from the DAO Class (i.e the super class)

        /// TWO STEPS;
        //STEP1: CHECK IF THE REQUIRED PRODUCT IS AVAILABLE IN THE THE 'preoderdetails' TABLE
        //STEP2: CHECKS IF ITS AVAILABLE (it's okey), IF ITS NOT AVAILABLE REMOVE IT FROM THE 'preoderdetails' TABLE
        //STEP1: 
        try {
            //Msyql Local Conncetion
            //con = (com.mysql.jdbc.Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/esimbarcode?user=root&password=ash123");

            ps = this.getCn().prepareStatement("SELECT b.productDescp, b.productID, b.Tqty_StoreA  FROM stockdetails AS b INNER JOIN preoderdetails AS x ON x.productID=b.productID WHERE x.unitQty > b.Tqty_StoreA AND x.productID=b.productID AND store_title='STORE A' ");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                myProdDescp = rs.getString(1);
                myProdID = rs.getString(2);
                myQtyinStock = rs.getInt(3);

                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Product Selection Error.", "The QTY for the product: " + " '" + myProdDescp + " '" + " IS NOT available \n We have: " + myQtyinStock + " in stock.");
                RequestContext.getCurrentInstance().showMessageInDialog(message);

                //FacesContext.getCurrentInstance().addMessage(
                //null,
                // new FacesMessage(FacesMessage.SEVERITY_ERROR,
                //"Product Selection Error. " + "The QTY for the product: " + " '" + myProdDescp + " '" + " IS NOT available \n We have: " + myQtyinStock + " in stock.",
                //" Thank you."));
                //STEP2:
                //Removed the just added product (into the 'preoderdetails'), because the product is not AVAILABLE IN THE STOCK
                ps = this.getCn().prepareStatement("DELETE FROM  preoderdetails WHERE productID=? AND productDescp=? ");
                ps.setString(1, myProdID);
                ps.setString(2, myProdDescp);
                ps.executeUpdate();

                //increment the checkAvialability counter 
                checkAvailabilityCountter += 1;
            }

        } catch (Exception e) {
            throw e;
        } finally {
            this.Close();
            ps.close();
        }

    }//end of the method

    public void checksIFPre_orderTableContainsTuple() throws Exception {

        this.connector();//establishes connectdion from the DAO Class (i.e the super class)

        //TO CHECK if the products have been added into the pre-order table (i.e before clickin on the confirm order button)
        try {
            //con = (com.mysql.jdbc.Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/esimbarcode?user=root&password=ash123");

            ps = this.getCn().prepareStatement("SELECT *  FROM preoderdetails WHERE username=? ");
            ps.setString(1, userName);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                //increment the checkAvialability counter 
                checksIfPre_OrderifAdded += 1;

                // FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Product Adding Error", "Pls, add some product first before you CONFIRM THE ORDER! ");
                // RequestContext.getCurrentInstance().showMessageInDialog(message);
            }

        } catch (Exception e) {
            throw e;
        } finally {
            this.Close();
        }

    }//end of the method

    /*
    
    public List<String> fromCompleteList(String query) {
        List<String> results = new ArrayList<String>();
        results.add("HONEYWELL Flour (50 KG)");
        results.add("HONEYWELL Bakers Delight (50 KG)");
        results.add("DANGOTE FLOURS MILLS Flour (50 KG)");
        results.add("DANGOTE FLOURS MILLS Salt (100 KG)");
        results.add("DANGOTE FLOURS MILLS Sugar (50 KG)");
        return results;
    }

     */
    /////////////Marking the order 'Acceptance status' as accepted 
    public void acceptOrder() throws Exception {

        this.connector();//establishes connectdion from the DAO Class (i.e the super class)

        try {

            PreparedStatement ps = this.getCn().prepareStatement("UPDATE permantodertr SET acceptance='Accepted' WHERE orderTransactId=? ");
            ps.setString(1, transactionID);
            ps.executeUpdate();

            FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO,
                            "Order Acceptance. " + "Order with 'Transaction ID': " + transactionID + " has been accepted!",
                            " Thank you."));

        } catch (Exception e) {
            throw e;
        } finally {

            if (this.getCn() != null) {
                this.Close();
            }//end of
        }//end of finally-block

    }//end of the method

    //Initiating the delivery process after Checking if the acceptance column is marked 'Accepted'. (This must be done before delivery ).
    public void deliveryMthd() throws Exception {
        //Mysql Connection 

        this.connector();//establishes connectdion from the DAO Class (i.e the super class)

        try {

            PreparedStatement ps = this.getCn().prepareStatement("UPDATE permantodertr SET delivery='delivered' WHERE orderTransactId=? AND acceptance='Accepted'   ");
            ps.setString(1, transactionID);
            int noofraws = ps.executeUpdate();

            if (noofraws > 0) {

                FacesContext.getCurrentInstance().addMessage(
                        null,
                        new FacesMessage(FacesMessage.SEVERITY_INFO,
                                "Order Delivery. " + "Order with 'Transaction ID': " + transactionID + " has been delivered sucessfully",
                                " Thank you."));

            } else {

                FacesContext.getCurrentInstance().addMessage(
                        null,
                        new FacesMessage(FacesMessage.SEVERITY_FATAL,
                                "Order Delivery Error. " + "Order with 'Transaction ID': " + transactionID + " Order must be accepted before initiating the delivery process! ",
                                " Thank you."));

            }

        } catch (Exception e) {
            throw e;
        } finally {

            if (this.getCn() != null) {
                this.Close();
            }//end of
        }//end of finally-block

    }//end of the mehtod 

    public List<orderBEAN> getDisplayTheConfirmedOrderList() throws Exception {
        //retriveTransactionIDFromUI();//invoked the method

        this.connector();//establishes connectdion from the DAO Class (i.e the super class)

        List<orderBEAN> confirmedOrder = new ArrayList<orderBEAN>();

        try {

            //Mysql Connection 
            //con = (com.mysql.jdbc.Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/esimbarcode?user=root&password=ash123");
            PreparedStatement ps = this.getCn().prepareStatement("SELECT orderTransactId, productID FROM permantodertr WHERE orderTransactId = ? ");
            ps.setString(1, transactionID);
            rs = ps.executeQuery();
            System.out.println("ESIM2 TST: " + transactionID);
            while (rs.next()) {
                orderBEAN tbl = new orderBEAN();
                tbl.setOrderTransactionID(rs.getString(1));
                tbl.setProductID(rs.getString("productID"));
                //tbl.setProductDescp(rs.getString("productDescp"));
                //tbl.setUnitQty(rs.getInt("unitQty"));
                //tbl.setPrice(rs.getDouble("Price"));
                //tbl.setTotalPerProduct(rs.getDouble("TotalPerProduct"));
                //tbl.setOrderDate(rs.getString("order_Date"));
                confirmedOrder.add(tbl);
            }//end of while-block

        } catch (Exception e) {
            throw e;
        } finally {

            if (this.getCn() != null) {
                this.Close();
            }//end of
        }//end of finally-block

        return confirmedOrder;
    }//end of method
}//end of class orderBEAN

