package comp.esimbarcode.BEAN;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import comp.esimbarcode.DAO.DAO;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.swing.ImageIcon;
import org.primefaces.context.RequestContext;

@ManagedBean
@SessionScoped
public class productDataCaptureBEAN extends DAO implements Serializable {

    int prodRegCounter = 0;
    String filePath = "";
    Connection con;
    PreparedStatement ps;

    private String imgUrl;
    String myCompid;
    private String companyName;
    private String productDescp;
    private String productID;
    private Date date_of_registion;

    private double costPrice;
    private double SalesPrice;

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public double getCostPrice() {
        return costPrice;
    }

    public void setCostPrice(double costPrice) {
        this.costPrice = costPrice;
    }

    public double getSalesPrice() {
        return SalesPrice;
    }

    public void setSalesPrice(double SalesPrice) {
        this.SalesPrice = SalesPrice;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getProductDescp() {
        return productDescp;
    }

    public void setProductDescp(String productDescp) {
        this.productDescp = productDescp;
    }

    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public Date getDate_of_registion() {
        return date_of_registion;
    }

    public void setDate_of_registion(Date date_of_registion) {
        this.date_of_registion = date_of_registion;
    }

    /*
     public void retriveCompanyIDFromUI() {//get the current CompanyID on the UI 
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        companyID = ec.getRequestParameterMap().get("ProductsRegForm:Companyid");
        mycompanyID = companyID;
    }
     */
    String mycompanyID = "";

    /*
    
    public void companyIDSearchDMethod() throws ClassNotFoundException, SQLException {//retrieving the  COmpany ID
        //this block of code is used to search for the registered subject prior its data entry
        Class.forName("com.mysql.jdbc.Driver");

        //Mysql Local connection 
        con = (com.mysql.jdbc.Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/esimbarcode_hhy_store?user=root&password=ash123");

        // Jealastic connection on the Jealastic Cloud
        //  con = (com.mysql.jdbc.Connection) DriverManager.getConnection("jdbc:mysql://node12525-env-esimbarcode.cloud.interhostsolutions.be/esimbarcode", "root", "HHBoea11492");
        System.out.println(" Company ID" + companyID);

        ps = con.prepareStatement("SELECT partner_name FROM partner WHERE PartnerID=?");
        ps.setString(1, companyID);

        ResultSet rss = ps.executeQuery();

        if (rss.next()) {//if found result 
            companyName = rss.getString(1);
            RequestContext.getCurrentInstance().update("ProductsRegForm:productRegPanel");
            rss.close();
        } else {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "CompanyName search error", "the Company-ID entered is invalid.  Pls, check and try again. ");
            RequestContext.getCurrentInstance().showMessageInDialog(message);

        }
    }//end of method 
    
     */
    public void registerProduct() throws Exception {

        this.connector();

        //retriveCompanyIDFromUI(); //calls the method, so the variable (companyID) get initialized 
        Class.forName("com.mysql.jdbc.Driver");

        ps = this.getCn().prepareStatement("Select * from products where ProductDescp=? ");
        ps.setString(1, productDescp);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {

            FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            "Product Registration Error." + " The 'Product': " + productDescp + " has been registered. Pls, check your enteries!",
                            " Thank you."));
        } else if (costPrice == 0.0 || costPrice == 0) {
            FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            "Product Registration Error." + " Cost Price CAN NOT BE ZERO" + " . Pls, try again",
                            " Thank you."));

        } else if (SalesPrice == 0.0 || SalesPrice == 0) {
            FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            "Product Registration Error." + " Sales Price CAN NOT BE ZERO" + " . Pls, try again",
                            " Thank you."));

        } else if (costPrice > SalesPrice) {
            FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            "Product Registration Error." + " Cost price cannot be > than Sales price" + " . Pls, try again",
                            " Thank you."));
        } else {

            String QrDirectoryOnCDrive = "";
            /// QR code Image Creation  
            try {
                new File("C:\\QR Code Images").mkdir();//creates a directory called 'QR Code Images' in C: drive
                String qrCodeData = productDescp;
                filePath = "C:\\QR Code Images\\" + productDescp + ".png";
                String charset = "UTF-8";
                Map< EncodeHintType, ErrorCorrectionLevel> hintMap = new HashMap< EncodeHintType, ErrorCorrectionLevel>();
                hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
                BitMatrix matrix = new MultiFormatWriter().encode(
                        new String(qrCodeData.getBytes(charset), charset),
                        BarcodeFormat.QR_CODE, 200, 200, hintMap);
                MatrixToImageWriter.writeToFile(matrix, filePath.substring(filePath
                        .lastIndexOf('.') + 1), new File(filePath));

                System.out.println("QR Code image created successfully!");

                FacesContext.getCurrentInstance().addMessage(
                        null,
                        new FacesMessage(FacesMessage.SEVERITY_INFO,
                                "QR Code Info" + "QR Code image created successfully!" + " . Thank you",
                                ""));

            } catch (Exception e) {
                System.err.println(e.getMessage());
                FacesMessage messag = new FacesMessage(FacesMessage.SEVERITY_FATAL, "QR Code Error", e.getMessage() + " . Pls, try again");
                RequestContext.getCurrentInstance().showMessageInDialog(messag);
            }

            ////////////// //actual tuple insertion command 
            try {
                //Mysql Local Connection
                // con = (com.mysql.jdbc.Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/esimbarcode_hhy_store?user=root&password=ash123");

                // Jealastic connection on the Jealastic Cloud
                //  con = (com.mysql.jdbc.Connection) DriverManager.getConnection("jdbc:mysql://node12525-env-esimbarcode.cloud.interhostsolutions.be/esimbarcode", "root", "HHBoea11492");
                ps = this.getCn().prepareStatement("insert into products values(null,CONCAT('P',ID), ?,?,?,?,?, now() )");
                ps.setString(1, productDescp);
                ps.setString(2, companyName);
                InputStream is = new FileInputStream(new File(filePath));

                ps.setBlob(3, is);
                ps.setDouble(4, costPrice);
                ps.setDouble(5, SalesPrice);

                //Converting the Date from Java.util.Date to Java.Sql.Date
                // java.util.Date utilDateofReg = date_of_registion;
                //java.sql.Date sqlutilDateofReg = new java.sql.Date(utilDateofReg.getTime());
                //System.out.println("my Test: " + sqlutilDateofReg);
                //ps.setDate(6, sqlutilDateofReg);
                ps.executeUpdate();
                ps.close();

                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Congrats! " + productDescp + " registered successfully, as a product.", "Thank you."));

                prodRegCounter += 1;

            } catch (Exception e) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Product Registration Error" + e.getMessage() + " . Pls, try again", ""));
 
            } finally {

                try {
                    rs.close();
                    ps.close();
                    //this.Close();
                } catch (Exception e) {
                    e.printStackTrace();
                    System.err.println("Error while viewing stock: " + e.getMessage());
                }
            }//end of finally-block

            ////------->UPDATING THE ProductID FROM THE "P000" into 'P'+ID (e.g P001)
            ///////////////////////
            if (prodRegCounter > 0) {//Testing if a product was captured
                try {
                    ps = this.getCn().prepareStatement("UPDATE products SET productID = CONCAT('P',ID) WHERE ProductDescp=?");
                    ps.setString(1, productDescp);
                    ps.executeUpdate();
                    //  ps.close();
                } catch (Exception e) {
                    FacesMessage messag = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Product Registration Error", e.getMessage() + " . Pls, try again");
                    RequestContext.getCurrentInstance().showMessageInDialog(messag);

                } finally {

                    try {
                        rs.close();
                        ps.close();
                        this.Close(); //////////////
                    } catch (Exception e) {
                        e.printStackTrace();
                        System.err.println("Error while viewing stock: " + e.getMessage());
                    }
                }//end of finally-block
            }

            clearMthd();//Clears the form after insertion

        }//end of else block

    }//end of partnerRegMethod

    public void clearMthd() {
        productDescp = "";
        companyName = "";
        costPrice = 0;
        SalesPrice = 0;
        //date_of_registion = null;       
    }//end of method 

}//end of class
