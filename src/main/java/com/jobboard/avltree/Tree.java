package com.jobboard.avltree;

public interface Tree {
    void put(byte[] key, byte[] value);
    byte[] get(byte[] key);
}
