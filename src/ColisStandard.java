package src;

public class ColisStandard extends Colis {
    public ColisStandard(String code, int poids, boolean horsDakar) {
        super(code, poids, horsDakar);
    }

    @Override
    long tarif() { return tarifBase(); }

    @Override
    String sigle() { return "STD"; }

    @Override
    int priorite() { return 1; }
}