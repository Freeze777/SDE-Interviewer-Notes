package leetcode.medium.trees;

/**
 * <a href="https://leetcode.com/problems/my-calendar-i/">https://leetcode.com/problems/my-calendar-i/</a>
 */
public class MyCalendar {
    private AvlTreeNode _root;

    private static class AvlTreeNode {
        int start, end, height, balanceFactor;
        AvlTreeNode left, right;

        public AvlTreeNode(int start, int end) {
            this.start = start;
            this.end = end;
            this.balanceFactor = this.height = 0;
        }

        @Override
        public String toString() {
            return "AvlTreeNode{" + "start=" + start + ", end=" + end + ", height=" + height
                    + ",left=" + left + ", right=" + right + "}";
        }
    }

    public MyCalendar() {
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

    private void update(AvlTreeNode node) {
        int leftHeight = node.left == null ? -1 : node.left.height;
        int rightHeight = node.right == null ? -1 : node.right.height;
        node.height = 1 + Math.max(leftHeight, rightHeight);
        node.balanceFactor = rightHeight - leftHeight;
    }

    private boolean hasOverlap(int start, int end, AvlTreeNode node) {
        return Math.max(start, node.start) <= Math.min(end, node.end);
    }

    private AvlTreeNode overlap(AvlTreeNode node, int start, int end) {
        if (node == null) return null;
        if (hasOverlap(start, end, node)) return node;
        if (end <= node.start) return overlap(node.left, start, end);
        return overlap(node.right, start, end);
    }

    private AvlTreeNode insert(AvlTreeNode node, int start, int end) {
        if (node == null) return new AvlTreeNode(start, end);
        if (end < node.start) node.left = insert(node.left, start, end);
        if (start > node.end) node.right = insert(node.right, start, end);
        update(node);
        return balance(node);
    }

    public boolean book(int start, int end) {
        AvlTreeNode overlappedNode = overlap(_root, start, end - 1);
        if (overlappedNode != null) return false;
        _root = insert(_root, start, end - 1);
        return true;
    }

    public static void main(String[] args) {
        MyCalendar calendar = new MyCalendar();
        System.out.println(calendar.book(47, 50));//true
        System.out.println(calendar.book(33, 41));//true
        System.out.println(calendar.book(39, 45));//false
        System.out.println(calendar.book(33, 42));//false
        System.out.println(calendar.book(25, 32));//true
        System.out.println(calendar.book(26, 35));//false
        System.out.println(calendar.book(19, 25));//true
        System.out.println(calendar.book(3, 8));//true
        System.out.println(calendar.book(8, 13));//true
        System.out.println(calendar.book(18, 27));//false
    }
}

