package comp.esim.BEAN;

import comp.esimbarcode.DAO.DAO;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

@ManagedBean
@RequestScoped
public class deleteItemRowInPreOrderBEAN extends DAO {

    PreparedStatement ps;
    ResultSet rs;

    private String userName;

    private String productID;
    private String productDescp;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public String getProductDescp() {
        return productDescp;
    }

    public void setProductDescp(String productDescp) {
        this.productDescp = productDescp;
    }

    public void retriveUserNameFromUI() {//get the current Username  on the UI 
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        userName = ec.getRequestParameterMap().get("CustomerOrderForm:userName");
    }//end of method  

    ////DELETING/REMOVING THE PRODUCTS FROM THE PRE-ORDER LIST (Customer may deside to do some adjustments for some reasons)
    public void removeProductFromOrderListWithCommandLink(String productDescp, String userName) throws Exception {

        this.connector();//establishes connectdion from the DAO Class (i.e the super class)

        retriveUserNameFromUI();//invokes the method so 'userName' variable get captured from the BEAN

        try {

            ps = this.getCn().prepareStatement("DELETE FROM preoderdetails WHERE productDescp =? AND username=? ");
            ps.setString(1, productDescp);
            ps.setString(2, userName);

            ps.executeUpdate();

            FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(FacesMessage.SEVERITY_WARN,
                            "Product:  " + productDescp + " has been removed successfully from " + " Order-List .",
                            "Thank you."));

        } catch (Exception e) {
            throw e;
        } finally {
            this.Close();
            ps.close();
        }//end of finally-block

    }//end of method
     ////DELETING/REMOVING THE PRODUCTS FROM THE PRE-ORDER LIST (Customer may deside to do some adjustments for some reasons)
    public void removeProductFromOrderListWithCommandLinkOriginal(String productDescp) throws Exception {

        this.connector();//establishes connectdion from the DAO Class (i.e the super class)

        retriveUserNameFromUI();//invokes the method so 'userName' variable get captured from the BEAN

        try {

            ps = this.getCn().prepareStatement("DELETE FROM preoderdetails WHERE productDescp =? ");
            ps.setString(1, productDescp);
            // ps.setString(2, userName);

            ps.executeUpdate();

            FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(FacesMessage.SEVERITY_WARN,
                            "Product:  " + productDescp + " has been removed successfully from " + " Order-List .",
                            "Thank you."));

        } catch (Exception e) {
            throw e;
        } finally {
            this.Close();
            ps.close();
        }//end of finally-block

    }//end of method

}//end of the class

