    #       
    # Sum of Absolute Differences Algorithm
    #       
    # Authors:
    #	Basil Feitknecht, Camil Schmid
    #       
.data
L: .word 1, 2, 3
R: .word 4, 0, 4


.text
main:
   

    # Loop over the elements of left_image and right_image
    move    $s0,        $0                      # $s0 = 0, move back to the beginning of the array
    addi    $s1,        $0,         0           # $s1 = i = 0
    addi    $s2,        $0,         9           # $s2 = image_size = 9
    j       loop


loop:
    beq     $s1,        $s2,        break       # If i == image_size, jump to break

    lw      $a0,        0($s0)                  # Load left_image[i] into $a0
    lw      $a1,        36($s0)                 # Load right_image[i] into $a1
    jal     abs_diff                            # Call abs_diff
    sw      $v0,        72($s0)                 # Store the result of abs_diff in sad_array[i]

    addi    $s1,        $s1,        1           # i++
    addi    $s0,        $s0,        4           # Move to the next element in the array
    j       loop                                # repeat loop


break:
    # recursive_sum(sad_array, 9)
    li      $a0,        72                      # Load the base address of sad_array into $a0
    li      $a1,        9                       # Load the size of the array into $a1
    jal     sum                                 # call recursive_sum
    move    $t2,        $v0						# move returned value to $t2

    j       end									# enter infinite loop


end:
    j       end                                 # Infinite loop at the end of the program.



    # Absolute difference between two integers
abs_diff:
    sub     $t1,        $a0,        $a1         # Subtract second integer from first integer
    sra     $t2,        $t1,        31          # Arithmetic right shift to get sign of the difference
    xor     $t1,        $t1,        $t2         # XOR to flip the sign if necessary
    sub     $v0,        $t1,        $t2         # Subtract the flipped sign from the difference to get absolute difference
    jr      $ra                                 # Return to the calling function

