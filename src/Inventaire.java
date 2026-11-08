package src;

public class Inventaire {

    public static long valeurAssuree(Object[] objets) {
        if (objets == null) {
            return 0L;
        }
        long somme = 0;
        for (Object obj : objets) {
            if (obj instanceof ColisFragile) {
                somme += ((ColisFragile) obj).getValeurDeclaree();
            }
        }
        return somme;
    }

    public static int[] compterParType(Object[] objets) {
        int standard = 0;
        int express = 0;
        int fragile = 0;
        int autres = 0;

        if (objets != null) {
            for (Object obj : objets) {
                if (obj == null) {
                    autres++;
                    continue;
                }
                // Test ColisFragile en premier car il hérite de ColisStandard
                if (obj instanceof ColisFragile) {
                    fragile++;
                } else if (obj instanceof ColisStandard) {
                    standard++;
                } else if (obj instanceof ColisExpress) {
                    express++;
                } else {
                    autres++;
                }
            }
        }
        return new int[] { standard, express, fragile, autres };
    }
}