/**
 * @author: Sanket S. Desai
 * @date: 20.06.2015
 * @description: Class to calculate the distances between the residues in a given protein chain(s). It stores the distances in a form of matrix.
*/
package pcmgen;
public class ContactMap
{
	Matrix cm_;
	String type_ ;
	String seq1, seq2;
	double cutoff_;
	public ContactMap(ProteinChain ch1, ProteinChain ch2, String type, double cutoff)
	{
		this.type_=type;
		this.cutoff_=cutoff;
		this.cm_ = new Matrix(ch1.getNumberOfResidues(), ch2.getNumberOfResidues());
		this.cm_.init();
		this.seq1 = ch1.getResidueSequence();
		this.seq2 = ch2.getResidueSequence();
		if(this.type_.equals("CB")==true)
		{
			for(int i=0; i < ch1.getNumberOfResidues(); i++)
			{
				ProteinResidue pr1 = ch1.getResidue(i);
				for(int j=0; j < ch2.getNumberOfResidues(); j++)
				{
					ProteinResidue pr2 = ch2.getResidue(j);
					if(pr1.getName().equals("GLY")==true && pr2.getName().equals("GLY")==true)
					{
						
						cm_.set(i, j, Calculation.getDistance(pr1.getAtom("CA"),pr2.getAtom("CA")));
					}
					else if(pr2.getName().equals("GLY")==true && pr1.getName().equals("GLY")==false)
					{
						cm_.set(i, j, Calculation.getDistance(pr1.getAtom(type),pr2.getAtom("CA")));
					}
					else if(pr1.getName().equals("GLY")==true && pr2.getName().equals("GLY")==false)
					{
						cm_.set(i, j, Calculation.getDistance(pr1.getAtom("CA"),pr2.getAtom(type)));
					}
					else if(pr1.getName().equals("GLY")==false && pr2.getName().equals("GLY")==false){
						cm_.set(i, j, Calculation.getDistance(pr1.getAtom(type),pr2.getAtom(type)));
					}
				}
			}
		}
		else if(this.type_.equals("CA")==true)
		{
			for(int i=0; i < ch1.getNumberOfResidues(); i++)
			{
				ProteinResidue pr1 = ch1.getResidue(i);
				for(int j=0; j < ch2.getNumberOfResidues(); j++)
				{
					ProteinResidue pr2 = ch2.getResidue(j);
					cm_.set(i, j, Calculation.getDistance(pr1.getAtom(type),pr2.getAtom(type)));
				}
			}
		}
		else{
			System.err.println("Illegal atom type!! Should be either CA or CB");
			System.exit(0);
		}
	}
	public ContactMap(ProteinChain ch1, ProteinChain ch2)
	{
		this.seq1=ch1.getResidueSequence();
		this.seq2=ch2.getResidueSequence();
		this.type_="CA";
		this.cutoff_=8.0;
		this.cm_ = new Matrix(ch1.getNumberOfResidues(), ch2.getNumberOfResidues());
		this.cm_.init();
		for(int i=0; i < ch1.getNumberOfResidues(); i++)
		{	
			ProteinResidue pr1 = ch1.getResidue(i);
			for(int j=0; j < ch2.getNumberOfResidues(); j++)
			{
				ProteinResidue pr2 = ch2.getResidue(j);
				cm_.set(i, j, Calculation.getDistance(pr1.getAtom(this.type_),pr2.getAtom(this.type_)));
			}
		}
	}
	public ContactMap(ProteinChain ch, String type, double cutoff, boolean adj)
	{
		this.seq1=ch.getResidueSequence();
		this.seq2=this.seq1;
		this.type_=type;
		this.cutoff_=cutoff;
		this.cm_ = new Matrix(ch.getNumberOfResidues(), ch.getNumberOfResidues());
		this.cm_.init();
		if(this.type_.equals("CB")==true)
		{
			for(int i=0; i < ch.getNumberOfResidues(); i++)
			{
				ProteinResidue pr1 = ch.getResidue(i);
				for(int j=0; j < ch.getNumberOfResidues(); j++)
				{
					ProteinResidue pr2 = ch.getResidue(j);
					if(pr1.getName().equals("GLY")==true && pr2.getName().equals("GLY")==true)
					{
						
						cm_.set(i, j, Calculation.getDistance(pr1.getAtom("CA"),pr2.getAtom("CA")));
					}
					else if(pr2.getName().equals("GLY")==true && pr1.getName().equals("GLY")==false)
					{
						cm_.set(i, j, Calculation.getDistance(pr1.getAtom(type),pr2.getAtom("CA")));
					}
					else if(pr1.getName().equals("GLY")==true && pr2.getName().equals("GLY")==false)
					{
						cm_.set(i, j, Calculation.getDistance(pr1.getAtom("CA"),pr2.getAtom(type)));
					}
					else{
						cm_.set(i, j, Calculation.getDistance(pr1.getAtom(type),pr2.getAtom(type)));
					}
				}
			}
		}
		else if(this.type_.equals("CA")==true)
		{
			for(int i=0; i < ch.getNumberOfResidues(); i++)
			{
				ProteinResidue pr1 = ch.getResidue(i);
				for(int j=0; j < ch.getNumberOfResidues(); j++)
				{
					ProteinResidue pr2 = ch.getResidue(j);
					cm_.set(i, j, Calculation.getDistance(pr1.getAtom(type),pr2.getAtom(type)));
				}
			}
		}
		else{
			System.err.println("Illegal atom type!! Should be either CA or CB");
			System.exit(0);
		}
	}
	public ContactMap(ProteinChain ch, String type, double cutoff)
	{
		this.seq1=ch.getResidueSequence();
		this.seq2=this.seq1;
		this.type_=type;
		this.cutoff_=cutoff;
		this.cm_ = new Matrix(ch.getNumberOfResidues(), ch.getNumberOfResidues());
		this.cm_.init();
		if(this.type_.equals("CB")==true)
		{
			for(int i=0; i < ch.getNumberOfResidues(); i++)
			{
				ProteinResidue pr1 = ch.getResidue(i);
				for(int j=0; j < ch.getNumberOfResidues(); j++)
				{
					ProteinResidue pr2 = ch.getResidue(j);
					if(pr1.getName().equals("GLY")==true && pr2.getName().equals("GLY")==true)
					{
						
						cm_.set(i, j, Calculation.getDistance(pr1.getAtom("CA"),pr2.getAtom("CA")));
					}
					else if(pr2.getName().equals("GLY")==true && pr1.getName().equals("GLY")==false)
					{
						cm_.set(i, j, Calculation.getDistance(pr1.getAtom(type),pr2.getAtom("CA")));
					}
					else if(pr1.getName().equals("GLY") && pr2.getName().equals("GLY")==false)
					{
						cm_.set(i, j, Calculation.getDistance(pr1.getAtom("CA"),pr2.getAtom(type)));
					}
					else{
						cm_.set(i, j, Calculation.getDistance(pr1.getAtom(type),pr2.getAtom(type)));
					}
				}
			}
		}
		else if(this.type_.equals("CA")==true)
		{
			for(int i=0; i < ch.getNumberOfResidues(); i++)
			{
				ProteinResidue pr1 = ch.getResidue(i);
				for(int j=0; j < ch.getNumberOfResidues(); j++)
				{
					ProteinResidue pr2 = ch.getResidue(j);
					cm_.set(i, j, Calculation.getDistance(pr1.getAtom(type),pr2.getAtom(type)));
				}
			}
		}
		else{
			System.err.println("Illegal atom type!! Should be either CA or CB");
			System.exit(0);
		}
	}
	public ContactMap(ProteinChain ch)
	{
		this.seq1=ch.getResidueSequence();
		this.seq2=this.seq1;
		this.type_="CA";
		this.cutoff_=8.0;
		this.cm_ = new Matrix(ch.getNumberOfResidues(), ch.getNumberOfResidues());
		this.cm_.init();
		for(int i=0; i< ch.getNumberOfResidues(); i++)
		{
			ProteinResidue pr1 = ch.getResidue(i);
			for(int j=0; j< ch.getNumberOfResidues(); j++)
			{
				ProteinResidue pr2 = ch.getResidue(j);
				cm_.set(i, j, Calculation.getDistance(pr1.getAtom(this.type_),pr2.getAtom(this.type_)));
			}
		}
	}
	//Functions
	public Matrix getDistanceMatrix()
	{
		return this.cm_;
	}
	public Matrix getContactMatrix()
	{
		Matrix con= new Matrix(this.cm_.getNumberOfRows(), this.cm_.getNumberOfColumns());
		con.init();
		for(int i =0; i< this.cm_.getNumberOfRows(); i++ )
			for(int j=0; j<this.cm_.getNumberOfColumns(); j++)
				if(cm_.get(i,j)< (this.cutoff_+0.001) )
					con.set(i,j,1);	
		return con;
	}

	/*
		Contact Score = 1 - ( Distance / cutoff )
	*/
	public Matrix getContactScoreMatrix()
	{
		Matrix con = new Matrix(this.cm_.getNumberOfRows(), this.cm_.getNumberOfColumns());
		con.init();
		for(int i=0; i<this.cm_.getNumberOfRows(); i++)
		{
			for(int j=0; j< this.cm_.getNumberOfColumns(); j++)
			{
				double score = 1.000 - ( cm_.get(i,j) / (this.cutoff_ + 0.001) );
				if(score>0)
					con.set(i,j, score);
			}
		}	
		return con;
	}
	/* Boolean argument to either ignore or consider adjacency of amino acids*/
	public Matrix getContactScoreMatrix(boolean adj)
	{
		Matrix con = new Matrix(this.cm_.getNumberOfRows(), this.cm_.getNumberOfColumns());
		con.init();
		for(int i=0; i<this.cm_.getNumberOfRows(); i++)
		{
			for(int j=0; j< this.cm_.getNumberOfColumns(); j++)
			{
				if(adj==true && Math.abs(i-j)>2)
				{
					double score = 1.000 - ( cm_.get(i,j) / (this.cutoff_ + 0.001) );
					if(score>0)
						con.set(i,j, score);
				}
				else
				{
					double score = 1.000 - ( cm_.get(i,j) / (this.cutoff_ + 0.001) );
					if(score>0)
						con.set(i,j, score);
				}
			}
		}	
		return con;
	}
	public String getSequence1()
	{
		return this.seq1;
	}
	public String getSequence2()
	{
		return this.seq2;
	}
}
