 #    compile on Pi:
JAVA_HOME=/usr/lib/java-17-openjdk-arm64
 g++ -c -fPIC -I${JAVA_HOME}/include -I${JAVA_HOME}/include/linux com_maehem_copper_pi_NativeControllerImpl.cpp -o libcopper.o   
 g++ -shared -fPIC -o lib4copper.so libcopper.o -lc

