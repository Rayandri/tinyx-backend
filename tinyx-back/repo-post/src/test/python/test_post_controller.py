import requests
import unittest

class TestPostController(unittest.TestCase):

    BASE_URL = "http://localhost:8080/api/post"

    def test_get_posts(self):
        response = requests.get(f"{self.BASE_URL}/all")
        self.assertEqual(response.status_code, 200)
        self.assertIsInstance(response.json(), list)

    def test_get_user_posts(self):
        user_id = "some-uuid"
        response = requests.get(f"{self.BASE_URL}/user", params={"id": user_id})
        self.assertEqual(response.status_code, 200)
        self.assertIsInstance(response.json(), list)

    def test_get_post(self):
        post_id = "some-uuid"
        response = requests.get(f"{self.BASE_URL}", params={"id": post_id})
        self.assertEqual(response.status_code, 200)
        self.assertIsInstance(response.json(), dict)

    def test_get_reply(self):
        post_id = "some-uuid"
        response = requests.get(f"{self.BASE_URL}/reply", params={"id": post_id})
        self.assertEqual(response.status_code, 200)
        self.assertIsInstance(response.json(), dict)

    def test_add_post(self):
        user_id = "some-uuid"
        post_content = {
            "content": "This is a test post",
            "media": [],
            "repost": None,
            "replyTo": None
        }
        response = requests.post(f"{self.BASE_URL}", headers={"X-user-id": user_id}, json=post_content)
        self.assertEqual(response.status_code, 200)

    def test_delete_post(self):
        user_id = "some-uuid"
        post_id = "some-uuid"
        response = requests.delete(f"{self.BASE_URL}", headers={"X-user-id": user_id}, json={"id": post_id})
        self.assertEqual(response.status_code, 200)

if __name__ == "__main__":
    unittest.main()
