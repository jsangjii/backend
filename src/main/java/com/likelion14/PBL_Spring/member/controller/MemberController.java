package com.likelion14.PBL_Spring.member.controller;

import com.likelion14.PBL_Spring.member.domain.role.Role;
import com.likelion14.PBL_Spring.member.domain.role.Lion;
import com.likelion14.PBL_Spring.member.domain.role.Staff;
import com.likelion14.PBL_Spring.member.dto.*;
import com.likelion14.PBL_Spring.member.service.MemberService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Tag(name = "Member", description = "멤버 관리 API")
@RestController
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @Operation(summary = "전체 멤버 조회 또는 이름으로 검색",
            description = "쿼리 파라미터(name)가 없으면 전체 목록을, 있으면 해당 이름의 멤버를 조회합니다.")
    @GetMapping
    public ResponseEntity<List<?>> getAllOrSearchMembers(
            @Parameter(description = "검색할 멤버의 이름 (선택 사항)", example = "홍길동")
            @RequestParam(required = false) String name) {
        List<Role> members;
        if (name != null && !name.isEmpty()) {
            Role member = memberService.searchByName(name);
            if (member == null) {
                return ResponseEntity.ok(List.of());
            }
            members = List.of(member);
        } else {
            members = memberService.getAllMembers();
        }
        List<?> response = members.stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "아기사자(Lion) 생성", description = "새로운 아기사자 멤버를 등록합니다.")
    @PostMapping("/lions")
    public ResponseEntity<LionResponse> createLion(@RequestBody LionCreateRequest request) {
        Role lion = memberService.createLion(request);
        if (lion == null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(LionResponse.from((Lion) lion));
    }

    @Operation(summary = "운영진(Staff) 생성", description = "새로운 운영진 멤버를 등록합니다.")
    @PostMapping("/staffs")
    public ResponseEntity<StaffResponse> createStaff(@RequestBody StaffCreateRequest request) {
        Role staff = memberService.createStaff(request);
        if (staff == null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(StaffResponse.from((Staff) staff));
    }

    @Operation(summary = "이름으로 단일 멤버 조회", description = "URL 경로에 이름을 넣어 특정 멤버를 조회합니다.")
    @GetMapping("/{name}")
    public ResponseEntity<?> getMember(
            @Parameter(description = "조회할 멤버의 이름", example = "김멋사")
            @PathVariable String name) {
        Role member = memberService.searchByName(name);
        if (member == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(toResponse(member));
    }

    private Object toResponse(Role role) {
        if (role instanceof Lion lion) {
            return LionResponse.from(lion);
        } else if (role instanceof Staff staff) {
            return StaffResponse.from(staff);
        }
        return role;
    }

    @Operation(summary = "아기사자 정보 수정", description = "이름을 기준으로 아기사자의 정보를 수정합니다.")
    @PutMapping("/lions/{name}")
    public ResponseEntity<LionResponse> updateLion(
            @Parameter(description = "수정할 아기사자 이름", example = "이멋사") @PathVariable String name,
            @RequestBody LionUpdateRequest request) {
        Role updated = memberService.updateLion(name, request);
        if (updated == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(LionResponse.from((Lion) updated));
    }

    @Operation(summary = "운영진 정보 수정", description = "이름을 기준으로 운영진의 정보를 수정합니다.")
    @PutMapping("/staffs/{name}")
    public ResponseEntity<StaffResponse> updateStaff(
            @Parameter(description = "수정할 운영진 이름", example = "박운영") @PathVariable String name,
            @RequestBody StaffUpdateRequest request) {
        Role updated = memberService.updateStaff(name, request);
        if (updated == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(StaffResponse.from((Staff) updated));
    }

    @Operation(summary = "멤버 삭제", description = "이름을 기준으로 멤버를 삭제합니다.")
    @DeleteMapping("/{name}")
    public ResponseEntity<Void> deleteMember(
            @Parameter(description = "삭제할 멤버 이름", example = "홍길동") @PathVariable String name) {
        boolean success = memberService.deleteMember(name);
        if (!success) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }
}