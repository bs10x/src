class Main {
    public static void main(String[] args) {
      
        // Uncomment this line if you want to read from a file
        // In.open("public/example.in");
        // Out.compareTo("public/example.out");

        int m = In.readInt(); //Read the number of sequences
        
        
        for(int i=0;i<m;i++){
          
          int n = In.readInt(); // Read the number of charachters of the i-th sequence 
          String SA=In.readString();        // Read A as a string
          
          char[] A=new char[n];
          for(int j=0;j<n;j++){
            A[j]=SA.charAt(j);
          }

          Out.println(Palindromic_Edit_Distance(A,n));
        }
        
       
                
        // Uncomment this line if you want to read from a file
        // In.close();
   
    }
    
    public static int Palindromic_Edit_Distance(char[] A, int n)
    {
      
        //  A:      The input sequence
        //  n:      The length of A;
        
        int [][]DP_table=new int[n][n];

        
    
        return DP_table[0][n-1];
    
    }

   
}