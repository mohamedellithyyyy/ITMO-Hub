-- =========================
-- USERS TABLE
-- =========================
CREATE TABLE IF NOT EXISTS users (
                                     id SERIAL PRIMARY KEY,
                                     username VARCHAR(50) UNIQUE NOT NULL,
    password_hash VARCHAR(56) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
    );

-- =========================
-- MUSIC BANDS TABLE
-- =========================
CREATE TABLE IF NOT EXISTS music_bands (
    id SERIAL PRIMARY KEY,
    name TEXT NOT NULL,
    x DOUBLE PRECISION NOT NULL,
    y BIGINT NOT NULL,
    creation_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    number_of_participants INTEGER,
    albums_count INTEGER,
    genre VARCHAR(50),

    -- frontman info
    frontman_name TEXT,
    frontman_height DOUBLE PRECISION,
    frontman_eye_color VARCHAR(20),
    frontman_hair_color VARCHAR(50),
    frontman_nationality VARCHAR(50),

    -- location
    location_x BIGINT,
    location_y DOUBLE PRECISION,
    location_z BIGINT,
    location_name TEXT,

    -- ownership
    owner_id INTEGER NOT NULL,

    CONSTRAINT fk_owner
    FOREIGN KEY (owner_id)
    REFERENCES users(id)
    ON DELETE CASCADE
    );

-- =========================
-- OPTIONAL (recommended index)
-- =========================
CREATE INDEX IF NOT EXISTS idx_music_bands_owner_id
    ON music_bands(owner_id);