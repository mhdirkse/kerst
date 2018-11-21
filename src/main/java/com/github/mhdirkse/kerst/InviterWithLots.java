package com.github.mhdirkse.kerst;

import java.util.concurrent.ThreadLocalRandom;

public class InviterWithLots implements NonSelfPermutations.Visitor {
    private MailSender mailSender;

    public InviterWithLots(MailSender mailSender){
        this.mailSender = mailSender;
    }

    public void sendAll() {
        NonSelfPermutations perms = new NonSelfPermutations(Member.values().length);
        int selected = ThreadLocalRandom.current().nextInt(0, perms.getNumPermutations());
        perms.accept(selected, this);
    }

    @Override
    public void visit(int from, int to) {
        try {
            Member subject = Member.values()[from];
            Member lot = Member.values()[to];
            mailSender.sendMail(
                    subject.getEmail(), "Uitnodiging, met lot", "body.html",
                    "home.jpg", new FormatterForLot(subject, lot));
        }
        catch(Exception e) {
            throw new IllegalStateException(e);
        }
    }
}
