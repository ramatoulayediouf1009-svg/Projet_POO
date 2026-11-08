package src;

public class Tournee {

    // a) Tri fusion sur place selon compareTo
    public static void trierFusion(Colis[] t) {
        if (t == null || t.length <= 1) return;
        int mid = t.length / 2;
        Colis[] gauche = new Colis[mid];
        Colis[] droite = new Colis[t.length - mid];

        System.arraycopy(t, 0, gauche, 0, mid);
        System.arraycopy(t, mid, droite, 0, t.length - mid);

        trierFusion(gauche);
        trierFusion(droite);

        fusionner(t, gauche, droite);
    }

    private static void fusionner(Colis[] t, Colis[] gauche, Colis[] droite) {
        int i = 0, j = 0, k = 0;
        while (i < gauche.length && j < droite.length) {
            if (gauche[i].compareTo(droite[j]) <= 0) {
                t[k++] = gauche[i++];
            } else {
                t[k++] = droite[j++];
            }
        }
        while (i < gauche.length) t[k++] = gauche[i++];
        while (j < droite.length) t[k++] = droite[j++];
    }

    // b) Chargement optimal par programmation dynamique
    public static Colis[] chargementOptimal(Colis[] t, int capacite) {
        int n = t.length;
        long[][] meilleur = new long[n + 1][capacite + 1];

        for (int i = 1; i <= n; i++) {
            int poidsI = t[i - 1].getPoids();
            long tarifI = t[i - 1].tarif();
            for (int cap = 0; cap <= capacite; cap++) {
                if (poidsI > cap) {
                    meilleur[i][cap] = meilleur[i - 1][cap];
                } else {
                    meilleur[i][cap] = Math.max(meilleur[i - 1][cap], meilleur[i - 1][cap - poidsI] + tarifI);
                }
            }
        }

        // Retrouver les colis choisis en remontant la matrice
        int capRestante = capacite;
        int count = 0;
        boolean[] pris = new boolean[n];
        for (int i = n; i > 0; i--) {
            if (meilleur[i][capRestante] != meilleur[i - 1][capRestante]) {
                pris[i - 1] = true;
                count++;
                capRestante -= t[i - 1].getPoids();
            }
        }

        Colis[] resultat = new Colis[count];
        int idx = 0;
        for (int i = 0; i < n; i++) {
            if (pris[i]) {
                resultat[idx++] = t[i];
            }
        }
        return resultat;
    }

    // c) Programme principal (main)
    public static void main(String[] args) {
        Colis[] lot = {
            new ColisStandard("C01", 4, false),
            new ColisExpress("C02", 3, true),
            new ColisFragile("C03", 5, false, 120_000),
            new ColisStandard("C04", 7, true),
            new ColisExpress("C05", 2, false),
            new ColisFragile("C06", 6, true, 45_000),
            new ColisStandard("C07", 1, true),
            new ColisExpress("C08", 8, false),
            new ColisFragile("C09", 3, false, 300_000),
            new ColisStandard("C10", 5, false),
        };

        // Tri du tableau avec le tri fusion
        trierFusion(lot);

        // Affichage de chaque colis trié sur une ligne
        for (Colis c : lot) {
            System.out.println(c);
        }

        // Calcul du chargement optimal en utilisant Parametres.CAPACITE
        Colis[] optimal = chargementOptimal(lot, Parametres.CAPACITE);

        // Calcul de la somme des tarifs de la solution optimale
        long sommeTarifs = 0;
        for (Colis c : optimal) {
            sommeTarifs += c.tarif();
        }

        // Affichage final au format exact demandé
        System.out.println("Recette optimale : " + sommeTarifs + " FCFA (capacite " + Parametres.CAPACITE + " kg)");
    }
}