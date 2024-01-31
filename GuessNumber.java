import java.util.Random;

public class GuessNumber {
    public static void main(String[] args){
        Random rand = new Random();
        int target = rand.nextInt(100) + 1;
        int guess = 0;

        while (guess != target) {
            guess = rand.nextInt(100) + 1;

            System.out.println("Guessing: " + guess);
        }
        System.out.println("Found the number: " + target);
    }

    
}
