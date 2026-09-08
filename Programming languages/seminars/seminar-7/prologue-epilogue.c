/* prologue-epilogue.c */

int maximum(int a, int b) {
  char buffer[4096];
  buffer[0] = 0x7;
  if (a < b)
    return b;
  return a;
}

int main(void) {
  int x = maximum(42, 999);
  return 0;
}

