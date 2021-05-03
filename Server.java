/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import ser.TaxHelper;
import ser.Tax;
import ser.TaxPOA;
import org.omg.CosNaming.*;
import org.omg.CORBA.*;
import org.omg.PortableServer.*;
import org.omg.PortableServer.POA;


class taxMethods extends TaxPOA {
  private ORB orb;

  public void setORB(ORB orb_val) {
    orb = orb_val; 
  }
    
  
    
  // implement shutdown() method
  public void shutdown() {
    orb.shutdown(false);
  }

    @Override
    public double findTax(double sal, double taxR) {
        double totTax;
        
        totTax = taxR*sal ;
        
        return totTax;    
    }

    @Override
    public double findbracket(double bracket1) {
       double num = bracket1;
        
        if (bracket1 >= 0 && bracket1 <=0.05) {
            num=1;
            return num; 
        }
        else if (bracket1 >= 0.06 && bracket1 <= 0.10) {
            num= 2;
            return num; 
        }
        else if (bracket1 >= 0.11 && bracket1 <= 0.15) {
            num=3;
            return num; 
        }
        else if (bracket1 >= 0.16 && bracket1 <= 0.20) {
             num=4;
            return num;  
        }
        else  {
               num=5;
            return num; 
        }
    }
}


public class Server {

  public static void main(String args[]) {
    try{
      // create and initialize the ORB
      ORB orb = ORB.init(args, null);

      // get reference to rootpoa & activate the POAManager
      POA rootpoa = POAHelper.narrow(orb.resolve_initial_references("RootPOA"));
      rootpoa.the_POAManager().activate();

      // create servant and register it with the ORB
      taxMethods helloImpl = new taxMethods();
      helloImpl.setORB(orb); 

      // get object reference from the servant
      org.omg.CORBA.Object ref = rootpoa.servant_to_reference(helloImpl);
      Tax href = TaxHelper.narrow(ref);
          
      // get the root naming context
      // NameService invokes the name service
      org.omg.CORBA.Object objRef =
          orb.resolve_initial_references("NameService");
      // Use NamingContextExt which is part of the Interoperable
      // Naming Service (INS) specification.
      NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);

      // bind the Object Reference in Naming
      String name = "Hello";
      NameComponent path[] = ncRef.to_name( "ABC" );
      ncRef.rebind(path, href);

      System.out.println("HelloServer ready and waiting ...");

      // wait for invocations from clients
      for(;;){
      orb.run();
      }
    } 
        
      catch (Exception e) {
        System.err.println("ERROR: " + e);
        e.printStackTrace(System.out);
      }
          
      System.out.println("HelloServer Exiting ...");
        
  }
}