.data  
A:  .word   1, 2, 3, 4, 5, 6, 7, 8, 9, 10   # Array of numbers 
n:  .word   10                              # Number of elements in the array

.text   

main:
    la      $a0,    A                       # Load the base address of array A into $a0
    la      $a1,    n                       # Load the address of n into $a1
    lw      $a1,    0($a1)                  # Load the value of n into $a1
    li      $t2,    0                       # Initialize $t2 to 0 (loop counter)
    li      $t3,    0                       # Initialize $t3 to 0 (sum of array elements)

loop:
    beq     $t2,    $a1,    exit            # If $t2 equals $a1, exit the loop
    lw      $t4,    0($a0)                  # Load the value at the address stored in $a0 into $t4
    add     $t3,    $t3,    $t4             # Add the value in $t4 to the sum in $t3
    addi    $a0,    $a0,    4               # Increment the address in $a0 by 4 (to point to the next element)
    addi    $t2,    $t2,    1               # Increment the loop counter $t2 by 1
    j       loop                            # Jump back to the beginning of the loop

exit:
    li      $v0,    10                      # Set $v0 to 10 (exit syscall)
    syscall                                 # Exit the program
