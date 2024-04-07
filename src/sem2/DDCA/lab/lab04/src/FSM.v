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
    input L,
    input R,
    output LA, LB, LC, RA, RB,RC);

    integer state;

    always @ (posedge clk) begin
        if (rst) state <= 3'b000;
        else state <= (state++) % 4;
    end

endmodule

/*
state diagram:
    0: 000 -> 000

    1: 001 -> 001
    2: 010 -> 011
    3: 011 -> 111
    4: 100 -> 000
*/