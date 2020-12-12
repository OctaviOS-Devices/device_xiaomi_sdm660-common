#!/system/bin/sh
MODDIR=${0%/*}
mods=/data/adb/modules
if [ -e $mods/aml/post-fs-data.sh ]; then
    cp -af $MODDIR/AIO/audiomods $mods/aml/post-fs-data.sh
fi
if [ -e $mods/aml/system/vendor/etc/mixer_path.xml ]; then
    rm -rf $mods/aml/system/vendor/etc/mixer_path.xml
fi
if [ -e $MODDIR/AIO/mixer_path.xml ]; then
    cp $MODDIR/AIO/mixer_path.xml $MODDIR/system/vendor/etc/mixer_path.xml
fi
if [ -e $MODDIR/AIO/soundboost.sh ]; then
    sleep 90
    sh $MODDIR/AIO/soundboost.sh
fi