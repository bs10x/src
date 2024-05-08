`timescale 1ns / 1ps

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