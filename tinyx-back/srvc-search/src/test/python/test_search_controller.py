import requests
import unittest
import uuid

class TestSearchController(unittest.TestCase):

    BASE_URL = "http://localhost:8085/api/search"

    def test_search_post(self):
        words = "test"
        hashtags = "#test"
        response = requests.get(f"{self.BASE_URL}/posts", params={"words": words, "hashtags": hashtags})
        self.assertEqual(response.status_code, 200)

    def test_get_user_posts(self):
        user_id = uuid.uuid4()
        response = requests.get(f"{self.BASE_URL}/posts/{user_id}")
        self.assertEqual(response.status_code, 200)

if __name__ == "__main__":
    unittest.main()
