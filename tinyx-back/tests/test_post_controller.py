import requests
import unittest

import uuid


class TestPostController(unittest.TestCase):

    BASE_URL = "http://localhost:8081/api/post"

    USER_UUID = "3618d024-564e-4019-ac28-eb2dbe26a86a"
    POST_UUID = "62aa49cb-b81b-4b99-b25c-b16c957f31f3"
#Example of uuid :
# 113c3ceb-c562-4474-8066-427d0bffdb3b

    def test_get_posts(self, status_code: int=200):
        response = requests.get(f"{self.BASE_URL}/all")
        self.assertEqual(status_code, response.status_code)
        if status_code != 404 and status_code != 400 and status_code != 500:
            self.assertIsInstance(response.json(), list)
        try:
            return response.json()
        except:
            return response

    def test_get_user_posts(self, user_id: uuid=USER_UUID, status_code: int=200):
        response = requests.get(f"{self.BASE_URL}/user", params={"id": user_id})

        self.assertEqual(status_code, response.status_code)
        if status_code != 404 and status_code != 400 and status_code != 500:
            self.assertIsInstance(response.json(), list)
        try:
            return response.json()
        except:
            return response

    def test_get_post(self, post_id: uuid=POST_UUID, status_code: int=200):
        response = requests.get(f"{self.BASE_URL}", params={"id": post_id})
        self.assertEqual(status_code, response.status_code)
        if status_code != 404 and status_code != 400 and status_code != 500:
            self.assertIsInstance(response.json(), dict)
        try:
            return response.json()
        except:
            return response

    def test_get_reply(self, post_id: uuid=POST_UUID, status_code: int=200):
        response = requests.get(f"{self.BASE_URL}/reply", params={"id": post_id})
        self.assertEqual(status_code, response.status_code)
        if status_code != 404 and status_code != 400 and status_code != 500:
            self.assertIsInstance(response.json(), dict)
        try:
            return response.json()
        except:
            return response


    def test_add_post(self, user_id: uuid=USER_UUID, content: str="This is a test post", media: str="", repost: str="", replyTo: str="", status_code=200):
        post_content = {
            "content": content,
            "media": "",
            "repost": repost,
            "replyTo": replyTo
        }
        response = requests.post(f"{self.BASE_URL}", headers={"X-user-id": str(user_id)}, json=post_content)
        self.assertEqual(status_code, response.status_code)
        try :
            return response.json()
        except:
            return response

    def test_delete_post(self, user_id: uuid=USER_UUID, post_id: uuid=POST_UUID, status_code=200):
        response = requests.delete(f"{self.BASE_URL}", headers={"X-user-id": user_id, "X-post-id": post_id})
        self.assertEqual(status_code, response.status_code)
        try:
            return response.json()
        except:
            return response
