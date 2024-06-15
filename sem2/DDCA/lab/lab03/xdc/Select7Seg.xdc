# input s[1:0], output AN[3:0]

#  input s[1:0] from SW0, SW1
set_property PACKAGE_PIN V17 [get_ports {s[0]}]
set_property PACKAGE_PIN V16 [get_ports {s[1]}]

# NEEDS TO BE SPECIFIED AS OUT IN FILE
# output s[1:0] to LD0, LD1
# set_property PACKAGE_PIN U16 [get_ports {s[0]}]
# set_property PACKAGE_PIN E19 [get_ports {s[1]}]

# output AN[3:0] to AN0, AN1, AN2, AN3
set_property PACKAGE_PIN U2 [get_ports {AN[0]}]
set_property PACKAGE_PIN U4 [get_ports {AN[1]}]
set_property PACKAGE_PIN V4 [get_ports {AN[2]}]
set_property PACKAGE_PIN W4 [get_ports {AN[3]}]

set_property IOSTANDARD LVCMOS33 [get_ports {s AN}]