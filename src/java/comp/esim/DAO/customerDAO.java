package comp.esimbarcode.DAO;

import comp.esimbarcode.MODEL.customer;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;

public class customerDAO extends DAO {

    public void customerReg(customer cstmr) throws Exception {
        this.connector();

        PreparedStatement pst1 = this.getCn().prepareStatement("Select * from customer where Buss_name=? OR Phone=? ");
        //pst1.setString(1, cstmr.getPhoneNumber());
        pst1.setString(1, cstmr.getBussinessName());
        pst1.setString(2, cstmr.getPhoneNumber());
        ResultSet rs = pst1.executeQuery();

        if (rs.next()) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Customer Registration Error", "This 'Company Name' OR 'Phone Number' have been Registered. Pls, make some changes and try again");
            RequestContext.getCurrentInstance().showMessageInDialog(message);
        } else {

            try {
                PreparedStatement ps = this.getCn().prepareStatement(" INSERT INTO customer VALUES(?,CONCAT('NC',ID),?,?,?, ?, Now())");
                ps.setString(1, null);
                ps.setString(2, cstmr.getCustomerName());
                ps.setString(3, cstmr.getBussinessName());
                ps.setString(4, cstmr.getPhoneNumber());
                ps.setString(5, cstmr.getAddress());
                ps.executeUpdate();
                FacesContext.getCurrentInstance().addMessage(
                        null,
                        new FacesMessage(FacesMessage.SEVERITY_INFO,
                                "Congrats! " + cstmr.getCustomerName() + " registered successfully, as a 'Customer'.",
                                "Thank you."));
            } catch (Exception e) {
                FacesMessage messag = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Customer Registration Error", e.getMessage() + " . Pls, try again");
                RequestContext.getCurrentInstance().showMessageInDialog(messag);
            }

            ////------->UPDATING THE CustomerID FROM THE "NC0000" into 'NC'+ID (e.g NC000001)
            ///////////////////////
            PreparedStatement ps = this.getCn().prepareStatement("UPDATE customer SET customerID = CONCAT('NC',ID)");
            ps.executeUpdate();
            //  ps.close();

            //CLEARING THE USER INPUTS FOR NEW ENTERIES
            cstmr.setBussinessName("");
            cstmr.setCustomerName("");
            cstmr.setPhoneNumber("");
            cstmr.setAddress("");
        }//end of else-block 

    }//end of method customerReg

}//end of class customerDAO
