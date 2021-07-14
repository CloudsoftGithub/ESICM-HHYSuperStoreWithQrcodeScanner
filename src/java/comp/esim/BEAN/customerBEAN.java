package comp.esimbarcode.BEAN;

import comp.esimbarcode.DAO.customerDAO;
import comp.esimbarcode.MODEL.customer;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class customerBEAN {

    customer cstm = new customer();

    public customer getCstm() {
        return cstm;
    }

    public void setCstm(customer cstm) {
        this.cstm = cstm;
    }

    public void customerReg() throws Exception {
        customerDAO dao;
        try {
            dao = new customerDAO();
            dao.customerReg(cstm);
        } catch (Exception e) {
            throw e;
        }//en of catch 

    }//end of customerReg
}//end of customerBEAN
