package multileveltaskqueue;

import java.util.EmptyStackException;
import java.util.LinkedList;

public class RRQueue {
    LinkedList<Task> tasks = new LinkedList<>();

    public void add(Task t) { tasks.add(t); }

    public boolean hasNext() {
        return !tasks.isEmpty();
    }

    public void execute() {
        if(tasks.isEmpty()) throw new EmptyStackException();

        //A queue hátuljára teszi az elemet, ami már 2 időszeletet használt
        if(Main.rrUsedTime >= 2) {
            rotate();
        }

        //"futtatás"
        Task t = tasks.removeFirst();
        if(!(t.remainingTime <= 1)) {
            t.remainingTime -= 1;
            tasks.addFirst(t);
            Main.rrUsedTime++;
        } else { //kimenti meddig várt a taszk és kiszedi a még futók közül.
            Main.waitingTimesFinished.put(t.id,Main.waitingTimesRunning.get(t.id));
            Main.waitingTimesRunning.remove(t.id);
            Main.rrUsedTime = 0;
        }
        Main.history.add(t.id);
        Main.incrementWaitTimes(t.id);

    }
    public void rotate() {
        Task t = tasks.removeFirst();
        tasks.addLast(t);
        Main.rrUsedTime = 0;
    }
}
