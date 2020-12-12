#!/system/bin/sh
echo "15 15" > /sys/kernel/sound_control/headphone_gain
echo "3" > /sys/kernel/sound_control/mic_gain
echo "7" > /sys/kernel/sound_control/earpiece_gain

echo "Sound Boost Excecuted on $(date +"%d-%m-%Y %r" )" >> /storage/emulated/0/soundboost.log
echo "" >> /storage/emulated/0/soundboost.log