import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        ArrayList<ProcessThread> processes = new ArrayList<>(); //create arraylist to store processthread objects


        try (BufferedReader br = new BufferedReader(new FileReader("processes.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.trim().split("\\s+");
                int pid = Integer.parseInt(parts[0]);
                int burstTime = Integer.parseInt(parts[1]);
                processes.add(new ProcessThread(pid, burstTime));
            }
        } catch (IOException e) {
            System.out.println("Error reading processes.txt");
            e.printStackTrace();
        }

        for (ProcessThread p : processes) { //start each thread
            p.start();
        }

        for (ProcessThread p : processes) { //wait for each thread to finish
            try {
                p.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("All processes completed.");
    }
}
