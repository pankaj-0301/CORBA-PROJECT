package Calculator;


/**
* Calculator/CalcHelper.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from Calc.idl
* Friday, March 21, 2025 2:09:02 PM IST
*/

abstract public class CalcHelper
{
  private static String  _id = "IDL:Calculator/Calc:1.0";

  public static void insert (org.omg.CORBA.Any a, Calculator.Calc that)
  {
    org.omg.CORBA.portable.OutputStream out = a.create_output_stream ();
    a.type (type ());
    write (out, that);
    a.read_value (out.create_input_stream (), type ());
  }

  public static Calculator.Calc extract (org.omg.CORBA.Any a)
  {
    return read (a.create_input_stream ());
  }

  private static org.omg.CORBA.TypeCode __typeCode = null;
  synchronized public static org.omg.CORBA.TypeCode type ()
  {
    if (__typeCode == null)
    {
      __typeCode = org.omg.CORBA.ORB.init ().create_interface_tc (Calculator.CalcHelper.id (), "Calc");
    }
    return __typeCode;
  }

  public static String id ()
  {
    return _id;
  }

  public static Calculator.Calc read (org.omg.CORBA.portable.InputStream istream)
  {
    return narrow (istream.read_Object (_CalcStub.class));
  }

  public static void write (org.omg.CORBA.portable.OutputStream ostream, Calculator.Calc value)
  {
    ostream.write_Object ((org.omg.CORBA.Object) value);
  }

  public static Calculator.Calc narrow (org.omg.CORBA.Object obj)
  {
    if (obj == null)
      return null;
    else if (obj instanceof Calculator.Calc)
      return (Calculator.Calc)obj;
    else if (!obj._is_a (id ()))
      throw new org.omg.CORBA.BAD_PARAM ();
    else
    {
      org.omg.CORBA.portable.Delegate delegate = ((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate ();
      Calculator._CalcStub stub = new Calculator._CalcStub ();
      stub._set_delegate(delegate);
      return stub;
    }
  }

  public static Calculator.Calc unchecked_narrow (org.omg.CORBA.Object obj)
  {
    if (obj == null)
      return null;
    else if (obj instanceof Calculator.Calc)
      return (Calculator.Calc)obj;
    else
    {
      org.omg.CORBA.portable.Delegate delegate = ((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate ();
      Calculator._CalcStub stub = new Calculator._CalcStub ();
      stub._set_delegate(delegate);
      return stub;
    }
  }

}
