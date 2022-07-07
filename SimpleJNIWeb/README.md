[root@symposia1 skill]# /opt/IBM/WebSphere/AppServer90ND/java/8.0/bin/javac simple/jni/MyJNI.java  
[root@symposia1 skill]# /opt/IBM/WebSphere/AppServer90ND/java/8.0/bin/javah -jni simple.jni.MyJNI
[root@symposia1 skill]# gcc -I/opt/IBM/WebSphere/AppServer90ND/java/8.0/include -I/opt/IBM/WebSphere/AppServer90ND/java/8.0/include/linux -shared -fPIC -o libMyJNI.so MyJNI.c
[root@symposia1 skill]# /opt/IBM/WebSphere/AppServer90ND/java/8.0/bin/javac MyMain.java 
[root@symposia1 skill]# export LD_LIBRARY_PATH=$LD_LIBRARY_PATH:/root/skill
[root@symposia1 skill]# /opt/IBM/WebSphere/AppServer90ND/java/8.0/jre/bin/java MyMain


```
[root@symposia1 skill]# cat simple/jni/MyJNI.java 
package simple.jni;

public class MyJNI {
     public native void crash();
 }
```

```
[root@symposia1 skill]# cat MyJNI.c 
#include <stdio.h>
#include <signal.h>
#include "simple_jni_MyJNI.h"

JNIEXPORT void JNICALL Java_simple_jni_MyJNI_crash (JNIEnv* env, jobject thisObj) {
    printf("I'm going to die..\n");
    raise(SIGSEGV);
}
```

```
[root@symposia1 skill]# cat MyMain.java 
public class MyMain {
     public static void main(String[] args) {
         System.loadLibrary("MyJNI");
         simple.jni.MyJNI myjni = new simple.jni.MyJNI();
         myjni.crash();
     }
 }
```
