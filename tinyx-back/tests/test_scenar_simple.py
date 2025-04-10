import unittest
from test_user_controller import TestUserController
from test_auth import TestAuthController

#Ceci est un exemple de scénario (il marche pas car la il y a pas de user créé dans la db mais c'est la structure)

class TestSimpleScenario(unittest.TestCase):
    def test_scenario(self):
        # Initialisation du test
        auth_controller = TestAuthController()
        user_controller = TestUserController()

        # Étape 1 : Créer un user
        user_id = auth_controller.test_create_user("TestUser", "password")["id"]
        # Étape 2 : Tenter une connexion avec le nouvel utilisateur
        auth_controller.test_login("TestUser", "password")
        # Étapoe 3 : Supprimer l'utilisateur
        auth_controller.test_delete(user_id)

if __name__ == "__main__":
    unittest.main()