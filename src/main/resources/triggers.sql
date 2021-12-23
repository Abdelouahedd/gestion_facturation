CREATE OR REPLACE PROCEDURE update_status_facture(factureId bigint)
as
$$
DECLARE
total_vr  double precision;
    total_fac double precision;
BEGIN
select total into total_fac from facture where id = factureId;
select coalesce(sum(prix), 0) into total_vr from virment where facture_id = factureId;
IF (total_fac = total_vr) THEN
UPDATE facture SET complete=true WHERE id = factureId;
END IF;
end;
$$
language plpgsql;

CREATE OR REPLACE FUNCTION facture_status_trigger()
    RETURNS TRIGGER
AS
$$
BEGIN
CALL update_status_facture(NEW.facture_id);
RETURN NEW;
END;
$$ language plpgsql;

CREATE TRIGGER is_complete_facture AFTER INSERT or UPDATE ON virment
                                                     FOR EACH ROW
                                                     execute procedure facture_status_trigger();
