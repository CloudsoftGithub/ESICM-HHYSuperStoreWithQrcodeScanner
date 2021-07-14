 
package comp.esimbarcode.BEAN;

import comp.esimbarcode.DAO.signupDAO;
import comp.esimbarcode.MODEL.signup;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class signupBEAN {
    signup signp = new signup();

    public signup getSignp() {
        return signp;
    }

    public void setSignp(signup signp) {
        this.signp = signp;
    }
    
    
    public void rootuserMthd() throws Exception{
        signupDAO dao;
        
        try {
            dao = new signupDAO();
            dao.AdminCreationMthd(signp);
        } catch (Exception e) {
            throw e;
        }
        
    }//end of method
    
    
}//end of class
