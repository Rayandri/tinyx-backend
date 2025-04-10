import unittest
from test_post_controller import TestPostController
from test_auth import TestAuthController

#Ceci est un exemple de scénario (il marche pas car la il y a pas de user créé dans la db mais c'est la structure)

class TestSimpleScenario(unittest.TestCase):

    # Initialisation des objets de test
    auth_controller = TestAuthController()
    post_controller = TestPostController()

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

if __name__ == "__main__":
    unittest.main()