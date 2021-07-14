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

@ManagedBean
@SessionScoped
public class viewStoresTable extends DAO{

    //Connection con;
    PreparedStatement ps;
    ResultSet rs;

    private String storeID;
    private String stroreTitle;
    private String dateRegistered;

    public String getStoreID() {
        return storeID;
    }

    public void setStoreID(String storeID) {
        this.storeID = storeID;
    }

    public String getStroreTitle() {
        return stroreTitle;
    }

    public void setStroreTitle(String stroreTitle) {
        this.stroreTitle = stroreTitle;
    }

    public String getDateRegistered() {
        return dateRegistered;
    }

    public void setDateRegistered(String dateRegistered) {
        this.dateRegistered = dateRegistered;
    }

    public List<viewStoresTable> getRegisteredStores() throws Exception {
        this.connector();

        Class.forName("com.mysql.jdbc.Driver");
       // con = (com.mysql.jdbc.Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/esimbarcode_hhy_store?user=root&password=ash123");

        List<viewStoresTable> storeInfo = new ArrayList<viewStoresTable>();

        try {
            ps = this.getCn().prepareStatement("select * from stores");
            rs = ps.executeQuery();

            while (rs.next()) {
                viewStoresTable tbl = new viewStoresTable();
                tbl.setStoreID(rs.getString("StoreID"));
                tbl.setStroreTitle(rs.getString("store_title"));
                tbl.setDateRegistered(rs.getString("date_of_registration"));
                storeInfo.add(tbl);
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
                System.err.println("Error in Store Registration Display: " + e.getMessage());
            }
        }//end of finally-block 

        return storeInfo;
    }//end of method

}//end of viewStoresTable class
