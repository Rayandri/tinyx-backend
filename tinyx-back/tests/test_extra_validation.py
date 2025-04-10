import uuid
from test_scenar_simple import create_user, create_post, get_all_users, get_all_posts

def test_user_creation_missing_name():
    """ Test de création d'utilisateur sans nom (champ requis) """
    user = create_user("", "no-name.jpg", "No name provided")
    assert user is None or user.status_code >= 400  # Probablement une erreur? 404? 5?

def test_post_with_invalid_user_id():
    """ Création de post avec un user_id invalide (UUID inexistant) """
    fake_id = str(uuid.uuid4())
    post = create_post("Test post", fake_id)
    assert post is None or post.status_code >= 400

def test_get_all_users_returns_list():
    """ Vérifie que l'on reçoit une liste d'utilisateurs """
    response = get_all_users()
    assert isinstance(response, list)
    for user in response:
        assert "id" in user and "name" in user

def test_get_all_posts_returns_list():
    """ Vérifie que l'on reçoit une liste de posts """
    response = get_all_posts()
    assert isinstance(response, list)
    for post in response:
        assert "id" in post and "userId" in post

def test_user_id_is_uuid():
    """ Vérifie que l'ID généré pour un utilisateur est bien un UUID valide """
    user = create_user("UUID Test", "uuid.jpg", "Check ID format")
    try:
        uuid_obj = uuid.UUID(user["id"])
        assert str(uuid_obj) == user["id"]
    except Exception:
        assert False, "ID is not a valid UUID"

def test_create_duplicate_users():
    """ Création de deux utilisateurs avec les mêmes données """
    user1 = create_user("Duplicate", "dup.jpg", "Same user")
    user2 = create_user("Duplicate", "dup.jpg", "Same user")
    assert user1 is not None
    assert user2 is not None
    assert user1["id"] != user2["id"]  # voir si le uuid est pas broken
