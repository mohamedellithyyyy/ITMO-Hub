#!/bin/bash
mkdir lab0
cd lab0
# 1. Создать приведенное в варианте дерево каталогов и файлов с содержимым. В качестве корня дерева использовать каталог lab0 своего домашнего каталога. Для создания и навигации по дереву использовать команды: mkdir, echo, cat, touch, ls, pwd, cd, more, cp, rm, rmdir, mv.


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
touch zorua9/slumga

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
echo "satk=7 sdef=4 spd=2" > zorua9/slumga
echo "text" > test

#2. Установить согласно заданию права на файлы и каталоги при помощи команды chmod, используя различные способы указания прав.

chmod 400 excadrill4
chmod 062 froslass7
chmod 751 jigglypuff3
chmod 311 jigglypuff3/cleffa
chmod 750 jigglypuff3/gyarados
chmod 400 jigglypuff3/beedrill
chmod 733 loudred3 # rwx -wx -wx
chmod 640 loudred3/sandile # rw- r-- ---
chmod 620 loudred3/leafeon # rw- -w- ---
chmod 736 loudred3/magmortar
chmod 624 loudred3/ekans # rw- -w- r--
chmod 764 loudred3/drifloon
chmod 764 loudred3/burmy # rwx rw- r--
chmod 046 treecko2
chmod 311 zorua9
chmod 337 zorua9/mandibuzz
chmod 737 zorua9/palpitoad # rwx -wx rwx
chmod 622 zorua9/lotad # rw- -w- -w- 622
chmod 752 zorua9/seaking # rwx r-x -w- 752
chmod 400 zorua9/slumga # read permission only


#3. Скопировать часть дерева и создать ссылки внутри дерева согласно заданию при помощи команд cp и ln, а также комманды cat и перенаправления ввода-вывода.

cat zorua9/seaking jigglypuff3/beedrill 
cp excadrill4 loudred3/sandileexcadrill 
chmod 746 treecko2
ln treecko2 loudred3/ekanstreecko
ln -s Copy_34 jigglypuff3
ln -s froslass7 loudred3/ekansfroslass # Here i have a bug 
cp -r loudred3 jigglypuff3/gyarados
chmod 646 treecko2
cp treecko2 zorua9/flaaffy

# 4. Используя команды cat, wc, ls, head, tail, echo, sort, grep выполнить в соответствии с вариантом задания поиск и фильтрацию файлов, каталогов и содержащихся в них данных.

# 5. Выполнить удаление файлов и каталогов при помощи команд rm и rmdir согласно варианту задания.

# rm froslass7 
# rm /zorua9/slugma
# # удалить символические ссылки lab0/loudred3/ekansfrosla*
# rm loudred3/ekanstreec
# rm -r loudred3
# rmdir /loudred3/magmortar
