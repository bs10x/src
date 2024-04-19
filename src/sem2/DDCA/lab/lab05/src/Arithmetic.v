module Arithmetic (
    input [1:0] op,
    input [31:0] A, B,
    output reg [31:0] Y
    );

    always @ (*) begin
        case (op)
            2'b00: Y <= A + B;
            2'b10: Y <= A - B;
        endcase
    end

endmodule