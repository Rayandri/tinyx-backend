import unittest
from test_post_controller import TestPostController
from test_auth import TestAuthController
from test_user_controller import TestUserController
from test_user_timeline import TestUserTimeline
import random
import string

#Ceci est un exemple de scénario (il marche pas car la il y a pas de user créé dans la db mais c'est la structure)

class TestSimpleScenario(unittest.TestCase):

    # Initialisation des objets de test
    auth_controller = TestAuthController()
    post_controller = TestPostController()
    user_controller = TestUserController()
    user_timeline = TestUserTimeline()

    def random_username_generator(self):
        return ''.join(random.choice(string.ascii_letters) for x in range(20))

    """
    Scénario qui créé un user, verifie qu'aucun post de ce user n'existe, change son password puis supprime le user
    """
    def test_scenario_1(self):
        # Étape 1 : Créer un user
        user1_id = self.auth_controller.test_create_user(self.random_username_generator(), "password")["id"]
        user2_id = self.auth_controller.test_create_user(self.random_username_generator(), "password")["id"]
        # Étape 2 : Publier un poste
        post_id = self.post_controller.test_add_post(user1_id, "This is a test poste", media="", repost="", replyto="")["id"]
        # Étape 3 :
        self.user_controller.test_add_like(post_id, user2_id)
        # Étape 4 : Récupérer le timeline
        self.user_timeline.test_get_full_timeline(user1_id)
        # Étape 5 : Supprimer les utilisateurs
        self.auth_controller.test_delete(user1_id)
        self.auth_controller.test_delete(user2_id)
if __name__ == "__main__":
    unittest.main()

