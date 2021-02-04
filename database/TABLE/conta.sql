-- Create table
create table CONTA
(
  numero         NUMBER not null,
  saldo_inicial  NUMBER(10,2) not null,
  saldo_atual    NUMBER(10,2) not null,
  saldo_anterior NUMBER(10,2) not null,
  cliente        VARCHAR2(15) not null,
  data_criacao   DATE default CURRENT_DATE not null
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
create unique index CONTA_NUMERO_UINDEX on CONTA (NUMERO)
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
alter table CONTA
  add constraint CONTA_PK primary key (NUMERO);
alter table CONTA
  add constraint CLIENTE_FK foreign key (CLIENTE)
  references CLIENTE (DOCUMENTO) on delete cascade;
