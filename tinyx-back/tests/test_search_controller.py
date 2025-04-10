import requests
import unittest
import uuid

class TestSearchController(unittest.TestCase):

    BASE_URL = "http://localhost:8085/api/search"

    def test_search_post(self, words: str="test", hashtags: str="#test", status_code=200):
        response = requests.get(f"{self.BASE_URL}/posts", params={"words": words, "hashtags": hashtags})
        self.assertEqual(status_code, response.status_code)
        return response

    def test_get_user_posts(self, user_id: uuid = uuid.uuid4(), status_code=200):
        response = requests.get(f"{self.BASE_URL}/posts/{user_id}")
        self.assertEqual(status_code, response.status_code)
        return response

    def test_get_user_name(self, name: str = "name", status_code=200):
        response = requests.get(f"{self.BASE_URL}/posts/{name}")
        self.assertEqual(status_code, response.status_code)
        return response

if __name__ == "__main__":
    unittest.main()
