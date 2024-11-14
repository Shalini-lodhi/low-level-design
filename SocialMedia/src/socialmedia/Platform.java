package socialmedia;

import java.util.*;
import java.text.SimpleDateFormat;

public class Platform{
    private Map<Integer, User> users;
    private Map<Integer, Post> posts;

    public Platform() {
        users = new HashMap<>();
        posts = new HashMap<>();
    }

    public void registerUser(int userId, String username) {
        if (!users.containsKey(userId)) {
            users.put(userId, new User(userId, username));
            System.out.println(username + " Registered!!");
        } else {
            System.out.println("User ID already exists.");
        }
    }

    public void uploadPost(int userId, String postContent) {
        if (users.containsKey(userId)) {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm a");
            String timestamp = formatter.format(new Date());
            Post newPost = new Post(postContent, timestamp);
            posts.put(newPost.getId(), newPost);
            users.get(userId).addPost(newPost.getId());
            System.out.println("Upload Successful with post id: " + String.format("%03d", newPost.getId()));
        } else {
            System.out.println("User not found.");
        }
    }

    public void interactWithPost(String interactionType, int userId, int postId) {
        if (posts.containsKey(postId)) {
            Post post = posts.get(postId);
            switch (interactionType.toUpperCase()) {
                case "LIKE":
                    post.likePost();
                    System.out.println("Post Liked!!");
                    break;
                case "DISLIKE":
                    post.dislikePost();
                    System.out.println("Post Disliked!!");
                    break;
                default:
                    System.out.println("Invalid interaction type.");
                    break;
            }
        } else {
            System.out.println("Post not found.");
        }
    }

    public void interactWithUser(String interactionType, int userId1, int userId2) {
        if (users.containsKey(userId1) && users.containsKey(userId2)) {
            User user1 = users.get(userId1);
            User user2 = users.get(userId2);
            if (interactionType.equalsIgnoreCase("FOLLOW")) {
                user1.follow(user2);
                System.out.println("Followed " + user2.getName() + "!!");
            } else if (interactionType.equalsIgnoreCase("UNFOLLOW")) {
                user1.unfollow(user2);
                System.out.println("Unfollowed " + user2.getName() + "!!");
            } else {
                System.out.println("Invalid interaction type.");
            }
        } else {
            System.out.println("User(s) not found.");
        }
    }

    public void showFeed(int userId) {
        if (users.containsKey(userId)) {
            User user = users.get(userId);
            List<Post> followedPosts = new ArrayList<>();
            List<Post> nonFollowedPosts = new ArrayList<>();

            // Collect posts from followed users and non-followed users
            for (int postId : user.getPosts()) {
                Post post = posts.get(postId);
                if (user.getFollowing().contains(postId)) {
                    followedPosts.add(post);
                } else {
                    nonFollowedPosts.add(post);
                }
            }

            // Sort posts by score (likes - dislikes), then by timestamp
            Comparator<Post> postComparator = Comparator
                    .comparingInt(Post::getScore)
                    .reversed()
                    .thenComparing(Post::getTimestamp, Comparator.reverseOrder());

            followedPosts.sort(postComparator);
            nonFollowedPosts.sort(postComparator);

            // Print followed posts first, then non-followed
            for (Post post : followedPosts) {
                System.out.println(post);
            }
            for (Post post : nonFollowedPosts) {
                System.out.println(post);
            }
        } else {
            System.out.println("User not found.");
        }
    }
}
