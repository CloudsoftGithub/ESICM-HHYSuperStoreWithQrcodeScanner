 
package comp.esimbarcode.BEAN;

import comp.esimbarcode.DAO.partnerRegistrationDAO;
import comp.esimbarcode.MODEL.partnerRegistration;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class partnerRegistrationBEAN {
     partnerRegistration partnerreg = new partnerRegistration();

    public partnerRegistration getPartnerreg() {
        return partnerreg;
    }

    public void setPartnerreg(partnerRegistration partnerreg) {
        this.partnerreg = partnerreg;
    }
     
     public void partnerRegMthd() throws Exception{
         partnerRegistrationDAO dao;
         
         try {
             dao = new partnerRegistrationDAO();
         dao.partnerRegMethod(partnerreg);
             
          } catch (Exception e) {
             throw e;
         }
         
     }//end of partnermthd 
     
}//end of partnerRegistrationBEAN class
