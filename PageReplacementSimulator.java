import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class PageReplacementSimulator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the reference string (space-separated numbers): ");
        String[] refString = scanner.nextLine().split(" ");
        
        System.out.print("Enter the number of frames: ");
        int numFrames = scanner.nextInt();

        simulateFIFO(refString, numFrames);
        simulateLRU(refString, numFrames);

        scanner.close();
    }

    private static void simulateFIFO(String[] refString, int numFrames) {
        System.out.println("\nFIFO Page Replacement Algorithm:");

        ArrayList<Integer> frames = new ArrayList<>();
        Queue<Integer> fifoQueue = new LinkedList<>();
        int pageFaults = 0;

        for (String pageStr : refString) {
            int page = Integer.parseInt(pageStr);

            if (!frames.contains(page)) {
                pageFaults++;

                if (frames.size() < numFrames) {
                    frames.add(page);
                    fifoQueue.add(page);
                } else {
                    int replacedPage = fifoQueue.poll();
                    frames.remove(Integer.valueOf(replacedPage));
                    frames.add(page);
                    fifoQueue.add(page);
                }
            }

            System.out.println("Page " + page + ": " + frames);
        }

        System.out.println("Total Page Faults (FIFO): " + pageFaults);
    }

    private static void simulateLRU(String[] refString, int numFrames) {
        System.out.println("\nLRU Page Replacement Algorithm:");

        ArrayList<Integer> frames = new ArrayList<>();
        int pageFaults = 0;

        for (String pageStr : refString) {
            int page = Integer.parseInt(pageStr);

            if (!frames.contains(page)) {
                pageFaults++;

                if (frames.size() < numFrames) {
                    frames.add(page);
                } else {
                    // Find the least recently used page
                    int lruPage = frames.get(0);
                    int lruIndex = 0;

                    for (int i = 1; i < frames.size(); i++) {
                        int currentPage = frames.get(i);

                        if (refStringIndex(refString, currentPage) < refStringIndex(refString, lruPage)) {
                            lruPage = currentPage;
                            lruIndex = i;
                        }
                    }

                    frames.set(lruIndex, page);
                }
            }

            System.out.println("Page " + page + ": " + frames);
        }

        System.out.println("Total Page Faults (LRU): " + pageFaults);
    }

    private static int refStringIndex(String[] refString, int page) {
        for (int i = refString.length - 1; i >= 0; i--) {
            if (Integer.parseInt(refString[i]) == page) {
                return i;
            }
        }
        return -1; // Page not found in the reference string
    }
}


// 7 0 1 2 0 3 0 4 2 3 0 3 2 1 2 0 1 7 0 1 
//3