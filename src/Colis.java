package src;

public abstract class Colis implements Comparable<Colis> {
    private String code;
    private int poids;
    private boolean horsDakar;

    public Colis(String code, int poids, boolean horsDakar) {
        if (poids <= 0) {
            throw new IllegalArgumentException("Le poids doit être strictement positif.");
        }
        this.code = code;
        this.poids = poids;
        this.horsDakar = horsDakar;
    }

    public String getCode() { return code; }
    public int getPoids() { return poids; }
    public boolean isHorsDakar() { return horsDakar; }

    abstract long tarif();
    abstract String sigle();
    abstract int priorite();

    protected long tarifBase() {
        return (long) poids * Parametres.TARIF_KG + (horsDakar ? Parametres.SURCHARGE_HORS_DAKAR : 0);
    }

    @Override
    public String toString() {
        return "[" + sigle() + "] " + code + " " + poids + "kg " + (horsDakar ? "hors-Dakar" : "Dakar") + " " + tarif() + " FCFA";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Colis colis = (Colis) o;
        return code.equals(colis.code);
    }

    @Override
    public int hashCode() {
        return code.hashCode();
    }

    @Override
    public int compareTo(Colis autre) {
        if (this.priorite() != autre.priorite()) {
            return Integer.compare(autre.priorite(), this.priorite());
        }
        if (this.tarif() != autre.tarif()) {
            return Long.compare(autre.tarif(), this.tarif());
        }
        return this.code.compareTo(autre.code);
    }
}