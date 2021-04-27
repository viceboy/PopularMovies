gmsaas auth login sumit.jha@newglobe.education Sumit@123
gmsaas config set android-sdk-path /home/mango/Android/Sdk
deviceId=`gmsaas instances start e6a305b5-ca40-4587-9aa8-623eb535b2f2 test1`
serialId=`gmsaas instances adbconnect "${deviceId}"`
gmsaas instances list
echo "Serial Id :"${serialId}
echo "Device Id: " ${deviceId}
java -jar /home/mango/Downloads/spoon-runner.jar --sdk /home/mango/Android/Sdk --apk /home/mango/StudioProjects/PopularMovies/app/build/outputs/apk/debug/app-debug.apk --test-apk /home/mango/StudioProjects/PopularMovies/app/build/outputs/apk/androidTest/debug/app-debug-androidTest.apk -serial ${serialId}  --debug --fail-on-failure --no-animations --grant-all
gmsaas instances stop "${deviceId}"
