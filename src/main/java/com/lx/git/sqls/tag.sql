use support_db;
CREATE TABLE tb_tag(
    id int(4) not null primary key auto_increment,
    name char(20) not null,
    commit_id char(20) not null
    );