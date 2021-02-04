create or replace package developer.pck_conta is

  -- Author  : JHOAN
  -- Created : 01/02/2021 20:37:18
  -- Purpose : 

  type t_cursor is ref cursor;

  procedure buscarConta(p_numero in developer.conta.numero%type,
                        p_result out t_cursor);

  procedure buscarCliente(p_cliente in developer.conta.cliente%type,
                          p_result  out t_cursor);

  procedure salvar(p_numero         in developer.conta.numero%type,
                   p_saldo_inicial  in developer.conta.saldo_inicial%type,
                   p_saldo_atual    in developer.conta.saldo_atual%type,
                   p_saldo_anterior in developer.conta.saldo_anterior%type,
                   p_cliente        in developer.conta.cliente%type);

  procedure operacao(p_numero   in developer.conta.numero%type,
                     p_valor    in number,
                     p_operacao in number);

end;
/
create or replace package body developer.pck_conta is

  procedure buscarConta(p_numero in developer.conta.numero%type,
                        p_result out t_cursor) is
  begin
    open p_result for
      select c.numero,
             c.saldo_inicial,
             c.saldo_atual,
             c.saldo_anterior,
             c.cliente
        from developer.conta c
       where c.numero = p_numero;
  
  end;

  procedure buscarCliente(p_cliente in developer.conta.cliente%type,
                          p_result  out t_cursor) is
  
    v_documento         developer.cliente.documento%type;
    v_nomeRazao         varchar2(100);
    v_sobrenomeFantasia varchar2(100);
    v_cep               developer.cliente.cep%type;
    v_telefone          developer.cliente.telefone%type;
    v_tipoCliente       developer.cliente.tipo_cliente%type;
  
  begin
  
    begin
      select p.documento, p.cep, p.telefone, p.tipo_cliente
        into v_documento, v_cep, v_telefone, v_tipoCliente
        from developer.cliente p
       where p.documento = p_cliente;
    end;
  
    begin
      if (v_tipoCliente = 1) then
        select p.nome, p.sobrenome
          into v_nomeRazao, v_sobrenomeFantasia
          from developer.cliente_pf p
         where p.cpf = v_documento;
      else
        select p.razaosocial, p.nomefantasia
          into v_nomeRazao, v_sobrenomeFantasia
          from developer.cliente_pj p
         where p.cnpj = v_documento;
      end if;
    end;
  
    open p_result for
      select v_documento         as documento,
             v_nomeRazao         as nomeRazao,
             v_sobrenomeFantasia as sobrenomeFantasia,
             v_cep               as cep,
             v_telefone          as telefone,
             v_tipoCliente       as tipoCliente
        from dual;
  
  end;

  procedure salvar(p_numero         in developer.conta.numero%type,
                   p_saldo_inicial  in developer.conta.saldo_inicial%type,
                   p_saldo_atual    in developer.conta.saldo_atual%type,
                   p_saldo_anterior in developer.conta.saldo_anterior%type,
                   p_cliente        in developer.conta.cliente%type) is
  
  begin
  
    insert into developer.conta
      (numero, saldo_inicial, saldo_atual, saldo_anterior, cliente)
    values
      (p_numero,
       p_saldo_inicial,
       p_saldo_atual,
       p_saldo_anterior,
       p_cliente);
  end;

  procedure operacao(p_numero   in developer.conta.numero%type,
                     p_valor    in number,
                     p_operacao in number) is
  
    v_saldo_atual    number(10, 2);
    v_saldo_anterior number(10, 2);
  
  begin
    begin
      select c.saldo_atual, c.saldo_anterior
        into v_saldo_atual, v_saldo_anterior
        from developer.conta c
       where c.numero = p_numero;
    end;
  
    if (p_operacao = 1) then
      v_saldo_anterior := v_saldo_atual;
      v_saldo_atual    := v_saldo_atual + p_valor;
    else
      v_saldo_anterior := v_saldo_atual;
      v_saldo_atual    := v_saldo_atual - p_valor;
    end if;
    
    begin 
      update developer.conta 
      set conta.saldo_atual = v_saldo_atual, conta.saldo_anterior = v_saldo_anterior
      where conta.numero = p_numero;
    end;
    
    commit;
    
  end;

end;
/
