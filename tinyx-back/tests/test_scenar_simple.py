import unittest
from test_post_controller import TestPostController
from test_auth import TestAuthController
from test_user_controller import TestUserController

#Ceci est un exemple de scénario (il marche pas car la il y a pas de user créé dans la db mais c'est la structure)

class TestSimpleScenario(unittest.TestCase):

    # Initialisation des objets de test
    auth_controller = TestAuthController()
    post_controller = TestPostController()
    user_controller = TestUserController()

    """
    Scénario qui créé un user, verifie qu'aucun post de ce user n'existe puis supprime le user
    """
    def test_scenario_1(self):
        # Étape 1 : Créer un user
        user_id = self.auth_controller.test_create_user("Test_User1", "password")["id"]
        # Étape 2 : Vérifie si un poste existe (spoiler : non)
        self.post_controller.test_get_post(user_id, 404)
        # Étapoe 3 : Supprimer l'utilisateur
        self.auth_controller.test_delete(user_id)

    """
    Scénario qui créé un user, publie un post, vérifie que le poste existe puis supprime le user
    """
    def test_scenario_2(self):
        # Étape 1 : Créer un user
        user_id = self.auth_controller.test_create_user("Test_User2", "password")["id"]
        # Étape 2 : Publier un poste
        self.post_controller.test_add_post(user_id, "This is a test poste", media="", repost="", replyto="")
        # Étape 3 : Vérifie si un poste existe
        self.post_controller.test_get_post(user_id, 200)
        # Étapoe 4 : Supprimer l'utilisateur
        self.auth_controller.test_delete(user_id)

    """
    Scénario qui créé deux user, l'un publie un post, l'autre like le post puis supprime les user
    """
    def test_scenario_3(self):
        # Étape 1 : Créer deux user
        user1_id = self.auth_controller.test_create_user("Test_User3", "password")["id"]
        user2_id = self.auth_controller.test_create_user("Test_User4", "password")["id"]
        # Étape 2 : Publier un poste
        post_id = self.post_controller.test_add_post(user1_id, "This is a test poste", media="", repost="", replyto="")["id"]
        # Étape 3 :
        self.user_controller.test_add_like(post_id, user2_id)
        # Étape 4 : Supprimer les utilisateurs
        self.auth_controller.test_delete(user1_id)
        self.auth_controller.test_delete(user2_id)

if __name__ == "__main__":
    unittest.main()