/* fork-example-1.c */

#include <stdio.h>
#include <stdlib.h>
#include <sys/mman.h>
#include <string.h>
#include <unistd.h>

void* create_shared_memory(size_t size) {
  return mmap(NULL,
              size,
              PROT_READ | PROT_WRITE,
              MAP_SHARED | MAP_ANONYMOUS,
              -1, 0);
}


int main() {
  void* shmem = create_shared_memory(128);

  printf("Shared memory at: %p\n" , shmem);
  int pid = fork();

  if (pid == 0) {
    while (1);
  } else {
    printf("Child's pid is: %d\n", pid);
    while(1);
  }
}
