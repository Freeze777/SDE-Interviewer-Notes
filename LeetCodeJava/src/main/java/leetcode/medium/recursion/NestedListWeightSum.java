package leetcode.medium.recursion;


import java.util.ArrayList;
import java.util.List;


public class NestedListWeightSum {
    private static class NestedInteger {
        Integer value;
        List<NestedInteger> list = new ArrayList<>();

        // @return true if this NestedInteger holds a single integer, rather than a nested list.
        public boolean isInteger() {
            return value != null;
        }

        // @return the single integer that this NestedInteger holds, if it holds a single integer
        // Return null if this NestedInteger holds a nested list
        public Integer getInteger() {
            return value;
        }

        // Set this NestedInteger to hold a single integer.
        public void setInteger(int value) {
            this.value = value;
        }

        // Set this NestedInteger to hold a nested list and adds a nested integer to it.
        public void add(NestedInteger ni) {
            list.add(ni);
        }

        // @return the nested list that this NestedInteger holds, if it holds a nested list
        // Return empty list if this NestedInteger holds a single integer
        public List<NestedInteger> getList() {
            return this.list;
        }
    }

    public int depthSum(List<NestedInteger> nestedList) {
        return depthSum(nestedList, 0, 1);
    }

    public int depthSum(List<NestedInteger> nestedList, int index, int depth) {
        if (index >= nestedList.size()) return 0;
        int sum = 0;
        if (nestedList.get(index).isInteger()) sum += depth * nestedList.get(index).getInteger();
        else sum += depthSum(nestedList.get(index).getList(), 0, depth + 1);
        sum += depthSum(nestedList, index + 1, depth);
        return sum;
    }

    public static void main(String[] args) {
        NestedListWeightSum nlws = new NestedListWeightSum();
        //[[1,1],2,[1,1]]
        List<NestedInteger> nested = new ArrayList<>();
        NestedInteger one = new NestedInteger();
        one.setInteger(1);
        NestedInteger oneOne = new NestedInteger();
        oneOne.add(one);
        oneOne.add(one);
        NestedInteger two = new NestedInteger();
        two.setInteger(2);
        nested.add(oneOne);
        nested.add(two);
        nested.add(oneOne);
        System.out.println(nlws.depthSum(nested));

        //[1,[4,[6]]]
        nested = new ArrayList<>();
        NestedInteger sixList = new NestedInteger();
        NestedInteger six = new NestedInteger();
        six.setInteger(6);
        sixList.add(six);
        NestedInteger fourList = new NestedInteger();
        NestedInteger four = new NestedInteger();
        four.setInteger(4);
        fourList.add(four);
        fourList.add(sixList);
        nested.add(one);
        nested.add(fourList);
        System.out.println(nlws.depthSum(nested));
    }
}
