module FullAdder (input a, input b, input ci, output s, output co);
    
    wire so0, co0, co1;

    xor s0 (so0, a, b);
    xor SUM (s, s0, ci);
    
    and c0 (co0, a, b);
    and c1 (co1, s0, ci);
    or CARRY (co, c0, c1);

endmodule

/*
    // boolean equations for SUM and CARRY

    S = a XOR b XOR ci
    CO = a AND b OR (a XOR b) AND ci
*/
