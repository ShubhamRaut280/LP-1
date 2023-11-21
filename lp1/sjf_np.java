import java.util.*;
// In pre-emptive scheduling, the CPU is dedicated to the process for a limited period.
// In non-preemptive scheduling, the CPU is dedicated to the process until it terminates or switches to the waiting state.
class sjf_np {

    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of process: ");
        int n = sc.nextInt();

        int process[] = new int[n];
        int burstTime[] = new int[n];
        int waitingTime[] = new int[n];
        int turnAroundTime[] = new int[n];

        int i, j, total = 0, pos, temp;
        float avgWaitingTime, avgTurnAroundTime;

        System.out.println("\nEnter Burst time:");
        for (i = 0; i < n; i++) {
            System.out.print("\nProcess[" + (i + 1) + "]: ");
            burstTime[i] = sc.nextInt();
            process[i] = i + 1;
        }

        // Sorting according to burst time. In SJF process with
        // least burst time is scheduled first.
        for (i = 0; i < n; i++) {
            pos = i;
            for (j = i + 1; j < n; j++) {
                if (burstTime[j] < burstTime[pos])
                    pos = j;
            }

            // swap burst time
            temp = burstTime[i];
            burstTime[i] = burstTime[pos];
            burstTime[pos] = temp;

            // sort process also
            temp = process[i];
            process[i] = process[pos];
            process[pos] = temp;
        }

        waitingTime[0] = 0;
        // calculate waiting time for every process
        for (i = 1; i < n; i++) {

            waitingTime[i] = 0;
            for (j = 0; j < i; j++)
            {
                waitingTime[i] += burstTime[j];
            }
            total += waitingTime[i]; // this is the avg of the waiting time
        }

        // Calculating Average waiting time
        avgWaitingTime = (float) total / n;
        total = 0;

        System.out.println("\nProcess\t Burst Time \tWaiting Time\tTurnaround Time");
        for (i = 0; i < n; i++) {

            turnAroundTime[i] = burstTime[i] + waitingTime[i]; // Calculating Turnaround Time

            total += turnAroundTime[i]; // this is the avg of the turnaround time

            System.out.println("\n p" + process[i] + "\t\t " + burstTime[i] + "\t\t " + waitingTime[i] + "\t\t " + turnAroundTime[i]);
        }

        // Calculation of Average Turnaround Time
        avgTurnAroundTime = (float) total / n;

        System.out.println("\n\nAverage Waiting Time: " + avgWaitingTime);
        System.out.println("\nAverage Turnaround Time: " + avgTurnAroundTime);

    }
}