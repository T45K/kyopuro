package AtCoder.ABC.ABC260.B;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.StringTokenizer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int n = scanner.nextInt();
        final int x = scanner.nextInt();
        final int y = scanner.nextInt();
        final int z = scanner.nextInt();

        final List<Integer> englishScores = Stream.generate(scanner::nextInt)
            .limit(n)
            .collect(Collectors.toList());
        final List<Integer> mathScores = Stream.generate(scanner::nextInt)
            .limit(n)
            .collect(Collectors.toList());
        final List<Student> students = IntStream.range(0, n)
            .mapToObj(i -> new Student(i, englishScores.get(i), mathScores.get(i)))
            .collect(Collectors.toList());

        final List<Student> englishHighScoreStudents = students.stream()
            .sorted(Comparator.comparing(Student::getEnglishScore, Comparator.reverseOrder()).thenComparing(Student::getId))
            .collect(Collectors.toList());

        final List<Student> mathHighScoreStudents = englishHighScoreStudents.subList(x, englishHighScoreStudents.size()).stream()
            .sorted(Comparator.comparing(Student::getMathScore, Comparator.reverseOrder()).thenComparing(Student::getId))
            .collect(Collectors.toList());

        final List<Student> sumHighScoreStudents = mathHighScoreStudents.subList(y, mathHighScoreStudents.size()).stream()
            .sorted(Comparator.comparing(Student::sumScore, Comparator.reverseOrder()).thenComparing(Student::getId))
            .collect(Collectors.toList());

        final String answer = Stream.concat(
                englishHighScoreStudents.stream().limit(x),
                Stream.concat(mathHighScoreStudents.stream().limit(y),
                    sumHighScoreStudents.stream().limit(z))
            ).map(student -> student.id + 1)
            .sorted()
            .map(Objects::toString)
            .collect(Collectors.joining("\n"));
        System.out.println(answer);
    }

    private static class Student {
        final int id;
        final int englishScore;
        final int mathScore;

        Student(final int id, final int englishScore, final int mathScore) {
            this.id = id;
            this.englishScore = englishScore;
            this.mathScore = mathScore;
        }

        int getId() {
            return id;
        }

        int getEnglishScore() {
            return englishScore;
        }

        int getMathScore() {
            return mathScore;
        }

        int sumScore() {
            return englishScore + mathScore;
        }
    }

    private static class FastScanner {
        private final BufferedReader reader;
        private StringTokenizer tokenizer;

        FastScanner(final InputStream in) {
            reader = new BufferedReader(new InputStreamReader(in));
        }

        String next() {
            if (tokenizer == null || !tokenizer.hasMoreTokens()) {
                try {
                    tokenizer = new StringTokenizer(reader.readLine());
                } catch (final IOException e) {
                    throw new RuntimeException(e);
                }
            }
            return tokenizer.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }
    }
}
