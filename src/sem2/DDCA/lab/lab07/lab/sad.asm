.data   

n:      .word   9
A:      .word   5, 16, 7, 1, 1, 13, 2, 8, 10
B:      .word   4, 15, 8, 0, 2, 12, 3, 7, 11

.text   

main:   

    la      $s0,            A                       # Load the address of A into $s0
    li      $s1,            0                       # $s1 = i = 0
    sw      $s2,            n                       # $s2 = n
    la      $s3,            SAD                     # Load the address of SAD into $s3


    # Loop over the elements of left_image and right_image
    li      $s1,            0                       # $s1 = i = 0
    move    $s2,            size                    # $s2 = size
    j       loop


loop:   
    bgt     $s1,            $s2,        break       # If i == image_size, jump to break

    lw      $a0,            0($s0)                  # Load left_image[i] into $a0
    lw      $a1,            36($s0)                 # Load right_image[i] into $a1
    jal     abs_diff                                # Call abs_diff
    sw      $v0,            72($s0)                 # Store the result of abs_diff in sad_array[i]

    addi    $s1,            $s1,        1           # i++
    addi    $s0,            $s0,        4           # Move to the next element in the array
    j       loop                                    # repeat loop


break:  
    # recursive_sum(sad_array, 9)
    move    $s0,            $0                      # $s0 = 0, move back to the beginning of the array
    lw      $a0,            72($s0)                 # Load the base address of sad_array into $a0
    addi    $a1,            $0,         9           # Load the size of the array into $a1
    jal     recursive_sum                           # call recursive_sum

    sw      $v0,            0($t2)                  # Store the returned value in $t2

    j       END


END:    
    j       END                                     # Infinite loop at the end of the program.



    # Absolute difference between two integers
abs_diff:
    sub     $t1,            $a0,        $a1         # Subtract second integer from first integer
    sra     $t2,            $t1,        31          # Arithmetic right shift to get sign of the difference
    xor     $t1,            $t1,        $t2         # XOR to flip the sign if necessary
    sub     $v0,            $t1,        $t2         # Subtract the flipped sign from the difference to get absolute difference
    jr      $ra                                     # Return to the calling function

    # MIPS assembly code for summing elements of an integer array

    # Registers:
    # $a0: pointer to the array A
    # $a1: n, the number of elements in the array
    # $t0: loop counter
    # $t1: temporary variable for storing the sum
    # $t2: pointer to the current element of the array

sum:    
    # Initialize sum to 0
    li      $t1,            0                       # $t1 = 0

    # Loop through the array
    li      $t0,            0                       # $t0 = 0 (loop counter)
loop_start:
    # Check if we have reached the end of the array
    bge     $t0,            $a1,        loop_end    # If $t0 >= $a1, exit loop

    # Load the current element of the array into $t2
    mul     $t2,            $t0,        4           # $t2 = $t0 * 4 (each integer is 4 bytes)
    add     $t2,            $t2,        $a0         # $t2 = address of A[i]
    lw      $t2,            0($t2)                  # $t2 = A[i]

    # Add the current element to the sum
    add     $t1,            $t1,        $t2         # $t1 = $t1 + A[i]

    # Increment the loop counter
    addi    $t0,            $t0,        1           # $t0++

    # Repeat the loop
    j       loop_start

    # End of loop
loop_end:

    # Return the sum
    move    $v0,            $t1                     # Return value in $v0
    jr      $ra                                     # Return

