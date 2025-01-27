package problem3FileSystem;

import java.util.Date;

public abstract class File implements Comparable<File> {
	private String name;
	private Date dateCreated;
	private Date lastChanged;
	private FilesType type;
	private Object content;
	private Directory parent;

	public File(String name, Date dateCreated, Date lastChanged,
			Object content, FilesType type) {
		this.name = name;
		this.dateCreated = dateCreated;
		this.lastChanged = lastChanged;
		this.type = type;
		this.content = content;
	}

	public Directory getParent() {
		return parent;
	}

	public void setParrent(Directory parent) {
		this.parent = parent;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	public Date getLastChanged() {
		return lastChanged;
	}

	public void setLastChanged(Date lastChanged) {
		this.lastChanged = lastChanged;
	}

	public FilesType getType() {
		return type;
	}

	public void setType(FilesType type) {
		this.type = type;
	}

	public Object getContent() {
		return content;
	}

	public void setContent(Object content) {
		this.content = content;
	}

	@Override
	public int compareTo(File other) {
		if (other == null) {
			return 1;
		}
		return this.name.compareTo(other.name);
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return this.name + "." + (this.type == FilesType.Text ? "txt" : "bin");
	}
}
