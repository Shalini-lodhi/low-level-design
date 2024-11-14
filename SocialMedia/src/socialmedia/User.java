package socialmedia;

import java.util.HashSet;
import java.util.Set;

class User {
    private int id;
    private String name;
    private Set<Integer> following;
    private Set<Integer> posts;

    public User(int id, String name) {
        this.id = id;
        this.name = name;
        this.following = new HashSet<>();
        this.posts = new HashSet<>();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Set<Integer> getFollowing() {
        return following;
    }

    public Set<Integer> getPosts() {
        return posts;
    }

    public void follow(User user) {
        following.add(user.getId());
    }

    public void unfollow(User user) {
        following.remove(user.getId());
    }

    public void addPost(int postId) {
        posts.add(postId);
    }
}
