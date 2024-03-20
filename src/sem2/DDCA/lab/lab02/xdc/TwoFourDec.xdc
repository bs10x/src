# input s[1:0], output o[3:0]

#  input s[1:0] from SW0, SW1
set_property PACKAGE_PIN V17 [get_ports {s[0]}]
set_property PACKAGE_PIN V16 [get_ports {s[1]}]

# output s[1:0] to LD0, LD1
set_property PACKAGE_PIN U16 [get_ports {s[0]}]
set_property PACKAGE_PIN E19 [get_ports {s[1]}]

# output o[3:0] to LD12, LD13, LD14, LD15
set_property PACKAGE_PIN P3 [get_ports {o[0]}]
set_property PACKAGE_PIN N3 [get_ports {o[1]}]
set_property PACKAGE_PIN P1 [get_ports {o[2]}]
set_property PACKAGE_PIN L1 [get_ports {o[3]}]

set_property IOSTANDARD LVCMOS33 [get_ports {s o}]
