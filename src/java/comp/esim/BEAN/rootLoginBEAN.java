package comp.esimbarcode.BEAN;

import comp.esimbarcode.DAO.DAO;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

@ManagedBean
@SessionScoped
public class rootLoginBEAN extends DAO {

    private String username;
    private String password;
    private String userType;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String rootUserLogin() throws Exception {
        Connection con = null;
        PreparedStatement ps = null;
        String myusername = username.toString();
        String mypassword = password.toString();

        try {
            //this.connector();

            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/esimbarcode_hhy_store?user=" + myusername + "&" + "password=" + mypassword);
            // Jealastic connection on the Jealastic Cloud
            //  con = (com.mysql.jdbc.Connection) DriverManager.getConnection("jdbc:mysql://node12525-env-esimbarcode.cloud.interhostsolutions.be/esimbarcode", "root", "HHBoea11492");

            ExternalContext redcontext = FacesContext.getCurrentInstance().getExternalContext();
            redcontext.redirect("RootuserDashboard.xhtml");   /// redirecting to  the 

            con.close();

            return "";
        } catch (Exception e) {

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL,
                    "Invalid Login!" + " Pls, make sure you are a Root User, then supply correct credentials",
                    "and, Try Again"));

            return "";
        }

    }//

    public String adminuserLogin() throws Exception {
        this.connector();

        try {

            Connection con = null;
            PreparedStatement ps = null;
            String myusername = username.toString();
            String mypassword = password.toString();

            // con = DriverManager.getConnection("jdbc:mysql://localhost:3306/losaportal?user=" + myusername + "&" + "password=" + mypassword);
            PreparedStatement st = this.getCn().prepareStatement("select username, password from adminusers where username= ? and password= ? ");
            st.setString(1, myusername);
            st.setString(2, mypassword);

            ResultSet rs = st.executeQuery();
            if (rs.next()) // found
            {

                ExternalContext redcontext = FacesContext.getCurrentInstance().getExternalContext();
                redcontext.redirect("AdminuserDashboard.xhtml");   /// redirecting to  the 
            } else {
                FacesContext.getCurrentInstance().addMessage(
                        null,
                        new FacesMessage(FacesMessage.SEVERITY_FATAL,
                                "Invalid username or password! " + "  Pls, use correct credentials",
                                "and try again"));
            }//end of else block

            return "";
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(FacesMessage.SEVERITY_FATAL,
                            "Invalid Login! " + e.getMessage() + "  Pls, make sure you are an Admin User",
                            "Please, Try Again"));

            return "";
        } finally {
            this.Close();
        }

    }//end of method

    public String userLogin() throws Exception {

        this.connector();

        try {

            Connection con = null;
            PreparedStatement ps = null;
            String myusername = username.toString();
            String mypassword = password.toString();
            String usertype = userType.toString();
            PreparedStatement st = this.getCn().prepareStatement("select username, password,userType from users where username= ? and password= ? and userType= ?");
            st.setString(1, myusername);
            st.setString(2, mypassword);
            st.setString(3, usertype);

            ResultSet rs = st.executeQuery();
            if (rs.next()) { // found i.e user exist with the supplied password 
                String myuserType = rs.getString(3);//getting the userType from the Database

                if (myuserType.equalsIgnoreCase("Store-Keeper")) {

                    ExternalContext redcontext = FacesContext.getCurrentInstance().getExternalContext();
                    redcontext.redirect("Store-KeeperDashboard.xhtml");   /// redirecting to  the store-keeperDashboard

                } else if (myuserType.equalsIgnoreCase("Sales-Manager") || myuserType.equalsIgnoreCase("Sales-Personnel")) {

                    ExternalContext redcontext = FacesContext.getCurrentInstance().getExternalContext();
                    redcontext.redirect("Sales-ManagerDashboard.xhtml");   /// redirecting to  the sales-manager Dashboard

                }else if (myuserType.equalsIgnoreCase("CEO") || myuserType.equalsIgnoreCase("Sales-Personnel")) {

                    ExternalContext redcontext = FacesContext.getCurrentInstance().getExternalContext();
                    redcontext.redirect("CEODashboard.xhtml");   /// redirecting to  the sales-manager Dashboard

                } else if (myuserType.equalsIgnoreCase("Cashier")) {

                    ExternalContext redcontext = FacesContext.getCurrentInstance().getExternalContext();
                    redcontext.redirect("CashierDashboard.xhtml");   /// redirecting to  the cashies-Dashboard
                }//end of if-block
                
                //Clear variables
                clear();
                
            } else {
                FacesContext.getCurrentInstance().addMessage(
                        null,
                        new FacesMessage(FacesMessage.SEVERITY_FATAL,
                                "Invalid username or password! " + "  Pls, use correct credentials",
                                "and try again"));
            }//end of else block

            return "";
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(FacesMessage.SEVERITY_FATAL,
                            "Invalid Login! " + e.getMessage() + "  Pls, make sure you are an Admin User",
                            "Please, Try Again"));

            return "";
        } finally {
            this.Close();
        }

    }//end of method

    public String signoutMethod() throws IOException {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        //return "/index.xhtml?faces-redirect=true";

        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "";

    }//end of signoutMethod

    public String clear() {

        password = "";//clears the used password

        return "";

    }//end of method

}//end of class
