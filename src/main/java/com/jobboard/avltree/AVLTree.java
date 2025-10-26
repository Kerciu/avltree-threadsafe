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
            return node;
        }

        updateHeight(node);

        int balanceFactor = getBalanceFactor(node);

        // LL
        if (balanceFactor > 1 && getBalanceFactor(node.getLeft()) >= 0) {
            return rightRotate(node);
        }

        // LR
        if (balanceFactor > 1 && getBalanceFactor(node.getLeft()) < 0) {
            node.setLeft(leftRotate(node.getLeft()));
            return rightRotate(node);
        }

        // RR
        if (balanceFactor < -1 && getBalanceFactor(node.getRight()) <= 0) {
            return leftRotate(node);
        }

        // RL
        if (balanceFactor < -1 && getBalanceFactor(node.getRight()) > 0) {
            node.setRight(rightRotate(node.getRight()));
            return leftRotate(node);
        }

        return node;
    }

    private TreeNode rightRotate(TreeNode y) {
        if (y == null) {
            return null;
        }

        TreeNode x = y.getLeft();
        TreeNode T2 = x.getRight();

        x.setRight(y);
        y.setLeft(T2);

        updateHeight(y);
        updateHeight(x);

        return x;
    }

    private TreeNode leftRotate(TreeNode x) {
        if (x == null) {
            return null;
        }

        TreeNode y = x.getRight();
        TreeNode T2 = y.getLeft();

        y.setLeft(x);
        x.setRight(T2);

        updateHeight(x);
        updateHeight(y);

        return y;
    }

    private int getBalanceFactor(TreeNode node) {
        if (node == null) {
            return 0;
        }
        return getHeightSafely(node.getLeft()) - getHeightSafely(node.getRight());
    }

    private void updateHeight(TreeNode node) {
        node.setHeight(
            1 + Math.max(
                getHeightSafely(node.getLeft()),
                getHeightSafely(node.getRight())
            )
        );
    }

    private int getHeightSafely(TreeNode node) {
        if (node == null) {
            return 0;
        }

        return node.getHeight();
    }

    private int compareTreeKeys(byte[] data1, byte[] data2) {
        return Arrays.compareUnsigned(data1, data2);
    }
}
