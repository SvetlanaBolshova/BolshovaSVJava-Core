package BolshovaSVdz1;

public class Wall implements Surpass {
    private int height;

    public Wall(int height) {
        this.height = height;
    }

    public void  surpass(Competitable competitable) {
competitable.jump(height);

    }

}

