create table company
(
    id           bigint not null auto_increment,
    contact_info varchar(255),
    description  varchar(255),
    name         varchar(255),
    primary key (id)
) engine = InnoDB;
create table education
(
    id              bigint not null auto_increment,
    degree          varchar(255),
    graduation_year integer,
    institution     varchar(255),
    specialization  varchar(255),
    profile_id      bigint,
    primary key (id)
) engine = InnoDB;
create table experience
(
    id                 bigint not null auto_increment,
    company_name       varchar(255),
    end_date           date,
    position           varchar(255),
    reason_for_leaving varchar(255),
    start_date         date,
    profile_id         bigint,
    primary key (id)
) engine = InnoDB;
create table job_requirements
(
    id                 bigint not null auto_increment,
    housing_required   bit    not null,
    min_salary         float(53),
    preferred_location varchar(255),
    profession_id      bigint,
    profile_id         bigint,
    primary key (id)
) engine = InnoDB;
create table profession
(
    id    bigint not null auto_increment,
    title varchar(255),
    primary key (id)
) engine = InnoDB;
create table profile_has_profession
(
    id            bigint not null auto_increment,
    skill_level   varchar(255),
    profession_id bigint,
    profile_id    bigint,
    primary key (id)
) engine = InnoDB;
create table profile_has_vacancy
(
    id           bigint not null auto_increment,
    created_date datetime(6),
    status       varchar(255),
    profile_id   bigint,
    vacancy_id   bigint,
    primary key (id)
) engine = InnoDB;
create table profiles
(
    id          bigint not null,
    address     varchar(255),
    birth_date  date,
    email       varchar(255),
    first_name  varchar(255),
    is_archived bit    not null,
    last_name   varchar(255),
    middle_name varchar(255),
    phone       varchar(255),
    primary key (id)
) engine = InnoDB;
create table roles
(
    id   bigint not null auto_increment,
    name varchar(255),
    primary key (id)
) engine = InnoDB;
create table users
(
    id       bigint not null auto_increment,
    password varchar(255),
    username varchar(255),
    primary key (id)
) engine = InnoDB;
create table users_roles
(
    users_id bigint not null,
    roles_id bigint not null,
    primary key (users_id, roles_id)
) engine = InnoDB;
create table vacancies
(
    id                 bigint not null auto_increment,
    housing_provided   bit    not null,
    is_archived        bit    not null,
    position           varchar(255),
    requirements       varchar(255),
    salary             float(53),
    working_conditions varchar(255),
    company_id         bigint,
    primary key (id)
) engine = InnoDB;
alter table job_requirements
drop index UKbrnk97dkvn70dm5ea8dslhhck;
alter table job_requirements
    add constraint UKbrnk97dkvn70dm5ea8dslhhck unique (profile_id);
alter table education
    add constraint FK8yafawga6neotslnlmcmevpmr foreign key (profile_id) references profiles (id);
alter table experience
    add constraint FKr6dkc6hdnv1sqfvyqbaqm0sa4 foreign key (profile_id) references profiles (id);
alter table job_requirements
    add constraint FK5385lh95dg0bd42elumx8ah0o foreign key (profession_id) references profession (id);
alter table job_requirements
    add constraint FKni4ax0cuaueqt30hkr1xvlxna foreign key (profile_id) references profiles (id);
alter table profile_has_profession
    add constraint FKkye7ddherutfhyjqrq5l76i3u foreign key (profession_id) references profession (id);
alter table profile_has_profession
    add constraint FKnmdnc3mq42vn8yi2okn28ym80 foreign key (profile_id) references profiles (id);
alter table profile_has_vacancy
    add constraint FKmvofc0oxyivn1evc1dp9qaq64 foreign key (profile_id) references profiles (id);
alter table profile_has_vacancy
    add constraint FKfdb98rql82q2i8s4qbua3ifq6 foreign key (vacancy_id) references vacancies (id);
alter table profiles
    add constraint FK55e5d3sfwkob62wtprm633alk foreign key (id) references users (id);
alter table users_roles
    add constraint FKa62j07k5mhgifpp955h37ponj foreign key (roles_id) references roles (id);
alter table users_roles
    add constraint FKml90kef4w2jy7oxyqv742tsfc foreign key (users_id) references users (id);
alter table vacancies
    add constraint FKpp72e4rwxyccdxt0svodtufkv foreign key (company_id) references company (id);