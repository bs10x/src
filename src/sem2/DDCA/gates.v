module gates(input [3:0] a, b output [3:0] y1, y2 , y3, y4, y5);

    // assigns five different two-input logic gates acting on 4 bit busses
    assign y1 = a & b;
    assign y2 = a | b;
    assign y3 = a ^ b;
    assign y4 = ~(a & b) ;
    assign y5 = ~(a | b);
    
endmodule