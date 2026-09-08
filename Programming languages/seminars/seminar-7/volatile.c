/* volatile.c */

#include <stdio.h>

void print_int(int x) { printf("%d", x); }

int main() {

  int x = 42;
  volatile int y = 42;

  print_int(x);
  print_int(y);
}

