module ClockDivisor #(parameter ratio = 1) (
    input reset,
    input clk_in,
    output clk_out
    );
    
    reg [ratio-1:0] clk_count;
    always @ (posedge clk_in) begin
        if (reset) clk_count <= 0;
        else clk_count <= clk_count + 1;
    end
    assign clk_out = &clk_count;
endmodule


module FORD(
    input clk_sys,
    input left,
    input right,
    input reset,
    output LA, LB, LC,
    output RA, RB, RC
    );

    // clock divisor
    wire clk;
    ClockDivisor #(.ratio(32)) clk_div (
        .reset(reset),
        .clk_in(clk_sys),
        .clk_out(clk)
        );

    // left state machine
    FSM LEFT (
        .clk(clk),
        .reset(reset),
        .selection(left),
        .L(LC),
        .M(LB),
        .R(LA)
        );

    // right state machine
    FSM RIGHT (
    .clk(clk),
    .reset(reset),
    .selection(right),
    .L(RA),
    .M(RB),
    .R(RC)
    );

endmodule