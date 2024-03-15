module FullAdder (input a, input b, input ci, output s, output co);
wire y, c, x;
HalfAdder ab(.a(a), .b(b), .s(y), .c(c));
HalfAdder abci(.a(y), .b(ci), .s(s), .c(x));
or carry (c, x, s);

endmodule




// `include "HalfAdder.v";