-- Create table
create table BALANCO
(
  id        INTEGER generated always as identity,
  valor     NUMBER(10,2) not null,
  descricao NUMBER not null,
  conta     INTEGER not null,
  data      DATE default current_date not null
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
-- Create/Recreate indexes 
create unique index BALANCO_ID_UINDEX on BALANCO (ID)
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
-- Create/Recreate primary, unique and foreign key constraints 
alter table BALANCO
  add constraint BALANCO_PK primary key (ID);
alter table BALANCO
  add constraint BALANCO_CONTA_NUMERO_FK foreign key (CONTA)
  references CONTA (NUMERO);
