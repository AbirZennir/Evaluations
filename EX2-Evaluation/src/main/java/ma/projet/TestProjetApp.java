package ma.projet;

import ma.projet.classes.*;
import ma.projet.service.*;

import java.time.LocalDate;

public class TestProjetApp {
    public static void main(String[] args) {
        EmployeService se = new EmployeService();
        ProjetService sp = new ProjetService();
        TacheService st = new TacheService();
        EmployeTacheService set = new EmployeTacheService();

        // Données
        Employe chef = se.add(new Employe("Zennir","Abir","0600000000"));
        Employe dev1 = se.add(new Employe("Ali","Ali","0700000000"));

        Projet prj = sp.add(new Projet("Gestion de stock",
                LocalDate.of(2013,1,14),
                LocalDate.of(2013,12,31),
                chef));

        Tache t1 = st.add(new Tache("Analyse",      LocalDate.of(2013,2,1),  LocalDate.of(2013,2,20), 1500, prj));
        Tache t2 = st.add(new Tache("Conception",   LocalDate.of(2013,3,1),  LocalDate.of(2013,3,15), 2200, prj));
        Tache t3 = st.add(new Tache("Développement",LocalDate.of(2013,4,1),  LocalDate.of(2013,4,30), 8000, prj));

        // dates réelles via EmployeTache
        set.add(new EmployeTache(dev1, t1, LocalDate.of(2013,2,10), LocalDate.of(2013,2,20)));
        set.add(new EmployeTache(dev1, t2, LocalDate.of(2013,3,10), LocalDate.of(2013,3,15)));
        set.add(new EmployeTache(dev1, t3, LocalDate.of(2013,4,10), LocalDate.of(2013,4,25)));

        // Affichages demandés
        System.out.printf("Projet : %d   Nom : %s   Date début : %s%n",
                prj.getId(), prj.getNom(), prj.getDateDebut());
        System.out.println("Liste des tâches (réelles) :");
        System.out.println("Num\tNom\t\tDate Début Réelle\tDate Fin Réelle");
        sp.tachesRealisees(prj.getId()).forEach(row ->
                System.out.printf("%s\t%-12s\t%s\t\t%s%n", row[0], row[1], row[2], row[3])
        );

        System.out.println("\nTâches planifiées du projet :");
        sp.tachesPlanifiees(prj.getId()).forEach(row ->
                System.out.printf("%s\t%-12s\tprev:%s -> %s\tprix:%.2f%n", row[0], row[1], row[2], row[3], row[4])
        );

        System.out.println("\nTâches > 1000 DH :");
        st.prixSup1000().forEach(t -> System.out.println("- " + t.getNom() + " (" + t.getPrix() + " DH)"));

        System.out.println("\nTâches réalisées entre 2013-02-01 et 2013-03-31 :");
        st.realiseesEntre(LocalDate.of(2013,2,1), LocalDate.of(2013,3,31))
                .forEach(row -> System.out.printf("%s - %s : %s -> %s%n", row[0], row[1], row[2], row[3]));

        System.out.println("\nTâches réalisées par l’employé Ali Ali :");
        se.tachesRealisees(dev1.getId())
                .forEach(row -> System.out.printf("%s - %s : %s -> %s%n", row[0], row[1], row[2], row[3]));

        System.out.println("\nProjets gérés par Abir Zennir (chef de projet) :");
        se.projetsGeres(chef.getId())
                .forEach(row -> System.out.printf("%s - %s (%s -> %s)%n", row[0], row[1], row[2], row[3]));
    }
}
