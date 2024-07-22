package landvibe.springintro.member.controller;

public class MemberForm {
    private String name; //createMemberForm에 있는 name이랑 매칭될거야

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
