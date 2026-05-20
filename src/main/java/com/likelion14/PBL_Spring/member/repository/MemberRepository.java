package com.likelion14.PBL_Spring.member.repository;

import com.likelion14.PBL_Spring.member.domain.role.Role;

public interface MemberRepository {
    void save(Role member);
    Role findByName(String name);
    java.util.List<Role> findAll();
    boolean existsByName(String name);
    void updateByName(String name, Role updated);
    boolean deleteMember(String name);
}
