package org.uml.funfitness.pojo;

import java.sql.Time;

public class Gym {

    private Integer memberAccount;
    private String memberName;
    private Time time;

    public Integer getMemberAccount() {
        return memberAccount;
    }

    public void setMemberAccount(Integer memberAccount) {
        this.memberAccount = memberAccount;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    @Override
    public String toString() {
        return "Gym{" +
                "memberAccount=" + memberAccount +
                ", memberName='" + memberName + '\'' +
                ", time=" + time +
                '}';
    }
}
