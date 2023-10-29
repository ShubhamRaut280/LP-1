import java.util.*;

public class PAGE_R {

    static void Menu() {
        System.out.println("1. FIFO");
        System.out.println("2. LRU");
        System.out.println("3. Exit");
    };

    static int FIFO(int incomingStream[], int len, int frames) {
        System.out.println("Incoming \tPages");
        System.out.println("__________________________\n");
        // Using Hashset to quickly check if a given
        // incoming stream item in set or not
        // here HashSet acts as frame into which we've to add pages
        HashSet<Integer> s = new HashSet<>(frames);
        // Queue created to store pages in FIFO manner
        // since set will not store order or entry
        // we will use queue to note order of entry of incoming page
        Queue<Integer> queue = new LinkedList<>();
        int page_faults = 0;
        for (int i = 0; i < len; i++) {
            // if set has lesser item than frames
            if (s.size() < frames) {
                // If incoming item is not present, add to set
                if (!s.contains(incomingStream[i])) {
                    s.add(incomingStream[i]);
                    page_faults++; // since the page was not already in the frame we've take it as page fault
                    // Push the incoming page into the queue
                    queue.add(incomingStream[i]);
                }
            }
            // If the set is full then we need to do page replacement
            // in FIFO manner that is remove first item from both
            // set and queue then insert incoming page
            else {
                // If incoming item is not present
                if (!s.contains(incomingStream[i])) {
                    // remove the first page from the queue
                    int val = (int) queue.peek(); // this will return head of the queue
                    // remove from queue
                    queue.poll();
                    // Remove from set
                    s.remove(val);
                    // insert incoming page to set
                    s.add(incomingStream[i]);
                    // push incoming page to queue
                    queue.add(incomingStream[i]);
                    page_faults++;
                }
            }
            // printing happens here
            System.out.print(incomingStream[i] + "\t\t");
            System.out.print(queue + " \n");
        }
        return page_faults;
    }

    static int LRU(int incomingStream[], int frames) {

        // If the user gives capacity of memory equal to zero, returning 0 since there
        // aren't going to be any faults
        if (frames == 0)
            return 0;

        // LinkedHashSet is implemented as a hash table with a linked list running
        // through it,
        // so it provides the order of insertion. The time complexity of basic methods
        // is O(1).
        // That way the time complexity for adding and removing pages is O(1).
        LinkedHashSet<Integer> memory = new LinkedHashSet<>(frames);

        int page_faults = 0; // Initializing the page faults

        // Iterating through every page
        for (int page : incomingStream) {
            // If this page is in the set, we remove it and adding it at the tail set since
            // it's implemented
            // as a linked list to maintain the LRU logic
            if (memory.contains(page)) {
                memory.remove(page); // Removes the page
                memory.add(page); // Adds the new page into the hash set
            }
            // Else adding the new page at the tail set since it's implemented as a linked
            // list to maintain
            // the LRU logic
            else {
                // If it reached its capacity, removing the last recently used page first(first
                // element of the set) and
                // then and adding the new page
                if (memory.size() == frames) {
                    Iterator<Integer> iterator = memory.iterator(); // Using and iterator just to get the first element
                                                                    // of the set in O(1)
                    int firstPageIndex = (int) iterator.next();
                    memory.remove(firstPageIndex); // Removes he RLU page
                }
                memory.add(page); // Adds the new page into the hash set
                page_faults++; // Increasing page faults
            }
        }
        return page_faults;

    }

    public static void main(String[] args) {

        int ch = 0;
        Scanner sc = new Scanner(System.in);

        System.out.print("\n\nENTER SIZE OF REFERENCE STRING::");
        int len = sc.nextInt();
        // int frames = 3;
        System.out.println("Enter the Reference string: ");
        int incomingStream[] = new int[len];
        for (int i = 0; i < len; i++) {
            incomingStream[i] = sc.nextInt();
        }
        System.out.println("Enter the number of frames: ");
        int frames = sc.nextInt();

        while (ch != 3) {
            Menu();
            System.out.print("\n\nENTER YOUR CHOICE::");
            ch = sc.nextInt();
            switch (ch) {
                case 1:
                    System.out.println("FIFO");
                    int pageFaults = FIFO(incomingStream, len, frames);
                    int hit = len - pageFaults;

                    System.out.println("Page faults: " + pageFaults);
                    System.out.println("Page fault Ratio: " + (double) pageFaults / len);
                    System.out.println("Hits: " + hit);
                    System.out.println("Hit Ratio : " + (double) hit / len);
                    break;
                case 2:
                    System.out.println("LRU");
                    int page_Faults = LRU(incomingStream, frames);
                    int hit_LRU = len - page_Faults;

                    System.out.println("Page faults: " + page_Faults);
                    System.out.println("Page fault Ratio: " + (double) page_Faults / len);
                    System.out.println("Hits: " + hit_LRU);
                    System.out.println("Hit Ratio : " + (double) hit_LRU / len);
                    break;
                case 3:
                    break;
            }
        }
        sc.close();
    }

}