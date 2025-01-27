package all;

import java.util.*;

public class Problem4Singer {
	private String name;
    private String nickname;
    private List<Problem4Album> albums;

    // Create a constructor of the class
    public Problem4Singer(String name, String nickname) {
        this.name = name;
        this.nickname = nickname;
        albums = new ArrayList<Problem4Album>();
    }

    // Method for adding new albums for every artist
    public void addAlbum(Problem4Album album) {
        albums.add(album);
    }

    // Create an overridden method to make the the information in the class  
    // into string 
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("Singers name " + this.name);
        sb.append('\n');
        sb.append("Singers nickname " + this.nickname);

        for (Problem4Album album : albums) {
            sb.append(album.toString());
            sb.append('\n');
        }
        return sb.toString();
    }

}
