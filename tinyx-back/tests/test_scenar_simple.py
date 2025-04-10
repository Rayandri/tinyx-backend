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
        user_id = auth_controller.test_create_user("testuser", "michel")

if __name__ == "__main__":
    unittest.main()