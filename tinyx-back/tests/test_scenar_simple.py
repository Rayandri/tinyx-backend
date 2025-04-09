import unittest
from test_user_controller import TestUserController

#Ceci est un exemple de scénario (il marche pas car la il y a pas de user créé dans la db mais c'est la structure)

class TestSimpleScenario(unittest.TestCase):
    def test_scenario(self):
        # Initialisation du test
        user_controller = TestUserController()

        # Étape 1 : Tester l'ajout d'un like
        user_controller.test_add_like()

        # Étape 2 : Vérifier les likes de l'utilisateur
        user_controller.test_get_user_likes()

        # Étape 3 : Supprimer un like
        user_controller.test_unlike()

        # Étape 4 : Vérifier que le like a été supprimé
        user_controller.test_get_user_likes(status_code=404)

if __name__ == "__main__":
    unittest.main()