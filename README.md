# US Map MST and SSSP

Author: Marisa Tania  

## About This Project
This project implements Prim's and Kruskal's algorithms for computing the minimum spanning tree (MST), as well as Djikstra's and Floyd's algorithms for computing Single-Source-Shortest-Path (SSSP) of all major cities in the United States.The graph costs are proportional to the distance between cities. This project has a graphical user interface (GUI ) that will display the graph of cities on the "map"1 (the "map" is simply an image), and the minimal spanning tree:


### What is proc?
proc is the process information pseudo-filesystem. Inside /proc, there are process IDs and several other virtual files that are updated dynamically with system information. Each of the system processes corresponds to one of the numbered directories in /proc. Some of the other information that can be obtained from this file system includes the kernel version, CPU counters, mounted disks, and many more.  Since this is just an example of an excellent README, you shouldn't plagiarize it. Copying and pasting this file into your own project carries a 5 point deduction.  To get a better understanding of proc, check out the man page: man proc. The manual has a complete description of every file and directory stored under /proc

### Program Options
Each portion of the display can be toggled with command line options. Here are the options:
```bash
$ ./inspector -h
Usage: ./inspector [-ahlrst] [-p procfs_dir]

Options:
    * -a              Display all (equivalent to -lrst, default)
    * -h              Help/usage information
    * -l              Task List
    * -p procfs_dir   Change the expected procfs mount point (default: /proc)
    * -r              Hardware Information
    * -s              System Information
    * -t              Task Information
```
The task list, hardware information, system information, and task information can all be turned on/off with the command line options. By default, all of them are displayed.

### Included Files
There are several files included. These are:
   - <b>Makefile</b>: Including to compile and run the program.
   - <b>inspector.c</b>: The program driver
   - <b>sys_info.c</b>: Includes functions to get the system information of the system. This information includes hostname, kernel version, and uptime.
   - <b>hardware_info.c</b>: Includes functions to get the hardware information of the system. This infomation includes CPU model, number of processing units, load average, CPU usage, and memory usage.
   - <b>task_info.c</b>: Includes functions that will count the number of tasks running, number of interrupts, context switches, and forks since boot. Also contains functions that will count the number of processes of the system and print them out as a list.
   - <b>read_files.c</b>: Includes helper functions to read in a file using system call read and also tokenize a string.

There are also header files for each of these files.


To compile and run:

```bash
make
./inspector
```


### Program Output
```bash
$ ./inspector
inspector.c:133:main(): Options selected: hardware system task_list task_summary

System Information
------------------
Hostname: lunafreya
Kernel Version: 4.20.3-arch1-1-ARCH
Uptime: 1 days, 11 hours, 49 minutes, 56 seconds

Hardware Information
------------------
CPU Model: AMD EPYC Processor (with IBPB)
Processing Units: 2
Load Average (1/5/15 min): 0.00 0.00 0.00
CPU Usage:    [--------------------] 0.0%
Memory Usage: [#-------------------] 9.5% (0.1 GB / 1.0 GB)

Task Information
----------------
Tasks running: 88
Since boot:
    Interrupts: 2153905
    Context Switches: 3678668
    Forks: 38849

  PID |        State |                 Task Name |            User | Tasks 
------+--------------+---------------------------+-----------------+-------
    1 |     sleeping |                   systemd |               0 | 1 
    2 |     sleeping |                  kthreadd |               0 | 1 
    3 |         idle |                    rcu_gp |               0 | 1 
    4 |         idle |                rcu_par_gp |               0 | 1 
    6 |         idle |      kworker/0:0H-kblockd |               0 | 1 

```

## Testing

To execute the test cases, use `make test`. To pull in updated test cases, run `make testupdate`. You can also run a specific test case instead of all of them:

```
# Run all test cases:
make test

# Run a specific test case:
make test run=4

# Run a few specific test cases (4, 8, and 12 in this case):
make test run='4 8 12'
```
