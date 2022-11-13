package leetcode.hard.trees;

/**
 * <a href="https://leetcode.com/problems/count-integers-in-intervals/description/">https://leetcode.com/problems/count-integers-in-intervals/description/</a>
 */
class CountIntervals {
    private AvlTreeNode _root;

    public CountIntervals() {
    }

    private static class AvlTreeNode {
        int start, end, height, balanceFactor, range, subtreeRange;
        AvlTreeNode left, right;

        public AvlTreeNode(int start, int end) {
            this.start = start;
            this.end = end;
            this.subtreeRange = this.range = end - start + 1;
            this.balanceFactor = this.height = 0;
        }

        @Override
        public String toString() {
            return "TreeNode{" + "start=" + start + ", end=" + end + ", subtreeRange=" + subtreeRange + "}";
        }
    }

    private AvlTreeNode balance(AvlTreeNode node) {
        //left heavy
        if (node.balanceFactor == -2) {
            if (node.left.balanceFactor > 0) {
                node.left = leftRotate(node.left);
            }
            return rightRotate(node);
        }
        //right heavy
        else if (node.balanceFactor == 2) {
            if (node.right.balanceFactor < 0) {
                node.right = rightRotate(node.right);
            }
            return leftRotate(node);
        }
        return node;
    }

    private AvlTreeNode rightRotate(AvlTreeNode a) {
        AvlTreeNode b = a.left;
        a.left = b.right;
        b.right = a;
        update(a);
        update(b);
        return b;
    }

    private AvlTreeNode leftRotate(AvlTreeNode b) {
        AvlTreeNode a = b.right;
        b.right = a.left;
        a.left = b;
        update(b);
        update(a);
        return a;
    }

    public void printTree(AvlTreeNode root) {
        if (root == null) return;
        printTree(root.left);
        System.out.println(root);
        printTree(root.right);
    }

    private void update(AvlTreeNode node) {
        int leftHeight = node.left == null ? -1 : node.left.height;
        int rightHeight = node.right == null ? -1 : node.right.height;
        node.height = 1 + Math.max(leftHeight, rightHeight);
        node.balanceFactor = rightHeight - leftHeight;
        int totalLeftRange = node.left == null ? 0 : node.left.subtreeRange;
        int totalRightRange = node.right == null ? 0 : node.right.subtreeRange;
        node.subtreeRange = node.range + totalLeftRange + totalRightRange;
    }

    private AvlTreeNode insert(AvlTreeNode node, int start, int end) {
        if (start > end) return node;
        if (node == null) return new AvlTreeNode(start, end);
        node.left = insert(node.left, start, Math.min(node.start - 1, end));
        node.right = insert(node.right, Math.max(node.end + 1, start), end);
        update(node);
        return balance(node);
    }

    public void add(int start, int end) {
        _root = insert(_root, start, end);
    }

    public int count() {
        return _root == null ? 0 : _root.subtreeRange;
    }

    public static void main(String[] args) {
        CountIntervals ci = new CountIntervals();
        ci.add(1, 10);
        System.out.println(ci.count());
        ci.add(1, 10);
        System.out.println(ci.count());
        ci.add(1, 15);
        System.out.println(ci.count());
        ci.add(16, 20);
        System.out.println(ci.count());
        ci.add(-10, 50);
        System.out.println(ci.count());//61
        ci.add(1, 30);
        System.out.println(ci.count());//61

        CountIntervals ci2 = new CountIntervals();
        ci2.add(2, 3);
        ci2.add(7, 10);
        System.out.println(ci2.count());//6
        ci2.add(5, 8);
        System.out.println(ci2.count());//8
        ci2.add(3, 4);
        ci2.add(6, 7);
        ci2.add(9, 11);
        ci2.add(15, 20);
        ci2.add(30, 40);
        ci2.add(60, 70);
        System.out.println(ci2.count());//38
    }
}
