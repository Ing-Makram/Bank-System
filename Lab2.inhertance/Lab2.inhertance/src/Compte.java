import lombok.*;

import java.time.LocalDate;
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public abstract class Compte {
    @EqualsAndHashCode.Include
    private String rib;
    private double solde;
    private LocalDate dateCreation;
    private EtatCompte etat;

    public Compte(String rib, double solde, EtatCompte etat) {
        this.rib = rib;
        this.solde = solde;
        this.dateCreation = LocalDate.now();
        this.etat = etat;
    }

    public void deposer(double montant) {
        if (montant <= 0) {
            throw new IllegalArgumentException("Le montant doit être supérieur à zéro.");
        }
        this.solde += montant;
    }

    public void retirer(double montant) {
        throw new UnsupportedOperationException("Cette méthode doit être redéfinie dans les sous-classes.");
    }

    public String getRib() {
        return rib;
    }

    public void setRib(String rib) {
        this.rib = rib;
    }

    public double getSolde() {
        return solde;
    }

    public void setSolde(double solde) {
        this.solde = solde;
    }

    public LocalDate getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(LocalDate dateCreation) {
        this.dateCreation = dateCreation;
    }

    public EtatCompte getEtat() {
        return etat;
    }

    public void setEtat(EtatCompte etat) {
        this.etat = etat;
    }
}
