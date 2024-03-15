module HalfAdder(input a, input b, output c, output y);

and CARRY(
    .a(a),
    .b(b),
    .o(c)
);

xor SUM(
    .a(a),
    .b(b),
    .o(y)
);

endmodule