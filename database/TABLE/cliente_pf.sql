-- Create table
create table CLIENTE_PF
(
  cpf       VARCHAR2(15) not null,
  nome      VARCHAR2(100) not null,
  sobrenome VARCHAR2(100) not null
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
-- Create/Recreate primary, unique and foreign key constraints 
alter table CLIENTE_PF
  add constraint CLIENTE_PF_PK primary key (CPF)
  using index 
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
alter table CLIENTE_PF
  add constraint CLIENTE_PF_FK foreign key (CPF)
  references CLIENTE (DOCUMENTO) on delete cascade;
