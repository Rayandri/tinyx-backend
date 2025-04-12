import unittest
from test_post_controller import TestPostController
from test_auth import TestAuthController
from test_user_controller import TestUserController
import random
import string


# Ceci est un exemple de scénario (il marche pas car la il y a pas de user créé dans la db mais c'est la structure)

class TestSimpleScenario(unittest.TestCase):
    # Initialisation des objets de test
    auth_controller = TestAuthController()
    post_controller = TestPostController()
    user_controller = TestUserController()

    def random_username_generator(self):
        return ''.join(random.choice(string.ascii_letters) for x in range(20))

    """
    Scénario qui créé un user, verifie qu'aucun post de ce user n'existe, change son password puis supprime le user
    """

    def test_scenario_1(self):
        # Étape 1 : Créer un user
        user_id = self.auth_controller.test_create_user(self.random_username_generator(), "password")["id"]
        # Étape 2 : Vérifie si un poste existe (spoiler : non)
        self.post_controller.test_get_post(user_id, 404)
        # Étape 3 : Change le password du user
        self.auth_controller.test_update_password(user_id, "new_password")
        # Étape 4 : Supprimer l'utilisateur
        self.auth_controller.test_delete(user_id)

    """
    Scénario qui créé un user, publie un post, vérifie que le poste existe puis supprime le post et supprime le user
    """

    def test_scenario_2(self):
        # Étape 1 : Créer un user
        user_id = self.auth_controller.test_create_user(self.random_username_generator(), "password")["id"]
        # Étape 2 : Publier un poste
        post_id = self.post_controller.test_add_post(user_id, "This is a test poste", media="", repost="", replyto="")[
            "id"]
        # Étape 3 : Vérifie si un poste existe
        self.post_controller.test_get_post(user_id, 200)
        # Étape 4 : Supprimer le post
        self.post_controller.test_delete_post(user_id, post_id)
        # Étape 5 : Supprimer le user
        self.auth_controller.test_delete(user_id)

    """
    Scénario qui créé deux user, l'un publie un post, l'autre like le post puis supprime les user
    """

    def test_scenario_3(self):
        # Étape 1 : Créer deux user
        user1_id = self.auth_controller.test_create_user(self.random_username_generator(), "password")["id"]
        user2_id = self.auth_controller.test_create_user(self.random_username_generator(), "password")["id"]
        # Étape 2 : Publier un poste
        post_id = self.post_controller.test_add_post(user1_id, "This is a test poste", media="", repost="", replyto="")[
            "id"]
        # Étape 3 :
        self.user_controller.test_add_like(post_id, user2_id)
        # Étape 4 : Supprimer les utilisateurs
        self.auth_controller.test_delete(user1_id)
        self.auth_controller.test_delete(user2_id)

    """
    Scénario qui créé deux user, l'un follow l'autre, l'unfollow puis supprime les user
    """

    def test_scenario_4(self):
        # Étape 1 : Créer deux user
        user1_id = self.auth_controller.test_create_user(self.random_username_generator(), "password")["id"]
        user2_id = self.auth_controller.test_create_user(self.random_username_generator(), "password")["id"]
        # Étape 2 : User2 follow User1
        self.user_controller.test_add_follow(user1_id, user2_id)
        # Étape 3 : Affiche les followers de User1
        self.user_controller.test_get_user_follows(user1_id)
        # Étape 4 : User2 unfollow User1
        self.user_controller.test_unfollow(user2_id, user1_id)
        # Étape 5 : Affiche les followers de User1
        self.user_controller.test_get_user_follows(user1_id, 404)
        # Étape 6 : Supprimer les utilisateurs
        self.auth_controller.test_delete(user1_id)
        self.auth_controller.test_delete(user2_id)

    """
    Scénario qui créé deux user, l'un bloque l'autre, le débloque puis supprime les user
    """

    def test_scenario_5(self):
        # Étape 1 : Créer deux user
        user1_id = self.auth_controller.test_create_user(self.random_username_generator(), "password")["id"]
        user2_id = self.auth_controller.test_create_user(self.random_username_generator(), "password")["id"]
        # Étape 2 : User1 bloque User2
        self.user_controller.test_add_block(user1_id, user2_id)
        # Étape 3 : Affiche les users bloqué de User1
        self.user_controller.test_get_user_blocked(user1_id)
        # Étape 4 : Affiche les users qui ont bloqué User2
        self.user_controller.test_get_user_blockers(user2_id)
        # Étape 5 : User1 débloque User2
        self.user_controller.test_unblock(user1_id, user2_id)
        # Étape 6 : Supprimer les utilisateurs
        self.auth_controller.test_delete(user1_id)
        self.auth_controller.test_delete(user2_id)

    """
    Scénario qui créé un user, qui se login correctement, qui change de mot de passe et supprime l'user
    """

    def test_scenario_6(self):
        # Étape 1 : Créer un user
        user1_id = self.auth_controller.test_create_user("user1", "password")["id"]
        # Étape 2 : Se login
        self.auth_controller.test_login("user1", "password")
        # Étape 3 : User modifie le mot de passe
        self.auth_controller.test_update_password(user1_id)
        # Étape 4 : Supprimer le user
        self.auth_controller.test_delete(user1_id)

    """
    Scénario qui créé un user, qui met le mauvais mot de passe et supprime l'user
    """

    def test_scenario_7(self):
        # Étape 1 : Créer un user
        user1_id = self.auth_controller.test_create_user("user3", "password")["id"]
        # Étape 2 : Se login avec le mauvais mot de passe
        try:
            self.auth_controller.test_login("user3", "bad_password", 404)
            self.auth_controller.test_delete(user1_id)
            # make test fail
            self.assertTrue(False, "Login should have failed")
        except:
            self.auth_controller.test_delete(user1_id)

    """
    Scénario qui créé deux users avec le meme username et supprime un user
    """

    def test_scenario_8(self):
        # Étape 1 : Créer un user
        user1_id = self.auth_controller.test_create_user("user1", "password")["id"]
        # Étape 2 : Créer un user avec le meme username
        self.auth_controller.test_create_user("user1", "other_password", 409)
        # Étape 3 : Affiche les users
        self.auth_controller.test_get_users()
        # Étape 4 : Supprimer le user
        self.auth_controller.test_delete(user1_id)


if __name__ == "__main__":
    unittest.main()
