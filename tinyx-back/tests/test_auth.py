import requests
import unittest
import uuid


class TestAuthController(unittest.TestCase):

    BASE_URL = "http://localhost:8083/api/auth"

    def test_login(self, username: str="testuser", password: str="password", status_code: int=200):
        payload = {"username": username, "password": password}
        response = requests.post(f"{self.BASE_URL}/login", json=payload)
        self.assertEqual(status_code, response.status_code)
        assert response.json()["username"] == username
        try:
            return response.json()
        except:
            return response

    def test_delete(self, user_id: uuid=uuid.uuid4(), status_code: int=200):
        response = requests.post(f"{self.BASE_URL}/delete", headers={"X-user-id": user_id})
        self.assertEqual(status_code, response.status_code)
        try:
            return response.json()
        except:
            return response

    def test_create_user(self, username: str="testuser", password: str="password", status_code: int=200):
        payload = {"username": username, "password": password}
        response = requests.post(f"{self.BASE_URL}/create", json=payload)
        self.assertEqual(status_code, response.status_code)
        assert response.json()["username"] == username
        try:
            return response.json()
        except:
            return response

    def test_update_password(self, user_id: uuid=uuid.uuid4(), password: str="newpassword", status_code: int=200):
        payload = {"password": password}
        response = requests.post(f"{self.BASE_URL}/update/password", headers={"X-user-id": user_id}, json=payload)
        self.assertEqual(status_code, response.status_code)
        try:
            return response.json()
        except:
            return response