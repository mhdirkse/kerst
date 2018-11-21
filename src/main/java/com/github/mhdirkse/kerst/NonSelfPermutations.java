package com.github.mhdirkse.kerst;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.google.common.base.Joiner;
import com.google.common.collect.Collections2;

public class NonSelfPermutations {
    private List<List<Integer>> data;

    public interface Visitor {
        void visit(int from, int to);
    }

    public NonSelfPermutations(final int size) {
        List<Integer> toPermute = IntStream.range(0, size)
                .boxed().collect(Collectors.toList());
        data = Collections2.orderedPermutations(toPermute).stream()
                .filter(p -> (!takesSelf(p)))
                .collect(Collectors.toList());
    }

    static boolean takesSelf(List<Integer> permutation) {
        for(int i = 0; i < permutation.size(); ++i) {
            if(i == permutation.get(i)) {
                return true;
            }
        }
        return false;
    }

    public int getNumPermutations() {
        return data.size();
    }

    public void accept(int seq, Visitor v) {
        List<Integer> selected = data.get(seq);
        doAccept(selected, v);
    }

    static void doAccept(List<Integer> selected, Visitor v) {
        for(int from = 0; from < selected.size(); ++from) {
            v.visit(from, selected.get(from).intValue());
        }
    }

    void list() {
        for(List<Integer> p : data) {
            List<String> labels = p.stream().map(i -> String.format("%2d", i)).collect(Collectors.toList());
            System.out.println(Joiner.on(", ").join(labels));
        }
    }
}
