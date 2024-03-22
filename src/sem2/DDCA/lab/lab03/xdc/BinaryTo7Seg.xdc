# input a[3:0], input b[3:0], output s[4:0]

# input a[3:0] from SW[3:0]
set_property PACKAGE_PIN V17 [get_ports {a[0]}]
set_property PACKAGE_PIN V16 [get_ports {a[1]}]
set_property PACKAGE_PIN W16 [get_ports {a[2]}]
set_property PACKAGE_PIN W17 [get_ports {a[3]}]

# input b[3:0] from SW[7:4]
set_property PACKAGE_PIN W15 [get_ports {b[0]}]
set_property PACKAGE_PIN V15 [get_ports {b[1]}]
set_property PACKAGE_PIN W14 [get_ports {b[2]}]
set_property PACKAGE_PIN W13 [get_ports {b[3]}]

# DOESN'T WORK IF NOT DECLARED AS OUTPUT
# output s[4:0] to LD[4:0]
# set_property PACKAGE_PIN U16 [get_ports {s[0]}]
# set_property PACKAGE_PIN E19 [get_ports {s[1]}]
# set_property PACKAGE_PIN U19 [get_ports {s[2]}]
# set_property PACKAGE_PIN V19 [get_ports {s[3]}]
# set_property PACKAGE_PIN W18 [get_ports {s[4]}]

# output DISPLAY[6:0] to AN0 (assuming DISPLAY[0] == a, DISPLAY[1] == b, etc.)
set_property PACKAGE_PIN W7 [get_ports {DISPLAY[0]}]
set_property PACKAGE_PIN W6 [get_ports {DISPLAY[1]}]
set_property PACKAGE_PIN U8 [get_ports {DISPLAY[2]}]
set_property PACKAGE_PIN V8 [get_ports {DISPLAY[3]}]
set_property PACKAGE_PIN U5 [get_ports {DISPLAY[4]}]
set_property PACKAGE_PIN V5 [get_ports {DISPLAY[5]}]
set_property PACKAGE_PIN U7 [get_ports {DISPLAY[6]}]

# output OVERFLOW to LD15
set_property PACKAGE_PIN L1 [get_ports {OVERFLOW}]

set_property IOSTANDARD LVCMOS33 [get_ports {a b DISPLAY OVERFLOW}]