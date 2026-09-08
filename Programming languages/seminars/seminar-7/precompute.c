/* precompute.c */

#include <stdio.h>
#include <string.h>

int main (){
  char buff[1024] = {0};
  strcat(buff, "hello");
  strcat(buff, " world");
  printf("%s", buff);
 
}

