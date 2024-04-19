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

    reg [2:0] state_prev, state_next;

    parameter S0 = 3'b000;
    parameter S1 = 3'b001;
    parameter S2 = 3'b011;
    parameter S3 = 3'b111;

    // state holding register
    always @ (posedge clk, posedge reset) begin
        if (reset == 1) state_prev <= S0;
        else state_prev <= state_next;
    end

    // next state logic 
    always @ (posedge clk) begin
        case (state_prev)
            S0: state_next <= (enable == 1) ? S1 : S0;
            S1: state_next <= S2;
            S2: state_next <= S3;
            S3: state_next <= S0;
        endcase
    end

    // output logic
    assign {A, B, C} = state_prev;
endmodule


module Ford (
    input clk_sys,
    input left,
    input right,
    input reset,
    output LA, LB, LC,
    output RA, RB, RC
    );

    wire [2:0] L, R;

    // clock divisor
    wire clk;
    ClockDivisor #(.ratio(25)) clk_div (
        .reset(reset),
        .clk_in(clk_sys),
        .clk_out(clk)
        );

    // left blinker
    FSM LEFT (
        .clk(clk),
        .reset(reset),
        .enable(left && !R[0]),
        .C(L[2]),
        .B(L[1]),
        .A(L[0])
        );

    // right blinker
    FSM RIGHT (
        .clk(clk),
        .reset(reset),
        .enable(right && !L[0]),
        .C(R[2]),
        .B(R[1]),
        .A(R[0])
        );

        assign {LC, LB, LA} = L;
        assign {RC, RB, RA} = R;
endmodule