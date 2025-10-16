package ma.projet;

import ma.projet.classes.*;
import ma.projet.service.*;

import java.time.LocalDate;
import java.util.List;

public class TestApp {
    public static void main(String[] args) {
        CategorieService sc = new CategorieService();
        ProduitService sp = new ProduitService();
        CommandeService scmd = new CommandeService();
        LigneCommandeService sl = new LigneCommandeService();

        // 1) Données de base
        Categorie pc = sc.add(new Categorie("PC", "Ordinateurs"));
        Categorie acc = sc.add(new Categorie("ACC", "Accessoires"));

        Produit p1 = sp.add(new Produit("ES12", 120, pc));
        Produit p2 = sp.add(new Produit("ZR85", 100, acc));
        Produit p3 = sp.add(new Produit("EE85", 200, pc));

        Commande c1 = scmd.add(new Commande(LocalDate.of(2013,3,14)));
        Commande c2 = scmd.add(new Commande(LocalDate.now()));

        // 2) Lignes commande
        // IMPORTANT: sauver la commande et le produit AVANT de créer la ligne,
        // puis construire la clé composite.
        sl.add(new LigneCommandeProduit(p1, c1, 7));
        sl.add(new LigneCommandeProduit(p2, c1, 14));
        sl.add(new LigneCommandeProduit(p3, c1, 5));

        sl.add(new LigneCommandeProduit(p1, c2, 2));

        // A) Produits par catégorie
        System.out.println("Produits catégorie PC:");
        sp.findByCategorie(pc.getId()).forEach(pr -> System.out.println("- " + pr.getReference()+" : "+pr.getPrix()+" DH"));

        // B) Produits commandés entre deux dates
        System.out.println("\nProduits commandés entre 2013-01-01 et 2013-12-31:");
        sp.findCommandesBetween(LocalDate.of(2013,1,1), LocalDate.of(2013,12,31))
                .forEach(pr -> System.out.println("- " + pr.getReference()));

        // C) Produits d’une commande donnée (format d'affichage demandé)
        Long idCommande = c1.getId();
        System.out.println("\nCommande : " + idCommande + "     Date : " + c1.getDate());
        System.out.println("Liste des produits :");
        System.out.println("Référence\tPrix\tQuantité");
        List<Object[]> lignes = sp.findProduitsInCommande(idCommande);
        for (Object[] row : lignes) {
            System.out.println(row[0] + "\t" + row[1] + " DH\t" + row[2]);
        }

        // D) Produits dont le prix > 100 (NamedQuery)
        System.out.println("\nProduits prix > 100 DH :");
        sp.findPrixSup100().forEach(pr -> System.out.println("- " + pr.getReference()+" : "+pr.getPrix()));
    }
}
