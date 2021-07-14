package comp.esimbarcode.BEAN;

import comp.esimbarcode.DAO.storesDataCaptureDAO;
import comp.esimbarcode.MODEL.storeDataCapture;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class storeDatacaptureBEAN {

    storeDataCapture strDtCapture = new storeDataCapture();
    
    public storeDataCapture getStrDtCapture() {
        return strDtCapture;
    }
    
    public void setStrDtCapture(storeDataCapture strDtCapture) {
        this.strDtCapture = strDtCapture;
    }
    
    public void storeDataCapMthd() throws Exception {
        storesDataCaptureDAO dao;
        
        try {
            dao = new storesDataCaptureDAO();
            dao.storeReg(strDtCapture);
        } catch (Exception e) {
            throw e;
        }
        
    }//end of class
    
}//end of class
