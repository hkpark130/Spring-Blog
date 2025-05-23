INSERT INTO users (id, username, password, role, created_at, updated_at)
VALUES
    (1, 'test', '$2a$10$jAkm342z0fAPlKZBjmSrH.DndspqidWOKaLPMJ2Vc4LoCQ2/g2jhe', 'USER', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO users (id, username, password, role, created_at, updated_at)
VALUES
    (2, 'admin', '$2a$10$jAkm342z0fAPlKZBjmSrH.DndspqidWOKaLPMJ2Vc4LoCQ2/g2jhe', 'ADMIN', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO category (name) VALUES ('ETC');
INSERT INTO category (name) VALUES ('Work Experience');
INSERT INTO category (name) VALUES ('Network');
INSERT INTO category (name) VALUES ('Programming Language');
INSERT INTO category (name) VALUES ('CS 지식');

-- 기존 기본 게시글 (ID: 1-51)
INSERT INTO posts (id, title, content, category_id, author_id, created_at, updated_at)
VALUES
    (1, 'Sample Post 1', 'Content of post 1', 1, 1, '2023-01-01 12:00:00', '2023-01-01 12:00:00'),
    (2, 'Sample Post 2', 'Content of post 2', 2, 2, '2023-01-02 12:00:00', '2023-01-02 12:00:00'),
    (3, 'Sample Post 3', 'Content of post 3', 3, 1, '2023-01-03 12:00:00', '2023-01-03 12:00:00'),
    (4, 'Sample Post 4', 'Content of post 4', 1, 2, '2023-01-04 12:00:00', '2023-01-04 12:00:00'),
    (5, 'Sample Post 5', 'Content of post 5', 2, 1, '2023-01-05 12:00:00', '2023-01-05 12:00:00'),
    (6, 'Sample Post 6', 'Content of post 6', 3, 2, '2023-01-06 12:00:00', '2023-01-06 12:00:00'),
    (7, 'Sample Post 7', 'Content of post 7', 1, 1, '2023-01-07 12:00:00', '2023-01-07 12:00:00'),
    (8, 'Sample Post 8', 'Content of post 8', 2, 2, '2023-01-08 12:00:00', '2023-01-08 12:00:00'),
    (9, 'Sample Post 9', 'Content of post 9', 3, 1, '2023-01-09 12:00:00', '2023-01-09 12:00:00'),
    (10, 'Sample Post 10', 'Content of post 10', 1, 2, '2023-01-10 12:00:00', '2023-01-10 12:00:00'),
    (11, 'Sample Post 11', 'Content of post 11', 2, 1, '2023-01-11 12:00:00', '2023-01-11 12:00:00'),
    (12, 'Sample Post 12', 'Content of post 12', 3, 2, '2023-01-12 12:00:00', '2023-01-12 12:00:00'),
    (13, 'Sample Post 13', 'Content of post 13', 1, 1, '2023-01-13 12:00:00', '2023-01-13 12:00:00'),
    (14, 'Sample Post 14', 'Content of post 14', 2, 2, '2023-01-14 12:00:00', '2023-01-14 12:00:00'),
    (15, 'Sample Post 15', 'Content of post 15', 3, 1, '2023-01-15 12:00:00', '2023-01-15 12:00:00'),
    (16, 'Sample Post 16', 'Content of post 16', 1, 2, '2023-01-16 12:00:00', '2023-01-16 12:00:00'),
    (17, 'Sample Post 17', 'Content of post 17', 2, 1, '2023-01-17 12:00:00', '2023-01-17 12:00:00'),
    (18, 'Sample Post 18', 'Content of post 18', 3, 2, '2023-01-18 12:00:00', '2023-01-18 12:00:00'),
    (19, 'Sample Post 19', 'Content of post 19', 1, 1, '2023-01-19 12:00:00', '2023-01-19 12:00:00'),
    (20, 'Sample Post 20', 'Content of post 20', 2, 2, '2023-01-20 12:00:00', '2023-01-20 12:00:00'),
    (21, 'Sample Post 21', 'Content of post 21', 3, 1, '2023-01-21 12:00:00', '2023-01-21 12:00:00'),
    (22, 'Sample Post 22', 'Content of post 22', 1, 2, '2023-01-22 12:00:00', '2023-01-22 12:00:00'),
    (23, 'Sample Post 23', 'Content of post 23', 2, 1, '2023-01-23 12:00:00', '2023-01-23 12:00:00'),
    (24, 'Sample Post 24', 'Content of post 24', 3, 2, '2023-01-24 12:00:00', '2023-01-24 12:00:00'),
    (25, 'Sample Post 25', 'Content of post 25', 1, 1, '2023-01-25 12:00:00', '2023-01-25 12:00:00'),
    (26, 'Sample Post 26', 'Content of post 26', 2, 2, '2023-01-26 12:00:00', '2023-01-26 12:00:00'),
    (27, 'Sample Post 27', 'Content of post 27', 3, 1, '2023-01-27 12:00:00', '2023-01-27 12:00:00'),
    (28, 'Sample Post 28', 'Content of post 28', 1, 2, '2023-01-28 12:00:00', '2023-01-28 12:00:00'),
    (29, 'Sample Post 29', 'Content of post 29', 2, 1, '2023-01-29 12:00:00', '2023-01-29 12:00:00'),
    (30, 'Sample Post 30', 'Content of post 30', 3, 2, '2023-01-30 12:00:00', '2023-01-30 12:00:00'),
    (31, 'Sample Post 31', 'Content of post 31', 1, 1, '2023-01-31 12:00:00', '2023-01-31 12:00:00'),
    (32, 'Sample Post 32', 'Content of post 32', 2, 2, '2023-02-01 12:00:00', '2023-02-01 12:00:00'),
    (33, 'Sample Post 33', 'Content of post 33', 3, 1, '2023-02-02 12:00:00', '2023-02-02 12:00:00'),
    (34, 'Sample Post 34', 'Content of post 34', 1, 2, '2023-02-03 12:00:00', '2023-02-03 12:00:00'),
    (35, 'Sample Post 35', 'Content of post 35', 2, 1, '2023-02-04 12:00:00', '2023-02-04 12:00:00'),
    (36, 'Sample Post 36', 'Content of post 36', 3, 2, '2023-02-05 12:00:00', '2023-02-05 12:00:00'),
    (37, 'Sample Post 37', 'Content of post 37', 1, 1, '2023-02-06 12:00:00', '2023-02-06 12:00:00'),
    (38, 'Sample Post 38', 'Content of post 38', 2, 2, '2023-02-07 12:00:00', '2023-02-07 12:00:00'),
    (39, 'Sample Post 39', 'Content of post 39', 3, 1, '2023-02-08 12:00:00', '2023-02-08 12:00:00'),
    (40, 'Sample Post 40', 'Content of post 40', 1, 2, '2023-02-09 12:00:00', '2023-02-09 12:00:00'),
    (41, 'Sample Post 41', 'Content of post 41', 2, 1, '2023-02-10 12:00:00', '2023-02-10 12:00:00'),
    (42, 'Sample Post 42', 'Content of post 42', 3, 2, '2023-02-11 12:00:00', '2023-02-11 12:00:00'),
    (43, 'Sample Post 43', 'Content of post 43', 1, 1, '2023-02-12 12:00:00', '2023-02-12 12:00:00'),
    (44, 'Sample Post 44', 'Content of post 44', 2, 2, '2023-02-13 12:00:00', '2023-02-13 12:00:00'),
    (45, 'Sample Post 45', 'Content of post 45', 3, 1, '2023-02-14 12:00:00', '2023-02-14 12:00:00'),
    (46, 'Sample Post 46', 'Content of post 46', 1, 2, '2023-02-15 12:00:00', '2023-02-15 12:00:00'),
    (47, 'Sample Post 47', 'Content of post 47', 2, 1, '2023-02-16 12:00:00', '2023-02-16 12:00:00'),
    (48, 'Sample Post 48', 'Content of post 48', 3, 2, '2023-02-17 12:00:00', '2023-02-17 12:00:00'),
    (49, 'Sample Post 49', 'Content of post 49', 1, 1, '2023-02-18 12:00:00', '2023-02-18 12:00:00'),
    (50, 'Sample Post 50', 'Content of post 50', 2, 2, '2023-02-19 12:00:00', '2023-02-19 12:00:00'),
    (51, 'Sam test 48', 'C18', 3, 2, '2023-02-20 12:00:00', '2023-02-20 12:00:00');

-- 자바 관련 다양한 게시글 (ID: 52-61)
INSERT INTO posts (id, title, content, category_id, author_id, created_at, updated_at) VALUES
(52, 'Java 프로그래밍 기초', 'Java는 객체지향 프로그래밍 언어로 많은 개발자들이 사용하는 언어입니다. 이 글에서는 Java의 기본 문법에 대해 알아봅니다.', 1, 1, '2023-02-21 12:00:00', '2023-02-21 12:00:00'),
(53, 'Java의 컬렉션 프레임워크', 'ArrayList, HashMap, HashSet 등 Java의 컬렉션 프레임워크는 데이터를 효율적으로 처리하는 방법을 제공합니다.', 1, 2, '2023-02-22 12:00:00', '2023-02-22 12:00:00'),
(54, '자바 스트림 API 사용법', '자바 8에서 도입된 스트림 API를 사용하면 데이터 처리를 선언적으로 할 수 있습니다. 이 포스트에서는 스트림 API의 다양한 활용법을 소개합니다.', 1, 1, '2023-02-23 12:00:00', '2023-02-23 12:00:00'),
(55, 'Java와 함수형 프로그래밍', '자바 8부터 도입된 람다식과 함수형 인터페이스를 통해 함수형 프로그래밍을 자바에서도 구현할 수 있습니다.', 1, 2, '2023-02-24 12:00:00', '2023-02-24 12:00:00'),
(56, '자바 JVM의 동작 원리', 'JVM(Java Virtual Machine)은 자바 프로그램을 실행하는 가상 머신입니다. JVM의 구조와 동작 원리를 알아봅니다.', 1, 1, '2023-02-25 12:00:00', '2023-02-25 12:00:00'),
(57, 'Java NIO와 블로킹/논블로킹 IO', '자바 NIO는 채널, 버퍼, 셀렉터를 통한 효율적인 IO 처리를 지원합니다. 블로킹과 논블로킹의 차이점과 사용법을 알아봅니다.', 1, 2, '2023-02-26 12:00:00', '2023-02-26 12:00:00'),
(58, '자바 멀티스레딩 프로그래밍', '자바에서 멀티스레드 프로그래밍을 구현하는 방법과 동기화, 락, 세마포어 등의 개념을 설명합니다.', 1, 1, '2023-02-27 12:00:00', '2023-02-27 12:00:00'),
(59, 'Java의 디자인 패턴', '싱글톤, 팩토리, 옵저버 등 자바에서 많이 사용되는 디자인 패턴들과 그 구현 방법을 소개합니다.', 1, 2, '2023-02-28 12:00:00', '2023-02-28 12:00:00'),
(60, '자바 애노테이션 프로세싱', '자바 애노테이션 프로세싱을 통한 코드 생성 및 메타데이터 처리 방법에 대해 알아봅니다.', 1, 1, '2023-03-01 12:00:00', '2023-03-01 12:00:00'),
(61, '자바 리플렉션 API 활용법', '자바 리플렉션을 사용하면 런타임에 클래스의 정보를 조회하고 조작할 수 있습니다. 이를 활용한 다양한 예제를 소개합니다.', 1, 2, '2023-03-02 12:00:00', '2023-03-02 12:00:00'),

-- 스프링 관련 다양한 게시글 (ID: 62-71)
(62, 'Spring Boot 시작하기', 'Spring Boot를 사용하면 스프링 기반 애플리케이션을 빠르게 개발할 수 있습니다. Spring Boot의 기본 설정과 시작 방법을 알아봅니다.', 2, 1, '2023-03-03 12:00:00', '2023-03-03 12:00:00'),
(63, 'Spring의 의존성 주입(DI)', 'Spring의 핵심 기능인 의존성 주입(DI)의 개념과 다양한 구현 방법에 대해 알아봅니다.', 2, 2, '2023-03-04 12:00:00', '2023-03-04 12:00:00'),
(64, 'Spring MVC 아키텍처', 'Spring MVC는 웹 애플리케이션 개발을 위한 아키텍처를 제공합니다. 컨트롤러, 모델, 뷰의 역할과 동작 원리를 설명합니다.', 2, 1, '2023-03-05 12:00:00', '2023-03-05 12:00:00'),
(65, 'Spring Data JPA 사용법', 'Spring Data JPA를 사용하면 데이터베이스 액세스 코드를 크게 줄일 수 있습니다. 리포지토리 인터페이스 설계와 사용법을 알아봅니다.', 2, 2, '2023-03-06 12:00:00', '2023-03-06 12:00:00'),
(66, 'Spring Security로 인증 구현하기', 'Spring Security를 사용하여 웹 애플리케이션의 인증과 권한 부여를 구현하는 방법을 설명합니다.', 2, 1, '2023-03-07 12:00:00', '2023-03-07 12:00:00'),
(67, 'Spring Boot와 RESTful API', 'Spring Boot를 사용하여 RESTful API를 개발하는 방법과 최적화 전략에 대해 알아봅니다.', 2, 2, '2023-03-08 12:00:00', '2023-03-08 12:00:00'),
(68, '스프링 AOP 프로그래밍', 'AOP(관점 지향 프로그래밍)를 사용하여 횡단 관심사를 분리하고 모듈화하는 방법을 설명합니다.', 2, 1, '2023-03-09 12:00:00', '2023-03-09 12:00:00'),
(69, 'Spring 트랜잭션 관리', 'Spring에서 제공하는 트랜잭션 관리 기능을 사용하여 데이터 일관성을 유지하는 방법을 알아봅니다.', 2, 2, '2023-03-10 12:00:00', '2023-03-10 12:00:00'),
(70, '스프링 배치 프로세싱', 'Spring Batch를 사용하여 대용량 데이터 처리 작업을 구현하는 방법과 최적화 전략을 소개합니다.', 2, 1, '2023-03-11 12:00:00', '2023-03-11 12:00:00'),
(71, '스프링 클라우드로 마이크로서비스 구축', 'Spring Cloud를 활용한 마이크로서비스 아키텍처 설계와 구현 방법에 대해 설명합니다.', 2, 2, '2023-03-12 12:00:00', '2023-03-12 12:00:00'),

-- 리액트 관련 다양한 게시글 (ID: 72-81)
(72, 'React 입문 가이드', 'React는 사용자 인터페이스를 구축하기 위한 자바스크립트 라이브러리입니다. React의 기본 개념과 시작 방법을 알아봅니다.', 3, 1, '2023-03-13 12:00:00', '2023-03-13 12:00:00'),
(73, 'React 컴포넌트와 Props', 'React 컴포넌트의 개념과 props를 통한 데이터 전달 방법에 대해 알아봅니다.', 3, 2, '2023-03-14 12:00:00', '2023-03-14 12:00:00'),
(74, 'React Hooks 완벽 가이드', 'useState, useEffect 등 React Hooks의 다양한 활용법과 커스텀 훅 작성법을 소개합니다.', 3, 1, '2023-03-15 12:00:00', '2023-03-15 12:00:00'),
(75, 'React와 상태 관리', 'Redux, Recoil, MobX 등 React 애플리케이션의 상태 관리 라이브러리 비교와 사용법을 설명합니다.', 3, 2, '2023-03-16 12:00:00', '2023-03-16 12:00:00'),
(76, 'React Router로 SPA 구현하기', 'React Router를 사용하여 단일 페이지 애플리케이션(SPA)의 라우팅을 구현하는 방법을 알아봅니다.', 3, 1, '2023-03-17 12:00:00', '2023-03-17 12:00:00'),
(77, 'React와 타입스크립트', 'React 프로젝트에 타입스크립트를 적용하여 타입 안정성을 확보하는 방법을 설명합니다.', 3, 2, '2023-03-18 12:00:00', '2023-03-18 12:00:00'),
(78, 'React 성능 최적화 기법', 'React.memo, useMemo, useCallback 등을 활용한 React 애플리케이션의 성능 최적화 방법을 소개합니다.', 3, 1, '2023-03-19 12:00:00', '2023-03-19 12:00:00'),
(79, '리액트 테스팅 전략', 'Jest와 React Testing Library를 사용하여 React 컴포넌트를 테스트하는 방법과 전략을 알아봅니다.', 3, 2, '2023-03-20 12:00:00', '2023-03-20 12:00:00'),
(80, 'React와 서버 사이드 렌더링', 'Next.js를 사용한 서버 사이드 렌더링(SSR) 구현과 그 장점에 대해 설명합니다.', 3, 1, '2023-03-21 12:00:00', '2023-03-21 12:00:00'),
(81, '리액트 애니메이션 구현하기', 'React Transition Group, Framer Motion 등을 활용한 애니메이션 구현 방법을 소개합니다.', 3, 2, '2023-03-22 12:00:00', '2023-03-22 12:00:00'),

-- 프로그래밍 일반 주제 (ID: 82-91)
(82, '알고리즘 기초: 정렬 알고리즘', '버블 정렬, 선택 정렬, 삽입 정렬, 퀵 정렬 등 다양한 정렬 알고리즘의 원리와 구현 방법을 설명합니다.', 1, 1, '2023-03-23 12:00:00', '2023-03-23 12:00:00'),
(83, '자료구조 이해하기: 트리와 그래프', '트리와 그래프 자료구조의 개념과 구현 방법, 그리고 실제 활용 사례를 소개합니다.', 1, 2, '2023-03-24 12:00:00', '2023-03-24 12:00:00'),
(84, 'HTTP 프로토콜의 이해', 'HTTP 요청과 응답, 상태 코드, 헤더 등 HTTP 프로토콜의 기본 개념과 동작 원리를 설명합니다.', 2, 1, '2023-03-25 12:00:00', '2023-03-25 12:00:00'),
(85, '데이터베이스 설계 원칙', '정규화, 인덱싱 등 효율적인 데이터베이스 설계를 위한 핵심 원칙들을 소개합니다.', 2, 2, '2023-03-26 12:00:00', '2023-03-26 12:00:00'),
(86, '클라우드 컴퓨팅 입문', 'AWS, Azure, Google Cloud 등 주요 클라우드 서비스의 특징과 활용 방법을 알아봅니다.', 3, 1, '2023-03-27 12:00:00', '2023-03-27 12:00:00'),
(87, '도커와 컨테이너 가상화', '도커를 사용한 컨테이너 가상화의 개념과 활용법을 설명합니다.', 1, 2, '2023-03-28 12:00:00', '2023-03-28 12:00:00'),
(88, 'DevOps와 CI/CD 파이프라인', '지속적 통합(CI)과 지속적 배포(CD)의 개념과 구현 방법에 대해 알아봅니다.', 2, 1, '2023-03-29 12:00:00', '2023-03-29 12:00:00'),
(89, '소프트웨어 아키텍처 패턴', '마이크로서비스, 모놀리식, 계층화 등 다양한 소프트웨어 아키텍처 패턴을 비교합니다.', 3, 2, '2023-03-30 12:00:00', '2023-03-30 12:00:00'),
(90, '웹 보안의 기초', 'XSS, CSRF, SQL 인젝션 등 흔한 웹 보안 취약점과 방어 방법을 설명합니다.', 1, 1, '2023-03-31 12:00:00', '2023-03-31 12:00:00'),
(91, '테스트 주도 개발(TDD)', '테스트 주도 개발의 원칙과 실천 방법에 대해 알아봅니다.', 2, 2, '2023-04-01 12:00:00', '2023-04-01 12:00:00');

-- 프론트엔드 관련 주제 (ID: 92-96)
INSERT INTO posts (id, title, content, category_id, author_id, created_at, updated_at) VALUES
(92, 'CSS Grid 레이아웃 마스터하기', 'CSS Grid는 웹 페이지 레이아웃을 효과적으로 구성할 수 있는 강력한 도구입니다. 그리드 시스템의 기본 개념부터 복잡한 레이아웃 구현까지 알아봅니다.', 3, 1, '2023-04-02 12:00:00', '2023-04-02 12:00:00'),
(93, 'JavaScript ES6+ 핵심 기능', 'ES6부터 도입된 화살표 함수, 템플릿 리터럴, 구조 분해 할당 등 모던 자바스크립트의 핵심 기능들을 설명합니다.', 3, 2, '2023-04-03 12:00:00', '2023-04-03 12:00:00'),
(94, '반응형 웹 디자인의 모든 것', '다양한 디바이스에 최적화된 반응형 웹 디자인을 구현하는 방법과 전략을 소개합니다.', 3, 1, '2023-04-04 12:00:00', '2023-04-04 12:00:00'),
(95, '웹 성능 최적화 기법', '이미지 최적화, 코드 분할, 지연 로딩 등 웹 성능을 향상시키는 다양한 기법을 알아봅니다.', 3, 2, '2023-04-05 12:00:00', '2023-04-05 12:00:00'),
(96, 'PWA(Progressive Web App) 개발', 'PWA의 특징과 개발 방법, 그리고 서비스 워커를 활용한 오프라인 지원 방법을 설명합니다.', 3, 1, '2023-04-06 12:00:00', '2023-04-06 12:00:00'),

-- 백엔드 관련 주제 (ID: 97-100)
(97, 'Node.js 서버 개발 기초', 'Node.js를 사용한 서버 개발의 기초와 Express 프레임워크 활용법을 알아봅니다.', 1, 2, '2023-04-07 12:00:00', '2023-04-07 12:00:00'),
(98, 'REST API 설계 원칙', 'REST 아키텍처의 기본 원칙과 RESTful API 설계 모범 사례를 소개합니다.', 2, 1, '2023-04-08 12:00:00', '2023-04-08 12:00:00'),
(99, '데이터베이스 쿼리 최적화', '인덱스 활용, 쿼리 리팩토링 등 데이터베이스 성능을 향상시키는 방법을 설명합니다.', 1, 2, '2023-04-09 12:00:00', '2023-04-09 12:00:00'),
(100, '인증과 권한 부여 시스템 설계', 'JWT, OAuth 등을 활용한 안전한 인증 및 권한 부여 시스템 설계 방법을 알아봅니다.', 2, 1, '2023-04-10 12:00:00', '2023-04-10 12:00:00'),

-- 데이터 과학 및 AI 주제 (ID: 101-104)
(101, '머신러닝 기초 개념', '지도 학습, 비지도 학습, 강화 학습 등 머신러닝의 기본 개념과 알고리즘을 소개합니다.', 3, 2, '2023-04-11 12:00:00', '2023-04-11 12:00:00'),
(102, '파이썬을 이용한 데이터 분석', 'Pandas, NumPy 등의 파이썬 라이브러리를 사용한 데이터 분석 방법을 설명합니다.', 1, 1, '2023-04-12 12:00:00', '2023-04-12 12:00:00'),
(103, '딥러닝과 신경망의 이해', '인공 신경망의 구조와 동작 원리, 그리고 딥러닝의 기본 개념을 알아봅니다.', 2, 2, '2023-04-13 12:00:00', '2023-04-13 12:00:00'),
(104, '자연어 처리(NLP) 입문', '토큰화, 품사 태깅, 워드 임베딩 등 자연어 처리의 기본 기법을 소개합니다.', 3, 1, '2023-04-14 12:00:00', '2023-04-14 12:00:00'),

-- 모바일 개발 주제 (ID: 105-107)
(105, '안드로이드 앱 개발 기초', '안드로이드 앱 개발의 기본 구조와 액티비티, 프래그먼트 등의 핵심 컴포넌트를 설명합니다.', 1, 2, '2023-04-15 12:00:00', '2023-04-15 12:00:00'),
(106, 'iOS 앱 개발과 Swift', 'Swift 언어를 사용한 iOS 앱 개발의 기초와 UIKit 프레임워크 활용법을 알아봅니다.', 2, 1, '2023-04-16 12:00:00', '2023-04-16 12:00:00'),
(107, 'Flutter로 크로스 플랫폼 앱 개발', '구글의 Flutter를 사용하여 iOS와 안드로이드 앱을 동시에 개발하는 방법을 소개합니다.', 3, 2, '2023-04-17 12:00:00', '2023-04-17 12:00:00'),

-- 검색 테스트를 위한 특수 키워드 포함 게시글 (ID: 108-110)
(108, '특별한 검색어: 파이썬과 자바 비교', '파이썬과 자바는 각각 특징이 있는 프로그래밍 언어입니다. 이 게시글은 검색 테스트를 위한 특수 키워드를 포함합니다.', 1, 1, '2023-04-18 12:00:00', '2023-04-18 12:00:00'),
(109, '특별한 검색어: 스프링과 노드JS', '백엔드 프레임워크로서 스프링과 노드JS의 차이점을 알아봅니다. 이 게시글은 검색 테스트를 위한 특수 키워드를 포함합니다.', 2, 2, '2023-04-19 12:00:00', '2023-04-19 12:00:00'),
(110, '특별한 검색어: 리액트와 뷰', '프론트엔드 라이브러리인 리액트와 뷰의 비교 분석입니다. 이 게시글은 검색 테스트를 위한 특수 키워드를 포함합니다.', 3, 1, '2023-04-20 12:00:00', '2023-04-20 12:00:00'),

-- 더 많은 일반 게시글 추가 (ID: 111-150)
(111, '코딩 테스트 준비 전략', '코딩 테스트 유형별 접근 방법과 효과적인 준비 전략을 소개합니다.', 1, 2, '2023-04-21 12:00:00', '2023-04-21 12:00:00'),
(112, '객체지향 설계의 5가지 원칙 SOLID', 'SOLID 원칙의 각 요소와 그 적용 방법에 대해 자세히 설명합니다.', 2, 1, '2023-04-22 12:00:00', '2023-04-22 12:00:00'),
(113, '함수형 프로그래밍의 이해', '순수 함수, 불변성, 고차 함수 등 함수형 프로그래밍의 핵심 개념을 알아봅니다.', 3, 2, '2023-04-23 12:00:00', '2023-04-23 12:00:00'),
(114, '깃(Git) 워크플로우 전략', 'Git-flow, GitHub-flow 등 다양한 깃 워크플로우 전략과 그 장단점을 비교합니다.', 1, 1, '2023-04-24 12:00:00', '2023-04-24 12:00:00'),
(115, '코드 리팩토링의 기술', '가독성과 유지보수성을 높이는 코드 리팩토링 기법과 원칙을 소개합니다.', 2, 2, '2023-04-25 12:00:00', '2023-04-25 12:00:00'),
(116, '클린 코드 작성법', '로버트 마틴의 클린 코드 원칙에 따른 가독성 높고 유지보수하기 좋은 코드 작성법을 설명합니다.', 3, 1, '2023-04-26 12:00:00', '2023-04-26 12:00:00'),
(117, '디자인 패턴의 실전 활용', '다양한 디자인 패턴의 실제 활용 사례와 구현 방법을 소개합니다.', 1, 2, '2023-04-27 12:00:00', '2023-04-27 12:00:00'),
(118, '웹 접근성과 시맨틱 HTML', '모든 사용자를 위한 접근성 높은 웹 페이지 구현 방법과 시맨틱 HTML의 중요성을 설명합니다.', 2, 1, '2023-04-28 12:00:00', '2023-04-28 12:00:00'),
(119, 'GraphQL API 설계와 구현', 'GraphQL의 기본 개념과 REST API와의 차이점, 그리고 실제 구현 방법을 알아봅니다.', 3, 2, '2023-04-29 12:00:00', '2023-04-29 12:00:00'),
(120, '마이크로서비스 아키텍처의 장단점', '모놀리식 아키텍처와 비교한 마이크로서비스 아키텍처의 장단점과 적용 시나리오를 설명합니다.', 1, 1, '2023-04-30 12:00:00', '2023-04-30 12:00:00'),
(121, '이벤트 기반 아키텍처 설계', '이벤트 소싱과 CQRS 패턴을 활용한 이벤트 기반 아키텍처의 설계와 구현 방법을 소개합니다.', 2, 2, '2023-05-01 12:00:00', '2023-05-01 12:00:00'),
(122, '프론트엔드 상태 관리 전략', '다양한 상태 관리 라이브러리와 패턴의 비교 및 적절한 선택 기준을 설명합니다.', 3, 1, '2023-05-02 12:00:00', '2023-05-02 12:00:00'),
(123, '데이터베이스 트랜잭션과 격리 수준', 'ACID 속성과 다양한 트랜잭션 격리 수준의 의미와 영향을 알아봅니다.', 1, 2, '2023-05-03 12:00:00', '2023-05-03 12:00:00'),
(124, 'NoSQL 데이터베이스의 종류와 특징', '문서형, 키-값, 칼럼, 그래프 등 다양한 NoSQL 데이터베이스의 특징과 적합한 사용 사례를 비교합니다.', 2, 1, '2023-05-04 12:00:00', '2023-05-04 12:00:00'),
(125, '웹 애플리케이션 보안 취약점 방어', 'OWASP Top 10 보안 취약점과 그 방어 방법에 대해 자세히 설명합니다.', 3, 2, '2023-05-05 12:00:00', '2023-05-05 12:00:00'),
(126, '확장 가능한 시스템 설계', '수평적/수직적 확장, 캐싱, 로드 밸런싱 등 확장 가능한 시스템 설계의 핵심 전략을 소개합니다.', 1, 1, '2023-05-06 12:00:00', '2023-05-06 12:00:00'),
(127, '프로젝트 관리 방법론', '애자일, 스크럼, 칸반 등 다양한 프로젝트 관리 방법론의 특징과 적용 방법을 비교합니다.', 2, 2, '2023-05-07 12:00:00', '2023-05-07 12:00:00'),
(128, '코드 품질과 정적 분석 도구', 'ESLint, SonarQube 등의 정적 분석 도구를 활용한 코드 품질 향상 방법을 설명합니다.', 3, 1, '2023-05-08 12:00:00', '2023-05-08 12:00:00'),
(129, '비동기 프로그래밍의 이해', '콜백, 프로미스, async/await 등 자바스크립트의 비동기 프로그래밍 패턴을 비교합니다.', 1, 2, '2023-05-09 12:00:00', '2023-05-09 12:00:00'),
(130, '데이터 시각화 기법과 도구', 'D3.js, Chart.js 등을 활용한 효과적인 데이터 시각화 방법을 소개합니다.', 2, 1, '2023-05-10 12:00:00', '2023-05-10 12:00:00'),
(131, '블록체인 기술의 이해', '블록체인의 기본 원리와 응용 분야, 그리고 스마트 컨트랙트의 개념을 설명합니다.', 3, 2, '2023-05-11 12:00:00', '2023-05-11 12:00:00'),
(132, '클라우드 네이티브 애플리케이션 개발', '클라우드 환경에 최적화된 애플리케이션 개발 원칙과 패턴을 알아봅니다.', 1, 1, '2023-05-12 12:00:00', '2023-05-12 12:00:00'),
(133, '서버리스 아키텍처의 장단점', 'AWS Lambda 등의 서버리스 컴퓨팅 서비스를 활용한 애플리케이션 개발의 장단점을 분석합니다.', 2, 2, '2023-05-13 12:00:00', '2023-05-13 12:00:00'),
(134, '실시간 웹 애플리케이션 구현', 'WebSocket, Server-Sent Events 등을 활용한 실시간 웹 애플리케이션 구현 방법을 소개합니다.', 3, 1, '2023-05-14 12:00:00', '2023-05-14 12:00:00'),
(135, '효율적인 로깅과 모니터링', '로그 수집, 분석, 알림 시스템 구축을 통한 효율적인 애플리케이션 모니터링 전략을 설명합니다.', 1, 2, '2023-05-15 12:00:00', '2023-05-15 12:00:00'),
(136, '유닛 테스트 작성 모범 사례', '효과적인 유닛 테스트 작성 방법과 테스트 주도 개발의 실천 방법을 알아봅니다.', 2, 1, '2023-05-16 12:00:00', '2023-05-16 12:00:00'),
(137, '컨테이너 오케스트레이션 - 쿠버네티스', '쿠버네티스의 핵심 개념과 컨테이너 오케스트레이션을 위한 설정 및 관리 방법을 설명합니다.', 3, 2, '2023-05-17 12:00:00', '2023-05-17 12:00:00'),
(138, '인메모리 데이터 그리드', 'Redis, Hazelcast 등의 인메모리 데이터 그리드 기술과 그 활용 사례를 소개합니다.', 1, 1, '2023-05-18 12:00:00', '2023-05-18 12:00:00'),
(139, 'API 게이트웨이 패턴', 'API 게이트웨이의 역할과 구현 방법, 그리고 마이크로서비스 아키텍처에서의 활용법을 알아봅니다.', 2, 2, '2023-05-19 12:00:00', '2023-05-19 12:00:00'),
(140, '브라우저 렌더링 최적화', '브라우저 렌더링 과정의 이해와 웹 성능 최적화를 위한 렌더링 개선 기법을 설명합니다.', 3, 1, '2023-05-20 12:00:00', '2023-05-20 12:00:00'),
(141, '확률론적 데이터 구조', 'Bloom filter, HyperLogLog 등의 확률론적 데이터 구조와 대용량 데이터 처리에서의 활용법을 소개합니다.', 1, 2, '2023-05-21 12:00:00', '2023-05-21 12:00:00'),
(142, '분산 시스템의 일관성 모델', '강한 일관성, 최종 일관성 등 분산 시스템에서의 데이터 일관성 모델과 그 트레이드오프를 설명합니다.', 2, 1, '2023-05-22 12:00:00', '2023-05-22 12:00:00'),
(143, '지리 정보 시스템(GIS) 개발', '지리 정보 데이터의 저장, 처리, 시각화 방법과 관련 라이브러리 활용법을 알아봅니다.', 3, 2, '2023-05-23 12:00:00', '2023-05-23 12:00:00'),
(144, '타입 시스템과 정적 타입 언어', '타입 시스템의 이점과 정적 타입 언어의 특징, 그리고 동적 타입 언어와의 비교를 다룹니다.', 1, 1, '2023-05-24 12:00:00', '2023-05-24 12:00:00'),
(145, '메모리 관리와 가비지 컬렉션', '다양한 프로그래밍 언어의 메모리 관리 방식과 가비지 컬렉션 알고리즘을 비교합니다.', 2, 2, '2023-05-25 12:00:00', '2023-05-25 12:00:00'),
(146, '엣지 컴퓨팅의 미래', '엣지 컴퓨팅의 개념과 클라우드 컴퓨팅과의 관계, 그리고 미래 전망을 분석합니다.', 3, 1, '2023-05-26 12:00:00', '2023-05-26 12:00:00'),
(147, 'WebAssembly의 가능성', 'WebAssembly의 기본 개념과 웹 개발에서의 활용 가능성, 그리고 성능 이점을 설명합니다.', 1, 2, '2023-05-27 12:00:00', '2023-05-27 12:00:00'),
(148, '양자 컴퓨팅과 프로그래밍', '양자 컴퓨팅의 기본 원리와 양자 알고리즘, 그리고 현재의 개발 상황을 소개합니다.', 2, 1, '2023-05-28 12:00:00', '2023-05-28 12:00:00'),
(149, '증강 현실(AR)과 가상 현실(VR) 개발', 'AR/VR 애플리케이션 개발을 위한 기술과 도구, 그리고 사용자 경험 설계 방법을 알아봅니다.', 3, 2, '2023-05-29 12:00:00', '2023-05-29 12:00:00'),
(150, '윤리적인 AI 개발', 'AI 개발에서의 윤리적 고려사항과 편향성 방지, 그리고 책임 있는 AI 개발 원칙을 설명합니다.', 1, 1, '2023-05-30 12:00:00', '2023-05-30 12:00:00'),

-- 특별 검색 테스트를 위한 추가 게시글 (ID: 151-153)
(151, '특별 검색어: 마이크로서비스', '마이크로서비스 아키텍처는 작은 독립적인 서비스로 구성된 애플리케이션 구조입니다. 이 게시글은 특별 검색 테스트용입니다.', 1, 2, '2023-06-01 12:00:00', '2023-06-01 12:00:00'),
(152, '특별 검색어: 리액트와 스프링부트', '리액트 프론트엔드와 스프링부트 백엔드를 연동하는 풀스택 개발 방법을 소개합니다. 이 게시글은 특별 검색 테스트용입니다.', 2, 1, '2023-06-02 12:00:00', '2023-06-02 12:00:00'),
(153, '특별 검색어: 도커와 쿠버네티스', '도커 컨테이너를 쿠버네티스로 오케스트레이션하는 방법을 설명합니다. 이 게시글은 특별 검색 테스트용입니다.', 3, 2, '2023-06-03 12:00:00', '2023-06-03 12:00:00');

-- 댓글 데이터도 날짜 차이를 두고 추가
INSERT INTO comments (content, post_id, user_id, is_guest, guest_name, created_at, updated_at) VALUES
('Java 프로그래밍 언어는 정말 유용합니다!', 52, 2, false, NULL, '2023-02-22 10:00:00', '2023-02-22 10:00:00'),
('객체지향의 개념을 잘 설명해주셔서 감사합니다.', 52, 1, false, NULL, '2023-02-23 11:30:00', '2023-02-23 11:30:00'),
('이 게시글 덕분에 많은 도움이 되었어요!', 52, NULL, true, '방문자1', '2023-02-24 09:15:00', '2023-02-24 09:15:00'),
('스프링 부트로 개발하면 정말 편리하네요.', 62, 1, false, NULL, '2023-03-04 10:00:00', '2023-03-04 10:00:00'),
('의존성 주입에 대해 더 자세히 알 수 있어서 좋았습니다.', 63, 2, false, NULL, '2023-03-05 11:30:00', '2023-03-05 11:30:00'),
('React Hooks가 정말 혁신적인 기능인 것 같아요!', 74, NULL, true, '리액트팬', '2023-03-16 09:15:00', '2023-03-16 09:15:00'),
('이 내용을 실무에서 바로 적용할 수 있을 것 같습니다.', 75, 1, false, NULL, '2023-03-17 10:00:00', '2023-03-17 10:00:00'),
('알고리즘 설명이 명확해서 이해하기 쉬웠어요.', 82, NULL, true, '알고리즘 학습자', '2023-03-24 11:30:00', '2023-03-24 11:30:00'),
('트리와 그래프에 대한 개념을 잘 잡을 수 있었습니다.', 83, 2, false, NULL, '2023-03-25 09:15:00', '2023-03-25 09:15:00'),
('HTTP 프로토콜에 대한 이해가 깊어졌어요!', 84, NULL, true, '웹 개발자', '2023-03-26 10:00:00', '2023-03-26 10:00:00');
