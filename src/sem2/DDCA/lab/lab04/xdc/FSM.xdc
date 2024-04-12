# clock signal
set_property PACKAGE_PIN W5 [get_ports clk_sys]
set_property IOSTANDARD LVCMOS33 [get_ports clk_sys]
create_clock -period 10 -waveform {0 5} [get_ports clk_sys]

# buttons
set_property PACKAGE_PIN W19 [get_ports left]
set_property PACKAGE_PIN U18 [get_ports reset]
set_property PACKAGE_PIN T17 [get_ports right]
set_property IOSTANDARD LVCMOS33 [get_ports {left reset right}]

# LEDs
set_property PACKAGE_PIN U14 [get_ports LC]
set_property PACKAGE_PIN U15 [get_ports LB]
set_property PACKAGE_PIN W18 [get_ports LA]
set_property PACKAGE_PIN U19 [get_ports RA]
set_property PACKAGE_PIN E19 [get_ports RB]
set_property PACKAGE_PIN U16 [get_ports RC]
set_property IOSTANDARD LVCMOS33 [get_ports {LC LB LA RA RB RC}]