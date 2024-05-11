

.data

A: .word 1, 2, 3, 4, 5, 6, 7, 8, 9, 10
n: .word 10

.text

main:
    la $t0, A
    la $t1, n
    lw $t1, 0($t1)
    li $t2, 0
    li $t3, 0

loop:
    beq $t2, $t1, exit
    lw $t4, 0($t0)
    add $t3, $t3, $t4
    addi $t0, $t0, 4
    addi $t2, $t2, 1
    j loop

exit:
    li $v0, 10
    syscall
