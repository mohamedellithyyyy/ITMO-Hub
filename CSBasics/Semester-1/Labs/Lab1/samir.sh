#!/bin/bash

mkdir lab0
cd lab0
mkdir 
chmod 550 cubone4
chmod 031 blitzle        # -wx--x-w-
chmod 444 gardevoir      # r-----r--
chmod 404 chandelure

chmod 731 shinx          # u=rwx g=rx o=wx
chmod 731 seadra         # u=wx g=rwx o=wx → 7 3 1
chmod 741 klinklang      # u=rwx g=rx o=w → 7 4 1

chmod 440 drifloon7
chmod 600 drowzee3
chmod 664 machoke3
chmod 515 nidoranM2      # r-x --x -wx → 5 1 5

chmod 400 bisharp
chmod 355 sudowoodo

chmod 002 swoobat        # -- -- rw → 0 0 2

chmod 763 makuhita       # rwx -wx rw- → 7 3 6 (o=6)

chmod 357 sewaddle
chmod 440 excadrill

chmod 731 porygon29      # rwx rx wx → 7 3 1

chmod 444 rhyhorn        # r-----r-- but must be file → 4 0 4 or 4 4 4? Given pattern: owner r, group ---, others r → 4 0 4 → used earlier
chmod 404 rhyhorn

chmod 733 grumpig        # u=rwx g=wx o=wx → 7 3 3

chmod 331 fearow         # u=wx g=wx o=rx → 3 3 1

chmod 404 magikarp       # u=r g=--- o=r → 4 0 4

