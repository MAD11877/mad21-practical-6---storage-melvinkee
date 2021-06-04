package sg.edu.np.madpractical;

public class User {
    private String Name;
    private String Description;
    private int Id;
    private boolean Followed;

    public String getName() {
        return Name;
    }

    public User setName(String name) {
        Name = name;
        return null;
    }

    public String getDescription() {
        return Description;
    }

    public User setDescription(String description) {
        Description = description;
        return null;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public boolean isFollowed() {
        return Followed;
    }

    public User setFollowed(boolean followed) {
        Followed = followed;
        return null;
    }
}
