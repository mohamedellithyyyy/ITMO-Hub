        ORG     0x000
V0:     WORD    $default, 0x180
V1:     WORD    $int1,    0x180     ; вектор ВУ-1
V2:     WORD    $int2,    0x180     ; вектор ВУ-2
V3:     WORD    $default, 0x180
V4:     WORD    $default, 0x180
V5:     WORD    $default, 0x180
V6:     WORD    $default, 0x180
V7:     WORD    $default, 0x180

        ORG     0x020
X:      WORD    ?
max:    WORD    0x0028              ; 40, максимум ОДЗ
min:    WORD    0xFFD3              ; -45, минимум ОДЗ

default:
        IRET                        ; необрабатываемые прерывания

START:  DI
        CLA
        OUT     0x1                 ; запрет прерываний для неиспользуемых ВУ
        OUT     0x7
        LD      #0x9                ; MR: IE=1, вектор 1
        OUT     3
        LD      #0xA                ; MR: IE=1, вектор 2
        OUT     5
        EI

main:   DI                          ; атомарность на время изменения X
        LD      X
        ADD     #3
        CMP     min
        BGE     chk1_max
        JUMP    chk1_ldmin
chk1_max:
        CMP     max
        BLT     chk1_store
        BEQ     chk1_store
chk1_ldmin:
        LD      min
chk1_store:
        ST      X
        EI
        JUMP    main

int1:   DI                          ; ВУ-1: вывод F(X) = -3X-8
        PUSH
        LD      X
        ADD     X                   ; 2X
        ADD     X                   ; 3X
        NEG                         ; -3X
        SUB     #8                  ; -3X-8
        OUT     2
        POP
        EI
        IRET

int2:   DI                          ; ВУ-2: X := X | РД(ВУ-2)
        PUSH
        CLA
        IN      4
        OR      X
        CMP     min
        BGE     chk2_max
        JUMP    chk2_ldmin
chk2_max:
        CMP     max
        BLT     chk2_store
        BEQ     chk2_store
chk2_ldmin:
        LD      min
chk2_store:
        ST      X
        POP
        EI
        IRET