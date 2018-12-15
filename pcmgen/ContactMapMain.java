/**
 * @author: Sanket S. Desai
 * @date: 20.06.2015
 * @description: Class to calculate the distances between the residues in a given protein chain(s). It stores the distances in a form of matrix.
*/
package pcmgen;

import java.io.*;

public class ContactMapMain
{
	public static void stdout(String ln)
	{
		System.out.print(String.format("\n%s",ln));
	}
	public static void main(String[] args)
	{
		try{
			BufferedReader b=new BufferedReader(new InputStreamReader(System.in));
			stdout("~~~Welcome to PCMGen~~~");
			stdout("~~~An Interactive Protein Contact Matrix Generator~~~");
			stdout("~~~Author: Sanket S. Desai");
			stdout("~~~Copyrights: (C) 2018 Sanket S Desai");
			stdout("~~~Licence: GPL 3.0");
			stdout("Enter PDB file(s) to be loaded from the disc. (give comma-separated files with complete path)");
			stdout("#Example: '/home/user/3TFA.pdb' or '3TFA.pdb' if in current directory.");
			stdout(">");
			//System.out.print(String.format("%-5"));
			String fls = b.readLine();
			if(fls.length()==0)
			{
				System.err.println("Enter a valid PDB file with complete path.");
				System.exit(0);
			}
			String[] files = fls.split(",");
			if(files.length>2)
			{
				System.err.println("Multiple files loaded. Maximum two PDB files allowed.");
				System.exit(0);
			}
			else if(files.length==1)
			{
				ProteinStructure ps= new ProteinStructure(files[0].trim());
				System.out.println("Protein structure " +files[0].trim()+" loaded in PCMGen...");
				int c1=1, c2=1;//Chain1 and Chain2 for PCMGen
				stdout("Enter first chain to be loaded (default=1) > ");
				String ch=b.readLine();
				if(ch.length()>0)
					c1 = Integer.parseInt(ch)-1;
				System.out.print("Enter second chain to be loaded (default=1) > ");
				ch=b.readLine();
				if(ch.length()>0)
					c2 = Integer.parseInt(ch)-1;
				String typ="CA";
				double cut=8.0;
				System.out.print("Do you wish to change the default PCMGen parameters?");
				stdout("Defaults: Atom type = 'CA' , Distance cutoff = '8.0'");
				stdout("Yes/No (y/n) >");
				ch=b.readLine();
				if(ch.length()==0){
					stdout("   Computing with default parameters...");
				}
				else
				{
					char c= ch.charAt(0);
					if(c=='y')
					{
						stdout("   Do you want to change the atom type to 'CB'? (y/n) >");
						ch= b.readLine();
						if(ch.length()>0)
						{
							c=ch.charAt(0);
							if(c=='y')
								typ="CB";
							else if(c=='n')
								typ="CA";
							else
								System.out.println("   Invalid type. Default restored !!");
						}
						System.out.print("   Enter distance cutoff >");
						ch=b.readLine();
						if(ch.length()==0)
							stdout("  No value entered. Default restored !!");
						else
							cut= Double.parseDouble(ch);
					}
					else if(c=='n'){
						stdout("   Computing with default parameters...");
					}
				}
				ContactMap cm = new ContactMap(ps.getChain(c1),ps.getChain(c2),typ,cut);
				Matrix rsmat = cm.getDistanceMatrix();
				stdout("Select the type of matrix to be generated (enter either 1, 2 or 3):");
				stdout(" 1: Contact Distance Matrix (default)");
				stdout(" 2: Contact Matrix");
				stdout(" 3: Contact Score Matrix");
				stdout(" >");
				ch=b.readLine();
				int tom=1;//Type of matrix 1,2,3
				if(ch.length()>0)
				{
					tom = Integer.parseInt(ch);
					if(tom!=1 && tom!=2 && tom!=3)
					{
						stdout("Invalid matrix type. Default restored !!");
						tom=1;
					}
				}
				if(tom==2)
					rsmat=cm.getContactMatrix();
				else if(tom==3)
					rsmat=cm.getContactScoreMatrix();
				System.out.print("Enter output file name (default='contmap.mat') > ");
				String ofn="contmap.mat";
				ch=b.readLine();
				if(ch.length()>0)
				{
					ofn=ch;
				}
				b.close();
				ProteinChain ch1=ps.getChain(c1);
				ProteinChain ch2=ps.getChain(c2);
				rsmat.toFile(ofn, ch1.getResidueSequenceAsList(), ch2.getResidueSequenceAsList());
				System.out.print("Output written to file : "+ ofn+".");
				stdout("Parameters:");
				stdout("\tProtein: "+ps.getId());
				stdout("\tChains: "+Integer.toString(c1)+" & "+ Integer.toString(c2));
				stdout("\tType of matrix: "+typ);
				stdout("\tCutoff: "+Double.toString(cut));
				stdout("\tOutput file: "+ofn);
				stdout("\n");
			}
			else if(files.length==2)
			{
				ProteinStructure ps= new ProteinStructure(files[0].trim());
				ProteinStructure ps2= new ProteinStructure(files[1].trim());
				System.out.println("Protein structure " +files[0].trim()+" & "+files[1].trim()+" loaded in PCMGen...");
				int c1=1, c2=1;//Chain1 and Chain2 for PCMGen
				stdout("Enter chain to be loaded for "+files[0]+" (default=1) > ");
				String ch=b.readLine();
				if(ch.length()>0)
					c1 = Integer.parseInt(ch)-1;
				System.out.print("Enter chain to be loaded for "+files[1]+" (default=1) > ");
				ch=b.readLine();
				if(ch.length()>0)
					c2 = Integer.parseInt(ch)-1;
				String typ="CA";
				double cut=8.0;
				System.out.print("Do you wish to change the default PCMGen parameters?");
				stdout("Defaults: Atom type = 'CA' , Distance cutoff = '8.0'");
				stdout("Yes/No (y/n) >");
				ch=b.readLine();
				if(ch.length()==0){
					stdout("   Computing with default parameters...");
				}
				else
				{
					char c= ch.charAt(0);
					if(c=='y')
					{
						stdout("   Do you want to change the atom type to 'CB'? (y/n) >");
						ch= b.readLine();
						if(ch.length()>0)
						{
							c=ch.charAt(0);
							if(c=='y')
								typ="CB";
							else if(c=='n')
								typ="CA";
							else
								System.out.println("   Invalid type. Default restored !!");
						}
						System.out.print("   Enter distance cutoff >");
						ch=b.readLine();
						if(ch.length()==0)
							stdout("  No value entered. Default restored !!");
						else
							cut= Double.parseDouble(ch);
					}
					else if(c=='n'){
						stdout("   Computing with default parameters...");
					}
				}
				ContactMap cm = new ContactMap(ps.getChain(c1),ps2.getChain(c2),typ,cut);
				Matrix rsmat = cm.getDistanceMatrix();
				stdout("Select the type of matrix to be generated (enter either 1, 2 or 3):");
				stdout(" 1: Contact Distance Matrix (default)");
				stdout(" 2: Contact Matrix");
				stdout(" 3: Contact Score Matrix");
				stdout(" >");
				ch=b.readLine();
				int tom=1;//Type of matrix 1,2,3
				if(ch.length()>0)
				{
					tom = Integer.parseInt(ch);
					if(tom!=1 && tom!=2 && tom!=3)
					{
						stdout("Invalid matrix type. Default restored !!");
						tom=1;
					}
				}
				if(tom==2)
					rsmat=cm.getContactMatrix();
				else if(tom==3)
					rsmat=cm.getContactScoreMatrix();
				System.out.print("Enter output file name (default='contmap.out') > ");
				String ofn="contmap.out";
				ch=b.readLine();
				if(ch.length()>0)
				{
					ofn=ch;
				}
				b.close();
				ProteinChain ch1=ps.getChain(c1);
				ProteinChain ch2=ps.getChain(c2);
				rsmat.toFile(ofn, ch1.getResidueSequenceAsList(), ch2.getResidueSequenceAsList());
				System.out.print("Output written to file : "+ ofn+".");
				stdout("Parameters:");
				stdout("\tProteins: "+ps.getId()+" & "+ps2.getId());
				stdout("\tChains: "+Integer.toString(c1)+" & "+ Integer.toString(c2));
				stdout("\tType of matrix: "+typ);
				stdout("\tCutoff: "+Double.toString(cut));
				stdout("\tOutput file: "+ofn);
				stdout("\n");
			}
		}
		catch(FileNotFoundException fnf)
		{
			System.err.println("Please enter a valid PDB file name with complete path.");
			System.exit(0);
		}
		catch(IOException ie)
		{
			System.err.println("Please enter valid arguments!!");
			System.err.println("Refer to 'Manuals' of the program for more details!");
		}
		catch(Exception e)
		{ e.printStackTrace(); }
	}
}
