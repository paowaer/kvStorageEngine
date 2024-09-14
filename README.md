# Key-Value Storage Engine
It's well known that the the core data structure of the storage engines in LevelDB and RocksDB is the skip list.

This project is a lightweight key-value storage engine implemented using a skip list, developed in the Java programming language. 

The core goal of the project is to build an efficient storage engine with basic data storage and retrieval capabilities, similar to the storage engine structure found in LevelDB and RocksDB. The skip list, as an efficient linked list structure, is widely used for fast data insertion, deletion, and querying operations, offering excellent performance and low time complexity, especially in scenarios involving ordered data.

Key features of this project include:

## Data Insertion: 
Supports efficient insertion of key-value pairs, with the skip list allowing operations to be completed in O(log n) time complexity.
## Data Deletion: 
Provides quick identification and removal of data by key, also maintaining O(log n) complexity.
## Data Querying: 
Enables fast retrieval of data by key, with multiple layers of indices in the skip list improving query efficiency.
## Data Display: 
Can display all stored data in order by key.
## Data Persistence: 
Offers functionality to persist data from memory to disk, ensuring data durability.
## File Data Loading: 
Supports loading data from external files into memory, facilitating system restarts and data recovery.
## In addition： 
the project includes a dedicated module for stress testing the skip list, which evaluates its performance in high-concurrency and large-data scenarios by inserting and querying large volumes of data.


## Project files:

SkipList.java: Core implementation of the skip list

StressTest.java: Stress testing for the skip list
