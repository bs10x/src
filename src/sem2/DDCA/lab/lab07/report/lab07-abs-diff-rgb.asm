    #       
    # Sum of Absolute Differences Algorithm
    #       
    # Authors:
    #	Basil Feitknecht, Camil Schmid
    #       

.text

main:
    li      $s0,            0           # Initialize the counter
    li      $t0,            1           # R1
    li      $t1,            2           # R2
    li      $t2,            3           # G1
    li      $t3,            4           # G2
    li      $t4,            0           # B1
    li      $t5,            4           # B2

    # Prepare the function arguments
    move    $a0,            $t0
    move    $a1,            $t1
    move    $a2,            $t2
    move    $a3,            $t3

    sub     $sp,            $sp,    8   # Reserve space on the stack
    sw      $t4,            0($sp)      # Save $t4 on the stack
    sw      $t5,            4($sp)      # Save $t5 on the stack

    jal     abs_diff_color
    move    $t9,            $v0         # Save the result in $t9
    j       end                         # Jump to the end of the program


    # Absolute difference between three channels
abs_diff_color:
    jal     abs_diff                    # abs_diff(R1, R2)
    add     $t0,            $t0,    $0 # Add the result to $t0

    move    $a0,            $a2         # Prepare the function arguments
    move    $a1,            $a3
    jal     abs_diff                    # abs_diff(G1, G2)
    add     $t0,            $t0,    $0 # Add the result to $t0

    sub     $sp,            $sp,    8   # Get the stack stored values
    sw      $a0,            0($sp)      # Prepare the function arguments
    sw      $a1,            4($sp)
    jal     abs_diff                    # abs_diff(B1, B2)
    add     $t0,            $t0,    $0 # Add the result to $t0

    move    $v0,            $t0         # Return the result in $v0

    # jr      $ra                         # Return to the calling function
    j end

    # Absolute difference, $a0 = l, $a1 = r
abs_diff:
    sub     $t1,            $a0,    $a1 # Subtract second integer from first integer
    sra     $t2,            $t1,    31  # Arithmetic right shift to get sign of the difference
    xor     $t1,            $t1,    $t2 # XOR to flip the sign if necessary
    sub     $v0,            $t1,    $t2 # Subtract flipped sign from difference to get abs_diff
    jr      $ra                         # Return to the calling function



    # Infinite loop at the end of the program
end:
    j       end
