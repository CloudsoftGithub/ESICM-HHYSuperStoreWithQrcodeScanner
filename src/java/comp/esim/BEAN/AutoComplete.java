package comp.esimbarcode.BEAN;

import comp.esimbarcode.DAO.DAO;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.swing.DefaultListModel;
import javax.swing.JList;

@ManagedBean
public class AutoComplete extends DAO {
//getter setter

    PreparedStatement ps;
    ResultSet rs;
    private String partnerName;

    public String getPartnerName() {
        return partnerName;
    }

    public void setPartnerName(String partnerName) {
        this.partnerName = partnerName;
    }

    private JList jlist;

    public JList getJlist() {
        return jlist;
    }

    public void setJlist(JList jlist) {
        this.jlist = jlist;
    }

    private String text;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<String> complete(String query) {
        List<String> results = new ArrayList<String>();
        for (int i = 0; i < 10; i++) {
            results.add(query + i);
        }
        return results;
    }

    public List<String> fromCompleteList(String query) {
        List<String> results = new ArrayList<String>();
        results.add("HONEYWELL Flour (50 KG)");
        results.add("HONEYWELL Bakers Delight (50 KG)");
        results.add("DANGOTE FLOURS MILLS Flour (50 KG)");
        results.add("DANGOTE FLOURS MILLS Salt (100 KG)");
        results.add("DANGOTE FLOURS MILLS Sugar (50 KG)");
        return results;
    }

    private void mthd() throws Exception {
        //this.connector();

        DefaultListModel m = new DefaultListModel();

        try {
            PreparedStatement st = this.getCn().prepareStatement("select ProductDescp from products");
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                String product = rs.getString("name");
                m.addElement(product);
            }
            jlist.setModel(m);
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(FacesMessage.SEVERITY_FATAL,
                            "Invalid product entered! " + "  Pls, select a valid product",
                            "and try again, " + e.getMessage()));
        }
    }

    public List<ViewPartnersTable> getRegisteredPartner() throws Exception {
        this.connector();

        List<ViewPartnersTable> partnerInfo = new ArrayList<ViewPartnersTable>();

        try {
            PreparedStatement ps = this.getCn().prepareStatement("select * from partner ");
            rs = ps.executeQuery();

            while (rs.next()) {
                ViewPartnersTable tbl = new ViewPartnersTable();
                tbl.setPartnerID(rs.getString("PartnerID"));
                tbl.setPartnerName(rs.getString("partner_name"));
                tbl.setDateRegistered(rs.getString("date_of_registration"));
                partnerInfo.add(tbl);
            }//end of while-block

        } catch (Exception e) {
            throw e;
        }//end of catch-block

        return partnerInfo;
    }//end of method

}//end of class
