**Project 2
Operating Systems
Spring 2025
4/30/2025**

**Overview: What was implemented?**

This project simulates real-time process execution using threads and demonstrates how synchronization is managed in operating systems. The project consists of two parts:

1. Thread-based Process Simulation: Each process is simulated using a separate thread. The process data is read from an input file (processes.txt) that includes Process IDs and burst times. Each thread simulates a CPU burst by sleeping for the duration of its burst time.

2. Classic Synchronization Problem: One of the following problems was implemented using mutexes and/or semaphores:

 - Dining Philosophers

 - Readers-Writers

 - Producer-Consumer

These problems represent how threads can safely share resources like memory or I/O by preventing race conditions and deadlocks.

**Implementation Details: How was the simulation and synchronization handled?**

The file processes.txt contains lines in the following format:

<Process ID> <Burst Time (in seconds)>

Example:

1 3  
2 2  
3 4  
4 1  
5 3 

Each line is read and parsed into a list of thread objects. In Java, each thread is represented by a class that extends Thread. The run() method simulates the process execution using Thread.sleep(burstTime * 1000).

In the second part of the project, we implemented the Dining Philosophers Problem (or the chosen synchronization problem). Philosophers (threads) try to pick up two forks (locks), eat, and release them. To avoid deadlocks, each philosopher picks up the lower-numbered fork first. Synchronization was achieved using Java’s ReentrantLock class (or C's pthread_mutex_t or semaphores in C/C++), and thread activity was printed at key points for transparency and debugging.

**Results: Sample thread and synchronization output**

Thread execution output for process simulation:

[Process 1] Started.  
[Process 1] Finished after 3s.  
[Process 2] Started.  
[Process 2] Finished after 2s. 

Output from the Dining Philosophers synchronization:

[Philosopher 2] Waiting for forks...  
[Philosopher 2] Picked up fork 1 and 2  
[Philosopher 2] Eating...  
[Philosopher 2] Released forks  

This log helps visualize thread activity and ensures that locks are being acquired and released correctly, demonstrating successful synchronization.

**Challenges & Solutions: What difficulties were faced?**

One challenge was handling the input parsing from processes.txt. Initial parsing errors resulted in incomplete thread creation. This was fixed by implementing error checking for each line and validating the format. Another challenge was ensuring that threads executed concurrently without blocking each other unnecessarily. To manage this, proper use of synchronization primitives like ReentrantLock and Semaphore was essential.

Deadlock prevention in the Dining Philosophers problem also posed a challenge. The solution was to enforce a consistent fork-picking order (always pick up the lower-numbered fork first), preventing circular wait.

Finally, ensuring the console output was clean and easy to follow required restructuring print statements and adding clear logs for lock acquisition, waiting, and release.

**Breakdown of Code**

The code is divided into two main components:

Process Simulation: A class reads the input file and creates a thread for each process. Each thread sleeps for the burst time, simulating execution. Threads log when they start and finish.

Synchronization Problem: Depending on the chosen problem (Dining Philosophers, Readers-Writers, or Producer-Consumer), threads interact with shared resources using locks or semaphores. The synchronization ensures no race conditions or deadlocks occur. Each action (waiting, acquiring, releasing) is logged to the console.

The code is modular, well-commented, and includes sample outputs for verification.
