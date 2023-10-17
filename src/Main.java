import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.ListIterator;

public class Main {
    public static LinkedList<Character> history = new LinkedList<>();
    public static void main(String[] args) throws IOException {
        LinkedList<Task> input = new LinkedList<>();
        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        String line;
        for(int i = 0; i < 10; i++) {
            line = r.readLine();
            if(line == null) break;
            if(!line.isEmpty()) input.add(new Task(line));
        }
        int time = 0;
        SRTFQueue srtf = new SRTFQueue();
        while(time < 20) {
            for(Task task : input) {
                if(task.start_time == time){
                    if(task.priority == 1) srtf.add(new Task(task.toString()));
                }
            }
            if(srtf.hasTask()) {
                srtf.execute();
            }
            time++;
        }
        int counter = 0;
        for(char c : history) {
            System.out.println("time: " + counter++ + " task: " + c);
        }
    }
}
