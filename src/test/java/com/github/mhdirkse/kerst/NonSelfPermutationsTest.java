package com.github.mhdirkse.kerst;

import java.util.Arrays;
import java.util.List;

import org.easymock.EasyMock;
import org.easymock.EasyMockRunner;
import org.easymock.Mock;
import org.easymock.MockType;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(EasyMockRunner.class)
public class NonSelfPermutationsTest {
    @Mock(type = MockType.STRICT)
    NonSelfPermutations.Visitor visitor;

    @Test
    public void testNotTakesSelfDetected() {
        List<Integer> value = Arrays.asList(new Integer[] {1, 2, 0});
        Assert.assertFalse(NonSelfPermutations.takesSelf(value));
    }

    @Test
    public void whenFirstTakesSelfThenTakesSelfDetected() {
        List<Integer> value = Arrays.asList(new Integer[] {0, 2, 1});
        Assert.assertTrue(NonSelfPermutations.takesSelf(value));
    }

    @Test
    public void whenOtherTakesSelfThenTakesSelfDetected() {
        List<Integer> value = Arrays.asList(new Integer[] {2, 1, 0});
        Assert.assertTrue(NonSelfPermutations.takesSelf(value));
    }

    @Test
    public void whenSizeTwoThenOnePermutation() {
        Assert.assertEquals(1, new NonSelfPermutations(2).getNumPermutations());        
    }

    @Test
    public void whenSizeThreeThenTwoPermutations() {
        Assert.assertEquals(2, new NonSelfPermutations(3).getNumPermutations());
    }

    @Test
    public void testVisiting() {
        visitor.visit(0, 1);
        visitor.visit(1, 2);
        visitor.visit(2, 0);
        EasyMock.replay(visitor);
        NonSelfPermutations.doAccept(Arrays.asList(1, 2, 0), visitor);
        EasyMock.verify(visitor);
    }

    @Test
    public void testList() {
        System.out.println("Listing permutations for four people");
        new NonSelfPermutations(4).list();
        System.out.println("Done");
    }
}
