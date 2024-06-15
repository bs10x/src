    #       
    # Sum of Absolute Differences Algorithm
    #       
    # Authors:
    #	Basil Feitknecht, Camil Schmid
    #       

.text

main:
    # Prepare the function arguments
    li      $a0,            1           # R1
    li      $a1,            5           # R2
    li      $a2,            1           # G1
    li      $a3,            10          # G2
    li      $t0,            5           # B1
    li      $t1,            1           # B2

    sub     $sp,            $sp,    8   # Reserve space on the stack

    sw      $t0,            0($sp)      # Save $a4 on the stack, B1
    sw      $t1,            4($sp)      # Save $a4 on the stack, B1

    jal     abs_diff_color
    move    $t9,            $v0         # Save the result in $t9
    j       end                         # Jump to the end of the program

    # Absolute difference between three channels
abs_diff_color:
    sub     $sp,            $sp,    4   # Reserve space on the stack
    sw      $ra,            0($sp)      # Save return address

    jal     abs_diff                    # abs_diff(R1, R2)
    move    $t0,            $v0         # write the result to $t0

    move    $a0,            $a2         # Prepare the function arguments
    move    $a1,            $a3
    jal     abs_diff                    # abs_diff(G1, G2)
    add     $t0,            $t0,    $v0 # Add the result to $s0

    lw      $a0,            4($sp)      # B1
    lw      $a1,            8($sp)      # B2
    jal     abs_diff                    # abs_diff(B1, B2)
    add     $t0,            $t0,    $v0 # Add the result to $t0

    lw      $ra,            0($sp)      # restore return address from stack
    add     $sp,            $sp,    12  # reset stack

    move    $v0,            $t0         # Return the result in $v0
    jr      $ra                         # Return to the calling function

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