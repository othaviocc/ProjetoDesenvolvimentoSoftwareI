CREATE TRUSTED PROCEDURAL LANGUAGE 'plpgsql'
 HANDLER plpgsql_call_handler
 VALIDATOR plpgsql_validator;

CREATE TRIGGER testa_plano
BEFORE INSERT
ON associado
FOR EACH ROW
EXECUTE PROCEDURE testa_plano(); 

CREATE OR REPLACE FUNCTION testa_plano()
RETURNS trigger AS
$BODY$DECLARE
TESTE INT;
BEGIN
IF NEW.ccodigo IS NULL THEN
RAISE EXCEPTION 'Codigo convenio deve ser informado - nao pode ser nulo';
END IF;
SELECT COUNT(*) INTO TESTE FROM convenio WHERE convenio.ccodigo = NEW.ccodigo;
IF TESTE = 0 THEN
RAISE EXCEPTION
'Convenio não encontrado na tabela de convenios - insercao de associado nao aceita';
END IF;
RETURN NEW;
END;
$BODY$
LANGUAGE 'plpgsql' VOLATILE;