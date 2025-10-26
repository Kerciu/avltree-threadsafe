# Thread-Safe AVL Tree in Java

This repository contains a custom implementation of a thread-safe, in-memory, sorted tree using an AVL Tree.

## Features
- Custom AVL Tree implementation (no usage of Java built-in sorted collections)
- Keys and values: `byte[]`
- `get(byte[] key)` and `put(byte[] key, byte[] value)` implemented
- Thread-safety via `ReentrantReadWriteLock` (concurrent reads, exclusive writes)

## Complexity
- `get` — O(log n) worst-case
- `put` — O(log n) worst-case (includes rotations)

## How to run
- Build with Maven or run from your IDE (Intellij).
- Example main in `com.jobboard.avltree.Main` demonstrates basic usage.

## Notes
- The code is implemented from scratch as required by the task.
