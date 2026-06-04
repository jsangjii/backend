package com.likelion14.PBL_Spring.member.repository;


import com.likelion14.PBL_Spring.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

// 멤버 저장소 인터페이스
public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByName(String name);
    boolean existsByName(String name);

    // part로 멤버 목록을 조회하는 쿼리 메서드
    List<Member> findByPart(String part);


}