public class CompteCourant extends Compte {
    private static final double SOLDE_MINIMUM = -900;

    public CompteCourant(String rib, double solde, EtatCompte etat) {
        super(rib, solde, etat);
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
}
