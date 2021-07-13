/* Loaded by Spring Data JDBC on start. Inspect application.yaml for more details.*/

CREATE TABLE IF NOT EXISTS products
(
    id         serial    NOT NULL,
    name       varchar   NOT NULL,
    price      money     NOT NULL,
    pictureUrl varchar,
    createdAt  timestamp NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS orders
(
    id        serial    NOT NULL,
    createdAt timestamp NOT NULL,
    status    varchar   NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS order_items
(
    product_id integer NOT NULL,
    order_id   integer NOT NULL,
    quantity   integer NOT NULL,
    FOREIGN KEY (product_id) REFERENCES products (id),
    FOREIGN KEY (order_id) REFERENCES orders (id),
    UNIQUE (product_id, order_id)
);