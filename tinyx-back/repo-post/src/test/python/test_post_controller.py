import requests
import unittest

class TestPostController(unittest.TestCase):

    BASE_URL = "http://localhost:8081/api/post"

#Example of uuid :
# 113c3ceb-c562-4474-8066-427d0bffdb3b

    def test_get_posts(self, status_code=200):
        response = requests.get(f"{self.BASE_URL}/all")
        self.assertEqual(response.status_code, status_code)
        self.assertIsInstance(response.json(), list)

    def test_get_user_posts(self, user_id, status_code=200):
        response = requests.get(f"{self.BASE_URL}/user", params={"id": user_id})
        self.assertEqual(response.status_code, status_code)
        self.assertIsInstance(response.json(), list)

    def test_get_post(self, post_id, status_code=200):
        response = requests.get(f"{self.BASE_URL}", params={"id": post_id})
        self.assertEqual(response.status_code, status_code)
        self.assertIsInstance(response.json(), dict)

    def test_get_reply(self, post_id, status_code=200):
        response = requests.get(f"{self.BASE_URL}/reply", params={"id": post_id})
        self.assertEqual(response.status_code, status_code)
        self.assertIsInstance(response.json(), dict)


# Structure post_content :
#post_content = {
#    "content": "This is a test post",
#    "media": [],
#    "repost": None,
#    "replyTo": None
#}

    def test_add_post(self, user_id, post_content, status_code=200):
        response = requests.post(f"{self.BASE_URL}", headers={"X-user-id": user_id}, json=post_content)
        self.assertEqual(response.status_code, status_code)

    def test_delete_post(self, user_id, post_id, status_code=200):
        response = requests.delete(f"{self.BASE_URL}", headers={"X-user-id": user_id}, json={"id": post_id})
        self.assertEqual(response.status_code, status_code)

if __name__ == "__main__":
    unittest.main()
