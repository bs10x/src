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

module FSM (
    input clk,
    input reset,
    input left,
    input right,
    output reg LA, LB, LC,
    output reg RA, RB, RC
    );

    // clock divisor
    wire clk_en;
    ClockDivisor #(.ratio(32)) clk_div (
        .reset(),  // no reset needed, right?
        .clk_in(clk),
        .clk_out(clk_en)
        );

    // state holding registers
    reg [2:0] state_left, state_right;
    reg [1:0] selection;
    
    // next state logic
    always @ (posedge clk_en, posedge reset) begin

        selection[1] <= left;
        selection[0] <= right;


        if (reset) begin
            state_left <= 0;
            state_right <= 0;
            selection  <= 0;
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
    always @ (reset, state_left, state_right) begin
        if (reset) {LA, LB, LC, RA, RB, RC} <= 0;
        else begin
            {LA, LB, LC} <= (1 << state_left) - 1;
            {RA, RB, RC} <= (1 << state_right) - 1;
        end
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



