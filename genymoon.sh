gmsaas auth login ${Username} ${Password}
gmsaas config set android-sdk-path ${ANDROID_HOME}
deviceId=`gmsaas instances start "${RecipeId}" test1`
serialId=`gmsaas instances adbconnect "${deviceId}"`
gmsaas instances list
echo "Serial Id :"${serialId}
echo "Device Id: " ${deviceId}
gmsaas instances stop "${deviceId}"
