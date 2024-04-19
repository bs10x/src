module Mux #(parameter width = 8) (
    input s,
    input [width-1:0] A, B,
    output [width-1:0] Y);

    assign Y = s ? B : A;
endmodule
