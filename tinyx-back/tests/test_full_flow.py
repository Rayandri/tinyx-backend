import unittest
import requests
import random
import string
import json

class TestFullFlow(unittest.TestCase):
    AUTH_URL = "http://localhost:8083/api/auth"
    POST_URL = "http://localhost:8081/api/post"
    USER_URL = "http://localhost:8082/api/user"

    def random_string(self, length=10):
        return ''.join(random.choices(string.ascii_letters + string.digits, k=length))

    def create_user(self, username):
        response = requests.post(f"{self.AUTH_URL}/create", json={
            "username": username,
            "password": "testpassword"
        })
        print(f"[CREATE USER] Status: {response.status_code}")
        print(f"[CREATE USER] Response: {response.text}")
        self.assertEqual(response.status_code, 200)
        return response.json().get("id")

    def test_full_user_post_like_flow(self):
        username1 = self.random_string()
        user1_id = self.create_user(username1)

        post_content = {
            "content": f"This is a test post from {username1}"
        }

        print(f"[DEBUG] Posting content: {json.dumps(post_content)}")
        print(f"[DEBUG] With header X-user-id: {user1_id}")

        response_post = requests.post(
            self.POST_URL,
            headers={"X-user-id": user1_id},
            json=post_content
        )

        print(f"[POST] Status code: {response_post.status_code}")
        print(f"[POST] Response: {response_post.text}")

        self.assertEqual(response_post.status_code, 200)


if __name__ == '__main__':
    unittest.main()
