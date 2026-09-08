; hello_mmap.asm
%define O_RDONLY 0 
%define PROT_READ 0x1
%define MAP_PRIVATE 0x2
%define SYS_WRITE 1
%define SYS_OPEN 2
%define SYS_CLOSE 3
%define SYS_FSTAT 5
%define SYS_MMAP 9
%define SYS_MUNMAP 11
%define SYS_EXIT 60
%define FD_STDOUT 1

section .data
    ; This is the file name. You are free to change it.
    fname: db 'hello.txt', 0

section .text
global _start

; use exit system call to shut down correctly
exit:
    mov  rax, SYS_EXIT
    xor  rdi, rdi
    syscall

; These functions are used to print a null terminated string
; rdi holds a string pointer
print_string:
    push rdi
    call string_length
    pop  rsi
    mov  rdx, rax 
    mov  rax, SYS_WRITE
    mov  rdi, FD_STDOUT
    syscall
    ret

string_length:
    xor  rax, rax
.loop:
    cmp  byte [rdi+rax], 0
    je   .end 
    inc  rax
    jmp .loop 
.end:
    ret

; This function is used to print a substring with given length
; rdi holds a string pointer
; rsi holds a substring length
print_substring:
    mov  rdx, rsi 
    mov  rsi, rdi
    mov  rax, SYS_WRITE
    mov  rdi, FD_STDOUT
    syscall
    ret

_start:
    ; Вызовите open и откройте fname в режиме read only.
    mov rax, SYS_OPEN
    mov rdi, fname
    mov rsi, O_RDONLY ; Open file read only
    mov rdx, 0 ; We are not creating a file
    ; so this argument has no meaning
    syscall
    ; rax holds the opened file descriptor now
    push rax

    ; allocate 144 bytes in the stack
    ; call fsat with file descriptor
    ; read from [rsp + 48] the file size (may use rsi directly here)
    ; deallocate 144 bytes
    ; save file size to stack

    ; Вызовите mmap c правильными аргументами
    mov r8, rax ; read fd from stack [rsp + 8]
    mov rax, SYS_MMAP
    mov rdi, 0
    mov rsi, 4096 ; read file size from stack or from some register
    mov rdx, PROT_READ
    mov r10, MAP_PRIVATE
    mov r9, 0
    syscall

    push rax

    ; с помощью print_string теперь можно вывести его содержимое
    mov rdi, rax
    mov rsi, 4096; read file size from stack, e.g. from [rsp + 8]
    ; think about if the stack is aligned now?
    call print_substring

    ; теперь можно освободить память с помощью munmap
    mov rax, SYS_MUNMAP
    pop rdi
    mov rsi, 4096 ; read file size (better just pop it, it's the second one in the stack)
    syscall

    ; закрыть файл используя close
    mov rax, SYS_CLOSE
    pop rdi
    syscall

    ; и выйти
    call exit
