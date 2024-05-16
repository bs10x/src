    #       
    # Sum of Absolute Differences Algorithm
    #       
    # Authors:
    #	Basil Feitknecht, Camil Schmid
    #       

.text

main:
    li      $s0,            0           # Initialize the counter

    # Prepare the function arguments
    li      $a0,            1           # R1
    li      $a1,            2           # R2
    li      $a2,            3           # G1
    li      $a3,            4           # G2

    sub     $sp,            $sp,    8   # Reserve space on the stack

    li      $sp,            5           # Save $a4 on the stack, B1
    add     $sp,            $sp,    4   # go to next stack argument

    li      $sp,            6           # Save $a5 on the stack, B2
    add     $sp,            $sp,    4   # go to next stack argument

    jal     abs_diff_color
    move    $t9,            $v0         # Save the result in $t9
    j       end                         # Jump to the end of the program


    # Absolute difference between three channels
abs_diff_color:
    jal     abs_diff                    # abs_diff(R1, R2)
    add     $s0,            $t0,    $v0 # Add the result to $t0

    move    $a0,            $a2         # Prepare the function arguments
    move    $a1,            $a3
    jal     abs_diff                    # abs_diff(G1, G2)
    add     $s0,            $t0,    $v0 # Add the result to $t0

    sub     $sp,            $sp,    8   # Get the stack stored values
    
    sw      $a0,            0($sp)      # B1
    add     $sp,            $sp,    4   # go to next stack argument
    sw      $a1,            0($sp)      # B2
    add     $sp,            $sp,    4   # go to next stack argument
    jal     abs_diff                    # abs_diff(B1, B2)

    add     $s0,            $t0,    $v0 # Add the result to $t0

    move    $v0,            $s0         # Return the result in $v0

    # jr      $ra                         # Return to the calling function
    # j       end

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
