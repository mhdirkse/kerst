package com.github.mhdirkse.kerst;

import java.util.function.Function;

public class FormatterForLot implements Function<String, String> {
    private Member subject;
    private Member lot;

    public FormatterForLot(Member subject, Member lot) {
        this.subject = subject;
        this.lot = lot;
    }

    @Override
    public String apply(String origText) {
        return origText
                .replaceAll(tag("subjectName"), subject.getFirstName())
                .replaceAll(tag("lotName"), lot.getFirstName());
    }

    private static String tag(final String item) {
        return "##" + item + "##";
    }
}
