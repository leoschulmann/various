import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String input = args.length == 0 ? new Scanner(System.in).nextLine() : args[0];
        String[] tokens = input.split(" ");
        Map<String, Integer> map = new HashMap<>();
        Arrays.stream(tokens).forEach(s -> map.compute(s,
                (str, integer) -> map.containsKey(str) ? map.get(str) + 1 : 1));

        map.keySet().stream().map(key -> new Tuple(key, map.get(key)))
                .sorted().limit(10).forEach(System.out::println);
    }
}

class Tuple implements Comparable<Tuple> {
    private final String k;
    private final Integer v;

    public Tuple(String k, Integer v) {
        this.k = k;
        this.v = v;
    }

    public String getK() {
        return k;
    }

    public Integer getV() {
        return v;
    }

    @Override
    public int compareTo(Tuple o) {
        if (this.v < o.v) return 1;
        else if (this.v > o.v) return -1;
        else return this.k.compareTo(o.k);
    }

    @Override
    public String toString() {
        return v + " - " + k;
    }
}

