package multileveltaskqueue;

import java.util.Comparator;
import java.util.EmptyStackException;
import java.util.PriorityQueue;

public class SRTFQueue{
    private final PriorityQueue<Task> queue = new PriorityQueue<>(new TaskComparator());
    public void add(Task t) {
        queue.add(t);
    }
    public boolean hasNext() {
        return !queue.isEmpty();
    }

    public void execute() {
        if(queue.isEmpty()) throw new EmptyStackException();
        Task t = queue.poll();
        if(t.remainingTime > 1) {
            t.remainingTime -= 1;
            queue.add(t);
        } else { //kimenti meddig várt a taszk és kiszedi a még futók közül.
            Main.waitingTimesFinished.put(t.id,Main.waitingTimesRunning.get(t.id));
            Main.waitingTimesRunning.remove(t.id);
        }
        Main.history.add(t.id);
        Main.incrementWaitTimes(t.id);
    }
    static class TaskComparator implements Comparator<Task> {
        @Override
        public int compare(Task o1, Task o2) {
            int i = Integer.compare(o1.remainingTime, o2.remainingTime);
            if(i == 0) {
                i = Character.compare(o1.id,o2.id);
            }
            return i;
        }
    }
}
