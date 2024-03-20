# input s[1:0], output o[3:0]

# these are correct, rightmost is s0, second from right is s1
set_property PACKAGE_PIN V17 [get_ports {s[0]}]
set_property PACKAGE_PIN V16 [get_ports {s[1]}]

# these should indicate selection (s0, s1) on the leds
set_property PACKAGE_PIN U16 [get_ports {s[0]}]
set_property PACKAGE_PIN E19 [get_ports {s[1]}]

# the leds that light up, LD12 is logic 0 (s == 0)
set_property PACKAGE_PIN P3 [get_ports {o[0]}]
set_property PACKAGE_PIN N3 [get_ports {o[1]}]
set_property PACKAGE_PIN P1 [get_ports {o[2]}]
set_property PACKAGE_PIN L1 [get_ports {o[3]}]

set_property IOSTANDARD LVCMOS33 [get_ports {s o}]
