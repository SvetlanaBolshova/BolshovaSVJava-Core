package BolshovaSVdz1;

public class Human implements Competitable {
    private int JumpHeight;
    private int RunLenght;

    public Human(int jumpHeight, int runLenght) {
        JumpHeight = jumpHeight;
        RunLenght = runLenght;
    }

    @Override
    public void jump(int height) {
        if (height > JumpHeight) {
            System.out.println("Человек не перепрыгнул!");
        } else {
            System.out.println("Человек смог перепрыгнуть!");
        }
    }

    @Override
    public void run(int lenght) {
        if (lenght>RunLenght) {
            System.out.println("Человек не пробежал!");
        }else {
            System.out.println("Человек пробежал!");

        }
    }
}
