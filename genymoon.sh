gmsaas auth login ${Username} ${Password}
gmsaas config set android-sdk-path ${ANDROID_HOME}
deviceId=`gmsaas instances start "${RecipeId}" test1`
serialId=`gmsaas instances adbconnect "${deviceId}"`
gmsaas instances list
echo "Serial Id :"${serialId}
echo "Device Id: " ${deviceId}
java -jar /home/mango/Downloads/spoon-runner.jar --sdk /home/mango/Android/Sdk --apk /home/mango/StudioProjects/PopularMovies/app/build/outputs/apk/debug/app-debug.apk --test-apk /home/mango/StudioProjects/PopularMovies/app/build/outputs/apk/androidTest/debug/app-debug-androidTest.apk -serial ${serialId}  --debug --fail-on-failure --no-animations --grant-all
gmsaas instances stop "${deviceId}"
