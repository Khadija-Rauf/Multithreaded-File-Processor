# Multithreaded File Processor

A Java project that demonstrates multithreaded file processing by splitting a large file into chunks and processing each chunk concurrently using `ExecutorService`, `Callable`, and `Future`.

## Features

- Process large files using multiple threads
- Split files into byte-based chunks
- Read chunks independently using `RandomAccessFile`
- Count total lines in parallel
- Aggregate results from multiple threads
- Configurable thread count
- Clean separation of responsibilities

## Technologies

- Java 21
- Java Concurrency API
- ExecutorService
- Callable
- Future
- RandomAccessFile

## Project Structure

```
├── src
│   ├── main
│   │   ├── java
│   │   │       ├── Main.java
│   │   │       ├── Chunk.java
│   │   │       ├── FileChunker.java
│   │   │       ├── FileReader.java
│   │   └── resources
│   │       └── file.txt
```

## How It Works

1. Determine the file size.
2. Divide the file into equal-sized byte chunks.
3. Create one task for each chunk.
4. Execute all tasks concurrently using a fixed thread pool.
5. Each thread counts the lines in its assigned chunk.
6. The main thread collects the results from all `Future` objects.
7. The individual counts are summed to produce the final line count.

## Architecture

```
                    File
                      │
                      ▼
              FileChunker
                      │
      ┌───────────────┼───────────────┐
      ▼               ▼               ▼
   Chunk 1         Chunk 2         Chunk N
      │               │               │
      ▼               ▼               ▼
 FileReader      FileReader      FileReader
      │               │               │
      └───────────────┼───────────────┘
                      ▼
              ExecutorService
                      │
                      ▼
             List<Future<Integer>>
                      │
                      ▼
              Total Line Count
```
## Sample Output

```
pool-1-thread-2 counted: 7143
pool-1-thread-3 counted: 7143
pool-1-thread-4 counted: 7142
pool-1-thread-1 counted: 7143

Total Lines = 28571
```

## Concepts Demonstrated

- Multithreading
- ExecutorService
- Callable vs Runnable
- Future
- Parallel processing
- File chunking
- RandomAccessFile
- Thread-safe result aggregation
- Separation of concerns
