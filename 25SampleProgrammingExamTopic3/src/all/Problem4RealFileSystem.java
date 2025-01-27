package all;

import problem3FileSystem.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributeView;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Problem4RealFileSystem {

	public static void main(String[] args) throws ParseException, IOException, Exception {
		Scanner input = new Scanner(System.in);
		String rootDirectory;
		System.out.print("Please enter path for directory , which you want to see details : ");
		while (true) {
			rootDirectory = input.nextLine();
			if (chackingPath(rootDirectory)) {
				break;
			}
			System.out.println("Invalid path, please enter valid path :");
		}
		File file = new File(rootDirectory);
		Directory rootDir = new Directory(file.getName(), new Date(file.lastModified()));
		Device fileSystem = new Device("File System", rootDir);
		fileSystemTraversal(file, rootDir);

		System.out.println(fileSystem);
		input.close();
	}

	private static boolean chackingPath(String rootDirectory) {
		try {
			Path path = Paths.get(rootDirectory);
			path.toRealPath();		
		} catch (InvalidPathException | IOException e) {
			return false;
		}
		return true;
	}

	static void fileSystemTraversal(File currentDirectoryInfo, Directory currentDirectory) throws ParseException, IOException, Exception {
        for (File file : currentDirectoryInfo.listFiles()) {
        	if (file.isFile()) {
	            currentDirectory.addFile(new BinaryFile(file.getName(), new SimpleDateFormat("dd/MM/yyyy HH:mm:ss")
	            .parse(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(getCreationTime(file).toMillis())), new Date(file.lastModified()), null));
        	} else {
        		Directory dir = new Directory(file.getName(), new Date(file.lastModified()));
        		currentDirectory.addDirectory(dir);
        		fileSystemTraversal(file, dir);
        	}
        }
    }
	
	public static FileTime getCreationTime(File file) throws IOException {
	    Path p = Paths.get(file.getAbsolutePath());
	    BasicFileAttributes view
	        = Files.getFileAttributeView(p, BasicFileAttributeView.class)
	                    .readAttributes();
	    FileTime fileTime=view.creationTime();
	    //  also available view.lastAccessTine and view.lastModifiedTime
	    return fileTime;
	  }
}
