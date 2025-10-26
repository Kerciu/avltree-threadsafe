import com.jobboard.avltree.AVLTree;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

public class AVLTreeTest {

    private AVLTree tree;

    @BeforeEach
    void setUp() {
        this.tree = new AVLTree();
    }

    public byte[] s(@NotNull String s) {
        return s.getBytes();
    }

    @Test
    void testSimplePutAndGet() {
        tree.put(s("key1"), s("value1"));

        assertArrayEquals(s("value1"), tree.get(s("key1")));
    }

    @Test
    void testGetNonExistentKey() {
        tree.put(s("key1"), s("value1"));

        assertNull(tree.get(s("non_existent")));
    }

    @Test
    void testGetFromEmptyTree() {
        assertNull(tree.get(s("anyKey")));
    }

    @Test
    void testPutUpdatesExistingValue() {
        tree.put(s("keyA"), s("valueA_v1"));
        assertArrayEquals(s("valueA_v1"), tree.get(s("keyA")));

        tree.put(s("keyA"), s("valueA_v2"));
        assertArrayEquals(s("valueA_v2"), tree.get(s("keyA")));
    }

    @Test
    void testSortingOrder() {
        tree.put(s("D"), s("val_D"));
        tree.put(s("B"), s("val_B"));
        tree.put(s("A"), s("val_A"));
        tree.put(s("E"), s("val_E"));
        tree.put(s("C"), s("val_C"));

        assertArrayEquals(s("val_A"), tree.get(s("A")));
        assertArrayEquals(s("val_B"), tree.get(s("B")));
        assertArrayEquals(s("val_C"), tree.get(s("C")));
        assertArrayEquals(s("val_D"), tree.get(s("D")));
        assertArrayEquals(s("val_E"), tree.get(s("E")));
    }

    @Test
    void testEdgeCaseEmptyKey() {
        tree.put(s(""), s("emptyKeyVal"));
        tree.put(s("a"), s("a_val"));

        assertArrayEquals(s("emptyKeyVal"), tree.get(s("")));
        assertArrayEquals(s("a_val"), tree.get(s("a")));
    }

    @Test
    void testRightRight() {
        tree.put(s("A"), s("vA"));
        tree.put(s("B"), s("vB"));
        tree.put(s("C"), s("vC")); // <- rotation

        assertArrayEquals(s("vA"), tree.get(s("A")));
        assertArrayEquals(s("vB"), tree.get(s("B")));
        assertArrayEquals(s("vC"), tree.get(s("C")));
    }

    @Test
    void testLeftLeft() {
        tree.put(s("C"), s("vC"));
        tree.put(s("B"), s("vB"));
        tree.put(s("A"), s("vA")); // <- rotation

        assertArrayEquals(s("vA"), tree.get(s("A")));
        assertArrayEquals(s("vB"), tree.get(s("B")));
        assertArrayEquals(s("vC"), tree.get(s("C")));
    }

    @Test
    void testLeftRight() {
        tree.put(s("C"), s("vC"));
        tree.put(s("A"), s("vA"));
        tree.put(s("B"), s("vB")); // <- rotation

        assertArrayEquals(s("vA"), tree.get(s("A")));
        assertArrayEquals(s("vB"), tree.get(s("B")));
        assertArrayEquals(s("vC"), tree.get(s("C")));
    }

    @Test
    void testRightLeft() {
        tree.put(s("A"), s("vA"));
        tree.put(s("C"), s("vC"));
        tree.put(s("B"), s("vB")); // <- rotation

        assertArrayEquals(s("vA"), tree.get(s("A")));
        assertArrayEquals(s("vB"), tree.get(s("B")));
        assertArrayEquals(s("vC"), tree.get(s("C")));
    }

    // O(logn) test
    @Test
    @Timeout(value = 2, unit = TimeUnit.SECONDS)
    void testPerformanceOnDegenerateCase() {
        int count = 10_000;

        for (int i = 0; i < count; i++) {
            String key = String.format("key-%05d", i);
            tree.put(s(key), s("v" + i));
        }

        for (int i = 0; i < count; i++) {
            String key = String.format("key-%05d", i);
            assertArrayEquals(s("v" + i), tree.get(s(key)));
        }
    }

    @Test
    void testInOrderTraversalKeepsSorting() {
        tree.put(s("D"), s("val_D"));
        tree.put(s("B"), s("val_B"));
        tree.put(s("E"), s("val_E"));
        tree.put(s("A"), s("val_A"));
        tree.put(s("C"), s("val_C"));

        List<byte[]> expectedKeys = List.of(s("A"), s("B"), s("C"), s("D"), s("E"));
        List<byte[]> actualKeys = tree.getKeysInOrder();

        assertEquals(expectedKeys.size(), actualKeys.size());
        for (int i = 0; i < expectedKeys.size(); i++) {
            assertArrayEquals(expectedKeys.get(i), actualKeys.get(i));
        }
    }

    @Test
    void testAllNodesHaveBalanceFactorAtMostOne() {
        tree.put(s("A"), s("vA"));
        tree.put(s("B"), s("vB"));
        tree.put(s("C"), s("vC"));
        tree.put(s("D"), s("vD"));
        tree.put(s("E"), s("vE"));
        tree.put(s("F"), s("vF"));

        assertTrue(tree.allNodesBalanced(), "Found node with |balance| > 1");
    }
}
