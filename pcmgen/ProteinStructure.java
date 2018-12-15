/**
 * @author Sanket S. Desai
 * @date 29.07.2012
 * @description An abstraction of protein structure
 */
 package pcmgen;

import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ProteinStructure {

	ArrayList<ProteinChain> pc = new ArrayList<ProteinChain>();
	String id_;

	public ProteinStructure(ArrayList<String> atomSection)
	{
		char[] c = new char[atomSection.size()];
		for(int i = 0; i < atomSection.size(); i++)
		{
			c[i] = atomSection.get(i).charAt(21);
		}
		char [] uc = Utilities.getUniqueElements(c);
		for(int j = 0; j < uc.length; j++)
		{
			ArrayList<String> apc = new ArrayList<String>();
			for(int k = 0; k < c.length; k++)
			{
				if(uc[j] == c[k])
				{
					apc.add(atomSection.get(k));
				}
			}
			pc.add(new ProteinChain(apc));
		}
        }// Constructor ends here

	public ProteinStructure(String fn)
	{
                try
                {
                        BufferedReader pdis = new BufferedReader(new FileReader(fn));
                        String s = pdis.readLine();
			ArrayList<String> atom_sec = new ArrayList<String>();
			if(s.startsWith("HEADER"))
			{
			    this.id_ = s.substring(62, 66);
			}
			else {
				System.err.println("File " + fn +" is not compliant with PDB file format");
				System.exit(0);
			}
                        while((s = pdis.readLine()) != null )
                        {
                                if(s.startsWith("ATOM ") )//&& (s.substring(0,3).compareTo("TER") != 0))
                                {
                                    atom_sec.add(s);
                                }
                        }
			char[] c = new char[atom_sec.size()];
			for(int i = 0; i < atom_sec.size(); i++)
			{
				c[i] = atom_sec.get(i).charAt(21);
			}
			char [] uc = Utilities.getUniqueElements(c);
			for(int j = 0; j < uc.length; j++)
			{
				ArrayList<String> apc = new ArrayList<String>();
				for(int k = 0; k < c.length; k++)
				{
					if(uc[j] == c[k])
					{
						apc.add(atom_sec.get(k));
					}
				}
				pc.add(new ProteinChain(apc));
			}
				pdis.close();
                }
                catch(FileNotFoundException fi)
                {
			System.err.println("Enter a valid PDB file.");
			System.exit(0);
                }
                catch (IOException e) {
                        e.printStackTrace();
                }
                catch(Exception e)
                {
                    e.printStackTrace();
                }
	}

	public int getNumberOfChains()
	{
		return pc.size();
	}
	public ProteinChain getChain(int i)
	{
		return pc.get(i);
	}
	public String getId()
	{
		return this.id_;
	}
	public ProteinChain getChain(char c)
	{
		ProteinChain p_ch = null;
		for(int x = 0; x < pc.size(); x++)
		{
			if(pc.get(x).getChainId() == c)
			{
				p_ch = pc.get(x);
			}
		}
		return p_ch;
        }
}
