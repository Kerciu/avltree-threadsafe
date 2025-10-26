package com.jobboard.avltree;

public class TreeNode {
    private final byte[] key;
    private byte[] value;

    private int height;
    private TreeNode left;
    private TreeNode right;

    public TreeNode(byte[] key, byte[] value) {
        this.key = key;
        this.value = value;
    }

    public TreeNode getLeft() {
        return left;
    }

    public TreeNode getRight() {
        return right;
    }

    public byte[] getKey() {
        return key;
    }

    public byte[] getValue() {
        return value;
    }

    public int getHeight() {
        return height;
    }

    public void setLeft(TreeNode left) {
        this.left = left;
    }

    public void setRight(TreeNode right) {
        this.right = right;
    }

    public void setValue(byte[] value) {
        this.value = value;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
