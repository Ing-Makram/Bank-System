import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        Banque banque = new Banque();

        // Ajouter des comptes
        banque.ajouterCompte(new CompteCourant("12345", 500, EtatCompte.ACTIF));
        banque.ajouterCompte(new CompteCourant("67890", -200, EtatCompte.ACTIF));
        banque.ajouterCompte(new CompteEpargne("11223", 1000, EtatCompte.ACTIF, 1.5, LocalDate.now().minusYears(2)));
        banque.ajouterCompte(new CompteEpargne("44556", 1500, EtatCompte.ACTIF, 2.0, LocalDate.now().minusYears(5)));

        // Rechercher un compte
        System.out.println("Recherche par RIB :");
        System.out.println(banque.rechercherCompte("12345"));

        // Dépôt et retrait
        banque.rechercherCompte("12345").deposer(100);
        banque.rechercherCompte("12345").retirer(50);

        // Afficher tous les comptes
        banque.afficherTousLesComptes();

        // Transfert
        banque.transferer("12345", "11223", 200);

        // Comptes avec solde négatif
        System.out.println("\nComptes avec solde négatif :");
        banque.getComptesAvecSoldeNegatif().forEach(System.out::println);

        // Comptes épargne récents
        System.out.println("\nComptes épargne récents :");
        banque.getComptesEpargneRecents().forEach(System.out::println);
    }
}
