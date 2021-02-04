-- Create table
create table CLIENTE_PJ
(
  cnpj         VARCHAR2(15) not null,
  razaosocial  VARCHAR2(100) not null,
  nomefantasia VARCHAR2(100) not null
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
alter table CLIENTE_PJ
  add constraint CLIENTE_PJ_PK primary key (CNPJ)
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
alter table CLIENTE_PJ
  add constraint CLIENTE_PJ_FK foreign key (CNPJ)
  references CLIENTE (DOCUMENTO) on delete cascade;
