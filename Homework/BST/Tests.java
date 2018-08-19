
public class Tests {
	
	    public int sumOddLevels() {
        if (root == null) {
            return 0;
        }
        return sumOddLevels(root);
    }

    private int sumOddLevels(BSTNode<T> current) {
        if (current == null) {
            return 0;
        }
        int sum = 0;
        sum += current.data;
        if (current.left != null) {
            sum += sumOddLevels(current.left);
        }
        if (current.right != null) {
            sum += sumOddLevels(current.right);
        }
        return sum;
    }

    
}