# Community Platform API Endpoints Documentation

**Base URL:** `http://localhost:8080`

**Note:** All endpoints marked with 🔒 require authentication (JWT token in header or `@AuthenticationPrincipal`)

---

## 🔐 **1. USER AUTHENTICATION** (`/`)

### 1.1 Register User
- **Method:** `POST`
- **URL:** `/adduser`
- **Auth:** ❌ No auth required
- **Request Body:**
```json
{
  "email": "user@example.com",
  "name": "John Doe",
  "password": "password123",
  "bio": "Optional bio",
  "interests": "Optional interests"
}
```
- **Response:** `Login_User` object (with encrypted password)
- **Description:** Creates a new user account. Password is automatically encrypted with BCrypt.

---

### 1.2 Login User
- **Method:** `POST`
- **URL:** `/loginUser`
- **Auth:** ❌ No auth required
- **Request Body:**
```json
{
  "userId": "user@example.com",
  "password": "password123"
}
```
- **Response:** `Boolean` (true if login successful, false otherwise)
- **Description:** Authenticates user credentials. Returns true if email and password match.

---

### 1.3 Login User (PUT)
- **Method:** `PUT`
- **URL:** `/loginUser`
- **Auth:** ❌ No auth required
- **Request Body:** Same as POST `/loginUser`
- **Response:** `Boolean`
- **Description:** Alternative login endpoint using PUT method.

---

## 👥 **2. INTEREST GROUPS** (`/api/groups`)

### 2.1 Create Group
- **Method:** `POST`
- **URL:** `/api/groups`
- **Auth:** 🔒 Required
- **Request Body:**
```json
{
  "name": "Music Lovers",
  "description": "A group for music enthusiasts"
}
```
- **Response:** `InterestGroup` object (201 Created)
- **Description:** Creates a new interest group. User becomes the creator.

---

### 2.2 List All Groups
- **Method:** `GET`
- **URL:** `/api/groups`
- **Auth:** ❌ No auth required
- **Response:** `List<InterestGroup>`
- **Description:** Returns all available interest groups.

---

### 2.3 Get Group by ID
- **Method:** `GET`
- **URL:** `/api/groups/{groupId}`
- **Auth:** ❌ No auth required
- **Path Variables:** `groupId` (Long)
- **Response:** `InterestGroup` object
- **Description:** Returns details of a specific group.

---

### 2.4 Join Group
- **Method:** `POST`
- **URL:** `/api/groups/{groupId}/join`
- **Auth:** 🔒 Required
- **Path Variables:** `groupId` (Long)
- **Response:** `"Joined group successfully"`
- **Description:** Adds current user to the group. Throws error if already a member.

---

### 2.5 Leave Group
- **Method:** `POST`
- **URL:** `/api/groups/{groupId}/leave`
- **Auth:** 🔒 Required
- **Path Variables:** `groupId` (Long)
- **Response:** `"Left group successfully"`
- **Description:** Removes current user from the group. Throws error if not a member.

---

## 📝 **3. POSTS** (`/api/posts`)

### 3.1 Create Post
- **Method:** `POST`
- **URL:** `/api/posts`
- **Auth:** 🔒 Required
- **Content-Type:** `multipart/form-data`
- **Request Parameters:**
  - `caption` (String) - Post caption/text
  - `files` (MultipartFile[]) - Array of image/media files
- **Response:** `"Post created"`
- **Description:** Creates a new post with media files. Files are saved to `uploads/` directory.

---

### 3.2 Get Feed (All Posts)
- **Method:** `GET`
- **URL:** `/api/posts/feed`
- **Auth:** ❌ No auth required
- **Query Parameters:**
  - `page` (int, default: 0) - Page number
  - `size` (int, default: 10) - Items per page
- **Response:** `Page<Post>` (paginated, sorted by newest first)
- **Description:** Returns paginated feed of all posts, newest first.

---

### 3.3 Get Post by ID
- **Method:** `GET`
- **URL:** `/api/posts/{postId}`
- **Auth:** ❌ No auth required
- **Path Variables:** `postId` (Long)
- **Response:** `Post` object
- **Description:** Returns details of a specific post.

---

### 3.4 Get Posts by User Email
- **Method:** `GET`
- **URL:** `/api/posts/user/{userEmail}`
- **Auth:** ❌ No auth required
- **Path Variables:** `userEmail` (String)
- **Query Parameters:**
  - `page` (int, default: 0)
  - `size` (int, default: 10)
- **Response:** `List<Post>`
- **Description:** Returns paginated posts by a specific user.

---

### 3.5 Get My Posts
- **Method:** `GET`
- **URL:** `/api/posts/my-posts`
- **Auth:** 🔒 Required
- **Response:** `List<Post>`
- **Description:** Returns all posts by the currently authenticated user.

---

### 3.6 Update Post
- **Method:** `PUT`
- **URL:** `/api/posts/{postId}`
- **Auth:** 🔒 Required (must be post owner)
- **Path Variables:** `postId` (Long)
- **Request Parameters:**
  - `caption` (String) - New caption text
- **Response:** `Post` object (updated)
- **Description:** Updates post caption. Only post owner can update.

---

### 3.7 Delete Post
- **Method:** `DELETE`
- **URL:** `/api/posts/{postId}`
- **Auth:** 🔒 Required (must be post owner)
- **Path Variables:** `postId` (Long)
- **Response:** `"Post deleted successfully"`
- **Description:** Deletes a post. Only post owner can delete.

---

### 3.8 Like Post
- **Method:** `POST`
- **URL:** `/api/posts/{postId}/like`
- **Auth:** 🔒 Required
- **Path Variables:** `postId` (Long)
- **Response:** `"Liked"`
- **Description:** Adds a like to the post. If already liked, does nothing.

---

### 3.9 Unlike Post
- **Method:** `POST`
- **URL:** `/api/posts/{postId}/unlike`
- **Auth:** 🔒 Required
- **Path Variables:** `postId` (Long)
- **Response:** `"Unliked"`
- **Description:** Removes user's like from the post.

---

### 3.10 Get Like Info
- **Method:** `GET`
- **URL:** `/api/posts/{postId}/likes`
- **Auth:** 🔒 Required
- **Path Variables:** `postId` (Long)
- **Response:**
```json
{
  "likeCount": 42,
  "hasLiked": true
}
```
- **Description:** Returns like count and whether current user has liked the post.

---

### 3.11 Add Comment
- **Method:** `POST`
- **URL:** `/api/posts/{postId}/comment`
- **Auth:** 🔒 Required
- **Path Variables:** `postId` (Long)
- **Request Body:** `String` (raw text)
- **Response:** `"Comment added"`
- **Description:** Adds a comment to the post.

---

### 3.12 Get Comments
- **Method:** `GET`
- **URL:** `/api/posts/{postId}/comments`
- **Auth:** ❌ No auth required
- **Path Variables:** `postId` (Long)
- **Response:** `List<PostComment>`
- **Description:** Returns all comments for a post.

---

### 3.13 Get Comment Count
- **Method:** `GET`
- **URL:** `/api/posts/{postId}/comments/count`
- **Auth:** ❌ No auth required
- **Path Variables:** `postId` (Long)
- **Response:**
```json
{
  "commentCount": 15
}
```
- **Description:** Returns total number of comments on a post.

---

### 3.14 Update Comment
- **Method:** `PUT`
- **URL:** `/api/posts/{postId}/comments/{commentId}`
- **Auth:** 🔒 Required (must be comment owner)
- **Path Variables:**
  - `postId` (Long)
  - `commentId` (Long)
- **Request Body:** `String` (new comment text)
- **Response:** `PostComment` object (updated)
- **Description:** Updates comment text. Only comment owner can update.

---

### 3.15 Delete Comment
- **Method:** `DELETE`
- **URL:** `/api/posts/{postId}/comments/{commentId}`
- **Auth:** 🔒 Required (must be comment owner)
- **Path Variables:**
  - `postId` (Long)
  - `commentId` (Long)
- **Response:** `"Comment deleted successfully"`
- **Description:** Deletes a comment. Only comment owner can delete.

---

## 🎵 **4. JAMMING SESSIONS** (`/api/jamming-sessions`)

### 4.1 Create Jamming Session
- **Method:** `POST`
- **URL:** `/api/jamming-sessions/groups/{groupId}`
- **Auth:** 🔒 Required (must be group member)
- **Path Variables:** `groupId` (Long)
- **Request Body:**
```json
{
  "title": "Guitar Jam Session",
  "description": "Let's play some music!",
  "startTime": "2024-12-25T18:00:00",
  "durationMinutes": 120
}
```
- **Response:** `JammingSession` object
- **Description:** Creates a new jamming session for a group. User must be a group member.

---

### 4.2 Join Jamming Session
- **Method:** `POST`
- **URL:** `/api/jamming-sessions/{id}/join`
- **Auth:** 🔒 Required
- **Path Variables:** `id` (Long) - Session ID
- **Response:** `void` (200 OK)
- **Description:** Adds current user to a jamming session. Throws error if already joined or session ended.

---

### 4.3 Leave Jamming Session
- **Method:** `POST`
- **URL:** `/api/jamming-sessions/{id}/leave`
- **Auth:** 🔒 Required
- **Path Variables:** `id` (Long) - Session ID
- **Response:** `void` (200 OK)
- **Description:** Removes current user from a jamming session. Sets `leftAt` timestamp.

---

### 4.4 Get Participants
- **Method:** `GET`
- **URL:** `/api/jamming-sessions/{id}/participants`
- **Auth:** ❌ No auth required
- **Path Variables:** `id` (Long) - Session ID
- **Response:** `List<JammingParticipant>`
- **Description:** Returns list of active participants (who haven't left) in a session.

---

## 📊 **Summary**

| Category | Endpoints | Auth Required |
|----------|-----------|---------------|
| User Auth | 3 | 0 |
| Interest Groups | 5 | 2 |
| Posts | 15 | 9 |
| Jamming Sessions | 4 | 3 |
| **TOTAL** | **27** | **14** |

---

## 🔑 **Authentication**

Currently, all endpoints marked with 🔒 use `@AuthenticationPrincipal Login_User` which means:
- If using JWT: Include token in `Authorization: Bearer <token>` header
- If using session-based: User must be logged in
- Current implementation allows all requests (`permitAll()` in SecurityConfig)

---

## 📝 **Notes**

1. **File Uploads:** Post creation accepts multiple files. Files are stored in `uploads/` directory.
2. **Pagination:** Feed endpoints support pagination with `page` and `size` parameters.
3. **Ownership:** Update/Delete operations check if user owns the resource.
4. **Error Handling:** Currently uses `RuntimeException` - consider custom exceptions for better error messages.
5. **Validation:** Some endpoints lack input validation - consider adding `@Valid` annotations.
