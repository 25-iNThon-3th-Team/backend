-- Drop tables in reverse dependency order
DROP TABLE IF EXISTS track_course CASCADE;
DROP TABLE IF EXISTS track CASCADE;
DROP TABLE IF EXISTS graduation_requirement CASCADE;
DROP TABLE IF EXISTS class CASCADE;
DROP TABLE IF EXISTS course CASCADE;
DROP TABLE IF EXISTS major CASCADE;
DROP TABLE IF EXISTS user_roles CASCADE;
DROP TABLE IF EXISTS users CASCADE;

-- 1. Users table
CREATE TABLE users (
    id BIGSERIAL PRIMARY KEY,
    username VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    enabled BOOLEAN NOT NULL DEFAULT TRUE,
    roles JSONB DEFAULT '["USER"]',

    -- Grade / Semester
    grade SMALLINT,
    semester SMALLINT,

    -- Major
    major_code VARCHAR(100),

    -- Credits
    credits_major_required INT NOT NULL DEFAULT 0,
    credits_major_elective INT NOT NULL DEFAULT 0,
    credits_general INT NOT NULL DEFAULT 0,

    -- Time constraints
    preferred_off_days JSONB,
    preferred_time_slot VARCHAR(10) DEFAULT 'NONE',
    max_transfer_minutes SMALLINT DEFAULT 20,

    -- Priority
    priority_order JSONB,

    -- Audit fields
    created_at TIMESTAMP NOT NULL,
    last_modified_at TIMESTAMP NOT NULL
);

-- 2. Major table (no dependencies)
CREATE TABLE major (
    id BIGSERIAL PRIMARY KEY,
    code VARCHAR(100) UNIQUE NOT NULL,
    name VARCHAR(255) NOT NULL
);

-- 3. Course table (depends on Major)
CREATE TABLE course (
    id BIGSERIAL PRIMARY KEY,
    course_code VARCHAR(100) UNIQUE NOT NULL,
    name VARCHAR(255) NOT NULL,
    major_id BIGINT REFERENCES major(id),
    credit_type VARCHAR(50) NOT NULL,
    credits SMALLINT NOT NULL DEFAULT 3,
    target_grade SMALLINT,
    competition_rate NUMERIC(5, 2) DEFAULT 1.0,
    easiness_score NUMERIC(3, 2) DEFAULT 3.0
);

-- 4. Class table (depends on Course)
CREATE TABLE class (
    id BIGSERIAL PRIMARY KEY,
    course_id BIGINT NOT NULL REFERENCES course(id),
    class_code VARCHAR(20) NOT NULL DEFAULT '01',
    professor_name VARCHAR(255),
    schedule JSONB,
    total_seats INT DEFAULT 50
);

-- 5. GraduationRequirement table (depends on Major)
CREATE TABLE graduation_requirement (
    id BIGSERIAL PRIMARY KEY,
    major_id BIGINT NOT NULL REFERENCES major(id),
    admission_year INT NOT NULL,
    credit_type VARCHAR(50) NOT NULL,
    required_credits INT NOT NULL,
    UNIQUE(major_id, admission_year, credit_type)
);

-- 6. Track table (depends on Major)
CREATE TABLE track (
    id BIGSERIAL PRIMARY KEY,
    major_id BIGINT NOT NULL REFERENCES major(id),
    name VARCHAR(255) NOT NULL,
    description TEXT
);

-- 7. TrackCourse table (depends on Track and Course)
CREATE TABLE track_course (
    track_id BIGINT NOT NULL REFERENCES track(id),
    course_id BIGINT NOT NULL REFERENCES course(id),
    PRIMARY KEY (track_id, course_id)
);

-- 1. Timetable (시간표 마스터)
-- 사용자가 저장한 시간표의 '컨테이너'입니다.
-- (e.g., "내 1순위 시간표", "공강 최적화안")
CREATE TABLE Timetable (
                           id BIGSERIAL PRIMARY KEY,

    -- 이 시간표의 소유자
                           user_id BIGINT NOT NULL REFERENCES users(id) ON DELETE CASCADE,

    -- "내 시간표", "최종본", "AI 추천 1안" 등
                           name VARCHAR(255) NOT NULL,

    -- [추가됨] 이 시간표가 대상인 학년과 학기
                           grade SMALLINT NOT NULL, -- (e.g., 2 -> 2학년)
                           semester SMALLINT NOT NULL, -- (e.g., 1 -> 1학기)

    -- 이 시간표의 총 학점 (계산된 값)
                           total_credits INT NOT NULL DEFAULT 0,

    -- 사용자가 메인으로 사용하는 시간표인지 여부
                           is_active BOOLEAN NOT NULL DEFAULT false,

                           created_at TIMESTAMP DEFAULT now(),
                           updated_at TIMESTAMP DEFAULT now(),

    -- [수정됨] 한 명의 유저는 동일한 (학년, 학기, 이름)의 시간표를 중복해서 가질 수 없음
                           UNIQUE(user_id, name, grade, semester)
);

-- 2. TimetableClass (시간표-수업 매핑)
-- 특정 시간표(Timetable)에 어떤 수업(Class)이 포함되는지 N:M 관계를 정의합니다.
-- 사용자의 모든 수정/추가/삭제/고정 작업은 이 테이블을 대상으로 이루어집니다.
CREATE TABLE TimetableClass (
    -- 어떤 시간표에 속해있는지
                                timetable_id BIGINT NOT NULL REFERENCES Timetable(id) ON DELETE CASCADE,

    -- 어떤 수업(분반)이 포함되는지 (기존 Class 테이블 참조)
                                class_id BIGINT NOT NULL REFERENCES Class(id) ON DELETE CASCADE,

    -- (timetable_id, class_id) 조합은 유일해야 함
                                PRIMARY KEY (timetable_id, class_id)
);

-- Indexes for performance
CREATE INDEX idx_course_major ON course(major_id);
CREATE INDEX idx_course_credit_type ON course(credit_type);
CREATE INDEX idx_class_course ON class(course_id);
CREATE INDEX idx_graduation_requirement_major ON graduation_requirement(major_id);
CREATE INDEX idx_track_major ON track(major_id);
CREATE INDEX idx_users_username ON users(username);
CREATE INDEX idx_users_major_code ON users(major_code);