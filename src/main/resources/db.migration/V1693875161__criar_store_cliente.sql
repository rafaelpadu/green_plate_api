CREATE TABLE address
(
    id         INTEGER NOT NULL,
    created_by VARCHAR(255),
    updated_by VARCHAR(255),
    created_at TIMESTAMP WITHOUT TIME ZONE,
    updated_at TIMESTAMP WITHOUT TIME ZONE,
    cep        INTEGER,
    country    VARCHAR(255),
    state      VARCHAR(255),
    city       VARCHAR(255),
    address    VARCHAR(255),
    number     INTEGER,
    complement VARCHAR(255),
    CONSTRAINT pk_address PRIMARY KEY (id)
);
CREATE TABLE usuario
(
    id          INTEGER NOT NULL,
    created_by  VARCHAR(255),
    updated_by  VARCHAR(255),
    created_at  TIMESTAMP WITHOUT TIME ZONE,
    updated_at  TIMESTAMP WITHOUT TIME ZONE,
    user_name   VARCHAR(255),
    email       VARCHAR(255),
    pass_word   VARCHAR(255),
    customer_id INTEGER,
    active      BOOLEAN,
    CONSTRAINT pk_usuario PRIMARY KEY (id)
);
CREATE TABLE customer
(
    id         INTEGER NOT NULL,
    created_by VARCHAR(255),
    updated_by VARCHAR(255),
    created_at TIMESTAMP WITHOUT TIME ZONE,
    updated_at TIMESTAMP WITHOUT TIME ZONE,
    full_name  VARCHAR(255),
    phone      VARCHAR(255),
    address_id INTEGER,
    user_id    INTEGER,
    CONSTRAINT pk_customer PRIMARY KEY (id)
);
CREATE TABLE product
(
    id          INTEGER NOT NULL,
    created_by  VARCHAR(255),
    updated_by  VARCHAR(255),
    created_at  TIMESTAMP WITHOUT TIME ZONE,
    updated_at  TIMESTAMP WITHOUT TIME ZONE,
    name        VARCHAR(255),
    description VARCHAR(255),
    ean13       BIGINT,
    image_url   VARCHAR(255),
    active      BOOLEAN,
    CONSTRAINT pk_product PRIMARY KEY (id)
);
CREATE TABLE price
(
    id         INTEGER NOT NULL,
    created_by VARCHAR(255),
    updated_by VARCHAR(255),
    created_at TIMESTAMP WITHOUT TIME ZONE,
    updated_at TIMESTAMP WITHOUT TIME ZONE,
    unit_value DECIMAL,
    stock_id   INTEGER,
    CONSTRAINT pk_price PRIMARY KEY (id)
);
CREATE TABLE stock
(
    id          INTEGER NOT NULL,
    created_by  VARCHAR(255),
    updated_by  VARCHAR(255),
    created_at  TIMESTAMP WITHOUT TIME ZONE,
    updated_at  TIMESTAMP WITHOUT TIME ZONE,
    store_id    INTEGER,
    product_id  INTEGER,
    current_qty INTEGER,
    minimal_qty INTEGER,
    max_qty     INTEGER,
    due_date    TIMESTAMP WITHOUT TIME ZONE,
    CONSTRAINT pk_stock PRIMARY KEY (id)
);

CREATE TABLE stock_price_list
(
    stock_id      INTEGER NOT NULL,
    price_list_id INTEGER NOT NULL
);
CREATE TABLE "order"
(
    id          INTEGER NOT NULL,
    created_by  VARCHAR(255),
    updated_by  VARCHAR(255),
    created_at  TIMESTAMP WITHOUT TIME ZONE,
    updated_at  TIMESTAMP WITHOUT TIME ZONE,
    store_id    INTEGER,
    customer_id INTEGER,
    item_total  DECIMAL,
    CONSTRAINT pk_order PRIMARY KEY (id)
);
CREATE TABLE store
(
    id            INTEGER      NOT NULL,
    created_by    VARCHAR(255),
    updated_by    VARCHAR(255),
    created_at    TIMESTAMP WITHOUT TIME ZONE,
    updated_at    TIMESTAMP WITHOUT TIME ZONE,
    trade_name    VARCHAR(255) NOT NULL,
    business_name VARCHAR(255),
    address_id    INTEGER      NOT NULL,
    logo_img_url  VARCHAR(255),
    CONSTRAINT pk_store PRIMARY KEY (id)
);

CREATE TABLE store_stock_list
(
    stock_list_id INTEGER NOT NULL,
    store_id      INTEGER NOT NULL
);
CREATE TABLE order_item
(
    id            INTEGER NOT NULL,
    created_by    VARCHAR(255),
    updated_by    VARCHAR(255),
    created_at    TIMESTAMP WITHOUT TIME ZONE,
    updated_at    TIMESTAMP WITHOUT TIME ZONE,
    stock_id      INTEGER,
    item_total    DECIMAL,
    unit_value    DECIMAL,
    discount      DECIMAL,
    qty_requested INTEGER,
    CONSTRAINT pk_orderitem PRIMARY KEY (id)
);

ALTER TABLE order_item
    ADD CONSTRAINT FK_ORDERITEM_ON_STOCK FOREIGN KEY (stock_id) REFERENCES stock (id);
ALTER TABLE store_stock_list
    ADD CONSTRAINT uc_store_stock_list_stocklist UNIQUE (stock_list_id);
ALTER TABLE store
    ADD CONSTRAINT FK_STORE_ON_ADDRESS FOREIGN KEY (address_id) REFERENCES address (id);
ALTER TABLE store_stock_list
    ADD CONSTRAINT fk_stostolis_on_stock FOREIGN KEY (stock_list_id) REFERENCES stock (id);
ALTER TABLE store_stock_list
    ADD CONSTRAINT fk_stostolis_on_store FOREIGN KEY (store_id) REFERENCES store (id);
ALTER TABLE "order"
    ADD CONSTRAINT FK_ORDER_ON_CUSTOMER FOREIGN KEY (customer_id) REFERENCES customer (id);
ALTER TABLE "order"
    ADD CONSTRAINT FK_ORDER_ON_STORE FOREIGN KEY (store_id) REFERENCES store (id);
ALTER TABLE stock_price_list
    ADD CONSTRAINT uc_stock_price_list_pricelist UNIQUE (price_list_id);
ALTER TABLE stock
    ADD CONSTRAINT FK_STOCK_ON_PRODUCT FOREIGN KEY (product_id) REFERENCES product (id);
ALTER TABLE stock
    ADD CONSTRAINT FK_STOCK_ON_STORE FOREIGN KEY (store_id) REFERENCES store (id);
ALTER TABLE stock_price_list
    ADD CONSTRAINT fk_stoprilis_on_price FOREIGN KEY (price_list_id) REFERENCES price (id);
ALTER TABLE stock_price_list
    ADD CONSTRAINT fk_stoprilis_on_stock FOREIGN KEY (stock_id) REFERENCES stock (id);
ALTER TABLE price
    ADD CONSTRAINT FK_PRICE_ON_STOCK FOREIGN KEY (stock_id) REFERENCES stock (id);
ALTER TABLE usuario
    ADD CONSTRAINT FK_USUARIO_ON_CUSTOMER FOREIGN KEY (customer_id) REFERENCES customer (id);
ALTER TABLE customer
    ADD CONSTRAINT FK_CUSTOMER_ON_ADDRESS FOREIGN KEY (address_id) REFERENCES address (id);
ALTER TABLE customer
    ADD CONSTRAINT FK_CUSTOMER_ON_USER FOREIGN KEY (user_id) REFERENCES usuario (id);
