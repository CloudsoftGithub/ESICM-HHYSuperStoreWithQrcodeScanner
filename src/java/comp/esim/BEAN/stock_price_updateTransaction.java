package comp.esim.BEAN;

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
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;

@ManagedBean
@RequestScoped
public class stock_price_updateTransaction extends DAO implements Serializable {

    int counter = 0;
    //Connection con;
    PreparedStatement ps;
    ResultSet rs;
    ResultSet rssP;
    int priceUpdateCounter =0;

    int productFoudFlag = 0;

    private String transactionID;
    private String productID;
    private String productDescp;
    private String partnerName;
    private Date date_purchased;
    private int qty_added;
    private int previous_qty;
    private int total_qty;
    private String store_title;

    private int tQty_StoreA;
    private int tQty_StoreB;
    private int tQty_StoreC;

    private String Product_Title;
    private double newPrice;

    public double getNewPrice() {
        return newPrice;
    }

    public void setNewPrice(double newPrice) {
        this.newPrice = newPrice;
    }
  
    private List<String> Product_TitleList = new ArrayList<>();

    public List<String> getProduct_TitleList() {
        return Product_TitleList;
    }

    public void setProduct_TitleList(List<String> Product_TitleList) {
        this.Product_TitleList = Product_TitleList;
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

    public String getPartnerName() {
        return partnerName;
    }

    public void setPartnerName(String partnerName) {
        this.partnerName = partnerName;
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

    public int gettQty_StoreA() {
        return tQty_StoreA;
    }

    public void settQty_StoreA(int tQty_StoreA) {
        this.tQty_StoreA = tQty_StoreA;
    }

    public int gettQty_StoreB() {
        return tQty_StoreB;
    }

    public void settQty_StoreB(int tQty_StoreB) {
        this.tQty_StoreB = tQty_StoreB;
    }

    public int gettQty_StoreC() {
        return tQty_StoreC;
    }

    public void settQty_StoreC(int tQty_StoreC) {
        this.tQty_StoreC = tQty_StoreC;
    }

    /*
     public void retriveProductIDFromUI() {//get the current ProductID on the UI 
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        productID = ec.getRequestParameterMap().get("stockUpdateForm:ProductID");
    }//end of method
    
     */
    public void retriveProduct_TitleFromUI() {//get the current productTitle on the UI 
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        Product_Title = ec.getRequestParameterMap().get("stockUpdateForm:productTitle");
    }//end of method

    //Searching products by product_Title using the productTitleSearchbyIDDMethod
    public void productSearchbyproductTitleDMethod() throws ClassNotFoundException, SQLException, Exception {//retrieving the  product by ID
        //this block of code is used to search for product Decriptiona and Partner Name
        
        retriveProduct_TitleFromUI();//invoked
        
        this.connector();//

        productDescp = "";//clears before executing the below codes
        partnerName = "";//clears before executing the below codes

        if (Product_Title.equalsIgnoreCase("")) {//checks for a null inputs for productID
            FacesMessage messag = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Stock-update Error", "Pls, enter the Product Title");
            RequestContext.getCurrentInstance().showMessageInDialog(messag);

        } else {

            try {
 
                Class.forName("com.mysql.jdbc.Driver");
                //con = (com.mysql.jdbc.Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/esimbarcode?user=root&password=ash123");

                ///////SEARCHING FOR PRODUCT DESCRIPTION BY Product_Title ID
                ps = this.getCn().prepareStatement("SELECT products.productDescp,products.productID,products.CompanyName FROM  products WHERE ProductDescp=?");
                ps.setString(1, Product_Title);

                rssP = ps.executeQuery();

                if (rssP.next()) {//if found result 
                    productDescp = rssP.getString(1);
                    productID = rssP.getString(2);
                    partnerName = rssP.getString(3);
                    RequestContext.getCurrentInstance().update("stockUpdateForm:stokPriceUpdatePanel");
                    rssP.close();

                } else {
                    //Do nothing 
                    counter += 1;//increment counter 
                }
            } catch (Exception e) {

                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ProductSeachByTitle Error", "Pls, check the Product Title and try again." + e);
                RequestContext.getCurrentInstance().showMessageInDialog(message);

            } finally {
                //this.Close();
            }//end of finally-block

        }//end of else-block 

    }//end of method

    ///Creating a List method That retrieves the data from Db and display on the selectOneItem (Combobox)
    public List<String> get_productTitleMthd() throws Exception {
         
        Product_TitleList.removeAll(Product_TitleList);//
        this.connector();
        
        try {
            //con = (com.mysql.jdbc.Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/esimbarcode?user=root&password=ash123");

            ps = this.getCn().prepareStatement("SELECT ProductDescp FROM products");
            rs = ps.executeQuery();

            while (rs.next()) {
                Product_TitleList.add(rs.getString("ProductDescp"));//retrieves all the registered products and ADD into the produ_Title
            }//end of while-block
            
        
        } catch (Exception e) {
            FacesMessage messag = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Product Retrieval Error", e.getMessage() + " . Pls, try again");
            RequestContext.getCurrentInstance().showMessageInDialog(messag);
        }finally{
            this.Close();
        }
        

        return Product_TitleList;
        
        
    }//end of method

    ///////searching by ID
    /*
    
    public void productSearchbyIDDMethod() throws ClassNotFoundException, SQLException {//retrieving the  product by ID
        //this block of code is used to search for product Decriptiona and Partner Name

        productDescp = "";//clears before executing the below codes
        partnerName = "";//clears before executing the below codes

        if (productID.equalsIgnoreCase("")) {//checks for a null inputs for productID
            FacesMessage messag = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Stock-update Error", "Pls, enter the Product ID!");
            RequestContext.getCurrentInstance().showMessageInDialog(messag);

        } else {

            try {
                Class.forName("com.mysql.jdbc.Driver");
                con = (com.mysql.jdbc.Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/esimbarcode_hhy_store?user=root&password=ash123");

                // Jealastic connection on the Jealastic Cloud
               //  con = (com.mysql.jdbc.Connection) DriverManager.getConnection("jdbc:mysql://node12525-env-esimbarcode.cloud.interhostsolutions.be/esimbarcode", "root", "HHBoea11492");
                ///////SEARCHING FOR PRODUCT DESCRIPTION BY PRODUCT ID
                ps = con.prepareStatement("SELECT products.productDescp,partner.partner_name FROM  products,partner WHERE products.partnerID=partner.partnerID and productID=?");
                ps.setString(1, productID);

                rssP = ps.executeQuery();

                if (rssP.next()) {//if found result 
                    productDescp = rssP.getString(1);
                    partnerName = rssP.getString(2);
                    RequestContext.getCurrentInstance().update("stockUpdateForm:stokUpdatePanel");
                    rssP.close();

                } else {

                    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ProductSeachByID Error", "the product ID entered is invalid.  Pls, check and try again.");
                    RequestContext.getCurrentInstance().showMessageInDialog(message);
                }
            } catch (Exception e) {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ProductSeachByID Error", "Pls, check and try again.");
                RequestContext.getCurrentInstance().showMessageInDialog(message);

            } finally {
                if (con != null) {
                    con.close();
                }
            }//end of finally-block

        }//end of else-block 

    }//end of method
    
     */
    
    
        ///StockPriceUpdate transaction by ProductTitle
    public void stockPriceUpdateTransactionWithProTitle() throws Exception {
        productSearchbyproductTitleDMethod();//calls the search methd, so the product desc and the partner name get retrived first before storing the stock update tuples

        this.connector();
        
        ///STEPS FOR THE PRODUCT PRICE UPDATE 
        //STEP1: Identify the product whose price is to be updated
        //STEP2: Suggest new prise for the product 
        //STEP3: copy ONLY the Identify product into a table called; `products_new_price`
        //STEP4: get the new price and the update the Old (products) table and the new table (`products_new_price`) 
         
         try {
             //Step1:
             //Step2:
             //Step3: copy ONLY the Identify product into a table called; `products_new_price`
             ps = this.getCn().prepareStatement("INSERT INTO products_new_price select ID,productID,ProductDescp,CompanyName,SalesPrice,null,date_of_registration FROM products WHERE ProductDescp = ? ");
             ps.setString(1, Product_Title);
             ps.executeUpdate();
             
             priceUpdateCounter ++;  
             
             if(priceUpdateCounter > 0){//checking if insertion is made successfully, before the update is DONE
                
             /////////////
             //STEP4: get the new price and update the Old (products) table and the new table (`products_new_price`) 
         
            //STEP4, A: updating the old table 'products'
            PreparedStatement ps = this.getCn().prepareStatement("UPDATE products SET SalesPrice= ? WHERE ProductDescp = ?");
            ps.setDouble(1, newPrice);
            ps.setString(2, Product_Title);
            ps.executeUpdate();
            
            
            //STEP4, B: updating the new table 'products_new_price'
            PreparedStatement ps2 = this.getCn().prepareStatement("UPDATE products_new_price SET NewSalesPrice = ? WHERE ProductDescp = ?");
            ps2.setDouble(1, newPrice);
            ps2.setString(2, Product_Title);
            ps2.executeUpdate();
           
            //displays the update information to the user 
               FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO,
                             "The 'Product': " + Product_Title + " has been Updated with NEW PRICE "+ newPrice ,
                            " Thank you."));  
            
            //clearing the variables;
            Product_Title = "";
            newPrice = 0.0;

             }else{
                 
               FacesMessage messag = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Stock-PRICE-update Error. ", "Product Title NOT identified");
               RequestContext.getCurrentInstance().showMessageInDialog(messag);
            
             }
              
        } catch (Exception e) {
            throw e;
        }finally{
             
             
         }
         
        
    }
    
    ///Stockupdate transaction by ProductTitle
    public void stockUpdateTransactionWithProTitle() throws Exception {
        productSearchbyproductTitleDMethod();//calls the search methd, so the product desc and the partner name get retrived first before storing the stock update tuples

        this.connector();

        if (qty_added <= 0) {//checks for wrong input of zero for qty-Added field
 
             FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(FacesMessage.SEVERITY_FATAL,
                            "Stock-update Error.",
                            "Pls, enter the Qty-Added. It can not be zero or less."));

        } else if (counter > 0) {
           
              FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(FacesMessage.SEVERITY_FATAL,
                            "ProductSeachByTitle Error.",
                            "the product Title entered is invalid.  Pls, check and try again."));
        } else {
            try {
                ps = this.getCn().prepareStatement("insert into stockupdatetransc values(?,?,?,?,Now(),?,?)");
                ps.setString(1, null);
                ps.setString(2, productID);
                ps.setString(3, productDescp);
                ps.setString(4, partnerName);
                //ps.setDate(5, date_purchased);
                ps.setString(5, store_title);
                ps.setInt(6, qty_added);
                // ps.setInt(8, total_qty);

                ps.executeUpdate();
                
                FacesContext.getCurrentInstance().addMessage(
                        null,
                        new FacesMessage(FacesMessage.SEVERITY_INFO,
                                "Congrats! " + store_title + " updated with " + qty_added + " items successfully.",
                                "Thank you."));
            } catch (Exception e) {
                FacesMessage messag = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Stock-update Error", e.getMessage() + " . Pls, try again");
                RequestContext.getCurrentInstance().showMessageInDialog(messag);
            } finally {
               // this.Close();
            }//end of finally-block

            //////////////Process of UPDATING THE Qty-Added field AFTER INSERTING THE Qty-Added, so we can get the updated total qty
            //1. Select anquery the products by it product ID, store_title and partner from the stockDetails table 
            //2. if the record in 1. above exist then, update it with the qyt_added field 
            //3. insert it for the first titme 
            try {//No. 1: above
                ps = this.getCn().prepareStatement("SELECT * FROM stockdetails WHERE productID=? AND partner_name=? AND store_title=?");
                ps.setString(1, productID);
                ps.setString(2, partnerName);
                ps.setString(3, store_title);

                rs = ps.executeQuery();

            } catch (Exception e) {
                FacesMessage messag = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Stock-update Error", e.getMessage() + " . Pls, try again");
                RequestContext.getCurrentInstance().showMessageInDialog(messag);

            } finally {
               // this.Close();
            }//end of finally-block

            if (rs.next()) { ///////////No.2: above

                if (store_title.equalsIgnoreCase("STORE A")) {
                    try {
                        ps = this.getCn().prepareStatement("UPDATE stockdetails SET Tqty_StoreA = Tqty_StoreA +?, Ttotal_qty = Ttotal_qty+?  WHERE productID=? AND partner_name=? AND store_title=?");
                        ps.setInt(1, qty_added);
                        ps.setInt(2, qty_added);
                        ps.setString(3, productID);
                        ps.setString(4, partnerName);
                        ps.setString(5, store_title);
                        ps.executeUpdate();

                    } catch (Exception e) {
                        FacesMessage messag = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Stock-update Error", e.getMessage() + " . Pls, try again");
                        RequestContext.getCurrentInstance().showMessageInDialog(messag);
                    }//end of catch-block
                } else if (store_title.equalsIgnoreCase("STORE B")) {
                    try {
                        ps = this.getCn().prepareStatement("UPDATE stockdetails SET Tqty_StoreB = Tqty_StoreB +?, Ttotal_qty = Ttotal_qty+?  WHERE productID=? AND partner_name=? AND store_title=?");
                        ps.setInt(1, qty_added);
                        ps.setInt(2, qty_added);
                        ps.setString(3, productID);
                        ps.setString(4, partnerName);
                        ps.setString(5, store_title);
                        ps.executeUpdate();

                    } catch (Exception e) {
                        FacesMessage messag = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Stock-update Error", e.getMessage() + " . Pls, try again");
                        RequestContext.getCurrentInstance().showMessageInDialog(messag);
                    }  
                } else if (store_title.equalsIgnoreCase("STORE C")) {

                    try {
                        ps = this.getCn().prepareStatement("UPDATE stockdetails SET Tqty_StoreC = Tqty_StoreC +?, Ttotal_qty = Ttotal_qty+?  WHERE productID=? AND partner_name=? AND store_title=?");
                        ps.setInt(1, qty_added);
                        ps.setInt(2, qty_added);
                        ps.setString(3, productID);
                        ps.setString(4, partnerName);
                        ps.setString(5, store_title);
                        ps.executeUpdate();

                    } catch (Exception e) {
                        FacesMessage messag = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Stock-update Error", e.getMessage() + " . Pls, try again");
                        RequestContext.getCurrentInstance().showMessageInDialog(messag);
                    }  

                }//end of else-block

            } else//No.3: above
            {
                if (store_title.equalsIgnoreCase("STORE A")) {//checking for the actual store_title to correct Items should be updated into the corresponding stores

                    try {
                        ps = this.getCn().prepareStatement("insert into stockdetails values(?,?,?,?,?,?,?,?)");
                        ps.setString(1, productID);
                        ps.setString(2, productDescp);
                        ps.setString(3, partnerName);
                        ps.setString(4, store_title);
                        ps.setInt(5, qty_added);////this is the supplied qty from the user
                        ps.setInt(6, 0);
                        ps.setInt(7, 0);
                        ps.setInt(8, qty_added);//This field is added into the TotalQty field, so we ca get the total items at a glance regardles of the store_titla 

                        ps.executeUpdate();
                        FacesContext.getCurrentInstance().addMessage(
                                null,
                                new FacesMessage(FacesMessage.SEVERITY_INFO,
                                        "Congrats! " + store_title + " updated with " + qty_added + " items successfully.",
                                        "Thank you."));

                    } catch (Exception e) {
                        FacesMessage messag = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Stock-update Error", e.getMessage() + " . Pls, try again");
                        RequestContext.getCurrentInstance().showMessageInDialog(messag);

                    }  

                } else if (store_title.equalsIgnoreCase("STORE B")) {

                    try {
                        ps = this.getCn().prepareStatement("insert into stockdetails values(?,?,?,?,?,?,?,?)");
                        ps.setString(1, productID);
                        ps.setString(2, productDescp);
                        ps.setString(3, partnerName);
                        ps.setString(4, store_title);
                        ps.setInt(5, 0);
                        ps.setInt(6, qty_added);//this is the supplied qty from the user
                        ps.setInt(7, 0);
                        ps.setInt(8, qty_added);//This field is added into the TotalQty field, so we ca get the total items at a glance regardles of the store_titla 

                        ps.executeUpdate();
                        FacesContext.getCurrentInstance().addMessage(
                                null,
                                new FacesMessage(FacesMessage.SEVERITY_INFO,
                                        "Congrats! " + store_title + " updated with " + qty_added + " items successfully.",
                                        "Thank you."));

                    } catch (Exception e) {
                        FacesMessage messag = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Stock-update Error", e.getMessage() + " . Pls, try again");
                        RequestContext.getCurrentInstance().showMessageInDialog(messag);

                    }  

                } else if (store_title.equalsIgnoreCase("STORE C")) {

                    try {
                        ps = this.getCn().prepareStatement("insert into stockdetails values(?,?,?,?,?,?,?,?)");
                        ps.setString(1, productID);
                        ps.setString(2, productDescp);
                        ps.setString(3, partnerName);
                        ps.setString(4, store_title);
                        ps.setInt(5, 0);
                        ps.setInt(6, 0);
                        ps.setInt(7, qty_added);//this is the supplied qty from the user
                        ps.setInt(8, qty_added);//This field is added into the TotalQty field, so we ca get the total items at a glance regardles of the store_titla 

                        ps.executeUpdate();
                        FacesContext.getCurrentInstance().addMessage(
                                null,
                                new FacesMessage(FacesMessage.SEVERITY_INFO,
                                        "Congrats! " + store_title + " updated with " + qty_added + " items successfully.",
                                        "Thank you."));

                    } catch (Exception e) {
                        FacesMessage messag = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Stock-update Error", e.getMessage() + " . Pls, try again");
                        RequestContext.getCurrentInstance().showMessageInDialog(messag);

                    } 

                }//end of if-block//en of else-block
            }
        }//end of else block 
        
        this.Close();
        clearinput();//clears the input fields

    }//end of stockUpdateTransaction

    //stockupdate transanction by ID
    /*
    
     public void stockUpdateTransaction() throws Exception {
        productSearchbyIDDMethod();//calls the search methd, so the product desc and the partner name get retrived first before storing the stock update tuples

        this.connector();

        if (qty_added <= 0) {//checks for wrong input of zero for qty-Added field

            FacesMessage messag = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Stock-update Error", "Pls, enter the Qty-Added. It can not be zero or less");
            RequestContext.getCurrentInstance().showMessageInDialog(messag);

        } else {
            try {
                ps = this.getCn().prepareStatement("insert into stockupdatetransc values(?,?,?,?,Now(),?,?)");
                ps.setString(1, null);
                ps.setString(2, productID);
                ps.setString(3, productDescp);
                ps.setString(4, partnerName);
                //ps.setDate(5, date_purchased);
                ps.setString(5, store_title);
                ps.setInt(6, qty_added);
                // ps.setInt(8, total_qty);

                ps.executeUpdate();
                FacesContext.getCurrentInstance().addMessage(
                        null,
                        new FacesMessage(FacesMessage.SEVERITY_INFO,
                                "Congrats! " + store_title + " updated with " + qty_added + " items successfully.",
                                "Thank you."));

            } catch (Exception e) {
                FacesMessage messag = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Stock-update Error", e.getMessage() + " . Pls, try again");
                RequestContext.getCurrentInstance().showMessageInDialog(messag);
            } finally {
                if (con != null) {
                    con.close();
                }
            }//end of finally-block

            //////////////Process of UPDATING THE Qty-Added field AFTER INSERTING THE Qty-Added, so we can get the updated total qty
            //1. Select anquery the products by it product ID, store_title and partner from the stockDetails table 
            //2. if the record in 1. above exist then, update it with the qyt_added field 
            //3. insert it for the first titme 
            try {//No. 1: above
                ps = this.getCn().prepareStatement("SELECT * FROM stockdetails WHERE productID=? AND partner_name=? AND store_title=?");
                ps.setString(1, productID);
                ps.setString(2, partnerName);
                ps.setString(3, store_title);

                rs = ps.executeQuery();

            } catch (Exception e) {
                FacesMessage messag = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Stock-update Error", e.getMessage() + " . Pls, try again");
                RequestContext.getCurrentInstance().showMessageInDialog(messag);

            } finally {
                if (con != null) {
                    con.close();
                }
            }//end of finally-block

            if (rs.next()) { ///////////No.2: above

                if (store_title.equalsIgnoreCase("STORE A")) {
                    try {
                        ps = this.getCn().prepareStatement("UPDATE stockdetails SET Tqty_StoreA = Tqty_StoreA +?, Ttotal_qty = Ttotal_qty+?  WHERE productID=? AND partner_name=? AND store_title=?");
                        ps.setInt(1, qty_added);
                        ps.setInt(2, qty_added);
                        ps.setString(3, productID);
                        ps.setString(4, partnerName);
                        ps.setString(5, store_title);
                        ps.executeUpdate();

                    } catch (Exception e) {
                        FacesMessage messag = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Stock-update Error", e.getMessage() + " . Pls, try again");
                        RequestContext.getCurrentInstance().showMessageInDialog(messag);
                    }//end of catch-block
                } else if (store_title.equalsIgnoreCase("STORE B")) {
                    try {
                        ps = this.getCn().prepareStatement("UPDATE stockdetails SET Tqty_StoreB = Tqty_StoreB +?, Ttotal_qty = Ttotal_qty+?  WHERE productID=? AND partner_name=? AND store_title=?");
                        ps.setInt(1, qty_added);
                        ps.setInt(2, qty_added);
                        ps.setString(3, productID);
                        ps.setString(4, partnerName);
                        ps.setString(5, store_title);
                        ps.executeUpdate();

                    } catch (Exception e) {
                        FacesMessage messag = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Stock-update Error", e.getMessage() + " . Pls, try again");
                        RequestContext.getCurrentInstance().showMessageInDialog(messag);
                    } finally {
                        if (con != null) {
                            con.close();
                        }
                    }//end of finally-block
                } else if (store_title.equalsIgnoreCase("STORE C")) {

                    try {
                        ps = this.getCn().prepareStatement("UPDATE stockdetails SET Tqty_StoreC = Tqty_StoreC +?, Ttotal_qty = Ttotal_qty+?  WHERE productID=? AND partner_name=? AND store_title=?");
                        ps.setInt(1, qty_added);
                        ps.setInt(2, qty_added);
                        ps.setString(3, productID);
                        ps.setString(4, partnerName);
                        ps.setString(5, store_title);
                        ps.executeUpdate();

                    } catch (Exception e) {
                        FacesMessage messag = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Stock-update Error", e.getMessage() + " . Pls, try again");
                        RequestContext.getCurrentInstance().showMessageInDialog(messag);
                    } finally {
                        if (con != null) {
                            con.close();
                        }
                    }//end of finally-block

                }//end of else-block

            } else//No.3: above
             if (store_title.equalsIgnoreCase("STORE A")) {//checking for the actual store_title to correct Items should be updated into the corresponding stores

                    try {
                        ps = this.getCn().prepareStatement("insert into stockdetails values(?,?,?,?,?,?,?,?)");
                        ps.setString(1, productID);
                        ps.setString(2, productDescp);
                        ps.setString(3, partnerName);
                        ps.setString(4, store_title);
                        ps.setInt(5, qty_added);////this is the supplied qty from the user
                        ps.setInt(6, 0);
                        ps.setInt(7, 0);
                        ps.setInt(8, qty_added);//This field is added into the TotalQty field, so we ca get the total items at a glance regardles of the store_titla 

                        ps.executeUpdate();
                        FacesContext.getCurrentInstance().addMessage(
                                null,
                                new FacesMessage(FacesMessage.SEVERITY_INFO,
                                        "Congrats! " + store_title + " updated with " + qty_added + " items successfully.",
                                        "Thank you."));

                    } catch (Exception e) {
                        FacesMessage messag = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Stock-update Error", e.getMessage() + " . Pls, try again");
                        RequestContext.getCurrentInstance().showMessageInDialog(messag);

                    } finally {
                        if (con != null) {
                            con.close();
                        }
                    }//end of finally-block

                } else if (store_title.equalsIgnoreCase("STORE B")) {

                    try {
                        ps = this.getCn().prepareStatement("insert into stockdetails values(?,?,?,?,?,?,?,?)");
                        ps.setString(1, productID);
                        ps.setString(2, productDescp);
                        ps.setString(3, partnerName);
                        ps.setString(4, store_title);
                        ps.setInt(5, 0);
                        ps.setInt(6, qty_added);//this is the supplied qty from the user
                        ps.setInt(7, 0);
                        ps.setInt(8, qty_added);//This field is added into the TotalQty field, so we ca get the total items at a glance regardles of the store_titla 

                        ps.executeUpdate();
                        FacesContext.getCurrentInstance().addMessage(
                                null,
                                new FacesMessage(FacesMessage.SEVERITY_INFO,
                                        "Congrats! " + store_title + " updated with " + qty_added + " items successfully.",
                                        "Thank you."));

                    } catch (Exception e) {
                        FacesMessage messag = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Stock-update Error", e.getMessage() + " . Pls, try again");
                        RequestContext.getCurrentInstance().showMessageInDialog(messag);

                    } finally {
                        if (con != null) {
                            con.close();
                        }
                    }//end of finally-block

                } else if (store_title.equalsIgnoreCase("STORE C")) {

                    try {
                        ps = this.getCn().prepareStatement("insert into stockdetails values(?,?,?,?,?,?,?,?)");
                        ps.setString(1, productID);
                        ps.setString(2, productDescp);
                        ps.setString(3, partnerName);
                        ps.setString(4, store_title);
                        ps.setInt(5, 0);
                        ps.setInt(6, 0);
                        ps.setInt(7, qty_added);//this is the supplied qty from the user
                        ps.setInt(8, qty_added);//This field is added into the TotalQty field, so we ca get the total items at a glance regardles of the store_titla 

                        ps.executeUpdate();
                        FacesContext.getCurrentInstance().addMessage(
                                null,
                                new FacesMessage(FacesMessage.SEVERITY_INFO,
                                        "Congrats! " + store_title + " updated with " + qty_added + " items successfully.",
                                        "Thank you."));

                    } catch (Exception e) {
                        FacesMessage messag = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Stock-update Error", e.getMessage() + " . Pls, try again");
                        RequestContext.getCurrentInstance().showMessageInDialog(messag);

                    } finally {
                        if (con != null) {
                            con.close();
                        }
                    }//end of finally-block

                }//end of if-block//en of else-block
        }//end of else block 

        clearinput();//clears the input fields

    }//end of stockUpdateTransaction
    
     */
    public List<String> fromCompleteList(String query) {
        List<String> results = new ArrayList<String>();
        results.add("HONEYWELL Flour (50 KG)");

        return results;
    }

    public void clearinput() {
        productDescp = "";//clears the combo (selectOneMenu)
        partnerName = ""; //clears

        counter = 0;
        Product_Title = "";
        // productID = "";
        //productDescp = "";
        //partnerName = "";
        qty_added = 0;
        store_title = "";
    }//end of clearinput

}//end of class stock_updateTransaction
