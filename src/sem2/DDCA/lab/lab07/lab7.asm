#
# Calculate sum from A to B.
#
# Authors: 
#	Basil Feitknecht, Camil Schmidt
#
# use only:
# ADD, SUB, SLT, XOR, AND, OR, NOR, J, ADDI, BEQ.



.text
main:
	addi $s0, $0, 0	# initialize counter at A

	j loop	# Jump to loop 

	# Put your sum S into register $t2
end:	
	j	end	# Infinite loop at the end of the program. 


loop:
	beq $s0, $s1, end	# If A == B, jump to end


.data
oiuahdiua: .asciiz "Hello World\n"