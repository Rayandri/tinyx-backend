import requests
import unittest
import uuid

BASE_URL = "http://localhost:8086/api/timeline"

#Service cassé en local, pas acces au swagger, #todo quand service fix
class TestUserTimeline(unittest.TestCase):
    def test_get_full_timeline(uuid=uuid.uuid4()):
        url = f"{BASE_URL}/user"
        headers = {"X-user-id": str(uuid)}
        response = requests.get(url, headers=headers)
        assert response.status_code == 404

    def test_get_paginated_timeline(uuid=uuid.uuid4(), page=0, size=10, from_date="2022-01-01", to_date="2022-12-31"):
        url = f"{BASE_URL}/user/page"
        headers = {"X-user-id": str(uuid)}
        params = {
            "page": page,
            "size": size,
            "from": from_date,
            "to": to_date
        }
        response = requests.get(url, headers=headers, params=params)
        assert response.status_code == 404
