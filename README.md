# Task Manager Application

## About  
A Java-based Task Management System focused on data structures and algorithms (DSA).  
This application uses PriorityQueue, LinkedList, and HashMap to efficiently manage tasks with features such as adding, editing, completing, and deleting tasks. Each task has a priority and an optional deadline, and tasks are ordered primarily by priority.

## Features  
- Add tasks with a name, priority (integer), and optional deadline (YYYY-MM-DD)  
- Edit existing tasks (name, priority, deadline)  
- Mark tasks as completed  
- Delete tasks  
- View tasks ordered by priority or by insertion order  
- Prevent duplicate task names  
- User-friendly GUI built with Swing

## Data Structures Used  
- PriorityQueue: To maintain tasks ordered by priority and deadline  
- LinkedList: To preserve insertion order for task history  
- HashMap: For fast lookup, edit, and deletion by task name  

## Files  
- Task.java: Represents individual tasks and implements Comparable for priority ordering  
- TaskManager.java: Core logic using PriorityQueue, LinkedList, and HashMap to manage tasks  
- TaskManagerGUI.java: Swing GUI to interact with the Task Manager  
- Main.java: Entry point to launch the GUI

## How to Run  
1. Clone or download the repository  
2. Compile the Java files:  
   ```bash
   javac *.java
   
