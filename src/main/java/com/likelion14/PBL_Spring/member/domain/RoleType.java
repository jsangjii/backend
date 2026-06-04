package com.likelion14.PBL_Spring.member.domain;

public enum RoleType {
    ADMIN("운영진"),
    LION("아기사자");

    private final String displayName;

    RoleType(String displayName) {this.displayName = displayName;}

    public String getDisplayName() {return displayName;}
}
