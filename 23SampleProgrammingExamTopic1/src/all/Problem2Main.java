package all;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;

public class Problem2Main {
	
	public static void main(String[] args) {
		Scanner reader = null;
	    PrintStream writer = null;
	    try {
	    	reader = new Scanner(new File("Tests2\\test.025.in.txt"), "windows-1251");
		    int n = Integer.parseInt(reader.nextLine());
		    Problem2LabirintAllPaths labirint = new Problem2LabirintAllPaths(n);

		    for (int i = 0; i < n; i++) {
		        String line = reader.nextLine();
		        for (int j = 0; j < n; j++) {
		        	labirint.setMatrixElement(i, j, line.charAt(j));
		            if (line.charAt(j) == '*') {
		                labirint.setStartCell(new Problem2Cell(i, j));
		            }
		        }
		    }
		  
		    labirint.findingAllPaths();
		    int numberOfExits = labirint.findExits();
		    writer = new PrintStream(new File("Tests2\\test.025.out.txt"), "windows-1251");
		    writer.println(numberOfExits);
	    } catch (FileNotFoundException fnfe) {
        	System.err.println(fnfe.getMessage());
	    } catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
	    } finally {
	    	if (!(reader == null)) {
        		reader.close();
        	}
        	if (!(writer == null)) {
            	writer.close();
        	}
	    }
		
	}
}
