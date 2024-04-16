`timescale 1ns / 1ps

module FSM (
    input clk,
    input reset,
    input enable,
    output L, M, R
    );

    // state holding registers
    reg [2:0] state_p;
    parameter S0 = 3'b000;
    parameter S1 = 3'b001;
    parameter S2 = 3'b011;
    parameter S3 = 3'b111;
    
    // update state_ps based on inputs 
//    always @ (posedge enable) begin
//        case (state_p)
//            S0: state_n <= S1;
//            S1: state_n <= S2;
//            S2: state_n <= S3;
//            S3: state_n <= S0;
//            default: state_n = S0;
//        endcase
//    end


    // next state_p logic
    always @ (posedge clk, posedge reset, posedge enable) begin
        if (reset) state_p <= S0;
        else if(state_p == S0 && enable) state_p <= S1;
        else if(state_p == S1) state_p <= S2;
        else if(state_p == S2) state_p <= S3;
        else state_p <= S0;
    end

    // output logic
     assign {L, M, R} = state_p;
//    assign L = enable;
//    assign M = reset;
//    assign R = clk;
endmodule