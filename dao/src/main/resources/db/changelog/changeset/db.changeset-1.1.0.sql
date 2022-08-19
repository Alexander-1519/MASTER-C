CREATE TABLE masters(
    id              BIGSERIAL PRIMARY KEY,
    user_id         BIGINT REFERENCES users(id) NOT NULL,
    image_url       VARCHAR(512),
    created_at      TIMESTAMP WITHOUT TIME ZONE,
    updated_at      TIMESTAMP WITHOUT TIME ZONE,
    created_by      VARCHAR(64),
    updated_by      VARCHAR(64)
);

CREATE TABLE master_categories(
    id              BIGSERIAL PRIMARY KEY,
    name            VARCHAR(128) NOT NULL,
    created_at      TIMESTAMP WITHOUT TIME ZONE,
    updated_at      TIMESTAMP WITHOUT TIME ZONE,
    created_by      VARCHAR(64),
    updated_by      VARCHAR(64)
);

CREATE TABLE master_rooms(
    id              BIGSERIAL PRIMARY KEY,
    started_at      TIMESTAMP WITHOUT TIME ZONE,
    info            VARCHAR(2048),
    category_id     BIGINT REFERENCES master_categories(id),
    master_id       BIGINT REFERENCES masters(id) NOT NULL,
    created_at      TIMESTAMP WITHOUT TIME ZONE,
    updated_at      TIMESTAMP WITHOUT TIME ZONE,
    created_by      VARCHAR(64),
    updated_by      VARCHAR(64)
);

CREATE TABLE regions(
        id              BIGSERIAL PRIMARY KEY,
        name            VARCHAR(255),
        created_at      TIMESTAMP WITHOUT TIME ZONE,
        updated_at      TIMESTAMP WITHOUT TIME ZONE,
        created_by      VARCHAR(64),
        updated_by      VARCHAR(64)
);

CREATE TABLE cities(
        id              BIGSERIAL PRIMARY KEY,
        name            VARCHAR(255),
        region_id       BIGINT REFERENCES regions(id),
        created_at      TIMESTAMP WITHOUT TIME ZONE,
        updated_at      TIMESTAMP WITHOUT TIME ZONE,
        created_by      VARCHAR(64),
        updated_by      VARCHAR(64)
);