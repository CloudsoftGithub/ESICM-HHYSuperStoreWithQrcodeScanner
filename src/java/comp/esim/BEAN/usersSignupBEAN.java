 
package comp.esimbarcode.BEAN;

import comp.esimbarcode.DAO.usersSignupDAO;
import comp.esimbarcode.MODEL.usersSignup;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class usersSignupBEAN {
    usersSignup usersSignp = new usersSignup();

    public usersSignup getUsersSignp() {
        return usersSignp;
    }

    public void setUsersSignp(usersSignup usersSignp) {
        this.usersSignp = usersSignp;
    }
    
    
    public void usersMthd() throws Exception{
        usersSignupDAO dao;
        try {
            dao = new usersSignupDAO();
            dao.usersCreationMthd(usersSignp);
        } catch (Exception e) {
            throw e;
        }
        
    }//en of usersmthd
    
}//end of class

