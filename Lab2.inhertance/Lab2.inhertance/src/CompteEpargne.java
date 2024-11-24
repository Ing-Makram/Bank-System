import java.time.LocalDate;

public class CompteEpargne extends Compte {
    private static final double SOLDE_MINIMUM = 10;
    private double tauxInteret;

    public CompteEpargne(String rib, double solde, EtatCompte etat, double tauxInteret) {
        super(rib, solde, etat);
        if (solde < SOLDE_MINIMUM) {
            throw new IllegalArgumentException("Le solde initial doit être au moins de " + SOLDE_MINIMUM + " euros.");
        }
        this.tauxInteret = tauxInteret;
    }

    public CompteEpargne(String rib, double solde, EtatCompte etat, double tauxInteret, LocalDate dateCreation) {
        super(rib, solde, etat);
        this.tauxInteret = tauxInteret;
        this.setDateCreation(dateCreation);
    }

    @Override
    public void retirer(double montant) {
        if (montant <= 0) {
            throw new IllegalArgumentException("Le montant doit être supérieur à zéro.");
        }
        if (this.getSolde() - montant < SOLDE_MINIMUM) {
            throw new IllegalStateException("Retrait refusé : solde minimum dépassé.");
        }
        this.setSolde(this.getSolde() - montant);
    }

    public void calculerInteret() {
        if (this.getEtat() == EtatCompte.ACTIF) {
            this.setSolde(this.getSolde() + (this.getSolde() * tauxInteret / 100));
        }
    }
}
