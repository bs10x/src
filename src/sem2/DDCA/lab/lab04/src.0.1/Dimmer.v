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

module Dimmer (
    input clk_sys,
    input reset,
    output D);
    
    // reg[]

    wire clk;
    ClockDivisor #(.ratio(25)) clk_div (
        .clk_in(clk_sys),
        .reset(reset),
        .clk_out(clk)
    );

    always @ (posedge clk_sys) begin
        // Dimmer logic
    end


    reg [7:0] brightness;
    reg [7:0] counter;

    always @ (posedge clk) begin
        if (reset) begin
            brightness <= 0;
            counter <= 0;
        end else begin
            if (counter < 255) begin
                counter <= counter + 1;
            end
            else begin
                counter <= 0;
                if (brightness < 255) begin
                    brightness <= brightness + 1;
                end
            end
        end
    end

    assign D = (brightness >= counter);


endmodule