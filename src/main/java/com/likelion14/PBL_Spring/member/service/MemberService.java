package com.likelion14.PBL_Spring.member.service;

import com.likelion14.PBL_Spring.member.domain.RoleType;
import com.likelion14.PBL_Spring.member.dto.LionUpdateRequest;
import com.likelion14.PBL_Spring.member.dto.StaffCreateRequest;
import com.likelion14.PBL_Spring.member.dto.StaffUpdateRequest;
import org.springframework.stereotype.Service;;
import com.likelion14.PBL_Spring.member.dto.LionCreateRequest;
import com.likelion14.PBL_Spring.member.repository.MemberRepository;
import com.likelion14.PBL_Spring.member.domain.Member;
import java.util.List;

@Service
public class MemberService {
    private final MemberRepository repository;

    public MemberService(MemberRepository repository) {
        this.repository = repository;
    }

    // Lion 등록
    public Member createLion(LionCreateRequest request) {
        if (repository.existsByName(request.getName())) {
            return null;
        }
        Member member = Member.builder()
                .name(request.getName())
                .major(request.getMajor())
                .generation(request.getGeneration())
                .part(request.getPart())
                .roleType(RoleType.LION)
                .studentId(request.getStudentId())
                .position(request.getPosition())
                .build();

        return repository.save(member);
    }
    // Staff 등록
    public Member createStaff(StaffCreateRequest request) {
        if (repository.existsByName(request.getName())) {
            return null;
        }
        Member member = new Member(request.getName(), request.getMajor(), request.getGeneration(),
                request.getPart(), RoleType.ADMIN, null, request.getPosition());
        return repository.save(member);
    }
    // Lion 수정
    public Member updateLion(Long id, LionUpdateRequest request) {
        Member member = repository.findById(id).orElse(null);
        if (member == null) {
            return null;
        }
        member.updateInfo(request.getMajor(), request.getGeneration(), request.getPart());
        member.updateStudentId(request.getStudentId());
        return repository.save(member);
    }
    // 스태프 수정
    public Member updateStaff(Long id, StaffUpdateRequest request) {
        Member member = repository.findById(id).orElse(null);
        if (member == null) {
            return null;
        }
        member.updateInfo(request.getMajor(), request.getGeneration(), request.getPart());
        member.updatePosition(request.getPosition());
        return repository.save(member);
    }
    //이름으로 검색
    public Member searchByName(String name) {
        return repository.findByName(name).orElse(null);
    }
    //전체 조회
    public List<Member> getAllMembers() {
        return repository.findAll();
    }
    //ID로 조회
    public Member findById(Long id) {
        return repository.findById(id).orElse(null);
    }
    //삭제
    public boolean deleteMember(Long id) {
        if (!repository.existsById(id)) {
            return false;
        }
        repository.deleteById(id);
        return true;
    }
}

