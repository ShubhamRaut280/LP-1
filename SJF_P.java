import java.util.*;

public class SJF_P {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        System.out.println("enter no of process:");
        int n = sc.nextInt();
        int pid[] = new int[n]; // it takes pid of process
        int at[] = new int[n]; // at means arrival time
        int bt[] = new int[n]; // bt means burst time
        int ct[] = new int[n]; // ct means complete time
        int ta[] = new int[n];// ta means turn around time
        int wt[] = new int[n]; // wt means waiting time
        int k[] = new int[n]; // it is also stores brust time
        int i, st = 0, tot = 0;
        float avgwt = 0, avgta = 0;

        for (i = 0; i < n; i++) {
            pid[i] = i + 1;
            System.out.println("enter process " + (i + 1) + " arrival time:");
            at[i] = sc.nextInt();
            System.out.println("enter process " + (i + 1) + " burst time:");
            bt[i] = sc.nextInt();
            k[i] = bt[i];
        }
        while (true) {
            int min = 9999, c = n;
            if (tot == n)
                break;

            for (i = 0; i < n; i++) // shorter process
            {
                if ((at[i] <= st) && (bt[i] < min)) {
                    min = bt[i];
                    c = i;
                }
            }
            if (c == n)
                st++;
            else {
                bt[c]--;
                st++;
                if (bt[c] == 0) // current process finish
                {
                    ct[c] = st;
                    tot++;
                }
            }
        } // end while

        for (i = 0; i < n; i++) {
            ta[i] = ct[i] - at[i];
            wt[i] = ta[i] - k[i]; // k is duplicate of bt array
            avgwt += wt[i];
            avgta += ta[i];
        }

        System.out.println("pid  arrival  burst  complete turn waiting");
        for (i = 0; i < n; i++) {
            System.out.println(pid[i] + "\t" + at[i] + "\t" + k[i] + "\t" + ct[i] + "\t" + ta[i] + "\t" + wt[i]);
        }

        System.out.println("\naverage tat is " + (float) (avgta / n));
        System.out.println("average wt is " + (float) (avgwt / n));
    }
}