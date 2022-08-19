import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
	
	public static void main(String[] args) {	
		FastScanner fs = new FastScanner();
		PrintWriter out = new PrintWriter(System.out);
		int T = 1;
		T = fs.nextInt();
		for (int tc = 1; tc <= T; tc++) {
			int n = fs.nextInt();
			int[] a = fs.readArray(n);
			int from = 0;
			int to = n - 1;
			String turn = "";
			int alice = 0;
			int bob = 0;
			int moves = 0;
			int totalAlice = 0;
			int totalBob = 0;
			boolean[] visited = new boolean[n];
			Arrays.fill(visited, false);
			outer: while (from <= to) {
				boolean stop = false;
				if (turn.equals("") || turn.equals("alice")) {
					alice = 0;
					while (from <= to && alice <= bob) {
						if (from == to && visited[to]) {
							stop = true;
							break;
						}
						visited[from] = true;
						alice += a[from];
						from++;
						if (from == to && !visited[to] && alice <= bob) {
							visited[to] = true;
							alice += a[to];
							totalAlice += alice;
							moves++;
							break outer;
						}
					}
					totalAlice += alice;
					moves++;
					turn = "bob";
				} else if (turn.equals("bob")) {
					bob = 0;
					boolean alreadyMoved = false;
					while (to >= from && bob <= alice) {
						if (to == from && visited[from]) {
							stop = true;
							break;
						}
						visited[to] = true;
						bob += a[to];
						to--;
						if (to == from && !visited[from] && bob <= alice) {
							visited[from] = true;
							bob += a[to];
							totalBob += bob;
							moves++;
							break outer;
						}
					}
					totalBob += bob;
					moves++;
					turn = "alice";
				}
				if (stop) {
					break;
				}
			}
			out.println(moves + " " + totalAlice + " " + totalBob);
		}
		out.close();
	}
	
	static void sort(int[] a) {
		ArrayList<Integer> arr = new ArrayList<>();
		for (int x : a) {
			arr.add(x);
		}
		Collections.sort(arr);
		for (int i = 0; i < a.length; i++) {
			a[i] = arr.get(i);
		}
	}
	
	static void swap(int[] a, int x, int y) {
		int temp = a[x];
		a[x] = a[y];
		a[y] = temp;
	}
	
	static class FastScanner {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer("");
		
		String next() {
			while (!st.hasMoreTokens()) {
				try {
					st = new StringTokenizer(br.readLine());
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			return st.nextToken();
		}
		
		int nextInt() {
			return Integer.parseInt(next());
		}
		
		int[] readArray(int n) {
			int[] a = new int[n];
			for (int i = 0; i < n; i++) {
				a[i] = nextInt();
			}
			return a;
		}
		
		long[] readLongArray(int n) {
			long[] a = new long[n];
			for (int i = 0; i < n; i++) {
				a[i] = nextLong();
			}
			return a;
		}
		
		long nextLong() {
			return Long.parseLong(next());
		}
		
		double nextDouble() {
			return Double.parseDouble(next());
		}
		
		String nextLine() {
			String str = "";
			try {
				if (st.hasMoreTokens()) {
					str = st.nextToken("\n");
				} else {
					str = br.readLine();
				}
			} catch(IOException e) {
				e.printStackTrace();
			}
			return str;
		}
	}
}
