package comp.esimbarcode.DAO;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DAO {

    private Connection cn;
    private PreparedStatement ps;

    public Connection getCn() {
        return cn;
    }

    public void setCn(Connection cn) {
        this.cn = cn;
    }

    public PreparedStatement getPs() {
        return ps;
    }

    public void setPs(PreparedStatement ps) {
        this.ps = ps;
    }

    //creating conection with use of  connection string 
    public void connector() throws Exception {
        try {
  
            Class.forName("com.mysql.jdbc.Driver");
           cn = (com.mysql.jdbc.Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/esimbarcode_hhy_store?user=root&password=ash123");
     
        } catch (Exception e) {
            System.out.println("Error in login/Connecting() -->" + e.getMessage());
            throw e;
        } 
        
        /*
        finally {
            // TODO Auto-generated catch block
            //ps.close();
            //cn.close();
            
            if(cn==null){
                cn.close();
            }else{
                //do nothing
            }
        }//end of finally-block

        */
    }//end of connector method 
    
    
     //close connection method
     public void Close() throws Exception{
        try{
             
            
            if(cn.isClosed()== false){
                    cn.close();
                }
           
             
        }catch(Exception e){
            throw e; 
           
        }finally{
             
            /*
            
            if(cn.isClosed()== false){
                    cn.close();
                }
            */
        }
    }

}//end of DAO class 
