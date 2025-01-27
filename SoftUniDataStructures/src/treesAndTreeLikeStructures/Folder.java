package treesAndTreeLikeStructures;

import java.util.ArrayList;
import java.util.List;

public class Folder {
	
	private String name;
	private List<File> files;
	private List<Folder> childFolders;
	private long size;
		
	public Folder(String name) {
		this.name = name;
		this.files = new ArrayList<File>();
		this.childFolders = new ArrayList<Folder>();
	}
	
	public void addFile(File file) {
		this.files.add(file);
	}
	
	public void addFolder(Folder folder) {
		this.childFolders.add(folder);
	}
	
	public long getSize() {
		if (this.size != 0) {
			return this.size;
		} else {
			this.size = 0;
			for (Folder folder : childFolders) {
				this.size += folder.getSize();
			}
			for (File file : files) {
				this.size += file.getSize();
			}
			return this.size;
		}
	}
	public List<File> getFiles() {
		return files;
	}
	public void setFiles(List<File> files) {
		this.files = files;
	}
	public List<Folder> getChildFolders() {
		return childFolders;
	}
	public void setChildFolders(List<Folder> childFolders) {
		this.childFolders = childFolders;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	
	
}
