#    compile on Pi:
# Adjust this to reflect the Java you are using.
JAVA_HOME=/usr/lib/jvm/java-17-openjdk-arm64/

g++ -c -fPIC -I${JAVA_HOME}/include -I${JAVA_HOME}/include/linux com_maehem_copper_pi_NativeControllerImpl.cpp -o libcopper.o   
g++ -shared -fPIC -o libcopper.so libcopper.o -lc -lpigpio

echo "If the libcopper.so file was generated without errors, then copy it to /usr/lib:"
echo "    sudo cp libcopper.so /usr/lib"
