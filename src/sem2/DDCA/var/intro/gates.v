module gates(
    input [3:0] a,
    input [3:0] b,
    output [4:0] Y);

    // assigns five different two-input logic gates acting on 4 bit busses
    assign Y[0] = a & b;
    assign Y[1] = a | b;
    assign Y[2] = a ^ b;
    assign Y[3] = ~(a & b) ;
    assign Y[4] = ~(a | b);
endmodule
