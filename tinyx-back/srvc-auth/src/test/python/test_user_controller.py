import requests

BASE_URL = "http://localhost:8080/api/auth"

def test_create_user():
    url = f"{BASE_URL}/create"
    payload = {"username": "testuser", "password": "password"}
    response = requests.post(url, json=payload)
    assert response.status_code == 200
    assert response.json()["username"] == "testuser"

def test_login():
    url = f"{BASE_URL}/login"
    payload = {"username": "testuser", "password": "password"}
    response = requests.post(url, json=payload)
    assert response.status_code == 200
    assert response.json()["username"] == "testuser"
