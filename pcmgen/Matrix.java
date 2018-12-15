/**
 * @author Sanket S. Desai
 * @date 10.02.2015
 * @description Matrix data strcture to hold M x N double (numeric) matrix
*/
package pcmgen;

import java.io.*;
import java.util.*;

public class Matrix {
    //private final int M;             // number of rows
   // private final int N;             // number of columns
    final int M;             // number of rows
    final int N;             // number of columns
    double [][] data;   // M-by-N array

    // create M-by-N matrix of 0's
    public Matrix(int M, int N) {
        this.M = M;
        this.N = N;
        data = new double[M][N];
    }
        public void init()
        {
                for(int r=0; r < this.M; r++)
                {
                        for(int c=0; c< this.N; c++)
                        {
                                this.data[r][c]=0;
                        }
                }
        }

	public double get(int i, int j)
	{
		return this.data[i][j];
	}
	public void set(int i, int j, double val)
	{
		this.data[i][j] = val;
	}
	public int getNumberOfRows()
	{
		return this.M;
	}
	public int getNumberOfColumns()
	{
		return this.N;
	}
    // create matrix based on 2d array
    public Matrix(double [][] data) {
        M = data.length;
        N = data[0].length;
        this.data = new double[M][N];
        for (int i = 0; i < M; i++)
            for (int j = 0; j < N; j++)
                    this.data[i][j] = data[i][j];
    }

    // create and return a random M-by-N matrix with values between 0 and 1
    public void show() {
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++)
                System.out.printf("%9f", data[i][j]);
            System.out.println();
        }
    }

	public void toFile(String fn)
	{
		try{
			FileWriter fw = new FileWriter(fn);
			for (int i = 0; i < M; i++) {
			    for (int j = 0; j < N; j++) {
				fw.write(String.format("%9f\t",data[i][j]));
				}
			    fw.write("\n");
			}
			fw.close();
		}
		catch(Exception e)
		{
			System.err.println("File "+fn+" not writable, please check permissions.");
		}
	}
  public void toFile(String fn,List<String> rownames, List<String> colnames)
	{
		try{
			FileWriter fw = new FileWriter(fn);
      for(String cn: colnames){
        fw.write( String.format("\t%s",cn));
      }
      fw.write("\n");
			for (int i = 0; i < M; i++) {
        String rn=rownames.get(i);
        fw.write(String.format("%s\t", rn));
			    for (int j = 0; j < N; j++) {
				fw.write(String.format("%9f\t",data[i][j]));
				}
			    fw.write("\n");
			}
			fw.close();
		}
		catch(Exception e)
		{
			System.err.println("File "+fn+" not writable, please check permissions.");
		}
	}
    public double[][] get2dMatrix()
    {
    	return this.data;
    }
}
