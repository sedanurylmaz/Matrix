//-----------------------------------------------------
// Title: CMPE 223
// Name/Surname: Sedanur YILMAZ
// ID: 12049029478
// Section: 1
// Assignment: 1
// Description: :We create 4 txt files in 
//accordance with the instructions 
//given in the question and ensure 
//that these files are read in our code. Then, we get the name of the txt file from the user, 
//then we decide on the type of this file.We call it type1 if 
//it contains at least three consecutive 
//positive numbers, type2 if it contains 
//at least one negative number followed by another negative 
//number, and type 3 if it satisfies both 
//-----------------------------------------------------
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) 
	//--------------------------------------------------------
	 // Summary: It takes the name of the matrix file from the user, reads the received file, and then determines the matrix's type.
	 // Precondition: fName is a String,matrix is an integer array,type is an integer
	 // Postcondition: The value of fName depends on the value the user enters. matrix value depends on readMatrixFromFile(fName).
	// We can say that it depends on fName value. The value of type depends on the getMatrixType(matrix) method. We can say it depends on the matrix value.
	 //--------------------------------------------------------
	{
		
	Scanner scanner = new Scanner(System.in);
	System.out.println("Enter the file name:");
	String fName = scanner.nextLine();
	scanner.close();
	
	
	int[][] matrix = readMatrixFromFile(fName);
    int type = getMatrixType(matrix);
    
    switch (type) {
        case 1:
            System.out.println("It is a type 1 matrix.");
            break;
        case 2:
            System.out.println("It is a type 2 matrix.");
            break;
        case 3:
            System.out.println("It is a type 3 matrix.");
            break;
        default:
            System.out.println("It is not one of these types.");
            break;
    }
}
		 static void connectionLinkedList(int[][] matrix) 
		//--------------------------------------------------------
		 // Summary: Here I connected the matrix structure as requested by us in the 
		 // question.
		 // Precondition: l1,l2,l3 and l4 are an object from myLinkedList
		 // Postcondition: The value of the variable is set.
		 //--------------------------------------------------------
		 {
			
			myLinkedList l1 = new myLinkedList(); //postcondition : for l1
			myLinkedList l2 = new myLinkedList(); //postcondition : for l2
			myLinkedList l3 = new myLinkedList(); //postcondition : for l3
			myLinkedList l4 = new myLinkedList(); //postcondition : for l4
			
	      for(int j = 0; j < matrix.length; j++) {
				
	    	    l1.add(matrix[0][j]);
				l2.add(matrix[1][j]);
				l3.add(matrix[2][j]);
				l4.add(matrix[3][j]);
			}
	      
	      l1.head.down = l2.head;
	      l1.head.down.down = l2.head.down = l3.head;
	      l1.head.down.down.down = l3.head.down = l4.head;
		}
		
		
		 
		 private static int[][] readMatrixFromFile(String fileName) 
		//--------------------------------------------------------
		 // Summary: It is designed to read txt file. We take the name of the file and read the file accordingly. We add the values in the file to the integer array matrix.
		 // Precondition: matrix is an integer array,file is an object from File, numRows and numCols are integer,j is an integer,line is a String,values a String array
		 // Postcondition: The initial values of numRows and numCols are 0 but they will be incremented up to the length of the row and column of the numbers written in the file,
		 // The value of matrix is null at the beginning, the value of the matrix will be filled according to the following operations.
		 //The initial value of j is 0, its value will increase by one. j is a variable that helps us fill in the columns.line reads other parts of files, except for spaces.values separates line by \\s+
		 // Their values will be change.
		 //--------------------------------------------------------
		 {
		        int[][] matrix = null;
		        try {
		            File file = new File(fileName); // postcondition : for file
		            Scanner scanner = new Scanner(file);
		            int numRows = 0;
		            int numCols = 0;
		            while (scanner.hasNextLine()) {
		                String line = scanner.nextLine().trim();
		                if (line.isEmpty()) {
		                    continue;
		                }
		                String[] values = line.split("\\s+");
		                if (matrix == null) {
		                    numCols = values.length;
		                    matrix = new int[numCols][];
		                }
		                matrix[numRows] = new int[numCols];
		                for (int j = 0; j < numCols; j++) {
		                    matrix[numRows][j] = Integer.parseInt(values[j]);
		                }
		                numRows++;
		            }
		            scanner.close();
		        } catch (FileNotFoundException e) {
		            System.out.println("File not found: " + fileName);
		            System.exit(1);
		        }
		        return matrix;
		    }
		    
		    private static int getMatrixType(int[][] matrix) 
		  //--------------------------------------------------------
		    // Summary: Finds the type of matrix according to the rules given in the question.
		    // Precondition: hasConsecutivePositiveNumbers and hasNegativeNumbers are boolean values,numRows and numCols are integer,i and j are integer,
		    //current,next,next_next are integer
		    // Postcondition: The value of hasConsecutivePositiveNumbers and hasNegativeNumbers is initially false. Their values may vary.
		    // numRows and numCols keep the number of columns and rows of the matrix.The i and j values are initially 0. They help with operations related to rows and columns.
		    //
		    //--------------------------------------------------------
		    {
		        boolean hasConsecutivePositiveNumbers = false;
		        boolean hasNegativeNumbers = false;
		        int numRows = matrix.length;
		        int numCols = matrix[0].length;
		        for (int i = 0; i < numRows; i++) {
		            for (int j = 0; j < numCols - 2; j++) {
		                int current = matrix[i][j]; // postcondition : for current
		                int next = matrix[i][j+1]; // postcondition : for next
		                int next_next = matrix[i][j+2]; // postcondition : for next_next
		                if (current>0 && next>0 && current+1==next && next_next>0 && next_next==next+1) {
		                    hasConsecutivePositiveNumbers = true;
		                } 
		            }
		        }
		        for (int i = 0; i < numRows; i++) {
		            for (int j = 0; j < numCols - 1; j++) {
		                int current = matrix[i][j];
		                int next = matrix[i][j+1];
		                if (current < 0 && next < 0) {
		                    hasNegativeNumbers = true;
		                }
		            }
		        }
		        
		        if (hasConsecutivePositiveNumbers && hasNegativeNumbers) {
		            return 3;
		        } else if (hasConsecutivePositiveNumbers) {
		            return 1;
		        } else if (hasNegativeNumbers) {
		            return 2;
		        } else {
		            return 0;
		        }
		    }
	
		static class myLinkedList {
			
			Node head = null;
			Node tail = null;
			
		    void add(int data) 
		    //--------------------------------------------------------
		    // Summary: It adds elements to the linked list.
		    // Precondition: temp is a Node
		    // Postcondition: Node temp = new Node(data);
		    //--------------------------------------------------------
		    {
				
				Node temp = new Node(data);
				
				if(head == null) 
				{
					temp.next = null;
					head = temp;
					tail = temp;
				}
				else 
				{	
					temp.next = null;
					tail.next = temp;
					tail = temp;
				}
			}
			  
		    void print() 
		  //--------------------------------------------------------
		    // Summary: Prints the data of the added elements.
		    // Precondition: temp is a Node
		    // Postcondition: The initial value of temp is head, 
		    //but it will traverse the entire linked list, so its value will change.
		    //--------------------------------------------------------
		    {
		    	
		    	Node temp = head;
		    	
		    	while(temp.next != null) {
		    		
		    		System.out.print(temp.data + "");
		    		temp = temp.next;
		    	}
		    }
		}
		
		static class Node {
			//Description: this class is created to provide connectivity in linked list
			Node next;
			Node down;
			int data;
			
			Node(int data) {
				
				data = data;
				next = null;
				down = null;
			}
		}
}


