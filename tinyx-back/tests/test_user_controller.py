import requests
import unittest
import uuid

class TestUserController(unittest.TestCase):

    BASE_URL = "http://localhost:8082/api/user"

    def test_get_likes(self, status_code=200):
        response = requests.get(f"{self.BASE_URL}/likes/all")
        self.assertEqual(response.status_code, status_code)

    def test_get_user_likes(self, user_id: uuid=uuid.uuid4(), status_code=200):
        response = requests.get(f"{self.BASE_URL}/likes", headers={"X-user-id": str(user_id)})
        self.assertEqual(response.status_code, status_code)

    def test_get_user_likes_by_post(self, post_id: uuid = uuid.uuid4(), status_code=200):
        response = requests.get(f"{self.BASE_URL}/likes/post", headers={"X-post-id": str(post_id)})
        self.assertEqual(response.status_code, status_code)

    def test_add_like(self, user_id: uuid = uuid.uuid4(), post_id: uuid = uuid.uuid4(), status_code=200):
        response = requests.post(f"{self.BASE_URL}/like", headers={"X-user-id": str(user_id), "X-post-id": str(post_id)})
        self.assertEqual(response.status_code, status_code)

    def test_unlike(self, user_id: uuid = uuid.uuid4(), post_id: uuid = uuid.uuid4(), status_code=200):
        response = requests.delete(f"{self.BASE_URL}/unlike", headers={"X-user-id": str(user_id), "X-post-id": str(post_id)})
        self.assertEqual(response.status_code, status_code)

    def test_get_follows(self, status_code=200):
        response = requests.get(f"{self.BASE_URL}/follows/all")
        self.assertEqual(response.status_code, status_code)

    def test_get_user_follows(self, user_id: uuid = uuid.uuid4(), status_code=200):
        response = requests.get(f"{self.BASE_URL}/follows", headers={"X-user-id": str(user_id)})
        self.assertEqual(response.status_code, status_code)

    def test_get_followers(self, user_id: uuid = uuid.uuid4(), status_code=200):
        response = requests.get(f"{self.BASE_URL}/followers", headers={"X-user-id": str(user_id)})
        self.assertEqual(response.status_code, status_code)

    def test_add_follow(self, user_id: uuid = uuid.uuid4(), followed_user_id: uuid = uuid.uuid4(), status_code=200):
        response = requests.post(f"{self.BASE_URL}/follow", headers={"X-user-id": str(user_id), "X-followed-id": str(followed_user_id)})
        self.assertEqual(response.status_code, status_code)

    def test_unfollow(self, user_id: uuid = uuid.uuid4(), followed_user_id: uuid = uuid.uuid4(), status_code=200):
        response = requests.delete(f"{self.BASE_URL}/unfollow", headers={"X-user-id": str(user_id), "X-follow-id": str(followed_user_id)})
        self.assertEqual(response.status_code, status_code)

    def test_get_blocks(self, status_code=200):
        response = requests.get(f"{self.BASE_URL}/blocks")
        self.assertEqual(response.status_code, status_code)

    def test_get_user_blockers(self, user_id: uuid = uuid.uuid4(), status_code=200):
        response = requests.get(f"{self.BASE_URL}/blockers", headers={"X-user-id": str(user_id)})
        self.assertEqual(response.status_code, status_code)

    def test_get_user_blocked(self, user_id: uuid = uuid.uuid4(), status_code=200):
        response = requests.get(f"{self.BASE_URL}/blocked", headers={"X-user-id": str(user_id)})
        self.assertEqual(response.status_code, status_code)

    def test_add_block(self, user_id: uuid = uuid.uuid4(), blocked_user_id: uuid = uuid.uuid4(), status_code=200):
        response = requests.post(f"{self.BASE_URL}/block", headers={"X-user-id": str(user_id), "X-blocked-id": str(blocked_user_id)})
        self.assertEqual(response.status_code, status_code)

    def test_unblock(self, user_id: uuid = uuid.uuid4(), blocked_user_id: uuid = uuid.uuid4(), status_code=200):
        response = requests.delete(f"{self.BASE_URL}/unblock", headers={"X-user-id": str(user_id), "X-blocked-id": str(blocked_user_id)})
        self.assertEqual(response.status_code, status_code)

if __name__ == "__main__":
    unittest.main()
