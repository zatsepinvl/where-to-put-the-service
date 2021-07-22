CREATE TABLE IF NOT EXISTS products
(
    id          bigserial   NOT NULL,
    name        varchar     NOT NULL,
    price       numeric     NOT NULL,
    picture_url varchar,
    created_at  timestamptz NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS orders
(
    id         bigserial   NOT NULL,
    created_at timestamptz NOT NULL,
    status     varchar     NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS order_items
(
    pk_product_id bigint  NOT NULL,
    pk_order_id   bigint  NOT NULL,
    quantity      integer NOT NULL,
    PRIMARY KEY (pk_product_id, pk_order_id),
    FOREIGN KEY (pk_product_id) REFERENCES products (id),
    FOREIGN KEY (pk_order_id) REFERENCES orders (id)
);