package com.jobboard.avltree;

import java.util.Arrays;

public class AVLTree implements BSTTree {

    private TreeNode root;

    public AVLTree() {
        this.root = null;
    }

    @Override
    public void put(byte[] key, byte[] value) {
        this.root = this.putHelper(this.root, key, value);
    }

    @Override
    public byte[] get(byte[] key) {

        TreeNode current = root;

        while (current != null) {
            int compare = compareTreeKeys(key, current.getKey());

            if (compare < 0) {
                current = current.getLeft();
            } else if (compare > 0) {
                current = current.getRight();
            } else {
                return current.getValue();
            }
        }

        return null;
    }

    private TreeNode putHelper(TreeNode node, byte[] key, byte[] value) {
        if (node == null) {
            return new TreeNode(key, value);
        }

        int compare = this.compareTreeKeys(key, node.getKey());
        if (compare < 0) {
            node.setLeft(putHelper(node.getLeft(), key, value));
        } else if (compare > 0) {
            node.setRight(putHelper(node.getRight(), key, value));
        } else {
            node.setValue(value);
        }

        return node;
    }

    private TreeNode rightRotate(TreeNode node) {
        if (node == null) {
            return null;
        }

        TreeNode left = node.getLeft();
        node.setLeft(left.getRight());
        left.setRight(node.getRight());

        return node;
    }

    private TreeNode leftRotate(TreeNode node) {
        if (node == null) {
            return null;
        }

        TreeNode right = node.getRight();
        node.setRight(right.getLeft());
        right.setLeft(node.getLeft());

        return node;
    }

    private int compareTreeKeys(byte[] data1, byte[] data2) {
        return Arrays.compareUnsigned(data1, data2);
    }
}
