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
    id UUID PRIMARY KEY,
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

-- Indexes for performance
CREATE INDEX idx_course_major ON course(major_id);
CREATE INDEX idx_course_credit_type ON course(credit_type);
CREATE INDEX idx_class_course ON class(course_id);
CREATE INDEX idx_graduation_requirement_major ON graduation_requirement(major_id);
CREATE INDEX idx_track_major ON track(major_id);
CREATE INDEX idx_users_username ON users(username);
CREATE INDEX idx_users_major_code ON users(major_code);