CREATE TABLE masters_reviews
(
    id              BIGSERIAL PRIMARY KEY,
    review          VARCHAR(1024) NOT NULL,
    rating          DOUBLE PRECISION,
    master_room_id  BIGINT REFERENCES master_rooms(id) NOT NULL,
    user_id         BIGINT REFERENCES users(id) NOT NULL,
    created_at      TIMESTAMP WITHOUT TIME ZONE,
    updated_at      TIMESTAMP WITHOUT TIME ZONE,
    created_by      VARCHAR(64),
    updated_by      VARCHAR(64)
)