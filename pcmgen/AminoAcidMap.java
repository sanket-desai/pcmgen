/**
 * @author Sanket S. Desai
 * @date 29.07.2012
 * @description A library class to store Amino acide 3 letter, 1 letter codes
 */
 package pcmgen;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
public class AminoAcidMap {

    List<String> threeLetterAminoAcids = new ArrayList<String>(24);
    char [] singleLetterAminoAcids = new char[24];

    public AminoAcidMap()
    {
        String[] aa3 = {"ALA","ARG","ASN","ASP","CYS","GLN","GLU","GLY","HIS","ILE","LEU","LYS","MET","PHE","PRO","SER","THR","TRP","TYR","VAL","ASX","GLX","UNK","END" };
        char[] aa1 =  {'A','R','N','D','C','Q','E','G','H','I','L','K','M','F','P','S','T','W','Y','V','B','Z','_','*'};
        threeLetterAminoAcids = Arrays.asList(aa3);
        singleLetterAminoAcids = aa1;
    }

    public char getSingleLetterAminoAcid(String amino_acid)
    {
        char a = '_';
        for(int i = 0; i < threeLetterAminoAcids.size(); i++)
         {
            if(threeLetterAminoAcids.get(i).compareTo(amino_acid)==0)
            {
                a = singleLetterAminoAcids[i];
            }
         }
      return a;
    }

    void clearMemory()
    {
        try {
            finalize();
        } catch (Throwable ex) {
            Logger.getLogger(AminoAcidMap.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


}
