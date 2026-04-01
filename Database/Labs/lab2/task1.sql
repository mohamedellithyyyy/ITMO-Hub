SELECT 
    t.ИД,
    v.ДАТА
FROM Н_ТИПЫ_ВЕДОМОСТЕЙ t
INNER JOIN Н_ВЕДОМОСТИ v 
    ON t.ИД = v.ТВ_ИД
WHERE  
    t.НАИМЕНОВАНИЕ = 'Перезачет'
    AND v.ДАТА::date = '2010-06-18';