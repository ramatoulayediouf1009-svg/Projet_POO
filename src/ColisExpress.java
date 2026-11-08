package src;

public class ColisExpress extends Colis {
    public ColisExpress(String code, int poids, boolean horsDakar) {
        super(code, poids, horsDakar);
    }

    @Override
    long tarif() { return tarifBase() * 3 / 2; }

    @Override
    String sigle() { return "EXP"; }

    @Override
    int priorite() { return 3; }
}