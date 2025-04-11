import requests
import unittest
import uuid

class TestSearchController(unittest.TestCase):

    BASE_URL = "http://localhost:8085/api/search"

    def test_search_post(self, words: str="test", hashtags: str="#test", status_code=200):
        response = requests.get(f"{self.BASE_URL}/posts", params={"words": words, "hashtags": hashtags})
        self.assertEqual(status_code, response.status_code)
        try:
            return response.json()
        except:
            return response

    def test_get_user_posts(self, user_id: uuid = uuid.uuid4(), status_code=200):
        response = requests.get(f"{self.BASE_URL}/posts/{user_id}")
        self.assertEqual(status_code, response.status_code)
        try:
            return response.json()
        except:
            return response

    def test_get_user_name(self, name: str = "name", status_code=200):
        response = requests.get(f"{self.BASE_URL}/posts/{name}")
        self.assertEqual(status_code, response.status_code)
        try:
            return response.json()
        except:
            return response
        
    def test_save_post(self, body: dict = None, status_code=201):
        if body is None:
            body = {
                "id" : str(uuid.uuid4()),
                "authorId": "113c3ceb-c562-4474-8066-427d0bffdb3b",
                "content": "Ceci est un test de post #testUwU",
                "createdAt": datetime.now(timezone.utc).isoformat()
            }
        response = requests.post(f"{self.BASE_URL}/posts/save", json=body)
        self.assertEqual(status_code, response.status_code)
        try:
            return response.json()
        except:
            return response
    
    