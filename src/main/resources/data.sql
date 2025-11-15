-- 1. 학과 (Major) 삽입 (H2 호환)
MERGE INTO Major (code, name) KEY(code) VALUES ('5722', '컴퓨터학과');

---
-- 2. 과목 (Course) 삽입 (H2 호환)
---
MERGE INTO Course (course_code, name, major_id, credit_type, credits) KEY(course_code) VALUES ('COSE211', '이산수학(영강)', (SELECT id FROM Major WHERE code = '5722'), 'MAJOR_ELECTIVE', 3);
MERGE INTO Course (course_code, name, major_id, credit_type, credits) KEY(course_code) VALUES ('COSE212', '프로그래밍언어(영강)', (SELECT id FROM Major WHERE code = '5722'), 'MAJOR_ELECTIVE', 3);
MERGE INTO Course (course_code, name, major_id, credit_type, credits) KEY(course_code) VALUES ('COSE213', '자료구조(영강)', (SELECT id FROM Major WHERE code = '5722'), 'MAJOR_REQUIRED', 3);
MERGE INTO Course (course_code, name, major_id, credit_type, credits) KEY(course_code) VALUES ('COSE214', '알고리즘(영강)', (SELECT id FROM Major WHERE code = '5722'), 'MAJOR_REQUIRED', 3);
MERGE INTO Course (course_code, name, major_id, credit_type, credits) KEY(course_code) VALUES ('COSE215', '계산이론(영강)', (SELECT id FROM Major WHERE code = '5722'), 'MAJOR_ELECTIVE', 3);
MERGE INTO Course (course_code, name, major_id, credit_type, credits) KEY(course_code) VALUES ('COSE221', '논리설계', (SELECT id FROM Major WHERE code = '5722'), 'MAJOR_ELECTIVE', 3);
MERGE INTO Course (course_code, name, major_id, credit_type, credits) KEY(course_code) VALUES ('COSE222', '컴퓨터구조(영강)', (SELECT id FROM Major WHERE code = '5722'), 'MAJOR_REQUIRED', 3);
MERGE INTO Course (course_code, name, major_id, credit_type, credits) KEY(course_code) VALUES ('COSE242', '데이터통신', (SELECT id FROM Major WHERE code = '5722'), 'MAJOR_ELECTIVE', 3);
MERGE INTO Course (course_code, name, major_id, credit_type, credits) KEY(course_code) VALUES ('COSE281', '공학수학', (SELECT id FROM Major WHERE code = '5722'), 'MAJOR_ELECTIVE', 3);
MERGE INTO Course (course_code, name, major_id, credit_type, credits) KEY(course_code) VALUES ('COSE284', '전자기학', (SELECT id FROM Major WHERE code = '5722'), 'MAJOR_ELECTIVE', 3);
MERGE INTO Course (course_code, name, major_id, credit_type, credits) KEY(course_code) VALUES ('COSE322', '시스템프로그래밍', (SELECT id FROM Major WHERE code = '5722'), 'MAJOR_ELECTIVE', 3);
MERGE INTO Course (course_code, name, major_id, credit_type, credits) KEY(course_code) VALUES ('COSE341', '운영체제(영강)', (SELECT id FROM Major WHERE code = '5722'), 'MAJOR_REQUIRED', 3);
MERGE INTO Course (course_code, name, major_id, credit_type, credits) KEY(course_code) VALUES ('COSE342', '컴퓨터네트워크', (SELECT id FROM Major WHERE code = '5722'), 'MAJOR_REQUIRED', 3);
MERGE INTO Course (course_code, name, major_id, credit_type, credits) KEY(course_code) VALUES ('COSE352', '소프트웨어공학(영강)', (SELECT id FROM Major WHERE code = '5722'), 'MAJOR_ELECTIVE', 3);
MERGE INTO Course (course_code, name, major_id, credit_type, credits) KEY(course_code) VALUES ('COSE354', '정보보호(영강)', (SELECT id FROM Major WHERE code = '5722'), 'MAJOR_ELECTIVE', 3);
MERGE INTO Course (course_code, name, major_id, credit_type, credits) KEY(course_code) VALUES ('COSE361', '인공지능(영강)', (SELECT id FROM Major WHERE code = '5722'), 'MAJOR_REQUIRED', 3);
MERGE INTO Course (course_code, name, major_id, credit_type, credits) KEY(course_code) VALUES ('COSE362', '기계학습', (SELECT id FROM Major WHERE code = '5722'), 'MAJOR_ELECTIVE', 3);
MERGE INTO Course (course_code, name, major_id, credit_type, credits) KEY(course_code) VALUES ('COSE371', '데이터베이스(영강)', (SELECT id FROM Major WHERE code = '5722'), 'MAJOR_ELECTIVE', 3);
MERGE INTO Course (course_code, name, major_id, credit_type, credits) KEY(course_code) VALUES ('COSE372', '데이터베이스시스템', (SELECT id FROM Major WHERE code = '5722'), 'MAJOR_ELECTIVE', 3);
MERGE INTO Course (course_code, name, major_id, credit_type, credits) KEY(course_code) VALUES ('COSE380', '디지털신호처리', (SELECT id FROM Major WHERE code = '5722'), 'MAJOR_ELECTIVE', 3);
MERGE INTO Course (course_code, name, major_id, credit_type, credits) KEY(course_code) VALUES ('COSE382', '확률및랜덤과정', (SELECT id FROM Major WHERE code = '5722'), 'MAJOR_ELECTIVE', 3);
MERGE INTO Course (course_code, name, major_id, credit_type, credits) KEY(course_code) VALUES ('COSE389', '기업가정신과리더쉽', (SELECT id FROM Major WHERE code = '5722'), 'MAJOR_ELECTIVE', 3);
MERGE INTO Course (course_code, name, major_id, credit_type, credits) KEY(course_code) VALUES ('COSE401', '현장실습및창업실습I', (SELECT id FROM Major WHERE code = '5722'), 'MAJOR_ELECTIVE', 3);
MERGE INTO Course (course_code, name, major_id, credit_type, credits) KEY(course_code) VALUES ('COSE402', '현장실습및창업실습II', (SELECT id FROM Major WHERE code = '5722'), 'MAJOR_ELECTIVE', 3);
MERGE INTO Course (course_code, name, major_id, credit_type, credits) KEY(course_code) VALUES ('COSE403', '현장실습및창업실습III', (SELECT id FROM Major WHERE code = '5722'), 'MAJOR_ELECTIVE', 3);
MERGE INTO Course (course_code, name, major_id, credit_type, credits) KEY(course_code) VALUES ('COSE404', '현장실습및창업실습IV', (SELECT id FROM Major WHERE code = '5722'), 'MAJOR_ELECTIVE', 3);
MERGE INTO Course (course_code, name, major_id, credit_type, credits) KEY(course_code) VALUES ('COSE405', '컴퓨터학콜로키움(영강)', (SELECT id FROM Major WHERE code = '5722'), 'MAJOR_ELECTIVE', 3);
MERGE INTO Course (course_code, name, major_id, credit_type, credits) KEY(course_code) VALUES ('COSE407', '개별연구프로젝트(영강)', (SELECT id FROM Major WHERE code = '5722'), 'MAJOR_ELECTIVE', 3);
MERGE INTO Course (course_code, name, major_id, credit_type, credits) KEY(course_code) VALUES ('COSE432', '인간컴퓨터상호작용입문(영강)', (SELECT id FROM Major WHERE code = '5722'), 'MAJOR_ELECTIVE', 3);
MERGE INTO Course (course_code, name, major_id, credit_type, credits) KEY(course_code) VALUES ('COSE436', '인터렉티브시각화(영강)', (SELECT id FROM Major WHERE code = '5722'), 'MAJOR_ELECTIVE', 3);
MERGE INTO Course (course_code, name, major_id, credit_type, credits) KEY(course_code) VALUES ('COSE444', '클라우드컴퓨팅', (SELECT id FROM Major WHERE code = '5722'), 'MAJOR_ELECTIVE', 3);
MERGE INTO Course (course_code, name, major_id, credit_type, credits) KEY(course_code) VALUES ('COSE455', '스타트업프로젝트관리', (SELECT id FROM Major WHERE code = '5722'), 'MAJOR_ELECTIVE', 3);
MERGE INTO Course (course_code, name, major_id, credit_type, credits) KEY(course_code) VALUES ('COSE457', '실전SW프로젝트', (SELECT id FROM Major WHERE code = '5722'), 'MAJOR_ELECTIVE', 3);
MERGE INTO Course (course_code, name, major_id, credit_type, credits) KEY(course_code) VALUES ('COSE474', '딥러닝', (SELECT id FROM Major WHERE code = '5722'), 'MAJOR_ELECTIVE', 3);
MERGE INTO Course (course_code, name, major_id, credit_type, credits) KEY(course_code) VALUES ('COSE475', '고급딥러닝(영강)', (SELECT id FROM Major WHERE code = '5722'), 'MAJOR_ELECTIVE', 3);
MERGE INTO Course (course_code, name, major_id, credit_type, credits) KEY(course_code) VALUES ('COSE480', '산학캡스톤디자인', (SELECT id FROM Major WHERE code = '5722'), 'MAJOR_ELECTIVE', 3);
MERGE INTO Course (course_code, name, major_id, credit_type, credits) KEY(course_code) VALUES ('COSE484', '무선보안(영강)', (SELECT id FROM Major WHERE code = '5722'), 'MAJOR_ELECTIVE', 3);
MERGE INTO Course (course_code, name, major_id, credit_type, credits) KEY(course_code) VALUES ('COSE490', '전산학특강(영강)', (SELECT id FROM Major WHERE code = '5722'), 'MAJOR_ELECTIVE', 3);


---
-- 3. 분반 (Class) 삽입 (H2 호환)
-- (JSON 함수를 사용해 문자열을 JSON으로 명시적 변환)
---

INSERT INTO Class (course_id, class_code, professor_name, schedule) VALUES
    ((SELECT id FROM Course WHERE course_code = 'COSE211'), '01', '박성빈', JSON '[{"day": "TUE", "time_slot": "6", "location": "정보통신관 205호"}, {"day": "THU", "time_slot": "6", "location": "정보통신관 205호"}]');
INSERT INTO Class (course_id, class_code, professor_name, schedule) VALUES
    ((SELECT id FROM Course WHERE course_code = 'COSE211'), '02', '박성빈', JSON '[{"day": "MON", "time_slot": "5", "location": "정보통신관 205호"}, {"day": "WED", "time_slot": "5", "location": "정보통신관 205호"}]');
INSERT INTO Class (course_id, class_code, professor_name, schedule) VALUES
    ((SELECT id FROM Course WHERE course_code = 'COSE212'), '01', '박지혁', JSON '[{"day": "MON", "time_slot": "4", "location": "정운오IT교양관 B102"}, {"day": "WED", "time_slot": "4", "location": "정운오IT교양관 B102"}]');
INSERT INTO Class (course_id, class_code, professor_name, schedule) VALUES
    ((SELECT id FROM Course WHERE course_code = 'COSE212'), '02', '오학주', JSON '[{"day": "MON", "time_slot": "2-3", "location": "정운오IT교양관 B102"}, {"day": "WED", "time_slot": "2-3", "location": "정운오IT교양관 B102"}]');
INSERT INTO Class (course_id, class_code, professor_name, schedule) VALUES
    ((SELECT id FROM Course WHERE course_code = 'COSE213'), '01', '김세연', JSON '[{"day": "TUE", "time_slot": "5", "location": "정보통신관 205호"}, {"day": "THU", "time_slot": "5", "location": "정보통신관 205호"}]');
INSERT INTO Class (course_id, class_code, professor_name, schedule) VALUES
    ((SELECT id FROM Course WHERE course_code = 'COSE213'), '02', '박채용', JSON '[{"day": "TUE", "time_slot": "4", "location": "정운오IT교양관 B103호"}, {"day": "THU", "time_slot": "4", "location": "정운오IT교양관 B103호"}]');
INSERT INTO Class (course_id, class_code, professor_name, schedule) VALUES
    ((SELECT id FROM Course WHERE course_code = 'COSE213'), '03', '장부루', JSON '[{"day": "TUE", "time_slot": "3", "location": "정보통신관 202호"}, {"day": "THU", "time_slot": "3", "location": "정보통신관 202호"}]');
INSERT INTO Class (course_id, class_code, professor_name, schedule) VALUES
    ((SELECT id FROM Course WHERE course_code = 'COSE213'), '04', '이도길', JSON '[{"day": "MON", "time_slot": "2", "location": "정운오IT교양관 611호"}, {"day": "WED", "time_slot": "2", "location": "정운오IT교양관 611호"}]');
INSERT INTO Class (course_id, class_code, professor_name, schedule) VALUES
    ((SELECT id FROM Course WHERE course_code = 'COSE214'), '01', '박성빈', JSON '[{"day": "MON", "time_slot": "6", "location": "정보통신관 205호"}, {"day": "WED", "time_slot": "6", "location": "정보통신관 205호"}]');
INSERT INTO Class (course_id, class_code, professor_name, schedule) VALUES
    ((SELECT id FROM Course WHERE course_code = 'COSE214'), '02', '박성빈', JSON '[{"day": "MON", "time_slot": "4", "location": "정보통신관 205호"}, {"day": "WED", "time_slot": "4", "location": "정보통신관 205호"}]');
INSERT INTO Class (course_id, class_code, professor_name, schedule) VALUES
    ((SELECT id FROM Course WHERE course_code = 'COSE214'), '03', '안가빈', JSON '[{"day": "TUE", "time_slot": "4", "location": "정운오IT교양관 610호"}, {"day": "THU", "time_slot": "4", "location": "정운오IT교양관 610호"}]');
INSERT INTO Class (course_id, class_code, professor_name, schedule) VALUES
    ((SELECT id FROM Course WHERE course_code = 'COSE214'), '04', '유용재', JSON '[{"day": "THU", "time_slot": "1-2", "location": "정보통신관 604호"}]');
INSERT INTO Class (course_id, class_code, professor_name, schedule) VALUES
    ((SELECT id FROM Course WHERE course_code = 'COSE214'), '05', '김승태', JSON '[{"day": "THU", "time_slot": "5-6", "location": "애기능생활관 302호"}]');
INSERT INTO Class (course_id, class_code, professor_name, schedule) VALUES
    ((SELECT id FROM Course WHERE course_code = 'COSE215'), '00', '우승훈', JSON '[{"day": "TUE", "time_slot": "4", "location": "애기능생활관 301호"}, {"day": "THU", "time_slot": "4", "location": "애기능생활관 301호"}]');
INSERT INTO Class (course_id, class_code, professor_name, schedule) VALUES
    ((SELECT id FROM Course WHERE course_code = 'COSE221'), '00', '이숙윤', JSON '[{"day": "MON", "time_slot": "1", "location": "애기능생활관 302호"}, {"day": "WED", "time_slot": "1", "location": "애기능생활관 302호"}]');
INSERT INTO Class (course_id, class_code, professor_name, schedule) VALUES
    ((SELECT id FROM Course WHERE course_code = 'COSE222'), '01', '구건재', JSON '[{"day": "MON", "time_slot": "2", "location": "정운오IT교양관 B103호"}, {"day": "WED", "time_slot": "2", "location": "정운오IT교양관 B103호"}]');
INSERT INTO Class (course_id, class_code, professor_name, schedule) VALUES
    ((SELECT id FROM Course WHERE course_code = 'COSE222'), '02', '김영근', JSON '[{"day": "MON", "time_slot": "4", "location": "정운오IT교양관 609호"}, {"day": "WED", "time_slot": "4", "location": "정운오IT교양관 609호"}]');
INSERT INTO Class (course_id, class_code, professor_name, schedule) VALUES
    ((SELECT id FROM Course WHERE course_code = 'COSE222'), '03', '정성우', JSON '[{"day": "FRI", "time_slot": "3-4", "location": "정운오IT교양관 609호"}]');
INSERT INTO Class (course_id, class_code, professor_name, schedule) VALUES
    ((SELECT id FROM Course WHERE course_code = 'COSE222'), '04', '서태원', JSON '[{"day": "MON", "time_slot": "4", "location": "애기능생활관 302호"}, {"day": "WED", "time_slot": "4", "location": "애기능생활관 302호"}]');
INSERT INTO Class (course_id, class_code, professor_name, schedule) VALUES
    ((SELECT id FROM Course WHERE course_code = 'COSE222'), '05', '이중희', JSON '[{"day": "TUE", "time_slot": "5", "location": "정운오IT교양관 610호"}, {"day": "THU", "time_slot": "5", "location": "정운오IT교양관 610호"}]');
INSERT INTO Class (course_id, class_code, professor_name, schedule) VALUES
    ((SELECT id FROM Course WHERE course_code = 'COSE222'), '06', '이중희', JSON '[{"day": "TUE", "time_slot": "6", "location": "정운오IT교양관 610호"}, {"day": "THU", "time_slot": "6", "location": "정운오IT교양관 610호"}]');
INSERT INTO Class (course_id, class_code, professor_name, schedule) VALUES
    ((SELECT id FROM Course WHERE course_code = 'COSE242'), '01', '김효곤', JSON '[{"day": "TUE", "time_slot": "2", "location": "정운오IT교양관 611호"}, {"day": "THU", "time_slot": "2", "location": "정운오IT교양관 611호"}]');
INSERT INTO Class (course_id, class_code, professor_name, schedule) VALUES
    ((SELECT id FROM Course WHERE course_code = 'COSE242'), '02', '민성기', JSON '[{"day": "TUE", "time_slot": "4", "location": "정보통신관 604호"}, {"day": "THU", "time_slot": "4", "location": "정보통신관 604호"}]');
INSERT INTO Class (course_id, class_code, professor_name, schedule) VALUES
    ((SELECT id FROM Course WHERE course_code = 'COSE281'), '00', '박중석', JSON '[{"day": "FRI", "time_slot": "4-5", "location": "정보통신관 205호"}]');
INSERT INTO Class (course_id, class_code, professor_name, schedule) VALUES
    ((SELECT id FROM Course WHERE course_code = 'COSE284'), '00', '이주섭', JSON '[{"day": "MON", "time_slot": "5", "location": "애기능생활관 302호"}, {"day": "FRI", "time_slot": "5", "location": "애기능생활관 302호"}]');
INSERT INTO Class (course_id, class_code, professor_name, schedule) VALUES
    ((SELECT id FROM Course WHERE course_code = 'COSE322'), '00', '유혁', JSON '[{"day": "MON", "time_slot": "5", "location": "정운오IT교양관 B102"}, {"day": "WED", "time_slot": "5", "location": "정운오IT교양관 B102"}]');
INSERT INTO Class (course_id, class_code, professor_name, schedule) VALUES
    ((SELECT id FROM Course WHERE course_code = 'COSE341'), '01', '양경식', JSON '[{"day": "MON", "time_slot": "5", "location": "정보통신관 202호"}, {"day": "WED", "time_slot": "5", "location": "정보통신관 202호"}]');
INSERT INTO Class (course_id, class_code, professor_name, schedule) VALUES
    ((SELECT id FROM Course WHERE course_code = 'COSE341'), '02', '오상은', JSON '[{"day": "MON", "time_slot": "3", "location": "정보통신관 205호"}, {"day": "WED", "time_slot": "3", "location": "정보통신관 205호"}]');
INSERT INTO Class (course_id, class_code, professor_name, schedule) VALUES
    ((SELECT id FROM Course WHERE course_code = 'COSE342'), '01', '주창희', JSON '[{"day": "MON", "time_slot": "5", "location": "정운오IT교양관 611호"}, {"day": "TUE", "time_slot": "5", "location": "정운오IT교양관 611호"}, {"day": "WED", "time_slot": "5", "location": "정운오IT교양관 611호"}, {"day": "THU", "time_slot": "5", "location": "정운오IT교양관 611호"}]');
INSERT INTO Class (course_id, class_code, professor_name, schedule) VALUES
    ((SELECT id FROM Course WHERE course_code = 'COSE342'), '02', '곽정호', JSON '[{"day": "TUE", "time_slot": "5", "location": "정운오IT교양관 609호"}, {"day": "THU", "time_slot": "5", "location": "정운오IT교양관 609호"}]');
INSERT INTO Class (course_id, class_code, professor_name, schedule) VALUES
    ((SELECT id FROM Course WHERE course_code = 'COSE342'), '03', '민성기', JSON '[{"day": "MON", "time_slot": "4", "location": "정보통신관 604호"}, {"day": "WED", "time_slot": "4", "location": "정보통신관 604호"}]');
INSERT INTO Class (course_id, class_code, professor_name, schedule) VALUES
    ((SELECT id FROM Course WHERE course_code = 'COSE352'), '01', '김동선', JSON '[{"day": "TUE", "time_slot": "3-4", "location": "정운오IT교양관 609호"}, {"day": "THU", "time_slot": "3-4", "location": "정운오IT교양관 609호"}]');
INSERT INTO Class (course_id, class_code, professor_name, schedule) VALUES
    ((SELECT id FROM Course WHERE course_code = 'COSE352'), '02', '인호', JSON '[{"day": "MON", "time_slot": "2", "location": "정보통신관 202호"}, {"day": "WED", "time_slot": "2", "location": "정보통신관 202호"}]');
INSERT INTO Class (course_id, class_code, professor_name, schedule) VALUES
    ((SELECT id FROM Course WHERE course_code = 'COSE354'), '01', '이희조', JSON '[{"day": "TUE", "time_slot": "5", "location": "정운오IT교양관 B103호"}, {"day": "THU", "time_slot": "5", "location": "정운오IT교양관 B103호"}]');
INSERT INTO Class (course_id, class_code, professor_name, schedule) VALUES
    ((SELECT id FROM Course WHERE course_code = 'COSE354'), '02', '허준범', JSON '[{"day": "MON", "time_slot": "1-2", "location": "애기능생활관 301호"}, {"day": "WED", "time_slot": "1-2", "location": "애기능생활관 301호"}]');
INSERT INTO Class (course_id, class_code, professor_name, schedule) VALUES
    ((SELECT id FROM Course WHERE course_code = 'COSE361'), '00', '윤수식', JSON '[{"day": "FRI", "time_slot": "2-3", "location": "정보통신관 205호"}]');
INSERT INTO Class (course_id, class_code, professor_name, schedule) VALUES
    ((SELECT id FROM Course WHERE course_code = 'COSE361'), '02', '김동현', JSON '[{"day": "TUE", "time_slot": "3", "location": "정운오IT교양관 B102"}, {"day": "THU", "time_slot": "3", "location": "정운오IT교양관 B102"}]');
INSERT INTO Class (course_id, class_code, professor_name, schedule) VALUES
    ((SELECT id FROM Course WHERE course_code = 'COSE362'), '01', '김현철', JSON '[{"day": "TUE", "time_slot": "5", "location": "애기능생활관 301호"}, {"day": "THU", "time_slot": "5", "location": "애기능생활관 301호"}]');
INSERT INTO Class (course_id, class_code, professor_name, schedule) VALUES
    ((SELECT id FROM Course WHERE course_code = 'COSE362'), '02', '육동석', JSON '[{"day": "MON", "time_slot": "1", "location": "정보통신관 205호"}, {"day": "WED", "time_slot": "1", "location": "정보통신관 205호"}]');
INSERT INTO Class (course_id, class_code, professor_name, schedule) VALUES
    ((SELECT id FROM Course WHERE course_code = 'COSE362'), '03', '강재우', JSON '[{"day": "TUE", "time_slot": "4", "location": "정운오IT교양관 B102"}, {"day": "THU", "time_slot": "4", "location": "정운오IT교양관 B102"}]');
INSERT INTO Class (course_id, class_code, professor_name, schedule) VALUES
    ((SELECT id FROM Course WHERE course_code = 'COSE371'), '00', '박종혁', JSON '[{"day": "TUE", "time_slot": "4", "location": "정보통신관 205호"}, {"day": "THU", "time_slot": "4", "location": "정보통신관 205호"}]');
INSERT INTO Class (course_id, class_code, professor_name, schedule) VALUES
    ((SELECT id FROM Course WHERE course_code = 'COSE372'), '00', '정연돈', JSON '[{"day": "TUE", "time_slot": "2", "location": "정운오IT교양관 609호"}, {"day": "THU", "time_slot": "2", "location": "정운오IT교양관 609호"}]');
INSERT INTO Class (course_id, class_code, professor_name, schedule) VALUES
    ((SELECT id FROM Course WHERE course_code = 'COSE380'), '00', '이재훈', JSON '[{"day": "MON", "time_slot": "3", "location": "애기능생활관 301호"}, {"day": "WED", "time_slot": "3", "location": "애기능생활관 301호"}]');
INSERT INTO Class (course_id, class_code, professor_name, schedule) VALUES
    ((SELECT id FROM Course WHERE course_code = 'COSE382'), '01', '정원주', JSON '[{"day": "MON", "time_slot": "2", "location": "정보통신관 205호"}, {"day": "WED", "time_slot": "2", "location": "정보통신관 205호"}]');
INSERT INTO Class (course_id, class_code, professor_name, schedule) VALUES
    ((SELECT id FROM Course WHERE course_code = 'COSE382'), '02', '백승준', JSON '[{"day": "TUE", "time_slot": "5", "location": "정운오IT교양관 B102"}, {"day": "THU", "time_slot": "5", "location": "정운오IT교양관 B102"}]');
INSERT INTO Class (course_id, class_code, professor_name, schedule) VALUES
    ((SELECT id FROM Course WHERE course_code = 'COSE389'), '00', '이문영', JSON '[{"day": "WED", "time_slot": "5", "location": "애기능생활관 301호"}, {"day": "FRI", "time_slot": "5", "location": "애기능생활관 301호"}]');
INSERT INTO Class (course_id, class_code, professor_name, schedule) VALUES
    ((SELECT id FROM Course WHERE course_code = 'COSE401'), '00', '이숙윤', JSON '[]');
INSERT INTO Class (course_id, class_code, professor_name, schedule) VALUES
    ((SELECT id FROM Course WHERE course_code = 'COSE402'), '00', '이숙윤', JSON '[]');
INSERT INTO Class (course_id, class_code, professor_name, schedule) VALUES
    ((SELECT id FROM Course WHERE course_code = 'COSE403'), '00', '이숙윤', JSON '[]');
INSERT INTO Class (course_id, class_code, professor_name, schedule) VALUES
    ((SELECT id FROM Course WHERE course_code = 'COSE404'), '00', '이숙윤', JSON '[]');
INSERT INTO Class (course_id, class_code, professor_name, schedule) VALUES
    ((SELECT id FROM Course WHERE course_code = 'COSE405'), '00', '김진규', JSON '[{"day": "WED", "time_slot": "6-9", "location": "애기능생활관 301호"}, {"day": "SAT", "time_slot": "1", "location": "애기능생활관 301호"}]');
INSERT INTO Class (course_id, class_code, professor_name, schedule) VALUES
    ((SELECT id FROM Course WHERE course_code = 'COSE407'), '00', '김진규', JSON '[{"day": "TUE", "time_slot": "6", "location": "애기능생활관 303호"}, {"day": "SAT", "time_slot": "2-4", "location": "애기능생활관 304호"}]');
INSERT INTO Class (course_id, class_code, professor_name, schedule) VALUES
    ((SELECT id FROM Course WHERE course_code = 'COSE432'), '00', '김정현', JSON '[{"day": "MON", "time_slot": "2", "location": "정보통신관 604호"}, {"day": "WED", "time_slot": "2", "location": "정보통신관 604호"}]');
INSERT INTO Class (course_id, class_code, professor_name, schedule) VALUES
    ((SELECT id FROM Course WHERE course_code = 'COSE436'), '00', '정원기', JSON '[{"day": "TUE", "time_slot": "2", "location": "정운오IT교양관 610호"}, {"day": "THU", "time_slot": "2", "location": "정운오IT교양관 610호"}]');
INSERT INTO Class (course_id, class_code, professor_name, schedule) VALUES
    ((SELECT id FROM Course WHERE course_code = 'COSE444'), '00', '유헌창', JSON '[{"day": "MON", "time_slot": "2", "location": "애기능생활관 302호"}, {"day": "WED", "time_slot": "2", "location": "애기능생활관 302호"}]');
INSERT INTO Class (course_id, class_code, professor_name, schedule) VALUES
    ((SELECT id FROM Course WHERE course_code = 'COSE455'), '00', '이문영', JSON '[{"day": "WED", "time_slot": "4", "location": "애기능생활관 301호"}, {"day": "FRI", "time_slot": "4", "location": "애기능생활관 301호"}]');
INSERT INTO Class (course_id, class_code, professor_name, schedule) VALUES
    ((SELECT id FROM Course WHERE course_code = 'COSE457'), '00', '유헌창', JSON '[{"day": "THU", "time_slot": "4-5", "location": "정운오IT교양관 407호"}, {"day": "SAT", "time_slot": "3-4", "location": "애기능생활관 303호"}]');
INSERT INTO Class (course_id, class_code, professor_name, schedule) VALUES
    ((SELECT id FROM Course WHERE course_code = 'COSE474'), '01', '백승준', JSON '[{"day": "TUE", "time_slot": "2", "location": "정운오IT교양관 B102"}, {"day": "THU", "time_slot": "2", "location": "정운오IT교양관 B102"}]');
INSERT INTO Class (course_id, class_code, professor_name, schedule) VALUES
    ((SELECT id FROM Course WHERE course_code = 'COSE474'), '02', '이정범', JSON '[{"day": "TUE", "time_slot": "2", "location": "정운오IT교양관 B103호"}, {"day": "THU", "time_slot": "2", "location": "정운오IT교양관 B103호"}]');
INSERT INTO Class (course_id, class_code, professor_name, schedule) VALUES
    ((SELECT id FROM Course WHERE course_code = 'COSE474'), '03', '이상민', JSON '[{"day": "TUE", "time_slot": "5", "location": "정보통신관 B101호"}, {"day": "THU", "time_slot": "5", "location": "정보통신관 B101호"}]');
INSERT INTO Class (course_id, class_code, professor_name, schedule) VALUES
    ((SELECT id FROM Course WHERE course_code = 'COSE475'), '00', '서홍석', JSON '[{"day": "TUE", "time_slot": "1", "location": "애기능생활관 302호"}, {"day": "THU", "time_slot": "1", "location": "애기능생활관 302호"}]');
INSERT INTO Class (course_id, class_code, professor_name, schedule) VALUES
    ((SELECT id FROM Course WHERE course_code = 'COSE480'), '01', '이숙윤,구건재\r이숙윤', JSON '[{"day": "MON", "time_slot": "5-6", "location": "애기능생활관 303호"}, {"day": "SAT", "time_slot": "5-6", "location": "애기능생활관 303호"}]');
INSERT INTO Class (course_id, class_code, professor_name, schedule) VALUES
    ((SELECT id FROM Course WHERE course_code = 'COSE484'), '00', '전유석', JSON '[{"day": "TUE", "time_slot": "2", "location": "정보통신관 B101호"}, {"day": "THU", "time_slot": "2", "location": "정보통신관 B101호"}]');
INSERT INTO Class (course_id, class_code, professor_name, schedule) VALUES
    ((SELECT id FROM Course WHERE course_code = 'COSE490'), '00', '박성빈', JSON '[{"day": "FRI", "time_slot": "3-4", "location": "정보통신관 202호"}]');