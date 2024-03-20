# input [3:0] d, input [1:0] s, output o

set_property PACKAGE_PIN V17 [get_ports {d[0]}]
set_property PACKAGE_PIN V16 [get_ports {d[1]}]
set_property PACKAGE_PIN W16 [get_ports {d[2]}]
set_property PACKAGE_PIN W17 [get_ports {d[3]}]

set_property PACKAGE_PIN W15 [get_ports {s[0]}]
set_property PACKAGE_PIN V15 [get_ports {s[1]}]

set_property PACKAGE_PIN W18 [get_ports {o}]

set_property IOSTANDARD LVCMOS33 [get_ports {a b s}]