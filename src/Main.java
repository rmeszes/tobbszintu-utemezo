import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class Main {
    public static LinkedList<Character> history = new LinkedList<>();
    public static int totalRunTime = 0;
    public static int rrUsedTime = 0;
    public static HashMap<Character, Integer> waitingTimesRunning = new HashMap<>();
    public static HashMap<Character, Integer> waitingTimesFinished = new HashMap<>();
    public static LinkedList<Character> incomingOrder = new LinkedList<>();
    public static void main(String[] args) throws IOException {
        LinkedList<Task> input = new LinkedList<>();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String line;
        for(int i = 0; i < 10; i++) {
            line = bufferedReader.readLine();
            if(line == null) break;
            if(!line.isEmpty()) {
                Task t = new Task(line);
                input.add(t);
                totalRunTime += t.remainingTime;
            }
        }
        int time = 0;
        SRTFQueue srtf = new SRTFQueue();
        RRQueue rr = new RRQueue();
        while(time < totalRunTime) {
            //behelyezzük a taskokat a megfelelő queueba, amikor kezdődnek
            for(Task task : input) {
                if(task.start_time == time){
                    if(task.priority == 1) {
                        srtf.add(new Task(task.toString()));
                    } else if(task.priority == 0) {
                        rr.add(new Task(task.toString()));
                    }
                    waitingTimesRunning.put(task.id, 0);
                    incomingOrder.add(task.id);
                }
            }
            //eddig

            if(srtf.hasNext()) {
                if(rrUsedTime == 1) { //ebben az esetben interruptált egy RR taszkot
                    rr.rotate(); //ezért a végére tesszük őt
                }
                srtf.execute();
            } else if (rr.hasNext()) {
                rr.execute();
            }
            else {
                rrUsedTime = 0;
                history.add(' '); //szóköz == idle task
                totalRunTime++;
            }
            time++;
        }
        char prev = ' ';
        for(char c : history) {
            if(c != prev && c != ' ') {
                System.out.print(c);
                prev = c;
            }
        }
        System.out.println();
        boolean first = true;
        for(char taskId : incomingOrder) {
            if(first) {
                System.out.print("" + taskId + ':' + waitingTimesFinished.get(taskId));
                first = false;
            } else {
                System.out.print("," + taskId + ':' + waitingTimesFinished.get(taskId));
            }

        }
        System.out.println();
    }

    public static void incrementWaitTimes(char activeTask) {
        for(Map.Entry<Character,Integer> entry : waitingTimesRunning.entrySet()) {
            if(!entry.getKey().equals(activeTask)) {
                entry.setValue(entry.getValue() + 1);
            }
        }
    }
}
