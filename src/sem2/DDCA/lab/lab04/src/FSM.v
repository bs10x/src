module ClockDivisor #(parameter ratio = 1) (
    input clk_in,
    input reset,
    output clk_out
    );
    
    reg [ratio-1:0] clk_count;
    always @ (posedge clk_in) begin
        if (reset) clk_count <= 0;
        else clk_count <= clk_count + 1;
    end
    assign clk_out = &clk_count;
endmodule

module FSM (
    input clk_sys,
    input reset,
    input left,
    input right,
    output reg LA, LB, LC,
    output reg RA, RB, RC
    );

    // clock divisor
    wire clk;
    ClockDivisor #(.ratio(32)) clk_div (
        .clk_in(clk_sys),
        .reset(reset),
        .clk_out(clk)
        );

    // state holding registers
    reg [2:0] state_left, state_right;
    reg [1:0] selection;
    
    always @ (posedge reset, posedge left, posedge right) begin
        if (reset) selection <= 0;
        else begin
            if (left) selection[1] <= 1;
            if (right) selection[0] <= 1;
        end
    end

    // next state logic
    always @ (posedge clk) begin
        if (reset) begin
            state_left <= 0;
            state_right <= 0;
        end
        else begin
            if (selection[1]) begin
                if (~state_left) selection[1] <= 0;
                state_left <= state_left + 1;
            end
            if (selection[0]) begin
                if (~state_right) selection[0] <= 0;
                state_right <= state_right + 1;
            end
        end
    end

    // output logic
    always @ (state_left, state_right) begin
        {LC, LB, LA} <= (1 << state_left) - 1;
        {RC, RB, RA} <= (1 << state_right) - 1;
    end
endmodule



/***************************************
////////////////////////////////////////
// PROBLEM

l
state diagram:
    0 -> 000   // 0
    1 -> 001   // 1
    2 -> 011   // 3
    3 -> 111   // 7
l
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


r
state diagram:
    0 -> 000   // 0
    1 -> 100   // 4
    2 -> 110   // 6
    3 -> 111   // 7
r
pattern = [(1 << state) - 1] reversed
    0000    *
    0001
    0010
    0011
    0100    *
    0101
    0110    *
    0111    *
    1000

////////////////////////////////////////
***************************************/



