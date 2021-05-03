/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import ser.TaxHelper;
import ser.Tax;
import java.util.Scanner;
import org.omg.CosNaming.*;
import org.omg.CORBA.*;

public class Client
{
  
static Tax Tax1;
  public static void main(String args[])
    {
      try{
        
         // creating and initializing ORB
         ORB orb = ORB.init(args, null);
        // get the root naming context
            
        org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
            
        NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);
            
        Tax1 = (Tax) TaxHelper.narrow(ncRef.resolve_str("ABC"));

            Scanner reader = new Scanner(System.in);
           
            for (;;) {
                System.out.println("Please enter your Salary:");
                String line1 = reader.nextLine();
                System.out.println("Please enter your Tax Rate:");
                String line2 = reader.nextLine();
                
                double sal = Double.parseDouble(line1);
                double taxR =Double.parseDouble(line2);
                
                
                System.out.println("Your taxed salary is: " + Tax1.findTax(sal, taxR));
                System.out.println("You are in Bracket: " + Tax1.findbracket(taxR));
                
            }
        
        
        
        } catch (Exception e) {
          System.out.println("ERROR : " + e) ;
          e.printStackTrace(System.out);
          }
    }

}
