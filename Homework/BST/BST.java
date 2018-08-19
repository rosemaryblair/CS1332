import java.util.Collection;
import java.util.NoSuchElementException;
import java.util.List;
import java.util.LinkedList;

/**
 * Your implementation of a binary search tree.
 *
 * @author Rosemary Blair
 * @userid rblair8
 * @GTID 903359318
 * @version 1.0
 */
public class BST<T extends Comparable<? super T>> {
    // DO NOT ADD OR MODIFY INSTANCE VARIABLES.
    private BSTNode<T> root;
    private int size;

    /**
     * A no-argument constructor that should initialize an empty BST.
     *
     * Since instance variables are initialized to their default values, there
     * is no need to do anything for this constructor.
     */
    public BST() {
        // DO NOT IMPLEMENT THIS CONSTRUCTOR!
    }

    /**
     * Initializes the BST with the data in the Collection. The data
     * should be added in the same order it is in the Collection.
     *
     * Hint: Not all Collections are indexable like Lists, so a regular
     * for loop will not work here. What other type of loop would work?
     *
     * @param data the data to add to the tree
     * @throws IllegalArgumentException if data or any element in data is null
     */
    public BST(Collection<T> data) {
        if (data == null) {
            throw new IllegalArgumentException("BST cannot add null data.");
        }
        for (T t : data) {
            add(t);
        }
    }

    /**
     * Add the data as a leaf in the BST. Should traverse the tree to find the
     * appropriate location. If the data is already in the tree, then nothing
     * should be done (the duplicate shouldn't get added, and size should not be
     * incremented).
     * 
     * Should have a running time of O(log n) for a balanced tree, and a worst
     * case of O(n).
     *
     * @throws IllegalArgumentException if the data is null
     * @param data the data to be added
     */
    public void add(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Cannot add null data.");
        } else if (root == null) {
            root = new BSTNode<T>(data);
            size++;
        } else {
            addHelper(root, data);
        }
    }

    /**
     * A recursive add method.
     *
     * @param temp the node passed in.
     * @param data the data to be added.
     * @return the added node.
     */
    private BSTNode<T> addHelper(BSTNode<T> temp, T data) {
        if (temp == null) {
            temp = new BSTNode<T>(data);
            size++;
        } else if (temp.getData().compareTo(data) < 0) {
            temp.setRight(addHelper(temp.getRight(), data));
        } else if (temp.getData().compareTo(data) > 0) {
            temp.setLeft(addHelper(temp.getLeft(), data));
        } else {
            return temp;
        }
        return temp;
    }

    /**
     * Removes the data from the tree. There are 3 cases to consider:
     *
     * 1: the data is a leaf. In this case, simply remove it.
     * 2: the data has one child. In this case, simply replace it with its
     * child.
     * 3: the data has 2 children. Use the successor to replace the data.
     * You must use recursion to find and remove the successor (you will likely
     * need an additional helper method to handle this case efficiently).
     *
     * Should have a running time of O(log n) for a balanced tree, and a worst
     * case of O(n).
     *
     * @throws IllegalArgumentException if the data is null
     * @throws java.util.NoSuchElementException if the data is not found
     * @param data the data to remove from the tree.
     * @return the data removed from the tree. Do not return the same data
     * that was passed in.  Return the data that was stored in the tree.
     */
    public T remove(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Cannot remove null data.");
        }
        BSTNode<T> removed = removeHelper(root, root, data);
        if (removed != null) {
            return removed.getData();
        } else {
            throw new NoSuchElementException(
                "Data does not exist in tree, cannot remove.");
        }
    }

    /**
     * A recursive remove method.
     *
     * @param temp the node passed in.
     * @param parent the parent node.
     * @param data the data of removed node.
     * @return the removed node.
     */
    private BSTNode<T> removeHelper(BSTNode<T> temp, BSTNode<T> parent,
        T data) {
        if (temp == null) {
            throw new NoSuchElementException(
                "Data does not exist in tree, cannot remove.");
        } else {
            if (temp.getData().compareTo(data) == 0) {
                // System.out.println("at correct node");
                // we are at the correct node we want to remove

                // no children
                if (temp.getRight() == null && temp.getLeft() == null) {
                    return null;
                }

                // 1 smaller child
                if (temp.getRight() == null) {
                    size--;
                    return temp.getLeft();
                }

                // 1 greater child
                if (temp.getLeft() == null) {
                    size--;
                    // return temp.getRight();
                    // set the parent node's right child to temp node's right
                    parent.setRight(temp.getRight());
                    return temp;
                }

                // 2 children
                BSTNode<T> min = findMinimum(temp.getRight());
                temp.setData(min.getData());
                temp.setRight(removeHelper(temp.getRight(), temp,
                    min.getData()));
                size--;
                return temp;
            }

            // we are not at the node we want to remove
            // use recursion to find the correct node
            if (temp.getData().compareTo(data) > 0) {
                // System.out.println("go to left");
                // data has a smaller value than temp's value
                temp.setLeft(removeHelper(temp.getLeft(), temp, data));
                return temp;
            } else {
                // System.out.println("go to right");
                // data has a greater value than temp's value
                BSTNode<T> removedNode = removeHelper(temp.getRight(),
                    temp, data);
                temp.setRight(removedNode.getRight());
                return removedNode;
            }
        }
    }

    private BSTNode<T> findMinimum(BSTNode<T> temp) {
        if (temp.getLeft() == null) {
            return temp;
        } else {
            return findMinimum(temp.getLeft());
        }
    }

    // private BSTNode<T> findSuccessor(BSTNode<T> temp) {
    //     if (temp != null) {
    //         while (temp.getLeft() != null) {
    //             temp = temp.getLeft();
    //         }
    //     }
    //     return temp;
    // }

    /**
     * Returns the data in the tree matching the parameter passed in (think
     * carefully: should you use value equality or reference equality?).
     *
     * Should have a running time of O(log n) for a balanced tree, and a worst
     * case of O(n).
     *
     * @throws IllegalArgumentException if the data is null
     * @throws java.util.NoSuchElementException if the data is not found
     * @param data the data to search for in the tree.
     * @return the data in the tree equal to the parameter. Do not return the
     * same data that was passed in.  Return the data that was stored in the
     * tree.
     */
    public T get(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Cannot search for null data.");
        }
        BSTNode<T> temp = root;
        if (temp.getData().compareTo(data) > 0) {
            // data has a smaller value than temp's value
            
            return getHelper(temp.getLeft(), data);
        } else if (temp.getData().compareTo(data) < 0) {
            // data has a greater value than temp's value
            
            return getHelper(temp.getRight(), data);
        } else {
            return temp.getData();
        }
    }

    private T getHelper(BSTNode<T> temp, T data) {
        if (data == null) {
            throw new IllegalArgumentException("Cannot search for null data.");
        }
        if (temp.getData().compareTo(data) > 0) {
            // data has a smaller value than temp's value
            
            return getHelper(temp.getLeft(), data);
        } else if (temp.getData().compareTo(data) < 0) {
            // data has a greater value than temp's value
            
            return getHelper(temp.getRight(), data);
        } else {
            return temp.getData();
        }
    }

    /**
     * Returns whether or not data equivalent to the given parameter is
     * contained within the tree. The same type of equality should be used as
     * in the get method.
     *
     * Should have a running time of O(log n) for a balanced tree, and a worst
     * case of O(n).
     *
     * @throws IllegalArgumentException if the data is null
     * @param data the data to search for in the tree.
     * @return whether or not the parameter is contained within the tree.
     */
    public boolean contains(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Cannot search for null data.");
        }
        BSTNode<T> temp = root;
        if (temp.getData().compareTo(data) > 0) {
            // data has a smaller value than temp's value
            temp = containsHelper(temp.getLeft(), data);
        } else if (temp.getData().compareTo(data) < 0) {
            // data has a greater value than temp's value
            temp = containsHelper(temp.getRight(), data);
        }

        if (temp.getData().compareTo(data) == 0) {
            return true;
        } else if (temp == null) {
            return false;
        }
        return false;
    }

    private BSTNode<T> containsHelper(BSTNode<T> temp, T data) {
        if (data == null) {
            throw new IllegalArgumentException("Cannot search for null data.");
        }
        if (temp.getData().compareTo(data) > 0) {
            // data has a smaller value than temp's value
            return containsHelper(temp.getLeft(), data);
        } else if (temp.getData().compareTo(data) < 0) {
            // data has a greater value than temp's value
            return containsHelper(temp.getRight(), data);
        } else if (temp.getData().compareTo(data) == 0) {
            return temp;
        }
        return temp;
    }

    /**
     * Should run in O(n).
     *
     * @return a preorder traversal of the tree
     */
    public List<T> preorder() {
        LinkedList<T> preorderList = new LinkedList<T>();
        return preorderHelper(preorderList, root);
    }

    private LinkedList<T> preorderHelper(LinkedList<T> preorderList,
        BSTNode<T> data) {
        if (data != null) {

            // System.out.println(data.getData());

            preorderList.add(data.getData());
            preorderHelper(preorderList, data.getLeft());
            preorderHelper(preorderList, data.getRight());
        }
        return preorderList;
    }

    /**
     * Should run in O(n).
     *
     * @return an inorder traversal of the tree
     */
    public List<T> inorder() {
        LinkedList<T> inorderList = new LinkedList<T>();
        return inorderHelper(inorderList, root);
    }

    private LinkedList<T> inorderHelper(LinkedList<T> inorderList,
        BSTNode<T> data) {
        if (data != null) {
            inorderHelper(inorderList, data.getLeft());

            // System.out.println(data.getData());

            inorderList.add(data.getData());
            inorderHelper(inorderList, data.getRight());
        }
        return inorderList;
    }

    /**
     * Should run in O(n).
     *
     * @return a postorder traversal of the tree
     */
    public List<T> postorder() {
        LinkedList<T> postorderList = new LinkedList<T>();
        return postorderHelper(postorderList, root);
    }

    private LinkedList<T> postorderHelper(LinkedList<T> postorderList,
        BSTNode<T> data) {
        if (data != null) {

            postorderHelper(postorderList, data.getLeft());
            postorderHelper(postorderList, data.getRight());
            postorderList.add(data.getData());

            // System.out.println(data.getData());
        }
        return postorderList;
    }

    /**
     * Generate a level-order traversal of the tree.
     *
     * To do this, add the root node to a queue. Then, while the queue isn't
     * empty, remove one node, add its data to the list being returned, and add
     * its left and right child nodes to the queue. If what you just removed is
     * {@code null}, ignore it and continue with the rest of the nodes.
     *
     * Should run in O(n).
     *
     * @return a level order traversal of the tree
     */
    public List<T> levelorder() {
        List<T> levelorderList = new LinkedList<T>();

        // if (root != null) {
            
        //     levelorderList.add(root);
        //     while (!levelorderList.isEmpty()) {
        //         BSTNode<T> node = levelorderList.remove();

        //         System.out.println(node.getData());

        //         if (node.getLeft() != null) {
        //             levelorderList.add(node.getLeft());
        //         }

        //         if (node.getRight() != null) {
        //             levelorderList.add(node.getRight());
        //         }

        //     }
        // }
        return levelorderList;
    }

    /**
     * Clears the tree.
     *
     * Should run in O(1).
     */
    public void clear() {
        root = null;
        size = 0;
    }

    /**
     * Calculate and return the height of the root of the tree. A node's
     * height is defined as {@code max(left.height, right.height) + 1}. A leaf
     * node has a height of 0 and a null child should be -1.
     *
     * Should be calculated in O(n).
     *
     * @return the height of the root of the tree, -1 if the tree is empty
     */
    public int height() {
        return getHeight(root);
    }

    private int getHeight(BSTNode<T> temp) {
        //  TEST
        return 0;
    }

    /**
     * THIS METHOD IS ONLY FOR TESTING PURPOSES.
     *
     * DO NOT USE THIS METHOD IN YOUR CODE.
     *
     * @return the number of elements in the tree
     */
    public int size() {
        // DO NOT MODIFY THIS METHOD
        return size;
    }

    /**
     * THIS METHOD IS ONLY FOR TESTING PURPOSES.
     *
     * DO NOT USE THIS METHOD IN YOUR CODE.
     *
     * @return the root of the tree
     */
    public BSTNode<T> getRoot() {
        // DO NOT MODIFY THIS METHOD!
        return root;
    }
}
