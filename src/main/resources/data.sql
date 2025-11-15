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

-- 1. "신입생" (1학년 1학기, 꿀강/공강 중시)
INSERT INTO users (user_id, username, password, grade, semester, major_code, credits_major_required, credits_major_elective, credits_general, preferred_off_days, preferred_time_slot, priority_order, created_at, last_modified_at)
VALUES
    ('20250001', 'newbie', 'password123', 1, 1, '5722', 0, 0, 3,
     JSON '["MON"]', 'AM', JSON '["EASY_COURSE", "DAY_OFF", "GRADUATION", "COMPETITION"]',
     NOW(), NOW());

-- 2. "졸업준비생" (3학년 2학기, 졸업요건 최우선, 금공강 희망)
INSERT INTO users (user_id, username, password, grade, semester, major_code, credits_major_required, credits_major_elective, credits_general, preferred_off_days, preferred_time_slot, priority_order, created_at, last_modified_at)
VALUES
    ('20230002', 'junior_grad', 'password123', 3, 2, '5722', 24, 30, 15,
     JSON '["FRI"]', 'NONE', JSON '["GRADUATION", "DAY_OFF", "COMPETITION", "EASY_COURSE"]',
     NOW(), NOW());

-- 3. "아침형 인간" (2학년 1학기, 오전 선호)
INSERT INTO users (user_id, username, password, grade, semester, major_code, credits_major_required, credits_major_elective, credits_general, preferred_off_days, preferred_time_slot, priority_order, created_at, last_modified_at)
VALUES
    ('20240003', 'morning_bird', 'password123', 2, 1, '5722', 12, 15, 6,
     JSON '[]', 'AM', JSON '["DAY_OFF", "GRADUATION", "EASY_COURSE", "COMPETITION"]',
     NOW(), NOW());

-- 4. "오후형 인간" (2학년 2학기, 오후 선호, 월공강)
INSERT INTO users (user_id, username, password, grade, semester, major_code, credits_major_required, credits_major_elective, credits_general, preferred_off_days, preferred_time_slot, priority_order, created_at, last_modified_at)
VALUES
    ('20240004', 'night_owl', 'password123', 2, 2, '5722', 15, 21, 9,
     JSON '["MON"]', 'PM', JSON '["DAY_OFF", "EASY_COURSE", "GRADUATION", "COMPETITION"]',
     NOW(), NOW());

-- 5. "경쟁률 회피형" (4학년 1학기, 졸업만 하면 됨)
INSERT INTO users (user_id, username, password, grade, semester, major_code, credits_major_required, credits_major_elective, credits_general, preferred_off_days, preferred_time_slot, priority_order, created_at, last_modified_at)
VALUES
    ('20220005', 'senior_avoider', 'password123', 4, 1, '5722', 30, 40, 20,
     JSON '[]', 'NONE', JSON '["COMPETITION", "GRADUATION", "EASY_COURSE", "DAY_OFF"]',
     NOW(), NOW());

-- 6. "학점 풀로 채우는 학생" (3학년 1학기, 선호도 없음)
INSERT INTO users (user_id, username, password, grade, semester, major_code, credits_major_required, credits_major_elective, credits_general, preferred_off_days, preferred_time_slot, priority_order, created_at, last_modified_at)
VALUES
    ('20230006', 'max_credits', 'password123', 3, 1, '5722', 18, 24, 12,
     JSON '[]', 'NONE', JSON '["GRADUATION", "COMPETITION", "DAY_OFF", "EASY_COURSE"]',
     NOW(), NOW());

-- 7. "금공강 사수" (2학년 2학기, 오전 선호)
INSERT INTO users (user_id, username, password, grade, semester, major_code, credits_major_required, credits_major_elective, credits_general, preferred_off_days, preferred_time_slot, priority_order, created_at, last_modified_at)
VALUES
    ('20240007', 'friday_off', 'password123', 2, 2, '5722', 15, 18, 9,
     JSON '["FRI"]', 'AM', JSON '["DAY_OFF", "GRADUATION", "EASY_COURSE", "COMPETITION"]',
     NOW(), NOW());

-- 8. "수공강 사수" (3학년 1학기, 꿀강 중시)
INSERT INTO users (user_id, username, password, grade, semester, major_code, credits_major_required, credits_major_elective, credits_general, preferred_off_days, preferred_time_slot, priority_order, created_at, last_modified_at)
VALUES
    ('20230008', 'wed_off', 'password123', 3, 1, '5722', 21, 20, 10,
     JSON '["WED"]', 'NONE', JSON '["DAY_OFF", "EASY_COURSE", "GRADUATION", "COMPETITION"]',
     NOW(), NOW());

-- 9. "본격적인 꿀강러" (1학년 2학기, 꿀강 1순위)
INSERT INTO users (user_id, username, password, grade, semester, major_code, credits_major_required, credits_major_elective, credits_general, preferred_off_days, preferred_time_slot, priority_order, created_at, last_modified_at)
VALUES
    ('20250009', 'easy_hunter', 'password123', 1, 2, '5722', 3, 6, 6,
     JSON '[]', 'AM', JSON '["EASY_COURSE", "DAY_OFF", "COMPETITION", "GRADUATION"]',
     NOW(), NOW());

-- 10. "균형잡힌 표준 2학년" (2학년 1학기, 졸업/공강 둘 다 중요)
INSERT INTO users (user_id, username, password, grade, semester, major_code, credits_major_required, credits_major_elective, credits_general, preferred_off_days, preferred_time_slot, priority_order, created_at, last_modified_at)
VALUES
    ('20240010', 'balanced_user', 'password123', 2, 1, '5722', 12, 12, 6,
     JSON '["FRI"]', 'AM', JSON '["GRADUATION", "DAY_OFF", "EASY_COURSE", "COMPETITION"]',
     NOW(), NOW());

-- (주의: 이 스크립트는 sample_users.sql, data.h2.final.sql이 실행된 후
-- User ID와 Class ID가 1부터 순차적으로 생성되었다고 가정합니다.)

-- --- Timetable (시간표 마스터) 데이터 ---

-- 1. 'newbie' (user_id=1)의 1학년 1학기 "월공강 추천안"
MERGE INTO Timetable (id, user_id, name, grade, semester, total_credits, is_active, created_at, updated_at) KEY(id) VALUES
    (1, 1, 'AI 추천 1안 (월공강)', 1, 1, 15, true, NOW(), NOW());

-- 2. 'junior_grad' (user_id=2)의 3학년 2학기 "졸업 최적화 (금공강)"
MERGE INTO Timetable (id, user_id, name, grade, semester, total_credits, is_active, created_at, updated_at) KEY(id) VALUES
    (2, 2, '졸업 최적화 (금공강)', 3, 2, 18, true, NOW(), NOW());

-- 3. 'junior_grad' (user_id=2)의 3학년 2학기 "다른 금공강 조합"
MERGE INTO Timetable (id, user_id, name, grade, semester, total_credits, is_active, created_at, updated_at) KEY(id) VALUES
    (3, 2, '금공강 도전', 3, 2, 18, false, NOW(), NOW());

-- 4. 'night_owl' (user_id=4)의 2학년 2학기 "오후 수업 위주 (월공강)"
MERGE INTO Timetable (id, user_id, name, grade, semester, total_credits, is_active, created_at, updated_at) KEY(id) VALUES
    (4, 4, '오후 수업 위주', 2, 2, 15, true, NOW(), NOW());


-- --- TimetableClass (시간표-수업 매핑) 데이터 ---

-- 1. 'AI 추천 1안 (월공강)' (timetable_id=1) 구성
-- (월공강, 오전 선호, 15학점)
-- class_id=61 (COSE475-00, 화1/목1)
-- class_id=22 (COSE242-01, 화2/목2)
-- class_id=7  (COSE213-03, 화3/목3)
-- class_id=6  (COSE213-02, 화4/목4)
-- class_id=5  (COSE213-01, 화5/목5)
INSERT INTO TimetableClass (timetable_id, class_id) VALUES
                                                        (1, 61),
                                                        (1, 22),
                                                        (1, 7),
                                                        (1, 6),
                                                        (1, 5);

-- 2. '졸업 최적화 (금공강)' (timetable_id=2) 구성
-- (금공강, 전필 위주, 18학점)
-- class_id=8  (COSE213-04, 월2/수2) - 전필
-- class_id=28 (COSE341-02, 월3/수3) - 전필
-- class_id=10 (COSE214-02, 월4/수4) - 전필
-- class_id=2  (COSE211-02, 월5/수5) - 전선
-- class_id=9  (COSE214-01, 월6/수6) - 전필
-- class_id=41 (COSE371-00, 화4/목4) - 전선
INSERT INTO TimetableClass (timetable_id, class_id) VALUES
                                                        (2, 8),
                                                        (2, 28),
                                                        (2, 10),
                                                        (2, 2),
                                                        (2, 9),
                                                        (2, 41);

-- 3. '금공강 도전' (timetable_id=3) 구성
-- (금공강, 18학점)
-- class_id=35 (COSE354-02, 월1-2/수1-2)
-- class_id=43 (COSE380-00, 월3/수3)
-- class_id=3  (COSE212-01, 월4/수4)
-- class_id=26 (COSE322-00, 월5/수5)
-- class_id=11 (COSE214-03, 화4/목4)
-- class_id=5  (COSE213-01, 화5/목5)
INSERT INTO TimetableClass (timetable_id, class_id) VALUES
                                                        (3, 35),
                                                        (3, 43),
                                                        (3, 3),
                                                        (3, 26),
                                                        (3, 11),
                                                        (3, 5);

-- 4. '오후 수업 위주' (timetable_id=4) 구성
-- (월공강, 오후 선호, 15학점)
-- class_id=11 (COSE214-03, 화4/목4)
-- class_id=5  (COSE213-01, 화5/목5)
-- class_id=21 (COSE222-06, 화6/목6)
-- class_id=50 (COSE405-00, 수6-9/토1)
-- class_id=46 (COSE389-00, 수5/금5) -- (이 유저는 금공강 요청 안 함)
INSERT INTO TimetableClass (timetable_id, class_id) VALUES
                                                        (4, 11),
                                                        (4, 5),
                                                        (4, 21),
                                                        (4, 50),
                                                        (4, 46);