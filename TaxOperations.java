package ser;


/**
* TaxApp/TaxOperations.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from taxproj.idl
* Monday, April 27, 2020 7:37:14 PM EDT
*/

public interface TaxOperations 
{
  double findTax (double Sal, double taxR);
  double findbracket (double bracket);
  void shutdown ();
} // interface TaxOperations
