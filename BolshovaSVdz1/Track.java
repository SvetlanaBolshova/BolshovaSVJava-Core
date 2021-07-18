package BolshovaSVdz1;

public class Track implements Surpass  {
    private int lenght;

    public Track(int lenght) {
        this.lenght = lenght;
    }

    public void surpass(Competitable competitable) {
        competitable.run(lenght);
    }

}
