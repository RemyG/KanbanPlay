# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table section (
  id                        bigint not null,
  label                     varchar(255),
  constraint pk_section primary key (id))
;

create table task (
  id                        bigint not null,
  label                     varchar(255),
  description               varchar(255),
  section_id                bigint,
  constraint pk_task primary key (id))
;

create sequence section_seq;

create sequence task_seq;

alter table task add constraint fk_task_section_1 foreign key (section_id) references section (id) on delete restrict on update restrict;
create index ix_task_section_1 on task (section_id);



# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists section;

drop table if exists task;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists section_seq;

drop sequence if exists task_seq;

