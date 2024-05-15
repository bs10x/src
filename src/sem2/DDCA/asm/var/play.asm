.macro ggasdj %x

.end_macro

.data   
prompt: .asciiz "Enter a number: "
ln:     .asciiz "\n"
f0:     .word   0
f1:     .word   1

.text   
main:   
    li      $v0,    4
    la      $a0,    prompt
    syscall                         # print the prompt

    li      $v0,    5
    syscall                         # read n
    move    $a0,    $v0             # $a0 = n

    li      $s0,    0               # $s0 = i = 0
    j       fib

    # print the first two Fibonacci numbers
    lw      $a0,    fib0
    li      $v0,    1
    syscall 

    lw      $a0,    fib1
    li      $v0,    1
    syscall 

    sub     $t0,    $t0,    1       # subtract 1 to account for the first Fibonacci number

fib:    
    lw      $t1,    fib0
    lw      $t2,    fib1

    add     $t3,    $t1,    $t2

    # print the next Fibonacci number
    move    $a0,    $t3
    li      $v0,    1
    syscall 

    # update fib0 and fib1
    sw      $t2,    fib0
    sw      $t3,    fib1

    # decrement the input number
    sub     $t0,    $t0,    1

    # if the input number is zero, exit the loop
    beq     $t0,    $zero,  exit

    j       fib

exit:   
    li      $v0,    10
    syscall                         # exit the program
