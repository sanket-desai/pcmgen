/**
 * @author Sanket S. Desai
 * @date 29.07.2012
 * @description A class containing static functions for basic calculations related to protein structures
*/
package pcmgen;
public class Calculation {

    // 180 / pi
	   static final double RADIAN = 57.29577951 ;
	       public final static float radiansPerDegree = (float) (2 * Math.PI / 360);
	      public final static float degreesPerRadian = (float) (360 / (2 * Math.PI));
	      
	   public static final double getDistance(ProteinAtom a, ProteinAtom b) 
	   {
                double x = a.getX() - b.getX();
	        double y = a.getY() - b.getY();
	        double z = a.getZ() - b.getZ();
	        double s  = x * x  + y * y + z * z;
                double dist = Math.sqrt(s);
                          return dist ;
        }
	   
	   public static  double getDistanceFast(ProteinAtom a, ProteinAtom b)
	   {
		   double x = a.getX() - b.getX();
	       double y = a.getY() - b.getY();
	       double z = a.getZ() - b.getZ();
	       double distSquared  = x * x  + y * y + z * z;
	       return distSquared;
	   }

	private static void nullCheck(ProteinAtom a)
	{
		if  (a == null) 
		{		
			System.out.println("Empty Atom, returning default values!");
		}
	}

	public static final ProteinAtom add(ProteinAtom a, ProteinAtom b)
	{
        ProteinAtom c = new ProteinAtom();
        c.setX( a.getX() + b.getX() );
	    c.setY( a.getY() + b.getY() );
	    c.setZ( a.getZ() + b.getZ() );
	    return c ;
	}
	public static final ProteinAtom substract(ProteinAtom a, ProteinAtom b) 
	{
		return subtract(a,b);
	}
	public  static final ProteinAtom subtract(ProteinAtom a, ProteinAtom b)
	{
		nullCheck(a) ;
	    nullCheck(b) ;
	    ProteinAtom c = new ProteinAtom();
	    c.setX( a.getX() - b.getX() );
	    c.setY( a.getY() - b.getY() );
	    c.setZ( a.getZ() - b.getZ() );
	    return c ;
	 }
	public static final ProteinAtom vectorProduct(ProteinAtom a , ProteinAtom b)
	{
		ProteinAtom c = new ProteinAtom();  
		c.setX( a.getY() * b.getZ() - a.getZ() * b.getY() ) ;
	    c.setY( a.getZ() * b.getX() - a.getX() * b.getZ() ) ;
	    c.setZ( a.getX() * b.getY() - a.getY() * b.getX() ) ;
	    return c ;
	}

	public static final double scalarProduct(ProteinAtom a, ProteinAtom b)
	{
		double skalar ;
		skalar = a.getX() * b.getX() + a.getY()* b.getY() + a.getZ() * b.getZ();
		return skalar ;
	}

	public static final  double amount(ProteinAtom a)
	{
		return (double) Math.sqrt(scalarProduct(a,a));
	}	       
	public static final double angle(ProteinAtom a, ProteinAtom b)
	{
		double skalar;
	    double angle;
	    skalar = scalarProduct(a,b);
	    angle = skalar/( amount(a) * amount (b) );
	    angle = (double) Math.acos(angle);
	    angle = (double) (angle * RADIAN) ;
	    return angle;
	}
	       
	public static final ProteinAtom unitVector(ProteinAtom a) 
	{
		double amount = amount(a) ;
	    ProteinAtom U = a ;
	    double [] coords = new double[3];
	    coords[0] = a.getX() / amount ;
	    coords[1] = a.getY() / amount ;
	    coords[2] = a.getZ() / amount ;
	    U.setCoordinates(coords);
	    return U ;
	}
	        
	public static final double torsionAngle(ProteinAtom a, ProteinAtom b, ProteinAtom c, ProteinAtom d)
	{
		ProteinAtom ab = subtract(a,b);
		ProteinAtom cb = subtract(c,b);
		ProteinAtom bc = subtract(b,c);
		ProteinAtom dc = subtract(d,c);
		ProteinAtom abc = vectorProduct(ab,cb);        
		ProteinAtom bcd = vectorProduct(bc,dc);
		double angl = angle(abc,bcd) ;
		/* calc the sign: */
		ProteinAtom vecprod = vectorProduct(abc,bcd);  
		double val = scalarProduct(cb,vecprod);
	    if (val<0.0) angl = -angl ;
	    return angl;
	}
	       
	public static final double getPhi(ProteinResidue a, ProteinResidue b)
	{
		if ( ! isConnected(a,b))
		{
			
		}
		ProteinAtom a_C  = a.getAtom("C");
		ProteinAtom b_N  = b.getAtom("N");
		ProteinAtom b_CA = b.getAtom("CA");
		ProteinAtom b_C  = b.getAtom("C");
		double phi = torsionAngle(a_C,b_N,b_CA,b_C);
	    return phi;
	 }

	 public static final double getPsi(ProteinResidue a, ProteinResidue b)
	 {
		 if ( ! isConnected(a,b)) 
		 {
			 
	     }
		 ProteinAtom a_N   = a.getAtom("N");
		 ProteinAtom a_CA  = a.getAtom("CA");
		 ProteinAtom a_C   = a.getAtom("C");
		 ProteinAtom b_N   = b.getAtom("N");
		 double psi = torsionAngle(a_N,a_CA,a_C,b_N);
	     return psi ;
	  }
	 
	 public static final boolean isConnected(ProteinResidue a, ProteinResidue b)
	 {
		 ProteinAtom C = a.getAtom("C");
		 ProteinAtom N = b.getAtom("N");
		 // one could also check if the CA atoms are < 4 A...
	     double distance = getDistance(C,N);
	     if ( distance < 2.5) 
	     { 
	    	 return true ;
	     } 
	     else 
	     {
	    	 return false ;
	     }
	 }
}
