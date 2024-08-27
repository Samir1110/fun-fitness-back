package org.uml.funfitness.pojo;

public class Member {
    private Integer memberAccount;
    private String memberPassword;
    private String memberName;
    private String memberGender;
    private Integer memberAge;
    private Integer memberHeight;
    private Integer memberWeight;
    private Long memberPhone;
    private String cardTime;
    private Integer cardClass;
    private Integer cardNextClass;
    private Integer memberCredit;
    public Integer getMemberAccount() {
        return memberAccount;
    }

    public void setMemberAccount(Integer memberAccount) {
        this.memberAccount = memberAccount;
    }

    public String getMemberPassword() {
        return memberPassword;
    }

    public void setMemberPassword(String memberPassword) {
        this.memberPassword = memberPassword;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public String getMemberGender() {
        return memberGender;
    }

    public void setMemberGender(String memberGender) {
        this.memberGender = memberGender;
    }

    public Integer getMemberAge() {
        return memberAge;
    }

    public void setMemberAge(Integer memberAge) {
        this.memberAge = memberAge;
    }

    public Integer getMemberHeight() {
        return memberHeight;
    }

    public void setMemberHeight(Integer memberHeight) {
        this.memberHeight = memberHeight;
    }

    public Integer getMemberWeight() {
        return memberWeight;
    }

    public void setMemberWeight(Integer memberWeight) {
        this.memberWeight = memberWeight;
    }

    public Long getMemberPhone() {
        return memberPhone;
    }

    public void setMemberPhone(Long memberPhone) {
        this.memberPhone = memberPhone;
    }

    public String getCardTime() {
        return cardTime;
    }

    public void setCardTime(String cardTime) {
        this.cardTime = cardTime;
    }

    public Integer getCardClass() {
        return cardClass;
    }

    public void setCardClass(Integer cardClass) {
        this.cardClass = cardClass;
    }

    public Integer getCardNextClass() {
        return cardNextClass;
    }

    public void setCardNextClass(Integer cardNextClass) {
        this.cardNextClass = cardNextClass;
    }

    public Integer getMemberCredit() {
        return memberCredit;
    }

    public void setMemberCredit(Integer memberCredit) {
        this.memberCredit = memberCredit;
    }
    @Override
    public String toString() {
        return "Member{" +
                "memberAccount=" + memberAccount +
                ", memberPassword='" + memberPassword + '\'' +
                ", memberName='" + memberName + '\'' +
                ", memberGender='" + memberGender + '\'' +
                ", memberAge=" + memberAge +
                ", memberHeight=" + memberHeight +
                ", memberWeight=" + memberWeight +
                ", memberPhone=" + memberPhone +
                ", cardTime='" + cardTime + '\'' +
                ", cardClass=" + cardClass +
                ", cardNextClass=" + cardNextClass +
                ", memberCredit=" + memberCredit +
                '}';
    }
}
