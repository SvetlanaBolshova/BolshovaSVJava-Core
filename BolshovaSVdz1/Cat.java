package BolshovaSVdz1;

public class Cat implements Competitable {
    private int JumpHeight;
    private int RunLenght;

    public Cat(int jumpHeight, int runLenght) {
        JumpHeight = jumpHeight;
        RunLenght = runLenght;
    }

    @Override
    public void jump(int height) {
        if (height > JumpHeight) {
            System.out.println("Кот не перепрыгнул!");
        } else {
            System.out.println("Кот смог перепрыгнуть!");
        }
    }

    @Override
    public void run(int lenght) {
        if (lenght>RunLenght) {
            System.out.println("Кот не пробежал!");
        }else {
            System.out.println("Кот пробежал!");

        }
    }
}
