-- Create table
create table CLIENTE
(
  documento    VARCHAR2(15) not null,
  cep          VARCHAR2(8) not null,
  telefone     VARCHAR2(9),
  tipo_cliente NUMBER not null,
  data_criacao DATE default CURRENT_DATE
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 8K
    minextents 1
    maxextents unlimited
  );
-- Create/Recreate primary, unique and foreign key constraints 
alter table CLIENTE
  add constraint CLIENTE_PK primary key (DOCUMENTO)
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
alter table CLIENTE
  add constraint ENDERECO_FK foreign key (CEP)
  references ENDERECO (CEP);
