package com.github.mhdirkse.kerst;

import org.junit.Assert;
import org.junit.Test;

public class FormatterForLotTest {
    @Test
    public void test() {
        String source = "Dear ##subjectName##, your lot is ##lotName##.";
        String expected = "Dear Martijn, your lot is Arri.";
        Assert.assertEquals(expected, new FormatterForLot(Member.MARTIJN, Member.ARRI).apply(source));
    }
}
