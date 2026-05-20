-- Step 1: Create trigger function
CREATE OR REPLACE FUNCTION trg_update_door_count()
RETURNS TRIGGER
LANGUAGE plpgsql
AS $$
BEGIN
    IF TG_OP = 'INSERT' THEN
        UPDATE hall
        SET    door_count = door_count + 1
        WHERE  hall_id = NEW.hall_id;
        RETURN NEW;

    ELSIF TG_OP = 'DELETE' THEN
        UPDATE hall
        SET    door_count = door_count - 1
        WHERE  hall_id = OLD.hall_id;
        RETURN OLD;

    ELSIF TG_OP = 'UPDATE' THEN
        IF OLD.hall_id IS DISTINCT FROM NEW.hall_id THEN
            UPDATE hall
            SET    door_count = door_count - 1
            WHERE  hall_id = OLD.hall_id;

            UPDATE hall
            SET    door_count = door_count + 1
            WHERE  hall_id = NEW.hall_id;
        END IF;
        RETURN NEW;
    END IF;

    RETURN NULL;
END;
$$;

-- Step 2: Create trigger
CREATE OR REPLACE TRIGGER trg_door_count
AFTER INSERT OR UPDATE OR DELETE
ON door
FOR EACH ROW
EXECUTE FUNCTION trg_update_door_count();