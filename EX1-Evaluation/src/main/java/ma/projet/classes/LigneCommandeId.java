package ma.projet.classes;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class LigneCommandeId implements Serializable {
    private Long produitId;
    private Long commandeId;

    public LigneCommandeId() {}
    public LigneCommandeId(Long produitId, Long commandeId) {
        this.produitId=produitId; this.commandeId=commandeId;
    }

    @Override public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LigneCommandeId)) return false;
        LigneCommandeId that = (LigneCommandeId) o;
        return Objects.equals(produitId, that.produitId) &&
                Objects.equals(commandeId, that.commandeId);
    }
    @Override public int hashCode() { return Objects.hash(produitId, commandeId); }

    // getters/setters
    public Long getProduitId() { return produitId; }
    public void setProduitId(Long produitId) { this.produitId = produitId; }
    public Long getCommandeId() { return commandeId; }
    public void setCommandeId(Long commandeId) { this.commandeId = commandeId;}

}
