# input [3:0] d, input [1:0] s, output o

# input data, d[3:0]
set_property PACKAGE_PIN V17 [get_ports {d[0]}]
set_property PACKAGE_PIN V16 [get_ports {d[1]}]
set_property PACKAGE_PIN W16 [get_ports {d[2]}]
set_property PACKAGE_PIN W17 [get_ports {d[3]}]

# indicative leds on corresponding data channel
set_property PACKAGE_PIN P3 [get_ports {d[0]}]
set_property PACKAGE_PIN N3 [get_ports {d[1]}]
set_property PACKAGE_PIN P1 [get_ports {d[2]}]
set_property PACKAGE_PIN L1 [get_ports {d[3]}]

# selection channels, s[1:0]
set_property PACKAGE_PIN V17 [get_ports {s[0]}]
set_property PACKAGE_PIN V16 [get_ports {s[1]}]

# selection channels, s[1:0]
set_property PACKAGE_PIN U16 [get_ports {s[0]}]
set_property PACKAGE_PIN E19 [get_ports {s[1]}]

# output channel, o
set_property PACKAGE_PIN U19 [get_ports {o}]

set_property IOSTANDARD LVCMOS33 [get_ports {a b s}]