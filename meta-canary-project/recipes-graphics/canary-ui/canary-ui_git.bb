SUMMARY = "recipe for the canary dashboard"
DESCRIPTION = ""
LICENSE = "MIT"


SRC_URI = "gitsm://github.com/bilal-marghich/canary-ui.git;protocol=https;branch=main"
SRCREV = "c850c5bb3303caf5a2fdc20c1f3b0d53c2f0872e"

S = "${WORKDIR}/git"

inherit cmake

#PACKAGECONFIG is used to transform the systemd service (canary-ui.service) into a feature that can be easily enabled or disabled via PACKAGECONFIG:
PACKAGECONFIG = "systemd"
PACKAGECONFIG[systemd] = "-DWITH_SYSTEMD=ON,-DWITH_SYSTEMD=OFF"



# TO DO: impelment them by hand for cmake 
# do_configure(){
#     # Your code here
# }

# do_compile(){
#     # Your code here
# }


# do_install(){
#     # Your code here
# }