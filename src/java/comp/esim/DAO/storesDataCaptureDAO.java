package comp.esimbarcode.DAO;

import comp.esimbarcode.MODEL.storeDataCapture;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;

public class storesDataCaptureDAO extends DAO {

    public void storeReg(storeDataCapture storeDcaptr) throws Exception {
        this.connector();

        PreparedStatement pst1 = this.getCn().prepareStatement("Select * from stores where store_title=? ");
        pst1.setString(1, storeDcaptr.getStoreTitle());
        ResultSet rs = pst1.executeQuery();
        if (rs.next()) {
           
            FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(FacesMessage.SEVERITY_FATAL,
                            "Store Registration Error.",
                            "This 'Store' has been registered. Pls, check your enteries!"));
        } else {

            try {
                PreparedStatement ps = this.getCn().prepareStatement("INSERT INTO stores values(null, CONCAT('S',ID), ?, now() )");
                ps.setString(1, storeDcaptr.getStoreTitle());

                int count = ps.executeUpdate();

                if (count > 0) {
                    FacesContext.getCurrentInstance().addMessage(
                            null,
                            new FacesMessage(FacesMessage.SEVERITY_INFO,
                                    "Congrats! " + storeDcaptr.getStoreTitle() + " registered successfully, as a Store.",
                                    "Thank you."));
                }

            } catch (Exception e) {

                FacesMessage messag = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Store Registration Error", e.getMessage() + " .Pls, try again");
                RequestContext.getCurrentInstance().showMessageInDialog(messag);
            }//end of catch

            ////------->UPDATING THE StoreID FROM THE "NP" into 'NP'+ID (e.g NP000001)
            ///////////////////////
            PreparedStatement ps = this.getCn().prepareStatement("UPDATE stores SET StoreID = CONCAT('S',ID)");
            ps.executeUpdate();
            //  ps.close();

        }//end of else-block 

        this.Close();//closes connection 
    }//end of method

}//end of class
