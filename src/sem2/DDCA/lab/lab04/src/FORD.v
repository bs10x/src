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

module FSM (
    input clk,
    input reset,
    input enable,
    output A, B, C
    );

    // state holding registers
    reg [2:0] state_p, state_n;
    parameter S0 = 3'b000;
    parameter S1 = 3'b001;
    parameter S2 = 3'b011;
    parameter S3 = 3'b111;
    
    // update state based on inputs 
    always @ (posedge state_p) begin
        case (state_p)
            S0: state_n <= (enable) ? S1 : S0;
            S1: state_n <= S2;
            S2: state_n <= S3;
            S3: state_n <= S0;
        endcase
    end


    // next state_p logic
    always @ (posedge clk, posedge reset) begin
        if (reset) state_p <= S0;
        else state_p <= state_n;
    end

    // output logic
    assign {A, B, Cs} = state_n;
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
    ClockDivisor #(.ratio(24)) clk_div (
        .reset(reset),
        .clk_in(clk_sys),
        .clk_out(clk)
        );

    // left blinker
    FSM LEFT (
        .clk(clk),
        .reset(reset),
        .enable(left),
        .A(LA),
        .B(LB),
        .C(LC)
        );

    // right blinker
    // FSM RIGHT (
    //     .clk(clk),
    //     .reset(reset),
    //     .enable(right),
    //     .A(RA),
    //     .B(RB),
    //     .C(RC)
    //     );

endmodule