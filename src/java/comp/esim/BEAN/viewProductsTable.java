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
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped

public class viewProductsTable  extends DAO{

   // Connection con;
    PreparedStatement ps;
    ResultSet rs;

    private String productDescp;
    private String productID;
    private String partnerID;
    private String companyName;
    private Date date_of_registion;
    
    private double costPrice;
    private double salesPrice;


    public double getCostPrice() {
        return costPrice;
    }

    public void setCostPrice(double costPrice) {
        this.costPrice = costPrice;
    }

    public double getSalesPrice() {
        return salesPrice;
    }

    public void setSalesPrice(double salesPrice) {
        this.salesPrice = salesPrice;
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

    public String getPartnerID() {
        return partnerID;
    }

    public void setPartnerID(String partnerID) {
        this.partnerID = partnerID;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public Date getDate_of_registion() {
        return date_of_registion;
    }

    public void setDate_of_registion(Date date_of_registion) {
        this.date_of_registion = date_of_registion;
    }

    public List<viewProductsTable> getRegisteredProducts() throws Exception {
        this.connector();
 
       // Class.forName("com.mysql.jdbc.Driver");
       // con = (com.mysql.jdbc.Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/esimbarcode_hhy_store?user=root&password=ash123");

        List<viewProductsTable> productInfo = new ArrayList<viewProductsTable>();

        try {
            ps = this.getCn().prepareStatement("select * from products order by date_of_registration desc ");
             rs = ps.executeQuery();

            while (rs.next()) {
                viewProductsTable tbl = new viewProductsTable();
                tbl.setProductID(rs.getString("productID"));
                tbl.setProductDescp(rs.getString("productDescp"));
                tbl.setCostPrice(rs.getDouble("costPrice"));
                tbl.setSalesPrice(rs.getDouble("SalesPrice"));
                tbl.setCompanyName(rs.getString("CompanyName"));
                tbl.setDate_of_registion(rs.getDate("date_of_registration"));
                productInfo.add(tbl);
             
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
                System.err.println("Error in Products Registration Display: " + e.getMessage());
            }
        }//end of finally-block
        return productInfo;
    }//end of method

}//end of vla 
