# Machine Coding Question (FK Social Feed)

## Problem Statement
Build a console social media application for Flipkart that allows users to upload posts like pictures, blogs, etc., interact with others’ posts, and follow other users on the platform.

## Functional Requirements
1. **User Registration**: 
   - A new user should be able to register with a unique ID and username.

2. **Upload a Post**:
   - A user should be able to upload a post of their choice (For simplicity, consider the post as a string value).

3. **Follow/Unfollow Users**:
   - A user should be able to follow or unfollow any number of other users.

4. **Like/Dislike Post**:
   - A user should be able to like or unlike any number of posts.

5. **Show Feed for a User**:
   - A method to view posts uploaded by other users, sorted based on the following criteria (in order of priority):
     - **a. Posts from users that the current user follows will always appear at the top of the feed. Posts from non-followed users will come after.**
     - **b. Within each group of followed and unfollowed users, posts should be sorted by score (likes - dislikes).**
     - **c. If two posts have the same score, they are sorted by posting time, with the more recent post appearing first.**
