module tristate 
#(parameter width = 4)
(input [width-1:0] a,
input en,
output [width-1:0] y);

    assign y = en ? a : 4'bZ;
    
endmodule