SELECT
    у.ГРУППА AS "Группа",
    ROUND(AVG(DATE_PART('year', AGE(л.ДАТА_РОЖДЕНИЯ)))::numeric, 2) AS "Средний возраст"
FROM Н_УЧЕНИКИ у
JOIN Н_ЛЮДИ л ON л.ИД = у.ЧЛВК_ИД
WHERE л.ДАТА_РОЖДЕНИЯ IS NOT NULL
GROUP BY у.ГРУППА
HAVING AVG(DATE_PART('year', AGE(л.ДАТА_РОЖДЕНИЯ))) >
       (
           SELECT MAX(DATE_PART('year', AGE(л2.ДАТА_РОЖДЕНИЯ)))
           FROM Н_УЧЕНИКИ у2
           JOIN Н_ЛЮДИ л2 ON л2.ИД = у2.ЧЛВК_ИД
           WHERE у2.ГРУППА = '1101'
             AND л2.ДАТА_РОЖДЕНИЯ IS NOT NULL
       )
ORDER BY "Средний возраст" DESC;