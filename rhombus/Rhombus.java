import java.util.Arrays;

public class Rhombus {

    public static void main(String[] args) {
        Rhombus.draw(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
    }

    static void draw(int height, int width) {
        double tan = (height / 2.) / (width / 2.);
        char[] charArray = new char[width];
        Arrays.fill(charArray, ' ');
        char mark = 'X';

        for (int i = 0; i < height; i++) {
            char[] chars = Arrays.copyOf(charArray, charArray.length);
            int legA = i < height / 2 ? height / 2 - i : i - height / 2;
            int legB = (int) (1 / tan * legA);
            int oppositeB = width - legB - 1;
            chars[legB] = mark;
            chars[oppositeB] = mark;
            System.out.println(chars);
        }
    }
}
