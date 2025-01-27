package all;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.Stack;

public class DfsForAllDirectoriesOnHardDisk {

	public static void main(String[] args) {
		PrintStream fileOutput = null;
		String outputFile = "DirectoriesByDFS.txt";
		String hardDiskLetter;
		Scanner input = new Scanner(System.in);
		System.out.print("Please enter path for directory , which you want to see details : ");
		while (true) {
			hardDiskLetter = input.nextLine();
			if (chackingPath(hardDiskLetter)) {
				break;
			}
			System.out.println("Invalid path, please enter valid path :");
		}
		File file = new File(hardDiskLetter);
		Stack<File> stack = new Stack<File>();
		File[] listOfFiles = null;
		stack.add(file);
		try {
			fileOutput = new PrintStream(outputFile, "windows-1251");
			while (!stack.isEmpty()) {
				file = stack.pop();
				fileOutput.println(file);
				listOfFiles = file.listFiles();
				if (listOfFiles != null) {
					for (int i = listOfFiles.length - 1; i >= 0 ; i--) {
						if (listOfFiles[i].isDirectory()) {
							stack.add(listOfFiles[i]);
						}
					}
				}
			}
		} catch (FileNotFoundException fnfe) {
			System.err.println(fnfe.getMessage());
		} catch (UnsupportedEncodingException uee) {
			System.err.println(uee.getMessage());
		} catch (NullPointerException npe) {
			
		} finally {
			if (null != fileOutput) {
				fileOutput.close();
			}
		}
		System.out.println("File with name \"" + outputFile + "\" is created at program same directory.");
		try {
			System.out.println("Press Enter to close.");
	        System.in.read();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
		input.close();
	}

	private static boolean chackingPath(String pathName) {
		try {
			Path path = Paths.get(pathName);
			path.toRealPath();		
		} catch (InvalidPathException | IOException e) {
			return false;
		}
		return true;
	}	
}	