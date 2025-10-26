package com.jobboard.avltree;

public interface BSTTree {
    void put(byte[] key, byte[] value);
    byte[] get(byte[] key);
}
