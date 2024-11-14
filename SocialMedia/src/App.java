import socialmedia.Platform;

public class App {
    public static void main(String[] args) {
        Platform platform = new Platform();

        // Register users
        platform.registerUser(1, "Akash");
        platform.registerUser(2, "Hemant");

        // Upload posts
        platform.uploadPost(1, "This is my first post. My name is xyz");
        platform.uploadPost(1, "I work at Flipkart as a SDE1");
        platform.uploadPost(2, "I too worked at Flipkart as a SDE1");

        // Interact with posts
        platform.interactWithPost("LIKE", 2, 1);  // User 2 likes post 1
        platform.interactWithPost("DISLIKE", 2, 1);  // User 2 dislikes post 1

        // Follow/Unfollow users
        platform.interactWithUser("FOLLOW", 2, 1);  // User 2 follows User 1
        platform.interactWithUser("UNFOLLOW", 2, 1);  // User 2 unfollows User 1

        // Show feed for User 2
        platform.showFeed(2);
    }
}
