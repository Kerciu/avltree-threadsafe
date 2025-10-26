package com.jobboard;

import com.jobboard.avltree.AVLTree;
import com.jobboard.avltree.Tree;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        Tree tree = new AVLTree();

        tree.put("C".getBytes(), "ValueC".getBytes());
        tree.put("A".getBytes(), "ValueA".getBytes());
        tree.put("B".getBytes(), "ValueB".getBytes());
        tree.put("E".getBytes(), "ValueE".getBytes());
        tree.put("D".getBytes(), "ValueD".getBytes());

        System.out.println("values after insertion:");
        printValue(tree, "A");
        printValue(tree, "B");
        printValue(tree, "C");
        printValue(tree, "D");
        printValue(tree, "E");

        // overwrite existing key
        tree.put("C".getBytes(), "ValueC updated".getBytes());
        System.out.println("\nafter updating key 'C':");
        printValue(tree, "C");

        // check inorder key
        System.out.println("\nin-order traversal:");
        if (tree instanceof AVLTree avlTree) {
            List<byte[]> keys = avlTree.getKeysInOrder();
            for (byte[] k : keys) {
                System.out.println("key: " + new String(k));
            }

            System.out.println("\ntree balanced? " + avlTree.allNodesBalanced());
        }

        System.out.println("\nsearching for non-existent key 'Z':");
        byte[] notFound = tree.get("Z".getBytes());
        System.out.println("result: " + (notFound == null ? "null" : new String(notFound)));
    }

    private static void printValue(Tree tree, String key) {
        byte[] value = tree.get(key.getBytes());
        if (value != null) {
            System.out.println("key: " + key + "-> value: " + new String(value));
        } else {
            System.out.println("key: " + key + "-> not found");
        }
    }
}
