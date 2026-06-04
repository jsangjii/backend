package com.likelion14.PBL_Spring.member.service;

import com.likelion14.PBL_Spring.global.exception.DuplicateMemberException;
import com.likelion14.PBL_Spring.global.exception.MemberNotFoundException;
import com.likelion14.PBL_Spring.member.domain.RoleType;
import com.likelion14.PBL_Spring.member.dto.LionUpdateRequest;
import com.likelion14.PBL_Spring.member.dto.StaffCreateRequest;
import com.likelion14.PBL_Spring.member.dto.StaffUpdateRequest;
import jakarta.transaction.Transactional;
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
    @Transactional
    public Member createLion(LionCreateRequest request) {
        if (repository.existsByName(request.getName())) {
            throw new DuplicateMemberException("이미 존재하는 이름입니다. name: " + request.getName());
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
    @Transactional
    public Member createStaff(StaffCreateRequest request) {
        if (repository.existsByName(request.getName())) {
            throw new DuplicateMemberException("이미 존재하는 이름입니다. name: " + request.getName());
        }
        Member member = new Member(request.getName(), request.getMajor(), request.getGeneration(),
                request.getPart(), RoleType.ADMIN, null, request.getPosition());
        return repository.save(member);
    }

    // Lion 수정
    @Transactional
    public Member updateLion(Long id, LionUpdateRequest request) {
        Member member = repository.findById(id)
                .orElseThrow(() -> new MemberNotFoundException("해당 멤버를 찾을 수 없습니다. id: " + id));

        member.updateInfo(request.getMajor(), request.getGeneration(), request.getPart());
        member.updateStudentId(request.getStudentId());
        return repository.save(member);
        }

    // Staff 수정
    @Transactional
    public Member updateStaff(Long id, StaffUpdateRequest request) {
        Member staff = repository.findById(id)
                .orElseThrow(() -> new MemberNotFoundException("해당 멤버를 찾을 수 없습니다. id: " + id));

        staff.updateInfo(request.getMajor(), request.getGeneration(), request.getPart());
        staff.updatePosition(request.getPosition());
        return repository.save(staff);
        }

    //이름으로 Member 조회
    public Member searchByName(String name) {
        return repository.findByName(name)
                .orElseThrow(() -> new MemberNotFoundException("해당 멤버를 찾을 수 없습니다. name: " + name));
        }

    //ID로 Member 조회
    public Member findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new MemberNotFoundException("해당 멤버를 찾을 수 없습니다. id: " + id));
    }

    //전체 member 조회
    public List<Member> getAllMembers() {
        return repository.findAll();
    }

    //Member 삭제
    @Transactional
    public void deleteMember(Long id) {
        if (!repository.existsById(id)) {
            throw new MemberNotFoundException("해당 멤버를 찾을 수 없습니다. id: " + id);
        }
        repository.deleteById(id);
    }

    // 파트별 Member 목록 조회
    public List<Member> getMembersByPart(String part) {
        return repository.findByPart(part);
    }

}


