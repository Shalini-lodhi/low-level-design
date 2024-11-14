package socialmedia;

public class Post {
    private static int postCounter = 1;
    private int id;
    private String content;
    private String timestamp;
    private int likes;
    private int dislikes;

    public Post(String content, String timestamp) {
        this.id = postCounter++;
        this.content = content;
        this.timestamp = timestamp;
        this.likes = 0;
        this.dislikes = 0;
    }

    public int getId() {
        return id;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public String getContent() {
        return content;
    }

    public int getScore() {
        return likes - dislikes;
    }

    public void likePost() {
        likes++;
    }

    public void dislikePost() {
        dislikes++;
    }

    @Override
    public String toString() {
        return "Post ID: " + id + "\nContent: " + content + "\nLikes: " + likes + " | Dislikes: " + dislikes + "\nPosted on: " + timestamp;
    }
}
