module FullAdder (input a, input b, input ci, output s, output co);

wire y, c, x;
HalfAdder ab (.a(a), .b(b), .s(y), .c(c));
HalfAdder yci (.a(y), .b(ci), .s(s), .c(x));
or carryout (abc, abcic, co)

endmodule