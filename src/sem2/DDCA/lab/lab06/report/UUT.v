
module ClockDivisor #(parameter ratio = 1) (
    input clk_in,
    input reset,
    output clk_out, dim_out
    );
    
    reg [ratio-1:0] clk_count, dim_sum;
    reg dim_carry;
    always @ (posedge clk_in) begin
        if (reset) begin 
            clk_count <= 0;
            dim_sum <= 0;
            dim_carry <=0;
        end else begin 
            clk_count <= clk_count + 1;
            {dim_carry, dim_sum} <= dim_sum + clk_count;
        end
    end
    assign clk_out = &clk_count;
    assign dim_out = dim_carry;
endmodule

module Dim (
    input [2:0] stateL,
    input [2:0] stateR,
    input dim,
    output LA, LB, LC, RA, RB, RC
    );
    
    
    reg [2:0] stateL_prev, stateR_prev;
    
    always @ (*) begin
        case (stateL)
            3'b000: stateL_prev <= {1'b0, 1'b0, 1'b0};
            3'b001: stateL_prev <= {1'b0, 1'b0, dim};
            3'b011: stateL_prev <= {1'b0, dim, 1'b1};
            3'b111: stateL_prev <= {dim, 1'b1, 1'b1};                
        endcase
        case (stateR)
            3'b000: stateR_prev <= {1'b0, 1'b0, 1'b0};
            3'b001: stateR_prev <= {1'b0, 1'b0, dim};
            3'b011: stateR_prev <= {1'b0, dim, 1'b1};
            3'b111: stateR_prev <= {dim, 1'b1, 1'b1};
        endcase
    end
   
    assign {LC, LB, LA} = stateL_prev;
    assign {RC, RB, RA} = stateR_prev;

endmodule


module FSM (
    input clk,
    input reset,
    input eLeft,
    input eRight,
    output [2:0] stateL, stateR
    );

    // state holding registers
    reg [2:0] stateL_prev, stateL_next, stateR_prev, stateR_next;
    parameter S0 = 3'b000;
    parameter S1 = 3'b001;
    parameter S2 = 3'b011;
    parameter S3 = 3'b111;

    // update state based on inputs 
    always @ (*) begin
        case (stateL_prev)
            S0: stateL_next <= (eLeft && stateR_prev[0] == 0) ? S1 : S0;
            S1: stateL_next <= S2;
            S2: stateL_next <= S3;
            S3: stateL_next <= S0;
        endcase
        case (stateR_prev)
            S0: stateR_next <= (eRight && stateL_prev[0] == 0) ? S1 : S0;
            S1: stateR_next <= S2;
            S2: stateR_next <= S3;
            S3: stateR_next <= S0;
        endcase
    end

    // next state logic
    always @ (posedge clk, posedge reset) begin
        if (reset) begin
            stateL_prev <= S0;
            stateR_prev <= S0;
        end else begin 
            stateL_prev <= stateL_next;
            stateR_prev <= stateR_next;
        end
    end

    // output logic
    assign stateL = stateL_prev;
    assign stateR = stateR_prev;

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
    wire clk, dim;
    ClockDivisor #(.ratio(25)) clk_div (
        .reset(reset),
        .clk_in(clk_sys),
        .clk_out(clk),
        .dim_out(dim)
        );

    // state machine
    wire [2:0] stateL, stateR;
    FSM fsm (
        .clk(clk),
        .reset(reset),
        .eLeft(left),
        .eRight(right),
        .stateL(stateL),
        .stateR(stateR)
    );
    
    Dim DIM (
        .stateL(stateL),
        .stateR(stateR),
        .dim(dim),
        .LA(LA),
        .LB(LB),
        .LC(LC),
        .RA(RA),
        .RB(RB),
        .RC(RC)
    );
        
endmodule