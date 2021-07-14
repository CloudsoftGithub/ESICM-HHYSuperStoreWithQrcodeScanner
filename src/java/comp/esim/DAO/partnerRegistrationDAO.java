package comp.esimbarcode.DAO;

import comp.esimbarcode.MODEL.partnerRegistration;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;

public class partnerRegistrationDAO extends DAO {

    public void partnerRegMethod(partnerRegistration partreg) throws Exception {
        this.connector();

        PreparedStatement pst1 = this.getCn().prepareStatement("Select * from partner where partner_name=? ");
        pst1.setString(1, partreg.getPartnerName());
        ResultSet rs = pst1.executeQuery();
        if (rs.next()) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Partner Registration Error", "This 'partner company' has been registered. Pls, check your enteries!");
            RequestContext.getCurrentInstance().showMessageInDialog(message);

        } else {

            try {
                PreparedStatement ps = this.getCn().prepareStatement("insert into partner values(?, CONCAT('NP',ID), ?, ?)");
                ps.setString(1, null);
                ps.setString(2, partreg.getPartnerName());

                //Converting the Date from Java.util.Date to Java.Sql.Date
                java.util.Date utilDateofReg = partreg.getDate_of_registration();
                java.sql.Date sqlutilDateofReg = new java.sql.Date(utilDateofReg.getTime());

                ps.setDate(3, sqlutilDateofReg);
                ps.executeUpdate();
                
                 FacesContext.getCurrentInstance().addMessage(
                        null,
                        new FacesMessage(FacesMessage.SEVERITY_INFO,
                                "Congrats! " + partreg.getPartnerName() + " registered successfully, as a Partner.",
                                "Thank you."));
               
            } catch (Exception e) {
                FacesMessage messag = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Parner Registration Error", e.getMessage() + " . Pls, try again");
                RequestContext.getCurrentInstance().showMessageInDialog(messag);

            }//end of catch 
            
            
             ////------->UPDATING THE PartnerID FROM THE "NP" into 'NP'+ID (e.g NP000001)
            ///////////////////////
             PreparedStatement ps = this.getCn().prepareStatement("UPDATE partner SET PartnerID = CONCAT('NP',ID)");
                ps.executeUpdate();
                
                //this.connector();
                //ps.close();


        }//end of else block
        
        
       

    }//end of partnerRegMethod

}//enf od partnerRegistrationDAO
