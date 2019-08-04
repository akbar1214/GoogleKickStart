import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;


class Main {

    static class Reader {
        final private int BUFFER_SIZE = 1 << 16;
        private DataInputStream din;
        private byte[] buffer;
        private int bufferPointer, bytesRead;

        public Reader() {
            din = new DataInputStream(System.in);
            buffer = new byte[BUFFER_SIZE];
            bufferPointer = bytesRead = 0;
        }

        public Reader(String file_name) throws IOException {
            din = new DataInputStream(new FileInputStream(file_name));
            buffer = new byte[BUFFER_SIZE];
            bufferPointer = bytesRead = 0;
        }

        public String readLine() throws IOException {
            byte[] buf = new byte[1024]; // line length
            int cnt = 0, c;
            while ((c = read()) != -1) {
                if (c == '\n')
                    break;
                buf[cnt++] = (byte) c;
            }
            return new String(buf, 0, cnt);
        }

        public String read_s() throws IOException {
            byte[] buf = new byte[1024]; // line length
            int cnt = 0, c;

            while ((c = read()) != -1) {
                if (c == ' ' || c == '\n')
                    break;
                buf[cnt++] = (byte) c;
            }
            return new String(buf, 0, cnt);
        }

        public int nextInt() throws IOException {
            int ret = 0;
            byte c = read();
            while (c <= ' ')
                c = read();
            boolean neg = (c == '-');
            if (neg)
                c = read();
            do {
                ret = ret * 10 + c - '0';
            } while ((c = read()) >= '0' && c <= '9');

            if (neg)
                return -ret;
            return ret;
        }

        public long nextLong() throws IOException {
            long ret = 0;
            byte c = read();
            while (c <= ' ')
                c = read();
            boolean neg = (c == '-');
            if (neg)
                c = read();
            do {
                ret = ret * 10 + c - '0';
            }
            while ((c = read()) >= '0' && c <= '9');
            if (neg)
                return -ret;
            return ret;
        }

        public double nextDouble() throws IOException {
            double ret = 0, div = 1;
            byte c = read();
            while (c <= ' ')
                c = read();
            boolean neg = (c == '-');
            if (neg)
                c = read();

            do {
                ret = ret * 10 + c - '0';
            }
            while ((c = read()) >= '0' && c <= '9');

            if (c == '.') {
                while ((c = read()) >= '0' && c <= '9') {
                    ret += (c - '0') / (div *= 10);
                }
            }

            if (neg)
                return -ret;
            return ret;
        }

        private void fillBuffer() throws IOException {
            bytesRead = din.read(buffer, bufferPointer = 0, BUFFER_SIZE);
            if (bytesRead == -1)
                buffer[0] = -1;
        }

        private byte read() throws IOException {
            if (bufferPointer == bytesRead)
                fillBuffer();
            return buffer[bufferPointer++];
        }

        public void close() throws IOException {
            if (din == null)
                return;
            din.close();
        }
    }


    public static void main(String[] args) throws Exception {

        Reader cin = new Reader();
        PrintWriter pw = new PrintWriter(System.out);

        ////////////////////////////////////////////////////////////////////////////

        int T = cin.nextInt();
        for (int tt = 1; tt <= T; tt++) {
            pw.printf("Case #%d: ", tt);
            int n = cin.nextInt();
            int x;
            ArrayList<Integer> alex = new ArrayList<>();
            ArrayList<Integer> bob = new ArrayList<>();
            int[] arr = new int[n];
            for (int i = 0; i < n; i++) {
                x = cin.nextInt();
                if (x % 2 == 0) {
                    bob.add(x);
                } else {
                    alex.add(x);
                }
                arr[i] = x;
            }
            alex.sort(Comparator.comparingInt(o -> o));
            bob.sort((o1, o2) -> o2 - o1);

            int a_index = 0, b_index = 0, i = 0;
            while (a_index < alex.size() && b_index < bob.size()) {

                if (arr[i] % 2 == 0) {
                    pw.print(bob.get(b_index));
                    b_index++;
                } else {
                    pw.print(alex.get(a_index));
                    a_index++;
                }
                i++;
                pw.print(" ");
            }
            while (a_index < alex.size()) {
                pw.print(alex.get(a_index) + " ");
                a_index++;
            }
            while (b_index < bob.size()) {
                pw.print(bob.get(b_index) + " ");
                b_index++;
            }
            pw.println();
        }
        //////////////////////////////////////////////////////////////////////////////
        pw.flush();
        pw.close();
    }

}
