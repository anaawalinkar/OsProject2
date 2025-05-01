**Operating Systems Project 2
Thread-Based Process Simulation and Synchronization
Course: Operating Systems 4320–6320
Semester: Spring 2025**

**Overview**
This project simulates real-time process execution using threads and demonstrates how operating systems manage synchronization. The goal is to model how processes run concurrently and coordinate shared resources using synchronization primitives like mutexes and semaphores.

You will implement:

A thread-based simulation of processes based on a processes.txt input file.

One classic synchronization problem:

Dining Philosophers

Readers-Writers

Producer-Consumer

**Files Included**
ProcessSimulator.java (or main.c) – Simulates process execution using threads.

DiningPhilosophers.java / ReadersWriters.java / ProducerConsumer.java – Your chosen synchronization problem implementation.

processes.txt – Input file containing process ID and CPU burst time.

output_log.txt or screenshot – Console output showing thread activity and synchronization.

Report.pdf – A 1–2 page report explaining your implementation, logic, and output.

**Input Format:** processes.txt
Each line contains a space-separated Process ID and its CPU burst time (in seconds):
1 3  
2 2  
3 4  
4 1  
5 3  
Each process in the file is simulated by a separate thread that "runs" for the specified duration.

**Part 1: Process Simulation with Threads**
Parse processes.txt

For each line, spawn a thread representing a process

Simulate CPU burst using Thread.sleep(burstTime * 1000)

Print thread start and finish logs

Example log output:
[Process 1] Started.  
[Process 1] Finished after 3s.  

**Part 2: Synchronization Problem (Choose One)**
You must implement one of the following problems using mutexes, semaphores, or locks:

Option 1: Dining Philosophers
 - 5 philosopher threads
 - Each needs two forks (locks) to eat
 - Use strategies to avoid deadlocks

Option 2: Readers-Writers
 - Multiple readers can read at once
 - Writers need exclusive access
 - Use reader count and mutexes

Option 3: Producer-Consumer
 - Producer adds items to a bounded buffer
 - Consumers remove items
 - Use mutexes and semaphores to control access

Example output:
[Philosopher 2] Waiting for forks...  
[Philosopher 2] Picked up fork 1 and 2  
[Philosopher 2] Eating...  
[Philosopher 2] Released forks  
