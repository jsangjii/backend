## 1. 프로젝트소개
이 프로젝트는 멋쟁이 사자처럼 백엔드 파트 1학기 실습입니다.
멋쟁이 사자처럼 소속 멤버(아기사자, 운영진)을 관리할 수 있는 웹페이지입니다.

## 2. 기술스택
Java, Spring Boot, JPA, MySQL

## 3. 실행방법
1. MySQL 설치 및 실행
2. 프로젝트 클론

## 4. API 목록
| HTTP 메서드 | URI                | 설명                         |
|-------------|--------------------|------------------------------|
| POST        | /api/lions         | 새로운 사자 추가              |
| GET         | /api/lions         | 모든 사자 조회                |
| GET         | /api/lions/{id}    | 특정 사자 조회                |
| PUT         | /api/lions/{id}    | 특정 사자 정보 수정          |
| DELETE      | /api/lions/{id}    | 특정 사자 삭제              |    


## 5. 프로젝트 구조
```src
├── main
│   ├── java
│   │   └── com.likelion14PBL_Spring
│   │       ├── assignment
│   │       ├── global
│   │       ├── member
│   │       └── pblSpringApplication
│   └── resources
│       ├── static
│       └── application.properties
└── test
``` 