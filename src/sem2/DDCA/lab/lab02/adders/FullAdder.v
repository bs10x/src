module FullAdder (input a, input b, input ci, output s, output co);s
    
    wire s0, c0, c1;

    HalfAdder HA0(.a(a), .b(b), .s(s0), .c(c0));
    HalfAdder HA1(.a(s0), .b(ci), .s(s), .c(c1));
    or CARRY (c0, c1, co);

endmodule

/*
    CO = AND(ci, OR(a, b))
    S = XOR(ci, XOR(a, b))
*/