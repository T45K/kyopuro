package ABC.ABC128;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class B {
    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int counter = scanner.nextInt();
        final Map<String, List<Integer>> map = new HashMap<>();
        final Map<Integer,Integer> indexMap = new HashMap<>();
        scanner.nextLine();

        for(int i = 0;i<counter;i++){
            final String[] split = scanner.nextLine().split("[\\s]");
            map.computeIfAbsent(split[0], v -> new ArrayList<>())
                    .add(Integer.parseInt(split[1]));
            indexMap.put(Integer.parseInt(split[1]), i + 1);
        }

        final List<String> cities = new ArrayList<>();
        for (final Map.Entry<String, List<Integer>> entry : map.entrySet()) {
            cities.add(entry.getKey());
            entry.getValue().sort(Comparator.reverseOrder());
        }

        cities.sort(Comparator.naturalOrder());

        for (final String city : cities) {
            for (final Integer integer : map.get(city)) {
                System.out.println(indexMap.get(integer));
            }
        }
    }
}
