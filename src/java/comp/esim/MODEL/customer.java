 
package comp.esimbarcode.MODEL;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

 
public class customer {
    
    private String customerID;
    private String customerName;
    private String BussinessName;
    private String phoneNumber;
    private String address;

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getBussinessName() {
        return BussinessName;
    }

    public void setBussinessName(String BussinessName) {
        this.BussinessName = BussinessName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
     
    
}//end of class customer 
