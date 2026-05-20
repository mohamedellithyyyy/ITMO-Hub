SELECT '=== INITIAL STATE ===' AS info;
SELECT h.hall_id,
       h.name,
       h.door_count,
       COUNT(d.door_id) AS actual_doors
FROM hall h
LEFT JOIN door d ON d.hall_id = h.hall_id
GROUP BY h.hall_id, h.name, h.door_count
ORDER BY h.hall_id;
 
SELECT '=== TEST INSERT: add door to hall 1 ===' AS info;
INSERT INTO door (size, state, hall_id) VALUES ('малые', 'open', 1);
SELECT hall_id, name, door_count FROM hall ORDER BY hall_id;
 
SELECT '=== TEST UPDATE: move door from hall 1 to hall 2 ===' AS info;
UPDATE door SET hall_id = 2 WHERE door_id = (SELECT MAX(door_id) FROM door);
SELECT hall_id, name, door_count FROM hall ORDER BY hall_id;
 
SELECT '=== TEST DELETE: remove the door ===' AS info;
DELETE FROM door WHERE door_id = (SELECT MAX(door_id) FROM door);
SELECT hall_id, name, door_count FROM hall ORDER BY hall_id;
 
SELECT '=== FINAL CONSISTENCY CHECK ===' AS info;
SELECT h.hall_id,
       h.name,
       h.door_count                    AS stored_count,
       COUNT(d.door_id)                AS actual_count,
       h.door_count = COUNT(d.door_id) AS is_correct
FROM hall h
LEFT JOIN door d ON d.hall_id = h.hall_id
GROUP BY h.hall_id, h.name, h.door_count
ORDER BY h.hall_id;