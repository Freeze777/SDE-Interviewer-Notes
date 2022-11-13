package leetcode.hard.trees;

class CountIntervals {

    private TreeNode root;

    private static class TreeNode {
        int start, end, count;
        TreeNode left, right;

        public TreeNode(int start, int end) {
            this.start = start;
            this.end = end;
            this.count = end - start + 1;
        }

        @Override
        public String toString() {
            return "TreeNode{" + "start=" + start + ", end=" + end + ", count=" + count + "}";
        }
    }

    public CountIntervals() {
    }

    public void printTree(TreeNode root) {
        if (root == null) return;
        printTree(root.left);
        System.out.println(root);
        printTree(root.right);
    }

    private void computeCount(TreeNode root) {
        if (root == null) return;
        root.count = root.end - root.start + 1;
        if (root.left != null) root.count += root.left.count;
        if (root.right != null) root.count += root.right.count;
    }

    private TreeNode insertInterval(TreeNode root, int start, int end) {
        if (root == null) return new TreeNode(start, end);
        if (start >= root.start && end <= root.end) return root;
        if (end < root.start) {
            root.left = insertInterval(root.left, start, end);
            computeCount(root);
            return root;
        }
        if (start > root.end) {
            root.right = insertInterval(root.right, start, end);
            computeCount(root);
            return root;
        }
        //cut intervals
        if (start < root.start) root.left = insertInterval(root.left, start, root.start - 1);
        if (end > root.end) root.right = insertInterval(root.right, root.end + 1, end);
        computeCount(root);
        return root;
    }

    public void add(int start, int end) {
        root = insertInterval(root, start, end);
    }

    public int count() {
        return root == null ? 0 : root.count;
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
        //ci2.printTree(ci2._root);
    }
    /*
    ["CountIntervals","add","count","add","count","add","count","add","count","add","add","count","add","count","count","add","count","count","count","count","add","count","count","count","add","count","count","count","count","add","add","add","add","add","count","add","count","count","count","add","add","count","count","count","count","count","count","add","count","count","add","add","add","count","add","count","add","add","count","count","count","add","count","count","add","add","count","add","count","count","count","count"]
    [[],[25,159],[],[343,892],[],[565,976],[],[333,368],[],[424,666],[800,858],[],[249,746],[],[],[759,881],[],[],[],[],[447,668],[],[],[],[489,525],[],[],[],[],[686,833],[579,763],[453,770],[329,411],[229,860],[],[384,491],[],[],[],[882,924],[451,592],[],[],[],[],[],[],[691,820],[],[],[251,641],[921,968],[199,820],[],[781,892],[],[89,477],[142,610],[],[],[],[894,942],[],[],[995,998],[74,830],[],[882,958],[],[],[],[]]
    */

}
