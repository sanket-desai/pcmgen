/**
 * @author Sanket S. Desai
 * @date 29.07.2012
 * @description An abstraction of protein chain
 */
package pcmgen;
import java.util.*;

public class ProteinChain {

	ArrayList<ProteinResidue> pr = new ArrayList<ProteinResidue>();
	char chainId;
	public ProteinChain(ArrayList<String> prot_ch)
	{
		this.chainId = prot_ch.get(0).charAt(21); //Extract chainId from the first line. Please check the index passed to charAt
		long []aa = new long[prot_ch.size()];
		long []uaa;
		for(int i = 0; i < aa.length; i++)
		{
			aa[i] = Long.parseLong(prot_ch.get(i).substring(22, 27).trim());
		}
		uaa = Utilities.getUniqueElements(aa);
		for(int j = 0; j < uaa.length; j++)
		{
			ArrayList<String> rpc = new ArrayList<String>();
			for(int k = 0; k < prot_ch.size(); k++)
			{
				if(uaa[j] == aa[k])
				{
					rpc.add(prot_ch.get(k));
				}
			}
			this.pr.add(new ProteinResidue(rpc));
		}
	}//Constructor ends here

	public char getChainId()
	{
		return chainId;
	}
	public int getNumberOfResidues()
	{
		return pr.size();
	}
	public ProteinResidue getResidue(int i)
	{
		return pr.get(i);
	}
	public ProteinResidue getResidue(String s)
	{
		ProteinResidue pr_res = null;
		for(int i = 0; i < pr.size(); i++)
		{
			if(pr.get(i).getName().compareTo(s) == 0)
			{
				pr_res = pr.get(i);
			}
		}
		return pr_res;
	}
  public String getResidueSequence()
  {
  	String seq = "";
    AminoAcidMap aam = new AminoAcidMap();
    for(int i = 0; i < pr.size(); i++)
    {
    	seq += aam.getSingleLetterAminoAcid(pr.get(i).getName());
    }
    aam.clearMemory();
    return seq;
  }
	List<String> getResidueSequenceAsList()
	{
		List<String> rsl=new ArrayList<String>();
		String seq = "";
    AminoAcidMap aam = new AminoAcidMap();
    for(int i = 0; i < pr.size(); i++)
    {
    	seq = aam.getSingleLetterAminoAcid(pr.get(i).getName())+"_"+Integer.toString(i+1);
			rsl.add(seq);
    }
    aam.clearMemory();
    return rsl;
	}
}
