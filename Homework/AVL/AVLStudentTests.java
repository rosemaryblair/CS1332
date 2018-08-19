import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

import java.util.List;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

/**
 * These tests are not exhaustive.
 * @author CS 1332 TAs
 * @version 1.0
 */
public class AVLStudentTests {
    private static final int TIMEOUT = 200;
    private AVL<Integer> avlTree;

    @Before
    public void setup() {
        avlTree = new AVL<>();
    }

    @Test(timeout = TIMEOUT)
    public void testAddRightRotation() {
        avlTree.add(5);
        avlTree.add(4);
        avlTree.add(3);

        assertEquals(3, avlTree.size());

        AVLNode<Integer> root = avlTree.getRoot();
        assertEquals((Integer) 4, root.getData());
        assertEquals(1, root.getHeight());
        assertEquals(0, root.getBalanceFactor());
        assertEquals((Integer) 3,
                root.getLeft().getData());
        assertEquals(0, root.getLeft().getHeight());
        assertEquals(0, root.getLeft().getBalanceFactor());
        assertEquals((Integer) 5,
                root.getRight().getData());
        assertEquals(0, root.getRight().getHeight());
        assertEquals(0, root.getRight().getBalanceFactor());
    }

    @Test(timeout = TIMEOUT)
    public void testAddRightLeftRotationRoot() {
        avlTree.add(3);
        avlTree.add(5);
        avlTree.add(4);

        assertEquals(3, avlTree.size());

        AVLNode<Integer> root = avlTree.getRoot();
        assertEquals((Integer) 4, root.getData());
        assertEquals(1, root.getHeight());
        assertEquals(0, root.getBalanceFactor());
        assertEquals((Integer) 3,
                root.getLeft().getData());
        assertEquals(0, root.getLeft().getHeight());
        assertEquals(0, root.getLeft().getBalanceFactor());
        assertEquals((Integer) 5,
                root.getRight().getData());
        assertEquals(0, root.getRight().getHeight());
        assertEquals(0, root.getRight().getBalanceFactor());
    }

    @Test(timeout = TIMEOUT)
    public void testRemove() {
        Integer toBeRemoved = new Integer(526);
        avlTree.add(646);
        avlTree.add(386);
        avlTree.add(856);
        avlTree.add(toBeRemoved);
        avlTree.add(477);

        assertSame(toBeRemoved, avlTree.remove(new Integer(526)));

        assertEquals(4, avlTree.size());

        AVLNode<Integer> root = avlTree.getRoot();
        assertEquals((Integer) 646, root.getData());
        assertEquals(2, root.getHeight());
        assertEquals(1, root.getBalanceFactor());
        assertEquals((Integer) 477,
                root.getLeft().getData());
        assertEquals(1, root.getLeft().getHeight());
        assertEquals(1, root.getLeft().getBalanceFactor());
        assertEquals((Integer) 856,
                root.getRight().getData());
        assertEquals(0, root.getRight().getHeight());
        assertEquals(0, root.getRight().getBalanceFactor());
    }

    @Test(timeout = TIMEOUT)
    public void testHeight() {
        avlTree.add(646);
        avlTree.add(386);
        avlTree.add(856);
        avlTree.add(526);
        avlTree.add(477);

        assertEquals(2, avlTree.height());
    }

    @Test(timeout = TIMEOUT)
    public void testGet() {
        Integer maximum = new Integer(646);
        avlTree.add(526);
        avlTree.add(386);
        avlTree.add(477);
        avlTree.add(maximum);
        avlTree.add(856);

        assertSame(maximum, avlTree.get(new Integer(646)));
    }

    @Test(timeout = TIMEOUT)
    public void testContains() {
        Integer maximum = new Integer(646);
        avlTree.add(526);
        avlTree.add(386);
        avlTree.add(477);
        avlTree.add(maximum);
        avlTree.add(856);

        assertEquals(true, avlTree.contains(new Integer(646)));
    }

    @Test(timeout = TIMEOUT)
    public void testConstructorAndClear() {
        List<Integer> toAdd = new ArrayList<>();
        toAdd.add(24);
        toAdd.add(1);
        toAdd.add(7);
        avlTree = new AVL<>(toAdd);

        avlTree.clear();
        assertEquals(null, avlTree.getRoot());
        assertEquals(0, avlTree.size());
    }


    @Test(timeout = TIMEOUT)
    public void testSuccessor() {
        avlTree.add(76);
        avlTree.add(34);
        avlTree.add(90);
        avlTree.add(40);
        avlTree.add(81);

        assertEquals((Integer) 81, avlTree.successor(76));
        assertEquals((Integer) 76, avlTree.successor(40));
    }
}