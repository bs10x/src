# Hello, World!, in MIPS32 assembly
#       
#       
#       
#       

.data                                   # data declaration section
hw: .asciiz "\nHello, World!\n"         # string to be printed

.text                                   # code section
main:                                   # start of main function
    li      $v0,    4                   # syscall code to print string
    la      $a0,    hw                  # load string's address to $a0
    syscall                             # perform operation in $v0
    li      $v0,    10                  # terminate program
    syscall                             # syscall takes its arguments from $a0, $a1, $a2
