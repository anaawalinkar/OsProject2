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
Each line is read and parsed into a list of thread objects. Each process is represented by a class (ProcessThread) that extends Thread, and the run() method simulates the process execution using Thread.sleep(burstTime * 1000). Start and end messages are logged to the console to track execution.

In the second part of the project, we implemented the Producer-Consumer synchronization problem. A producer thread continuously generates items and attempts to add them to a bounded buffer, while a consumer thread removes and processes them. Synchronization was achieved using Java’s Semaphore and ReentrantLock to ensure mutual exclusion and prevent race conditions. The buffer size is limited, and semaphores track empty and full slots.

**Results: Sample thread and synchronization output**

Thread execution output for process simulation:

[Process 1] Started.  
[Process 1] Finished after 3s.  
[Process 2] Started.  
[Process 2] Finished after 2s.
...
Output from Producer-Consumer synchronization:

[Producer] Produced: 1  
[Consumer] Consumed: 1  
[Producer] Produced: 2  
[Consumer] Consumed: 2
...
This log helps visualize how threads execute concurrently and how synchronization mechanisms ensure proper access to shared resources.

**Challenges & Solutions: What difficulties were faced?**

One challenge was correctly parsing the input from processes.txt. At first, improper line formats caused errors or missing threads. This was fixed by implementing validation checks for each line.

Another challenge was ensuring true concurrency during process simulation. We had to confirm threads were not blocking each other and were executing independently. This was handled using proper thread construction and logging to verify execution order.

In the Producer-Consumer implementation, race conditions and potential deadlocks were a concern. Using semaphores for slot tracking and a ReentrantLock for buffer access ensured synchronization correctness. Initial versions of the program missed some synchronization points, but adding structured lock.lock() and lock.unlock() calls around critical sections resolved these bugs.

Finally, making the console output clean and informative required refining the print statements to clearly show each thread's lifecycle and interaction with the shared buffer.

**Breakdown of Code**

The code is divided into two main components:

Process Simulation:
A main controller reads processes.txt and spawns a ProcessThread for each line. Each thread simulates its burst time and prints logs indicating when it starts and finishes.

Producer-Consumer Synchronization:
The ProducerConsumer class sets up a bounded buffer with a fixed size. The Producer thread adds items to the buffer, while the Consumer thread removes them. Semaphores manage full and empty slots, while a lock prevents concurrent access. The actions (produce, consume, wait) are logged to show synchronization behavior.

The code is modular, well-commented, and includes sample outputs for verification.
