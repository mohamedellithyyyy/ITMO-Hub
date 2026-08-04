ORG 0x2EC

START:
    CLA
    ST $R_RES

; =========================
; R = f(S-1)
; =========================
    LD $S
    DEC
    PUSH
    CALL $SUBR
    POP
    ADD $R_RES
    ST $R_RES

; =========================
; + f(Z-1)
; =========================
    LD $Z
    DEC
    PUSH
    CALL $SUBR
    POP
    ADD $R_RES
    ST $R_RES

; =========================
; + (f(X) - 1)
; =========================
    LD $X
    PUSH
    CALL $SUBR
    POP
    DEC
    ADD $R_RES
    ST $R_RES

; =========================
; + (f(W+1) - 1)
; =========================
    LD $W
    INC
    PUSH
    CALL $SUBR
    POP
    DEC
    ADD $R_RES
    ST $R_RES

; =========================
; + (f(U) - 1)
; =========================
    LD $U
    PUSH
    CALL $SUBR
    POP
    DEC
    ADD $R_RES
    ST $R_RES

; =========================
; + f(Y-1)
; =========================
    LD $Y
    DEC
    PUSH
    CALL $SUBR
    POP
    ADD $R_RES
    ST $R_RES

; =========================
; + (f(Rvar) - 1)
; =========================
    LD $R_VAR
    PUSH
    CALL $SUBR
    POP
    DEC
    ADD $R_RES
    ST $R_RES

; =========================
; + (f(T-1) + 1)
; =========================
    LD $T
    DEC
    PUSH
    CALL $SUBR
    POP
    INC
    ADD $R_RES
    ST $R_RES

; =========================
; + (f(Q-1) - 1)
; =========================
    LD $Q
    DEC
    PUSH
    CALL $SUBR
    POP
    DEC
    ADD $R_RES
    ST $R_RES

; =========================
; + (f(V-1) - 1)
; =========================
    LD $V
    DEC
    PUSH
    CALL $SUBR
    POP
    DEC
    ADD $R_RES
    ST $R_RES

; =========================
; - (f(P-1) - 1)
; =========================
    LD $P
    DEC
    PUSH
    CALL $SUBR
    POP
    DEC
    NEG
    ADD $R_RES
    ST $R_RES

    HLT


; =========================
; FUNCTION f(x)
; =========================
ORG 0x6D9

SUBR:
    LD (SP+1)
    BMI LOAD_A
    CMP $A
    BGE LOAD_A

    ASL
    SUB $B
    ST (SP+1)
    RET

LOAD_A:
    LD $A
    ST (SP+1)
    RET


; =========================
; CONSTANTS
; =========================
A: WORD 0x05D6
B: WORD 0x003B


; =========================
; DATA
; =========================
ORG 0x0349

Z: WORD 0x0159
Y: WORD 0xFF16
X: WORD 0x007B
W: WORD 0x0000
V: WORD 0xFFC9
U: WORD 0x0001
T: WORD 0x0377
S: WORD 0xFE44
R_VAR: WORD 0x0060
Q: WORD 0xFBA9
P: WORD 0x0236
   WORD 0x0000

R_RES: WORD 0x0000
