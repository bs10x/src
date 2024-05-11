    #       
    # Sum of Absolute Differences Algorithm
    #       
    # Authors:
    #	Basil Feitknecht, Camil Schmid
    #       

.data   

nums:   .word   9
A:      .word   5, 16, 7, 1, 1, 13, 2, 8, 10
B:      .word   4, 15, 8, 0, 2, 12, 3, 7, 11
SAD:    .space  36

.text   

main:   

    la      $s0,            A                   # Load the address of A into $s0
    li      $s1,            0                   # $s1 = i = 0
    sw      $s2,            nums                # $s2 = nums
    la      $s3,            SAD                 # Load the address of SAD into $s3


    # Loop over the elements of left_image and right_image
    li      $s1,            0                   # $s1 = i = 0
    move    $s2,            size                # $s2 = size
    j       loop


loop:   
    bgt     $s1,            $s2,        break   # If i == image_size, jump to break

    lw      $a0,            0($s0)              # Load left_image[i] into $a0
    lw      $a1,            36($s0)             # Load right_image[i] into $a1
    jal     abs_diff                            # Call abs_diff
    sw      $v0,            72($s0)             # Store the result of abs_diff in sad_array[i]

    addi    $s1,            $s1,        1       # i++
    addi    $s0,            $s0,        4       # Move to the next element in the array
    j       loop                                # repeat loop


break:  
    # recursive_sum(sad_array, 9)
    move    $s0,            $0                  # $s0 = 0, move back to the beginning of the array
    lw      $a0,            72($s0)             # Load the base address of sad_array into $a0
    addi    $a1,            $0,         9       # Load the size of the array into $a1
    jal     recursive_sum                       # call recursive_sum

    sw      $v0,            0($t2)              # Store the returned value in $t2

    j       END


END:    
    j       END                                 # Infinite loop at the end of the program.



    # Absolute difference between two integers
abs_diff:
    sub     $t1,            $a0,        $a1     # Subtract second integer from first integer
    sra     $t2,            $t1,        31      # Arithmetic right shift to get sign of the difference
    xor     $t1,            $t1,        $t2     # XOR to flip the sign if necessary
    sub     $v0,            $t1,        $t2     # Subtract the flipped sign from the difference to get absolute difference
    jr      $ra                                 # Return to the calling function

    # Recursive sum of an array of integers
recursive_sum:
    addi    $sp,            $sp,        -8      # Adjust sp
    addi    $t0,            $a1,        -1      # Compute size - 1
    sw      $t0,            0($sp)              # Save size - 1 to stack
    sw      $ra,            4($sp)              # Save return address
    bne     $a1,            $zero,      else    # size == 0 ?
    addi    $v0,            $0,         0       # If size == 0, set return value to 0
    addi    $sp,            $sp,        8       # Adjust sp
    jr      $ra                                 # Return

else:   
    add     $a1,            $t0,        $0      # update the second argument
    jal     recursive_sum
    lw      $t0,            0($sp)              # Restore size - 1 from stack
    sll     $t1,            $t0,        2       # Multiply size by 4
    add     $t1,            $t1,        $a0     # Compute & arr[ size - 1 ]
    lw      $t2,            0($t1)              # t2 = arr[ size - 1 ]
    add     $v0,            $v0,        $t2     # retval = $v0 + arr[size - 1]
    lw      $ra,            4($sp)              # restore return address from stack
    addi    $sp,            $sp,        8       # Adjust sp
    jr      $ra                                 # Return



main:   
    # Initialize $t0 with the number of elements in the array
    li      $t0,            9

    # Initialize $t1 with 0 to hold the sum
    li      $t1,            0

    # Load the address of the first element of the array into $s0
    la      $s0,            A

sum_loop:
    # Load the current element of the array into $t2
    lw      $t2,            0($s0)

    # Add the current element to the sum
    add     $t1,            $t1,        $t2

    # Move to the next element of the array
    addiu   $s0,            $s0,        4

    # Decrement the number of elements
    addi    $t0,            $t0,        -1

    # If the number of elements is not zero, repeat the loop
    bnez    $t0,            sum_loop

    # Store the sum in memory
    sw      $t1,            sum




sum:    
    # $a0 = base address of the array
    # $a1 = size of the array

    lw      $t0,            0($s0)
    add     $t1,            $t1,        $t0
    addi    $s0,            4


    jr      $ra                                 # Return to the calling function

