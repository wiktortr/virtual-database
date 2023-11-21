create table virtual_table
(
    id          int8                                not null,
    name        text                                not null,
    created_at  timestamp default current_timestamp not null,
    updated_at  timestamp default current_timestamp not null,
    version_num int8      default 0                 not null,
    primary key (id)
);

create sequence virtual_table_id_seq owned by virtual_table.id;

create table virtual_column
(
    id                int8                                not null,
    table_id          int8                                not null,
    name              text                                not null,
    data_type         text                                not null,
    primary_key_ind   boolean                             not null,
    mandatory_key_ind boolean                             not null,
    created_at        timestamp default current_timestamp not null,
    updated_at        timestamp default current_timestamp not null,
    version_num       int8      default 0                 not null,
    primary key (id),
    CONSTRAINT fk_table
        FOREIGN KEY (table_id)
            REFERENCES virtual_table (id)
);

create sequence virtual_column_id_seq owned by virtual_column.id;

create table virtual_record
(
    id          int8                                not null,
    table_id    int8                                not null,
    created_at  timestamp default current_timestamp not null,
    updated_at  timestamp default current_timestamp not null,
    version_num int8      default 0                 not null,
    primary key (id),
    CONSTRAINT fk_table
        FOREIGN KEY (table_id)
            REFERENCES virtual_table (id)
);

create sequence virtual_record_id_seq owned by virtual_record.id;

create table virtual_value
(
    id        int8 not null,
    record_id int8 not null,
    column_id int8 not null,
    value     text,
    primary key (id),
    CONSTRAINT fk_record
        FOREIGN KEY (record_id)
            REFERENCES virtual_record (id),
    CONSTRAINT fk_column
        FOREIGN KEY (column_id)
            REFERENCES virtual_column (id)
);

create sequence virtual_value_id_seq owned by virtual_value.id;