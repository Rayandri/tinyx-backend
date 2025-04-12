import unittest
from test_post_controller import TestPostController
from test_auth import TestAuthController
from test_user_controller import TestUserController
from test_user_timeline import TestUserTimeline
import random
import string
import uuid
import requests

#Ceci est un exemple de scénario (il marche pas car la il y a pas de user créé dans la db mais c'est la structure)

class TestUserScenario(unittest.TestCase):

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
        # Création d'un utilisateur
        username = self.random_username_generator()
        response = self.auth_controller.test_create_user(username=username, password="password")
        user_id = response["id"]

        # Vérification qu'aucun post n'existe pour cet utilisateur
#        response = self.post_controller.test_get_user_posts(user_id=user_id)
#        self.assertEqual(len(response), 0)

        # Ajout d'un post
        response = self.post_controller.test_add_post(user_id=user_id, content="This is a test post")

        # Vérification que le post a été ajouté à la timeline de l'utilisateur
        response = self.user_timeline.test_get_full_timeline(uuid=user_id)
        self.assertEqual(len(response["entries"]), 1)
        self.assertEqual(response["userId"], user_id)

        # Vérification du id du post de la timeline
#        post_id = response["entries"][0]["postId"]
#     response = self.post_controller.test_get_post(post_id=post_id)
 #       self.assertEqual(response["id"], post_id)
  #      self.assertEqual(response["content"], "This is a test post")


if __name__ == "__main__":
    unittest.main()

