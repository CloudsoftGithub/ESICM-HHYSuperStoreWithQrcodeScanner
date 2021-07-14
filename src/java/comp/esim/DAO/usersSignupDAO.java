 
package comp.esimbarcode.DAO;

import comp.esimbarcode.MODEL.signup;
import comp.esimbarcode.MODEL.usersSignup;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;

 
public class usersSignupDAO extends  DAO{
    
    public void usersCreationMthd(usersSignup usersSigp) throws Exception {
        this.connector();

        ////////////// Checking the multiple signup with same username
        PreparedStatement st1 = this.getCn().prepareStatement("select * from users where username=?");
        st1.setString(1, usersSigp.getUsername());

        ResultSet rs = st1.executeQuery(); /////////////this block of code check for the multiple username signup as stated above. BUT THE TEST WWAS DONE BELOW IN THE ELSE IF BLOCK
        if (rs.next()) { // NOTE: the query state for this if block has been written above 

            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Signup Error", "This 'username' has been signed up, pls use a different username");
            RequestContext.getCurrentInstance().showMessageInDialog(message);
            //this.Close();
            // st1.close();

        } else {

            try {//thic block of codes does the actual insertions 

                PreparedStatement st = this.getCn().prepareStatement("INSERT INTO users values(?,?,?,?,?,?,Date(Now()))");
                st.setString(1, null);
                st.setString(2, usersSigp.getUsername());
                st.setString(3, usersSigp.getPassword());
                st.setString(4, usersSigp.getUserType());
                st.setString(5, usersSigp.getGsmNo());
                st.setString(6, usersSigp.getName());
                st.executeUpdate();
                st.close();

                FacesContext.getCurrentInstance().addMessage(
                        null,
                        new FacesMessage(FacesMessage.SEVERITY_INFO,
                                "You succcessfully signup" + " thank you! ",
                                "you will be redirected to a different page"));

                ExternalContext redcontext = FacesContext.getCurrentInstance().getExternalContext();
                redcontext.redirect("usersLogin.xhtml");

            } catch (Exception e) {
                FacesMessage messag = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Users Creation Error", e.getMessage() + " Pls, try again");
                RequestContext.getCurrentInstance().showMessageInDialog(messag);

            }//end of method

        }//end of else-block

    }//end of usersCreationMthd
    
}//end of class
