package comp.esimbarcode.BEAN;

import java.io.IOException;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;

@ManagedBean
@SessionScoped
public class idleMonitorView {

    public void onIdle() throws IOException {

        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Idle Monitor", "The time alloted is over, pls login again");
        RequestContext.getCurrentInstance().showMessageInDialog(message);

        ExternalContext redcontext = FacesContext.getCurrentInstance().getExternalContext();
        redcontext.redirect("index.xhtml");
    }//end of onIdle method

    //public void onActive() {
       // FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
               // "Welcome Back", "Well, that's a long coffee break!"));
   // }

}//end of method 
