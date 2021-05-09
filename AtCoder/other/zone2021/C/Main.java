package AtCoder.other.zone2021.C;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;
import java.util.function.ToIntFunction;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/*
400点
3人選ばないといけないがN=3,000から二重ループが限界
各要素が大きいほど良い結果を期待できるので、
各要素でソート -> 上三つだけ残しておく -> 二重ループを回しつつ良さそうなのを全探索
 */
public class Main {
    public static void main(final String[] args) throws IOException {
        final FastScanner scanner = new FastScanner(System.in);
        final int n = scanner.nextInt();
        final List<Member> list = IntStream.range(0, n)
            .mapToObj(i -> new Member(i, scanner.nextInt(), scanner.nextInt(), scanner.nextInt(), scanner.nextInt(), scanner.nextInt()))
            .collect(Collectors.toList());
        final List<Member> aList = makeTop3List(list, member -> member.a);
        final List<Member> bList = makeTop3List(list, member -> member.b);
        final List<Member> cList = makeTop3List(list, member -> member.c);
        final List<Member> dList = makeTop3List(list, member -> member.d);
        final List<Member> eList = makeTop3List(list, member -> member.e);

        int max = 0;
        for (int i = 0; i < list.size(); i++) {
            final Member memberA = list.get(i);
            for (int j = i + 1; j < list.size(); j++) {
                final Member memberB = list.get(j);
                max = Math.max(max, select(memberA, memberB, aList));
                max = Math.max(max, select(memberA, memberB, bList));
                max = Math.max(max, select(memberA, memberB, cList));
                max = Math.max(max, select(memberA, memberB, dList));
                max = Math.max(max, select(memberA, memberB, eList));
            }
        }
        System.out.println(max);
    }

    private static List<Member> makeTop3List(final List<Member> members, final ToIntFunction<Member> selector) {
        return members.stream()
            .sorted(Comparator.comparingInt(selector).reversed())
            .limit(3)
            .collect(Collectors.toList());
    }

    private static int select(final Member aMember, final Member bMember, final List<Member> list) {
        final Member member = find(aMember.id, bMember.id, list);
        return calc(aMember, bMember, member);
    }

    private static int calc(final Member mA, final Member mB, final Member mC) {
        int min = Math.max(Math.max(mA.a, mB.a), mC.a);
        min = Math.min(min, Math.max(Math.max(mA.b, mB.b), mC.b));
        min = Math.min(min, Math.max(Math.max(mA.c, mB.c), mC.c));
        min = Math.min(min, Math.max(Math.max(mA.d, mB.d), mC.d));
        return Math.min(min, Math.max(Math.max(mA.e, mB.e), mC.e));
    }

    private static Member find(final int a, final int b, final List<Member> list) {
        final int id0 = list.get(0).id;
        if (id0 != a && id0 != b) {
            return list.get(0);
        }
        final int id1 = list.get(1).id;
        if (id1 != a && id1 != b) {
            return list.get(1);
        } else {
            return list.get(2);
        }
    }

    private static class Member {
        final int id;
        final int a;
        final int b;
        final int c;
        final int d;
        final int e;

        public Member(final int id, final int a, final int b, final int c, final int d, final int e) {
            this.id = id;
            this.a = a;
            this.b = b;
            this.c = c;
            this.d = d;
            this.e = e;
        }
    }

    private static class FastScanner {
        private final BufferedReader reader;
        private StringTokenizer tokenizer;

        FastScanner(final InputStream in) throws IOException {
            reader = new BufferedReader(new InputStreamReader(in));
            tokenizer = new StringTokenizer(reader.readLine());
        }

        String next() throws IOException {
            if (!tokenizer.hasMoreTokens()) {
                tokenizer = new StringTokenizer(reader.readLine());
            }
            return tokenizer.nextToken();
        }

        int nextInt() {
            try {
                return Integer.parseInt(next());
            } catch (IOException e) {
                e.printStackTrace();
                return 0;
            }
        }
    }
}
    