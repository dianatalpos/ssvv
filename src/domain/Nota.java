package domain;

public class Nota implements HasID<Pair<Integer, Integer>> {
    Pair<Integer, Integer> idNota;
    private double nota;
    private int saptamanaPredare;
    private String feedback;

    public Nota(Pair<Integer, Integer> idNota, double nota, int saptamanaPredare, String feedback) {
        this.idNota = idNota;
        this.nota = nota;
        this.saptamanaPredare = saptamanaPredare;
        this.feedback = feedback;
    }

    @Override
    public Pair<Integer, Integer> getID() { return idNota; }

    @Override
    public void setID(Pair<Integer, Integer> idNota) { this.idNota = idNota; }

    public double getNota() { return nota; }

    public void setNota(double nota) { this.nota = nota; }

    public int getSaptamanaPredare() { return saptamanaPredare; }

    public void setSaptamanaPredare(int saptamanaPredare) { this.saptamanaPredare = saptamanaPredare; }

    public String getFeedback() { return feedback; }

    public void setFeedback(String feedback) { this.feedback = feedback; }

    @Override
    public String toString() {
        return "Nota{" +
                "id_student = " + idNota.getObject1() +
                ", id_tema = " + idNota.getObject2() +
                ", nota = " + nota +
                ", saptamanaPredare = " + saptamanaPredare +
                ", feedback = '" + feedback + '\'' +
                '}';
    }
}
