import java.util.List;
import java.util.ArrayList;

public class Driver {

	public static void main(String[] args) {
		AVL<Integer> avlTree = new AVL<>();

		System.out.println();
		System.out.println("Initial Size: " + avlTree.size() + "\n");

		System.out.println("-- Test 7 --\n");

		avlTree.add(76);
		avlTree.add(34);
		avlTree.add(90);
		avlTree.add(40);
		avlTree.add(81);

		boolean t1 = ((Integer) 76).equals(avlTree.successor(40));

		System.out.println("Equals? " + t1);

		System.out.println("Height: " + avlTree.height());
		System.out.println("Size: " + avlTree.size() + "\n");

		System.out.println();

		// //Add Right Rotation Test
		// avlTree.add(5);
		// avlTree.add(4);
		// avlTree.add(3);

		// System.out.println("-- Test 1 -- \n");

		// AVLNode<Integer> root = avlTree.getRoot();
		// boolean t1 = (Integer) 4 == root.getData();
		// boolean t2 = 1 == root.getHeight();
		// boolean t3 = 0 == root.getBalanceFactor();
		// boolean t4 = (Integer) 3 == root.getLeft().getData();
		// boolean t5 = 0 == root.getLeft().getHeight();
		// boolean t6 = 0 == root.getLeft().getBalanceFactor();
		// boolean t7 = (Integer) 5 == root.getRight().getData();
		// boolean t8 = 0 == root.getRight().getHeight();
		// boolean t9 = 0 == root.getRight().getBalanceFactor();

		// System.out.println("Equals? " + t7);
		// System.out.println("Equals? " + t8);
		// System.out.println("Equals? " + t9);

		// // Add Right-Left Rotation Test
		// avlTree.add(3);
		// avlTree.add(4);
		// avlTree.add(5);
		// AVLNode<Integer> root = avlTree.getRoot();

		// boolean t1 = 3 == avlTree.size();
		// boolean t2 = (Integer) 4 == root.getData();
		// boolean t3 = 1 == root.getHeight();
		// boolean t4 = 0 == root.getBalanceFactor();
		// boolean t5 = (Integer) 3 == root.getLeft().getData();
		// boolean t6 = 0 == root.getLeft().getHeight();
		// boolean t7 = 0 == root.getLeft().getBalanceFactor();
		// boolean t8 = (Integer) 5 == root.getRight().getData();
		// boolean t9 = 0 == root.getRight().getHeight();
		// boolean t10 = 0 == root.getRight().getBalanceFactor();

		// System.out.println("Equals? " + t5);
		// System.out.println("Equals? " + t6);
		// System.out.println("Equals? " + t7);
		// System.out.println("Equals? " + t8);
		// System.out.println("Equals? " + t9);
		// System.out.println("Equals? " + t10);

		// Remove Test
		// Integer r = new Integer(526);
		// avlTree.add(646);
		// avlTree.add(386);
		// avlTree.add(856);
		// avlTree.add(r);
		// avlTree.add(477);

		// Integer r2 = avlTree.remove(new Integer(526));
		// boolean t1 = r2.equals(r);

		// AVLNode<Integer> root = avlTree.getRoot();
		// boolean t2 = ((Integer) 646).equals(root.getData());
		// boolean t3 = 2 == root.getHeight();
		// boolean t4 = 1 == root.getBalanceFactor();
		// boolean t5 = ((Integer) 477).equals(root.getLeft().getData());
		// boolean t6 = 1 == root.getLeft().getHeight();
		// boolean t7 = 1 == root.getLeft().getBalanceFactor();
		// boolean t8 = ((Integer) 856).equals(root.getRight().getData());
		// boolean t9 = 0 == root.getRight().getHeight();
		// boolean t10 = 0 == root.getRight().getBalanceFactor();

		// System.out.println("Equals? " + t8);
		// System.out.println("Equals? " + t9);
		// System.out.println("Equals? " + t10);

		// Height Test
		// avlTree.add(646);
		// avlTree.add(386);
		// avlTree.add(856);
		// avlTree.add(526);
		// avlTree.add(477);

		// boolean t1 = 2 == avlTree.height();
		// System.out.println("Equals? " + t1);

		// System.out.println("Height: " + avlTree.height());

		// // Get Test
		// Integer maximum = new Integer(646);
		// avlTree.add(526);
		// avlTree.add(386);
		// avlTree.add(477);
		// avlTree.add(maximum);
		// avlTree.add(856);

		// Integer m = avlTree.get(new Integer(856));
		// boolean t1 = m.equals(maximum);
		// System.out.println("Equals? " + t1);

		// // Clear and Constructor Test
		// 		List<Integer> toAdd = new ArrayList<>();
		// toAdd.add(24); toAdd.add(1); toAdd.add(7);
		// avlTree = new AVL<>(toAdd);

		// System.out.println("Height: " + avlTree.height());
		// System.out.println("Size: " + avlTree.size());

		// avlTree.clear();
		// boolean t1 = null == avlTree.getRoot();
		// boolean t2 = 0 == avlTree.size();

		// System.out.println("Equals? " + t1);
		// System.out.println("Equals? " + t2);
	}

}
