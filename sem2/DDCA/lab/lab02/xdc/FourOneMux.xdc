# input [3:0] d, input [1:0] s, output Y

# input d[3:0] from SW12, SW13, SW14, SW15
set_property PACKAGE_PIN W2 [get_ports {d[0]}]
set_property PACKAGE_PIN U1 [get_ports {d[1]}]
set_property PACKAGE_PIN T1 [get_ports {d[2]}]
set_property PACKAGE_PIN R2 [get_ports {d[3]}]

# input s[1:0], from SW0, SW1
set_property PACKAGE_PIN V17 [get_ports {s[0]}]
set_property PACKAGE_PIN V16 [get_ports {s[1]}]

# DOESNT'T WORK UNLESS OUTPUT SPECIFIED
# output d[3:0] to LD13, LD12, LD14, LD15
# set_property PACKAGE_PIN P3 [get_ports {d[0]}]
# set_property PACKAGE_PIN N3 [get_ports {d[1]}]
# set_property PACKAGE_PIN P1 [get_ports {d[2]}]
# set_property PACKAGE_PIN L1 [get_ports {d[3]}]

# SAME THING HERE
# output s[1:0], to LD0, LD1
# set_property PACKAGE_PIN U16 [get_ports {s[0]}]
# set_property PACKAGE_PIN E19 [get_ports {s[1]}]

# output Y to LD2
set_property PACKAGE_PIN U19 [get_ports {Y}]

set_property IOSTANDARD LVCMOS33 [get_ports {a b s Y}]