public class Task {
    private char id;
    private int priority; //lehetne bool, mert 2 értéke lehet, de nem lenne logikus, se fejleszthető
    private int start_time;
    private int loketIdo;

    public Task(char id, int priority, int start_time, int loketIdo) {
        this.id = id;
        this.priority = priority;
        this.start_time = start_time;
        this.loketIdo = loketIdo;
    }

    public Task(String arg) {
        String[] attrs = arg.split(",");
        if(attrs.length != 4) {
            throw new IllegalArgumentException("A sorban nem 4 elem van!");
        }
        if(attrs[0].length() != 1) throw new IllegalArgumentException("Az ID csak egy betű lehet!");
        setId(attrs[0].charAt(0));
        //TODO: a setterek megírása után meghívni őket sorban
    }

    public char getId() {
        return id;
    }
    public void setId(char c) {
        if(Character.isAlphabetic(c)) throw new IllegalArgumentException("Az ID csak egy betű lehet!");
        id = Character.toUpperCase(c);
    }
}
