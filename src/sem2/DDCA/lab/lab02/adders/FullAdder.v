module FullAdder (input a, input b, input ci, output s, output co);s
    
    wire s0, c0, c1;

    xor s0 (a, b, s0);
    xor SUM (s0, ci, s);

    and c0 (a, b, c0);
    and c1 (s0, ci, c1);
    or CARRY (c0, c1, co);

endmodule

/*
    // boolean equations for SUM and CARRY

    S = a XOR b XOR ci
    CO = a AND b OR (a XOR b) AND ci
*/

// module instantiation method 
// wire s0, c0, c1;
// HalfAdder HA0(.a(a), .b(b), .s(s0), .c(c0));
// HalfAdder HA1(.a(s0), .b(ci), .s(s), .c(c1));
// or CARRY (c0, c1, co);