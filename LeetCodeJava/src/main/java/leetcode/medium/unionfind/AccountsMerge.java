package leetcode.medium.unionfind;

import java.util.*;

/**
 * <a href="https://leetcode.com/problems/accounts-merge/">https://leetcode.com/problems/accounts-merge/</a>
 */
public class AccountsMerge {
    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        int numVertices = accounts.size();
        UnionFind uf = new UnionFind(numVertices);
        Map<String, Integer> emailToNameMap = new HashMap<>();
        for (int i = 0; i < accounts.size(); i++) {
            List<String> account = accounts.get(i);
            for (int j = 1; j < account.size(); j++) {
                String email = account.get(j);
                if (emailToNameMap.containsKey(email)) {
                    uf.union(i, emailToNameMap.get(email));
                } else {
                    emailToNameMap.put(email, i);
                }
            }
        }

        Map<Integer, Set<String>> mergedAccounts = new HashMap<>();
        for (int i = 0; i < accounts.size(); i++) {
            int parent = uf.find(i);
            if (!mergedAccounts.containsKey(parent)) mergedAccounts.put(parent, new TreeSet<>());
            mergedAccounts.get(parent).addAll(accounts.get(i));
        }

        List<List<String>> finalAccounts = new ArrayList<>();
        for (Map.Entry<Integer, Set<String>> entry : mergedAccounts.entrySet()) {
            String name = accounts.get(entry.getKey()).get(0);
            List<String> account = new ArrayList<>();
            account.add(name);
            entry.getValue().remove(name);
            account.addAll(entry.getValue());
            finalAccounts.add(account);
        }

        return finalAccounts;
    }

    public static void main(String[] args) {
        AccountsMerge am = new AccountsMerge();
        List<List<String>> accounts = Arrays.asList(Arrays.asList("John", "johnsmith@mail.com", "john_newyork@mail.com"),
                Arrays.asList("John", "johnsmith@mail.com", "john00@mail.com"),
                Arrays.asList("Mary", "mary@mail.com"),
                Arrays.asList("John", "johnnybravo@mail.com")
        );
        System.out.println(am.accountsMerge(accounts));
    }
}
