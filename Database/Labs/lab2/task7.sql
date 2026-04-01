SELECT
    л.ИД,
    л.ФАМИЛИЯ,
    л.ИМЯ,
    л.ОТЧЕСТВО
FROM Н_ЛЮДИ л
WHERE NOT EXISTS (
    SELECT 1
    FROM Н_УЧЕНИКИ у
    WHERE у.ЧЛВК_ИД = л.ИД
)
ORDER BY л.ФАМИЛИЯ, л.ИМЯ;