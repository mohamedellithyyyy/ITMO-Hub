package world;

import java.util.ArrayList;

public class Logger {
    private ArrayList<String> logHistory;
    
    public Logger() {
        this.logHistory = new ArrayList<>();
    }
    
    public void log(String event) {
        String logEntry = "[" + java.time.LocalDateTime.now() + "] " + event;
        logHistory.add(logEntry);
        System.out.println(logEntry);
    }
    
    public ArrayList<String> getLogHistory() {
        return new ArrayList<>(logHistory);
    }
    
    public void clear() {
        logHistory.clear();
    }
    
    public void printHistory() {
        System.out.println("\n=== История логов ===");
        for (String entry : logHistory) {
            System.out.println(entry);
        }
    }
}