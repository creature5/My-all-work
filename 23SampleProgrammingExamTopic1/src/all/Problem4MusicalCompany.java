package all;

import java.util.*;

public class Problem4MusicalCompany {
	private String name;
    private String address;
    private String owner;
    private HashSet<Problem4Singer> artists;

    // Create a constructor for the class
    public Problem4MusicalCompany(String name, String address, String owner) {
        this.name = name;
        this.address = address;
        this.owner = owner;
        this.artists = new HashSet<Problem4Singer>();
    }

    // Method for adding new singers to the HashSet of artists
    public void addArtist(Problem4Singer singer) {
        this.artists.add(singer);
    }

    // Create an overridden method to make the the information in the class 
    // into string
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("Company name " + this.name);
        sb.append('\n');
        sb.append("Company adrres " + this.address);
        sb.append('\n');
        sb.append("Owner " + this.owner);
        sb.append('\n');

        for (Problem4Singer artist : artists) {
            sb.append(artist.toString());
            sb.append('\n');
        }
        return sb.toString();
    }
}
