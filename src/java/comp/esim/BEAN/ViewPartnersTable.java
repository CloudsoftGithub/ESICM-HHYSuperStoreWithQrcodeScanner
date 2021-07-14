package comp.esimbarcode.BEAN;

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

public class ViewPartnersTable {

    Connection con;
    PreparedStatement ps;
    ResultSet rs;

    private String partnerID;
    private String partnerName;
    private String dateRegistered;

    public String getPartnerID() {
        return partnerID;
    }

    public void setPartnerID(String partnerID) {
        this.partnerID = partnerID;
    }

    public String getPartnerName() {
        return partnerName;
    }

    public void setPartnerName(String partnerName) {
        this.partnerName = partnerName;
    }

    public String getDateRegistered() {
        return dateRegistered;
    }

    public void setDateRegistered(String dateRegistered) {
        this.dateRegistered = dateRegistered;
    }

    public List<ViewPartnersTable> getRegisteredPartner() throws Exception {

        List<ViewPartnersTable> partnerInfo = new ArrayList<ViewPartnersTable>();

        try {

            Class.forName("com.mysql.jdbc.Driver");
            con = (com.mysql.jdbc.Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/esimbarcode_hhy_store?user=root&password=ash123");
            ps = con.prepareStatement("select * from partner ");
            rs = ps.executeQuery();

            while (rs.next()) {
                ViewPartnersTable tbl = new ViewPartnersTable();
                tbl.setPartnerID(rs.getString("PartnerID"));
                tbl.setPartnerName(rs.getString("partner_name"));
                tbl.setDateRegistered(rs.getString("date_of_registration"));
                partnerInfo.add(tbl);

                //con.close();//closes connection 
                //ps.close();//closes the prepared statement
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
                System.err.println("Error in Partner Registration Display: " + e.getMessage());
            }
        }//end of finally-block
        return partnerInfo;
    }//end of method

}//end of ViewPartners class
