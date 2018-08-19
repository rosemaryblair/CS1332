
public class Driver {

	public static void main(String[] args) {

		LinkedDeque<Integer> linked = new LinkedDeque<>();

		// linked.addFirst(2);
  //       linked.addFirst(1);
  //       linked.addLast(3);
  //       linked.addLast(4);

  //       LinkedNode<Integer> node = linked.getHead();
  //       LinkedNode<Integer> prev = linked.getHead();

  //       System.out.println("");
  //       System.out.println("Number of elements: " + linked.size());

  //       node = node.getNext();
  //       prev = node;
  //       node = node.getNext();
  //       prev = node;
  //       node = node.getNext();

  //       boolean test1 = (node != null);
  //       boolean test2 = (node.getPrevious() == prev);
  //       boolean test3 = (linked.getTail() == node);
  //       boolean test4 = (node.getNext() == null);

  //       System.out.println("");
  //       System.out.println(test1);
  //       System.out.println(test2);
  //       System.out.println(test3);
  //       System.out.println(test4);
  //       System.out.println(node.getData());
  //       System.out.println("");

		linked.addFirst(2);
        linked.addFirst(1);
        linked.addLast(3);
        linked.addLast(4);

        linked.removeFirst();
        linked.removeLast();

        LinkedNode<Integer> node = linked.getHead();
        LinkedNode<Integer> prev = linked.getHead();

        prev = node;
        node = node.getNext();

        System.out.println("");
        boolean test1 = (null == node.getNext());
        System.out.println(test1);
        System.out.println(node.getData());
        System.out.println("");


	}

}