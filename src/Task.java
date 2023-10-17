public class Task {
    public final char id;
    public final int priority; //lehetne bool, mert 2 értéke lehet, de nem lenne logikus, se fejleszthető
    public final int start_time;
    public int remainingTime;


    public Task(char id, int priority, int start_time, int remainingTime) {
        this.id = id;
        this.priority = priority;
        this.start_time = start_time;
        this.remainingTime = remainingTime;
    }

    public Task(String arg) {
        String[] attrs = arg.split(",");
        this.id = attrs[0].charAt(0);
        this.priority = Integer.parseInt(attrs[1]);
        this.start_time = Integer.parseInt(attrs[2]);
        this.remainingTime = Integer.parseInt(attrs[3]);
    }

    public String toString() {
        return "" + id + ',' + priority + ',' + start_time + ',' + remainingTime;
    }
}
