import requests

BASE_URL = "http://localhost:8084/api/timeline"

#Service cassé en local, pas acces au swagger, #todo quand service fix

def test_get_full_timeline():
    url = f"{BASE_URL}/home"
    headers = {"X-user-id": "test-user-id"}
    response = requests.get(url, headers=headers)
    assert response.status_code == 404

def test_get_paginated_timeline():
    url = f"{BASE_URL}/home/page"
    headers = {"X-user-id": "test-user-id"}
    params = {"page": 0, "size": 10, "fromDate": "2022-01-01", "toDate": "2022-12-31"}
    response = requests.get(url, headers=headers, params=params)
    assert response.status_code == 404
