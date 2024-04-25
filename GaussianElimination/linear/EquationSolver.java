package linear;

import linear.algebra.GaussianElimination;

class EquationSolver
{

    public static double[][] stringsToDoubles(String[] args)
    {
        int rows = args.length;
        int cols = 0;
        boolean l = false;

        double[][] matrix = null;


        for(int i = 0; i < rows; i++)
        {
            String[] currentRow = args[i].split(",");
            
            if(!l)
            {
                cols = currentRow.length;
                l = true;
                matrix = new double[rows][cols];
            }

            
            for (int j = 0; j < currentRow.length; j++)
            {
                matrix[i][j] = Double.parseDouble(currentRow[j]);
            }
        }

        return matrix;

    }

    public static void main(String[] args)
    {
        
        double[][] m = stringsToDoubles(args);
        

        GaussianElimination matrix = new GaussianElimination(m.length,m[0].length, m);

        matrix.print();
        matrix.rowEchelonForm();
        matrix.print();
        matrix.backSubstitution();
        matrix.print();

        


    }
    
}