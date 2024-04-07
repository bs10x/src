module clk_div(input clk, input rst, output clk_en);
reg [24:0] clk_count;
always @ (posedge clk) begin
    if (rst) clk_count <= 0;
    else clk_count <= clk_count + 1;
end
assign clk_en = &clk_count;
endmodule

module FSM(
    input clk,
    input rst,
    input l,
    input r,
    output LA, LB, LC, RA, RB,RC);

    integer state;
    reg [2:0] pattern;

    always @ (posedge clk) begin
        if (rst) state <= 0;
        else state <= state++ % 4;

        pattern <= (1 << state) - 1;

        // non blocking, so both can happen at the same time
        // assign {LA, LB, LC} = (l) ? pattern[2:0] : 3'b000;
        // assign {RA, RB, RC} = (r) ? pattern[0:2] : 3'b000;
    end
endmodule

/*
state diagram:
    0 -> 000   // 0
    1 -> 001   // 1
    2 -> 011   // 3
    3 -> 111   // 7

pattern = (1 << state) - 1
    0000    *
    0001    *
    0010
    0011    *
    0100
    0101
    0110
    0111    *
    1000

    assign {LA, LB, LC} = (l) ? pattern[2:0] : 3'b000;
    assign {RA, RB, RC} = (r) ? pattern[0:2] : 3'b000;
*/