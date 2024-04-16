module FSM (
    input clk,
    input reset,
    input enable,
    output L, M, R
    );

    // state holding registers
    reg [2:0] state_prev, state_next;
    parameter S0 = 3'b000;
    parameter S1 = 3'b001;
    parameter S2 = 3'b011;
    parameter S3 = 3'b111;
    
    // update state based on inputs 
    always @ (posedge state_prev) begin
        case (state_prev)
            S0: state_next <= (enable) ? S1 : S0;
            S1: state_next <= S2;
            S2: state_next <= S3;
            S3: state_next <= S0;
        endcase
    end


    // next state_prev logic
    always @ (posedge clk, posedge reset) begin
        if (reset) state_prev <= S0;
        else state_prev <= state_next;
    end

    // output logic
    assign {L, M, R} = state_next;
endmodule
