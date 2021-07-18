package BolshovaSVdz1;

public class Robot implements Competitable {
    private int JumpHeight;
    private int RunLenght;

    public Robot(int jumpHeight, int runLenght) {
        JumpHeight = jumpHeight;
        RunLenght = runLenght;
    }

    @Override
    public void jump(int height) {
        if (height > JumpHeight) {
            System.out.println("Робот не перепрыгнул!");
        } else {
            System.out.println("Робот смог перепрыгнуть!");
        }
    }

    @Override
    public void run(int lenght) {
        if (lenght>RunLenght) {
            System.out.println("Робот не пробежал!");
        }else {
            System.out.println("Робот пробежал!");

        }
    }
}
