#!/usr/bin/env bash
mkdir lab0
cd lab0
# 1. Создать приведенное в варианте дерево каталогов и файлов с содержимым. В качестве корня дерева использовать каталог lab0 своего домашнего каталога. Для создания и навигации по дереву использовать команды: mkdir, echo, cat, touch, ls, pwd, cd, more, cp, rm, rmdir, mv.
echo "Task#1"
# Часть № 1
mkdir -p jigglypuff3/cleffa
mkdir -p jigglypuff3/gyarados
mkdir -p loudred3/magmortar
mkdir -p loudred3/drifloon
mkdir -p loudred3/burmy
mkdir -p zorua9/mandibuzz
mkdir -p zorua9/palpitoad
mkdir -p zorua9/flaaffy
touch excadrill4
touch froslass7
touch treecko2
touch jigglypuff3/beedrill
touch loudred3/sandile
touch loudred3/leafeon
touch loudred3/ekans
touch zorua9/lotad
touch zorua9/seaking
touch zorua9/slugma
# Часть № 2
echo "Содержимое Файлов"
echo "тип диеты Herbivore" > excadrill4
echo -e "Спосоьности Freezing\nPoint Haunt Snow Cloak Magic Guard">froslass7
echo -e "тип покемона BUG\nPOISON" > jigglypuff3/beedrill
echo "Живет Beach Desert" > loudred3/sandile
echo -e "тип покемона GRASS\nNONE" > loudred3/leafeon
echo "Живет Grassland Marsh" > loudred3/ekans
echo -e "тип покемона GRASS\nNONE" > treecko2
echo -e "Спосоьности Torrent Overgrow Swift Swim Rain\nDish" > zorua9/lotad
echo "тип покемона WATER NONE" > zorua9/seaking
echo "satk=7 sdef=4 spd=2" > zorua9/slugma
echo "Task1 completed ✅"
#2. Установить согласно заданию права на файлы и каталоги при помощи команды chmod, используя различные способы указания прав.
echo "Task#2"
# 2.1 | excadrill4: права 400
chmod 400 excadrill4
# 2.2 | froslass7: владелец должен не иметь никаких прав; группа-владелец должна читать и записывать файл; остальные пользователи должны записывать файл
chmod 062 froslass7
# 2.3 | jigglypuff3: права 751
chmod 751 jigglypuff3
# 2.4 | cleffa: владелец должен записывать директорию и переходить в нее; группа-владелец должна только переходить в директорию; остальные пользователи должны только переходить в директорию
chmod 311 jigglypuff3/cleffa
# 2.5 | gyarados: права 750
chmod 750 jigglypuff3/gyarados
# 2.6 | beedrill: владелец должен читать файл; группа-владелец должна не иметь никаких прав; остальные пользователи должны не иметь никаких прав
chmod 400 jigglypuff3/beedrill
# 2.7 | loudred3: rwx-wx-wx
chmod 733 loudred3 # rwx -wx -wx
# 2.8 | sandile: rw-r-----
chmod 640 loudred3/sandile # rw- r-- ---
# 2.9 | leafeon: rw--w----
chmod 620 loudred3/leafeon # rw- -w- ---
# 2.10| magmortar: владелец должен читать, записывать директорию и переходить в нее; группа-владелец должна записывать директорию и переходить в нее; остальные пользователи должны читать и записывать директорию
chmod 736 loudred3/magmortar
# 2.11| ekans: rw--w-r--
chmod 624 loudred3/ekans # rw- -w- r--
# 2.12| drifloon: владелец должен читать, записывать директорию и переходить в нее; группа-владелец должна читать и записывать директорию; остальные пользователи должны читать директорию
chmod 764 loudred3/drifloon
# 2.13| burmy: rwxrw-r--
chmod 764 loudred3/burmy # rwx rw- r--
# 2.14| treecko2: права 046
chmod 046 treecko2
# 2.15| zorua9: владелец должен записывать директорию и переходить в нее; группа-владелец должна только переходить в директорию; остальные пользователи должны только переходить в директорию
chmod 311 zorua9
# 2.16| mandibuzz: права 337
chmod 337 zorua9/mandibuzz
# 2.17| palpitoad: rwx-wxrwx
chmod 737 zorua9/palpitoad # rwx -wx rwx
# 2.18| lotad: rw--w-r--
chmod 624 zorua9/lotad # rw- -w- -w- 624
# 2.19| seaking: rw--w--w- 622
chmod 622 zorua9/seaking 
# 2.20| flaaffy: rwxr-x-w- 752
chmod 752 zorua9/flaaffy 
# 2.21| slugma: r-------- | read permission only slugma
chmod 400 zorua9/slugma
echo "Task2 completed ✅"
#3. Скопировать часть дерева и создать ссылки внутри дерева согласно заданию при помощи команд cp и ln, а также комманды cat и перенаправления ввода-вывода.
echo "Task#3"
# 3.1 | объеденить содержимое файлов lab0/zorua9/seaking, lab0/jigglypuff3/beedrill, в новый файл lab0/excadrill4_37
cat zorua9/seaking jigglypuff3/beedrill > excadrill4_37
# 3.2 | скопировать содержимое файла excadrill4 в новый файл lab0/loudred3/sandileexcadrill
cp excadrill4 loudred3/sandileexcadrill 
# 3.3 | cоздать жесткую ссылку для файла treecko2 с именем lab0/loudred3/ekanstreecko
chmod u+rwx loudred3
chmod u+rwx,g+rwx,o+rwx treecko2
cp treecko2 loudred3/ekanstreecko
chmod 046 treecko2 
# 3.4 | создать символическую ссылку c именем Copy_34 на директорию jigglypuff3 в каталоге lab0
ln -s jigglypuff3 Copy_34
# 3.5 | cоздать символическую ссылку для файла froslass7 с именем lab0/loudred3/ekansfroslass
chmod u+rwx,g+rwx,o+rwx loudred3
chmod u+rwx,g+rwx,o+rwx froslass7
ln -sf "$(pwd)/froslass7" loudred3/ekansfroslass
chmod 733 loudred3    # restore the original permissions
# 3.6 | скопировать рекурсивно директорию loudred3 в директорию lab0/jigglypuff3/gyarados
cp -r loudred3 jigglypuff3/gyarados
# 3.7 | скопировать файл treecko2 в директорию lab0/zorua9/flaaffy
chmod 646 treecko2
cp treecko2 zorua9/flaaffy
chmod 046 treecko2
echo "Task3 completed ✅"
# 4. Используя команды cat, wc, ls, head, tail, echo, sort, grep выполнить в соответствии с вариантом задания поиск и фильтрацию файлов, каталогов и содержащихся в них данных.
echo "Task#4"
chmod u+rx jigglypuff3/cleffa zorua9 zorua9/mandibuzz zorua9/palpitoad zorua9/flaaffy 2>/dev/null
# 4.1 | Подсчитать количество символов содержимого файлов: sandile, leafeon, ekans, отсортировать вывод по увеличению количества, подавить вывод ошибок доступа
wc -m sandile leafeon ekans 2>/dev/null | sort -n
# 4.2 | Вывести список имен и атрибутов файлов в директории zorua9, список отсортировать по убыванию даты доступа к файлу, подавить вывод ошибок доступа 
ls -lu --time=atime zorua9 2>/dev/null | sort -r -k6,7
# 4.3 | Рекурсивно вывести содержимое файлов с номерами строк из директории lab0, имя которых заканчивается на 'a', строки отсортировать по имени z->a, добавить вывод ошибок доступа в стандартный поток вывода
find . -type f -name "*a" -exec cat -n {} + 2>&1 | sort -r
# 4.4 | Вывести список имен файлов в директории jigglypuff3, список отсортировать по имени a->z, ошибки доступа не подавлять и не перенаправлять
ls jigglypuff3 | sort
# 4.5 | Рекурсивно вывести содержимое файлов с номерами строк из директории lab0, имя которых начинается на 's', строки отсортировать по имени a->z, ошибки доступа не подавлять и не перенаправлять
find . -type f -name "s*" -exec cat -n {} + | sort
# 4.6 | Вывести рекурсивно список имен и атрибутов файлов в директории lab0, заканчивающихся на символ 'a', список отсортировать по возрастанию размера, подавить вывод ошибок доступа
find . -type f -name "*a" -ls 2>/dev/null | sort -n -k7
# Restore original perms
chmod 311 jigglypuff3/cleffa
chmod 311 zorua9
chmod 337 zorua9/mandibuzz
chmod 737 zorua9/palpitoad
chmod 737 zorua9/flaaffy
echo "Task4 completed ✅"
# 5. Выполнить удаление файлов и каталогов при помощи команд rm и rmdir согласно варианту задания.
# 5.1 | Удалить файл froslass7
rm -f froslass7
# 5.2 | Удалить файл lab0/zorua9/slugma
rm -f zorua9/slugma
# 5.3 | удалить символические ссылки lab0/loudred3/ekansfrosla*
rm -f lab0/loudred3/ekansfrosla*
# 5.4 | удалить жесткие ссылки lab0/loudred3/ekanstreec*
rm -f loudred3/ekanstreec
# 5.5 | Удалить директорию loudred3
rm -rf loudred3
# 5.6 | Удалить директорию lab0/loudred3/magmortar
[ -d loudred3/magmortar ] && rmdir loudred3/magmortar
echo "Task5 completed ✅"
# Ураааааааааааааааааааааааааааааааааааааааа
