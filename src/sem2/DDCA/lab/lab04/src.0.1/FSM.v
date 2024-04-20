/////////////////////////////////////////////////////////////////////////////////////////////////////////

module ClockDivisor #(parameter ratio = 1) (
    input reset,
    input clk_in,
    output clk_out
    );
    
    reg [ratio-1:0] count;
    always @ (posedge clk_in) begin
        if (reset) count <= 0;
        else count <= count + 1;
    end
    assign clk_out = &count;
endmodule

/////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////

module FSM (
    input clk_sys,
    input reset,
    input [1:0] enable,
    output [5:0] LED,
    output A, B, C
    );


    ClockDivisor (.ratio(25)) clk_div (
        .reset(reset),
        .clk_in(clk_sys),
        .clk_out(clk)
        );

    // state holding register
    reg [5:0] state_prev, state_next;
    always @ (posedge clk, posedge reset) begin
        if (reset) state_prev <= S00;
        else state_prev <= state_next;
    end
    
    // next state logic 
    parameter S00 = 6'b000000;
    parameter S10 = 6'b001000;
    parameter S20 = 6'b011000;
    parameter S30 = 6'b111000;
    parameter S01 = 6'b000100;
    parameter S02 = 6'b000110;
    parameter S03 = 6'b000111;
    parameter S11 = 6'b111111;
    always @ (posedge clk) begin
        case (state_prev)
            S00: begin
                if (enable[1]) state_next <= S10;
                if (enable[0]) state_next <= S01;
                if (&enable) state_next <= S11;
            end 

            S10: state_next <= S20;
            S20: state_next <= S30;
            S30: state_next <= S00;

            S01: state_next <= S02;
            S02: state_next <= S03;
            S03: state_next <= S00;
        endcase
    end

    always @ (posedge clk_sys) begin


    end

    // output logic
    assign LED = state_prev;
endmodule


/*


module Dim (
    input clk,
    input [5:0] from,
    output [5:0] to
    );









    
always @ (posedge clk) begin
    case (state_prev)
        S0:  6'b000000
        S1L: 6'b001000
        S2L: 6'b011000
        S3L: 6'b111000
        S1R: 6'b000100
        S2R: 6'b000110
        S3R: 6'b000111
        S8:  6'b111111
    endcase
end

*/