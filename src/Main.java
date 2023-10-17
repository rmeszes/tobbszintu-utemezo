import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

public class Main {
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
        while(time < 10) {
            for(Task task : input) {
                if(task.start_time == time) srtf.add(new Task(task.toString()));
            }
            time++;
        }
    }
}
