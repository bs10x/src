/////////////////////////////////////////////////////////////////////////////////////////////////////////

module FullAdder (
    input a, 
    input b, 
    input ci, 
    output so, 
    output co);
    
    wire s;
    wire [1:0] c;

    xor s0 (s, a, b);
    xor SUM (so, s, ci);
    and c0 (c[0], a, b);
    and c1 (c[1], s, ci);
    or CARRY (co, c[0], c[1]);
endmodule

/////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////

module Adder (
    input [31:0] A,
    input [31:0] B,
    output [31:0] S,
    output overflow);
    
    wire [30:0] c;

    FullAdder FA0(.a(A[0]), .b(B[0]), .ci(), .so(S[0]), .co(c[0]));
    FullAdder FA1(.a(A[1]), .b(B[1]), .ci(c[0]), .so(S[1]), .co(c[1]));
    FullAdder FA2(.a(A[2]), .b(B[2]), .ci(c[1]), .so(S[2]), .co(c[2]));
    FullAdder FA3(.a(A[3]), .b(B[3]), .ci(c[2]), .so(S[3]), .co(c[3]));
    FullAdder FA4(.a(A[4]), .b(B[4]), .ci(c[3]), .so(S[4]), .co(c[4]));
    FullAdder FA5(.a(A[5]), .b(B[5]), .ci(c[4]), .so(S[5]), .co(c[5]));
    FullAdder FA6(.a(A[6]), .b(B[6]), .ci(c[5]), .so(S[6]), .co(c[6]));
    FullAdder FA7(.a(A[7]), .b(B[7]), .ci(c[6]), .so(S[7]), .co(c[7]));
    FullAdder FA8(.a(A[8]), .b(B[8]), .ci(c[7]), .so(S[8]), .co(c[8]));
    FullAdder FA9(.a(A[9]), .b(B[9]), .ci(c[8]), .so(S[9]), .co(c[9]));
    FullAdder FA10(.a(A[10]), .b(B[10]), .ci(c[9]), .so(S[10]), .co(c[10]));
    FullAdder FA11(.a(A[11]), .b(B[11]), .ci(c[10]), .so(S[11]), .co(c[11]));
    FullAdder FA12(.a(A[12]), .b(B[12]), .ci(c[11]), .so(S[12]), .co(c[12]));
    FullAdder FA13(.a(A[13]), .b(B[13]), .ci(c[12]), .so(S[13]), .co(c[13]));
    FullAdder FA14(.a(A[14]), .b(B[14]), .ci(c[13]), .so(S[14]), .co(c[14]));
    FullAdder FA15(.a(A[15]), .b(B[15]), .ci(c[14]), .so(S[15]), .co(c[15]));
    FullAdder FA16(.a(A[16]), .b(B[16]), .ci(c[15]), .so(S[16]), .co(c[16]));
    FullAdder FA17(.a(A[17]), .b(B[17]), .ci(c[16]), .so(S[17]), .co(c[17]));
    FullAdder FA18(.a(A[18]), .b(B[18]), .ci(c[17]), .so(S[18]), .co(c[18]));
    FullAdder FA19(.a(A[19]), .b(B[19]), .ci(c[18]), .so(S[19]), .co(c[19]));
    FullAdder FA20(.a(A[20]), .b(B[20]), .ci(c[19]), .so(S[20]), .co(c[20]));
    FullAdder FA21(.a(A[21]), .b(B[21]), .ci(c[20]), .so(S[21]), .co(c[21]));
    FullAdder FA22(.a(A[22]), .b(B[22]), .ci(c[21]), .so(S[22]), .co(c[22]));
    FullAdder FA23(.a(A[23]), .b(B[23]), .ci(c[22]), .so(S[23]), .co(c[23]));
    FullAdder FA24(.a(A[24]), .b(B[24]), .ci(c[23]), .so(S[24]), .co(c[24]));
    FullAdder FA25(.a(A[25]), .b(B[25]), .ci(c[24]), .so(S[25]), .co(c[25]));
    FullAdder FA26(.a(A[26]), .b(B[26]), .ci(c[25]), .so(S[26]), .co(c[26]));
    FullAdder FA27(.a(A[27]), .b(B[27]), .ci(c[26]), .so(S[27]), .co(c[27]));
    FullAdder FA28(.a(A[28]), .b(B[28]), .ci(c[27]), .so(S[28]), .co(c[28]));
    FullAdder FA29(.a(A[29]), .b(B[29]), .ci(c[28]), .so(S[29]), .co(c[29]));
    FullAdder FA30(.a(A[30]), .b(B[30]), .ci(c[29]), .so(S[30]), .co(c[30]));
    FullAdder FA31(.a(A[31]), .b(B[31]), .ci(c[30]), .so(S[31]), .co(overflow));
endmodule

/////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////

module TwoMux #(parameter data = 32) (
    input s,
    input [data-1:0] d0, d1,
    output [data-1:0] Y
    );

    assign Y = s ? d1 : d0;
endmodule

/////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////

module Arithmetic (
    input op,
    input [31:0] A, B,
    output [31:0] Y
    );

    wire [31:0] _B;
    wire [31:0] inv_B = ~B + 1;

    TwoMux b (
        .s(op),
        .d0(B),
        .d1(inv_B),
        .Y(_B)
        );

    Adder math (
        .A(A),
        .B(_B),
        .S(Y),
        .overflow()
        );
endmodule

/////////////////////////////////////////////////////////////////////////////////////////////////////////
