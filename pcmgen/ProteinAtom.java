/**
 * @author Sanket S. Desai
 * @date 29.07.2012
 * @description An abstraction of an atom in a protein structure
 */
package pcmgen;
public class ProteinAtom {
	long atm_num;
	String atm_name;
	double x_, y_, z_, bfactor;
	
	public ProteinAtom(String at_rec)
	{       
		atm_num =  Long.parseLong(at_rec.substring(7, 12).trim());
		atm_name = at_rec.substring(12, 16).trim();
		x_ = Double.parseDouble(at_rec.substring(30, 38).trim());
		y_ = Double.parseDouble(at_rec.substring(38, 46).trim());
		z_ = Double.parseDouble(at_rec.substring(46, 54).trim());
		bfactor = Double.parseDouble(at_rec.substring(60, 66).trim());		
	}//Constructor ends here
	
	public ProteinAtom() {
		atm_name = "QQQQ";
		atm_num = 9999;
		x_ = (double) 999.999;
		y_ = (double) 999.999;
		z_ = (double) 999.999;
		bfactor = (double) 999.999;
	}

	public String getName()	
	{
		return atm_name;
	}
	public long getNumber()
	{
		return atm_num;
	}
	public double getX()
	{
		return x_;
	}
	public void setX(double i)
	{
		x_ = i;
	}
	public double getY()
	{
		return y_;
	}
	public void setY(double i)
	{
		y_ = i;
	}
	public double getZ()
	{
		return z_;
	}
	public void setZ(double i)
	{
		z_ = i;
	}
	public double getBfactor()
	{
		return bfactor;
	}

	public void setCoordinates(double[] coords) 
	{
		x_ = coords[0];
		y_ = coords[1];
		z_ = coords[2];
	}
}
