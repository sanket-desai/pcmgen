/**
 * @author Sanket S. Desai
 * @date 29.07.2012
 * @description A library class to store Amino acide 3 letter, 1 letter codes
 */
 package pcmgen;

import java.util.ArrayList;
public class ProteinResidue {
	ArrayList<ProteinAtom> pa = new ArrayList<ProteinAtom>();
	long res_num;
	String res_name;
	public ProteinResidue(ArrayList<String> rpc)
	{
            res_num  = Long.parseLong(rpc.get(0).substring(22, 26).trim());
            res_name = rpc.get(0).substring(17, 20).trim();
            for(int i = 0; i < rpc.size(); i++)
            {
                ProteinAtom prt_atm = new ProteinAtom(rpc.get(i));
                pa.add(prt_atm);
            }
	}//Constructor ends here

        public int getNumberOfAtoms()
        {
            return pa.size();
        }
	public long getNumber()
	{
		return this.res_num;
	}
	public String getName()
	{
		return this.res_name;
	}
        public char getSingleLetterResidueName()
        {
            char ch;
            AminoAcidMap ama = new AminoAcidMap();
            ch = ama.getSingleLetterAminoAcid(this.res_name);
            ama.clearMemory();
            return ch;
        }
	public ProteinAtom getAtom(int i)
	{
		return pa.get(i);
	}
	public ProteinAtom getAtom(String at_nm)
	{
		ProteinAtom pr_at=null;
		for(int i = 0; i < pa.size(); i++)
		{
			if(pa.get(i).getName().compareTo(at_nm)==0)
			{
				pr_at = pa.get(i);
			}
		}
		return pr_at;
	}
}
