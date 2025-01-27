package problem3FileSystem;

import java.util.Date;

public class TextFile extends File{
	
	private String content;

	public TextFile(String name, Date dateCreated, Date lastChanged,
			String content) {
		super(name, dateCreated, lastChanged, content, FilesType.Text);
		this.content = content;
	}

	/**
	 * @return the content
	 */
	public String getContent() {
		return content;
	}

	/**
	 * @param content the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}
}
