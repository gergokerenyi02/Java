package linear.algebra;

public class GaussianElimination
{
    // Adattagok

    private double[][] matrix;
    
    private int cols;
    private int rows;

    // Getters

    public int getCols()
    {
        return cols;
    }

    public int getRows()
    {
        return rows;
    }

    public double[][] getMatrix()
    {
        return matrix;
    }


    // Kiegészítés

    private void checkMatrixDimensions(double[][] matrix)
    {
        if(matrix.length != rows || matrix[0].length != cols)
        {
            throw new IllegalArgumentException("Az uj matrix sor/oszlopszama nem egyezik meg az elozovel.");
        }
        
    }

    // Matrix Setter   

    public void setMatrix(double[][] matrix)
    {
        
        checkMatrixDimensions(matrix);
        
        for(int i = 0; i < rows; i++)
        {
            for(int j = 0; j < cols; j++)
            {
                this.matrix[i][j] = matrix[i][j];
            }
        }

        
    } 

    
    // Konstruktor

    public GaussianElimination(int rows, int cols, double[][] matrix)
    {
        this.rows = rows;
        this.cols = cols;
        this.matrix = new double[rows][cols];

        // Rows ~ sor
        // Cols ~ oszlop

        for(int i = 0; i < rows; i++)
        {
            for(int j = 0; j < cols; j++)
            {
                this.matrix[i][j] = matrix[i][j];
            }
        }

    }

    private int argMax(int row, int col)
    {
        double maxElement = Math.abs(matrix[row][col]);
        int maxIndex = row;

        for(int i = row + 1; i < rows; i++)
        {
            double currentElement = Math.abs(matrix[i][col]);

            if(currentElement > maxElement)
            {
                maxElement = currentElement;
                maxIndex = i;
            }

        }

        return maxIndex;


    }


    private void swapRows(int row1, int row2)
    {
        for(int j = 0; j < cols; j++)
        {
            double n_temp = matrix[row1][j];
            matrix[row1][j] = matrix[row2][j];
            matrix[row2][j] = n_temp;
        }
    }

    private void multiplyAndAddRow(int addRow, int mulRow, int colIndex)
    {
        // addRow = i
        // mulRow = h
        // colIndex = k

        double f = matrix[addRow][colIndex] / matrix[mulRow][colIndex];

        matrix[addRow][colIndex] = 0;

        for(int j = colIndex + 1; j < cols; j++)
        {
            matrix[addRow][j] = matrix[addRow][j] - matrix[mulRow][j] * f;
        }

    }

    private void multiplyRow(int row, int col)
    {
        double intersect = matrix[row][col];

        for(int j = 0; j < cols; j++)
        {
            matrix[row][j] = matrix[row][j] / intersect;
        }
    }

    public void rowEchelonForm()
    {
        int h = 0; // sor
        int k = 0; // oszlop

        while(h < rows && k < cols)
        {
            int i_max = argMax(h,k);

            if(matrix[i_max][k] == 0)
            {
                k++;
            }
            else 
            {
                swapRows(h, i_max);

                for(int i = h + 1; i < rows; i++)
                {
                    multiplyAndAddRow(i, h, k);
                }

                multiplyRow(h,k);
                h++;
                k++;
            }

        }

    }


    public void print()
    {
        char tokens[] = {'x','y','z','a','b','c','d','e','f','g','h','j','k','l','m','n','p','q','r'};

        for(int i = 0; i < rows; i++)
        {
            for(int j = 0; j < cols - 1; j++)
            {
                if(matrix[i][j] < 0)
                {
                    System.out.print(matrix[i][j] + "" + tokens[j]);
                } else
                {
                    System.out.print("+" + matrix[i][j] + "" + tokens[j]);
                }
                
            }

            System.out.print("=" + matrix[i][cols - 1]);
            System.out.println();
        }

    }

    public void backSubstitution()
    {
        for(int i = rows - 1; i >= 0; i--)
        {
            if(matrix[i][i] == 0)
            {
                throw new IllegalArgumentException("DiagonalElem = 0");
            } else
            {
                for(int j = i - 1; j >= 0; j--)
                {
                    multiplyAndAddRow(j, i, i);
                }
            }


        }
    }




}