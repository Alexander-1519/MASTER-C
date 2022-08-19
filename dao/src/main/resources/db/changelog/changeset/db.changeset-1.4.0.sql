CREATE TABLE maintenance_date
(
    id              BIGSERIAL PRIMARY KEY,
    date            TIMESTAMP WITHOUT TIME ZONE,
    master_room_id  BIGINT REFERENCES master_rooms(id) NOT NULL,
    created_at      TIMESTAMP WITHOUT TIME ZONE,
    updated_at      TIMESTAMP WITHOUT TIME ZONE,
    created_by      VARCHAR(64),
    updated_by      VARCHAR(64)
)