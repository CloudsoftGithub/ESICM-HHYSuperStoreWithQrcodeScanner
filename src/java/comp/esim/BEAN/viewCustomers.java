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

public class viewCustomers extends DAO {

    Connection con;
    PreparedStatement ps;
    ResultSet rs;

    private String customerID;
    private String customerName;
    private String bussinessName;
    private String phoneNumber;
    private String address;
    private Date date_of_registion;

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
        return bussinessName;
    }

    public void setBussinessName(String bussinessName) {
        this.bussinessName = bussinessName;
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

    public Date getDate_of_registion() {
        return date_of_registion;
    }

    public void setDate_of_registion(Date date_of_registion) {
        this.date_of_registion = date_of_registion;
    }

    public List<viewCustomers> getRegisteredCustomers() throws Exception {
        this.connector();

        List<viewCustomers> customerInfo = new ArrayList<viewCustomers>();

        try {
            //Mysql connection
            con = (com.mysql.jdbc.Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/esimbarcode_hhy_store?user=root&password=ash123");
            
            // Jealastic connection on the Jealastic Cloud
           //  con = (com.mysql.jdbc.Connection) DriverManager.getConnection("jdbc:mysql://node12525-env-esimbarcode.cloud.interhostsolutions.be/esimbarcode", "root", "HHBoea11492");

            ps = con.prepareStatement("select * from customer order by  Date_of_reg Desc  ");
            rs = ps.executeQuery();

            while (rs.next()) {
                viewCustomers tbl = new viewCustomers();
                tbl.setCustomerID(rs.getString("customerID"));
                tbl.setCustomerName(rs.getString("customerName"));
                tbl.setBussinessName(rs.getString("Buss_name"));
                tbl.setPhoneNumber(rs.getString("Phone"));
                tbl.setAddress(rs.getString("address"));
                tbl.setDate_of_registion(rs.getDate("Date_of_reg"));
                customerInfo.add(tbl);
            }//end of while-block

        } catch (Exception e) {
            throw e;
        } finally {

            try {
                rs.close();
                ps.close();
                con.close();
            } catch (Exception e) {
                e.printStackTrace();
                System.err.println("Erro Message: " + e.getMessage());
            }
        }//end of finally-block

        return customerInfo;
    }//end of method

}//end of vla 
