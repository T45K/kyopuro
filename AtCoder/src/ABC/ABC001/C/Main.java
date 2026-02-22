package ABC.ABC001.C;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int deg = scanner.nextInt();
        final int dis = scanner.nextInt();

        final String dir = convertDeg(deg);
        final int w = convertDis(dis);

        if (w == 0) {
            System.out.println("C 0");
        } else {
            System.out.println(dir + " " + w);
        }
    }

    private static String convertDeg(final int deg) {
        if (deg < 113) {
            return "N";
        } else if (deg < 338) {
            return "NNE";
        } else if (deg < 563) {
            return "NE";
        } else if (deg < 788) {
            return "ENE";
        } else if (deg < 1013) {
            return "E";
        } else if (deg < 1238) {
            return "ESE";
        } else if (deg < 1463) {
            return "SE";
        } else if (deg < 1688) {
            return "SSE";
        } else if (deg < 1913) {
            return "S";
        } else if (deg < 2138) {
            return "SSW";
        } else if (deg < 2363) {
            return "SW";
        } else if (deg < 2588) {
            return "WSW";
        } else if (deg < 2813) {
            return "W";
        } else if (deg < 3038) {
            return "WNW";
        } else if (deg < 3263) {
            return "NW";
        } else if (deg < 3488) {
            return "NNW";
        } else {
            return "N";
        }
    }

    private static int convertDis(final int dis) {
        if (dis < 15) {
            return 0;
        } else if (dis < 93) {
            return 1;
        } else if (dis < 201) {
            return 2;
        } else if (dis < 327) {
            return 3;
        } else if (dis < 477) {
            return 4;
        } else if (dis < 645) {
            return 5;
        } else if (dis < 831) {
            return 6;
        } else if (dis < 1029) {
            return 7;
        } else if (dis < 1245) {
            return 8;
        } else if (dis < 1467) {
            return 9;
        } else if (dis < 1707) {
            return 10;
        } else if (dis < 1959) {
            return 11;
        } else {
            return 12;
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
    