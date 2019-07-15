package comtest;
class AClass {
  public AClass() {
    System.out.println("AClass's Constructor");
  }
  static { 
    System.out.println("static block in AClass");
  }
  
  static { 
	    System.out.println("static block in AClass0000000000000000000");
	  }
}

public class Program { 
  public static void main(String[] args) {
    try { 
      System.out.println("The first time calls forName:");
      Class c   = Class.forName("comtest.AClass"); 
      AClass a = (AClass)c.newInstance();

      System.out.println("The second time calls forName:");
      Class c1 = Class.forName("comtest.AClass");

    } catch (ClassNotFoundException e) {
          
    } catch (InstantiationException e) {
            
    } catch (IllegalAccessException e) {
          
    }
        
  }
}