import lombok.*;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Banque {
    private Map<String, Compte> comptes= new HashMap<>();;
    public void ajouterCompte(Compte compte) {
        if (comptes.containsKey(compte.getRib())) {
            throw new IllegalArgumentException("Un compte avec ce RIB existe déjà.");
        }
        comptes.put(compte.getRib(), compte);
    }

    public void supprimerCompte(String rib) {
        if (!comptes.containsKey(rib)) {
            throw new IllegalArgumentException("Aucun compte trouvé avec ce RIB.");
        }
        comptes.remove(rib);
    }

    public Compte rechercherCompte(String rib) {
        return comptes.getOrDefault(rib, null);
    }

    public void afficherTousLesComptes() {
        for (Compte compte : comptes.values()) {
            System.out.println(compte);
        }
    }

    public void transferer(String ribSource, String ribDestination, double montant) {
        Compte source = rechercherCompte(ribSource);
        Compte destination = rechercherCompte(ribDestination);

        if (source == null || destination == null) {
            throw new IllegalArgumentException("Un ou plusieurs RIB sont introuvables.");
        }
        if (source.getEtat() != EtatCompte.ACTIF || destination.getEtat() != EtatCompte.ACTIF) {
            throw new IllegalStateException("L'un des comptes est inactif.");
        }

        try {
            source.retirer(montant);
            destination.deposer(montant);
            System.out.println("Transfert de " + montant + "€ de " + ribSource + " vers " + ribDestination + " réussi.");
        } catch (Exception e) {
            System.out.println("Échec du transfert : " + e.getMessage());
        }
    }

    public List<Compte> getComptesAvecSoldeNegatif() {
        return comptes.values().stream()
                .filter(compte -> compte.getSolde() < 0)
                .collect(Collectors.toList());
    }

    public List<CompteEpargne> getComptesEpargneRecents() {
        LocalDate threeYearsAgo = LocalDate.now().minusYears(3);
        return comptes.values().stream()
                .filter(c -> c instanceof CompteEpargne)
                .map(c -> (CompteEpargne) c)
                .filter(c -> c.getDateCreation().isAfter(threeYearsAgo))
                .collect(Collectors.toList());
    }
}
