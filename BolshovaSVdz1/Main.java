package BolshovaSVdz1;

public class Main {
    public static void main(String[] args) {
        Competitable[] competitors = { new Cat(3, 350), new Human(2,1000),
                new Robot(5, 5000)};

        Surpass[] surpasses ={new Wall(3), new Track(2000)};

        for (Surpass surpass: surpasses){
            for (Competitable competitable:competitors){
                surpass.surpass(competitable);
            }
        }
    }
}
