# input s[1:0], output o[3:0]

set_property PACKAGE_PIN V17 [get_ports {s[0]}]
set_property PACKAGE_PIN V16 [get_ports {s[1]}]

set_property PACKAGE_PIN U16 [get_ports {o[0]}]
set_property PACKAGE_PIN E19 [get_ports {o[1]}]
set_property PACKAGE_PIN U19 [get_ports {o[2]}]
set_property PACKAGE_PIN V19 [get_ports {o[3]}]

set_property IOSTANDARD LVCMOS33 [get_ports {s d}]
