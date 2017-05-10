
SUMMARY = "Produces a Manufacturing Tool compatible Linux Kernel"

require recipes-kernel/linux/linux-imx.inc
require recipes-kernel/linux/linux-dtb.inc


SRC_URI = "git://github.com/embeddedartists/linux-imx.git;protocol=git;branch=${SRCBRANCH}"

LOCALVERSION = "-2.0.3"
SRCBRANCH = "ea_4.1.15_2.0.0"
SRCREV = "4f0faf7e12f80e44772ab38746186e0010035757"
DEPENDS += "lzop-native bc-native"

COMPATIBLE_MACHINE = "(mx6|mx6ul|mx7)"

addtask copy_defconfig after do_unpack before do_configure
do_copy_defconfig () {
    # copy latest ea_imx_mfg_defconfig to use
    cp ${S}/arch/arm/configs/ea_imx_mfg_defconfig ${B}/.config
    cp ${S}/arch/arm/configs/ea_imx_mfg_defconfig ${B}/../defconfig
}


require recipes-kernel/linux/linux-mfgtool.inc

do_deploy () {
    install -d ${DEPLOY_DIR_IMAGE}
    install  arch/arm/boot/zImage ${DEPLOY_DIR_IMAGE}/zImage_mfgtool
}

