package leetcode.medium.graph;

import java.util.*;
import java.util.stream.Collectors;

public class MinimumGeneticMutation {

    private static class MutationNode {
        String mutation;
        int depth;

        public MutationNode(String mutation, int depth) {
            this.depth = depth;
            this.mutation = mutation;
        }
    }

    public final String GENE_CHARACTERS = "ACGT";

    public Set<String> mutate(String gene) {
        Set<String> mutations = new HashSet<>();
        for (int i = 1; i < gene.length() - 1; i++) {
            for (Character alpha : GENE_CHARACTERS.toCharArray()) {
                String mutation = gene.substring(0, i) + alpha.toString() + gene.substring(i + 1);
                mutations.add(mutation);
            }
        }
        for (Character alpha : GENE_CHARACTERS.toCharArray()) {
            mutations.add(alpha.toString() + gene.substring(1));
            mutations.add(gene.substring(0, gene.length() - 1) + alpha);
        }
        mutations.remove(gene);
        return mutations;
    }

    public int minMutation(String start, String end, String[] bank) {
        Set<String> _bank = Arrays.stream(bank).collect(Collectors.toSet());
        Queue<MutationNode> bfsQ = new LinkedList<>();
        Set<String> visited = new HashSet<>();
        int minMutations = -1;
        bfsQ.add(new MutationNode(start, 0));
        while (!bfsQ.isEmpty()) {
            MutationNode mutationNode = bfsQ.remove();

            if (mutationNode.mutation.equals(end)) {
                minMutations = mutationNode.depth;
                break;
            }
            if (visited.contains(mutationNode.mutation)) continue;
            Set<String> nextMutations = mutate(mutationNode.mutation);
            nextMutations.retainAll(_bank);
            bfsQ.addAll(nextMutations.stream().map(m -> new MutationNode(m, mutationNode.depth + 1)).collect(Collectors.toList()));
            visited.add(mutationNode.mutation);
        }
        return minMutations;
    }

    public static void main(String[] args) {
        MinimumGeneticMutation mgm = new MinimumGeneticMutation();
        System.out.println(mgm.minMutation("AACCGGTT", "AACCGGTA", new String[]{"AACCGGTA"}));//1
        System.out.println(mgm.minMutation("AACCGGTT", "AAACGGTA", new String[]{"AACCGGTA", "AACCGCTA", "AAACGGTA"}));//2
        System.out.println(mgm.minMutation("AAAAACCC", "AACCCCCC", new String[]{"AAAACCCC", "AAACCCCC", "AACCCCCC"}));//3
        System.out.println(mgm.minMutation("AAAAACCC", "AACCCCCC", new String[]{"GGTACCCT"}));//-1
    }

}
