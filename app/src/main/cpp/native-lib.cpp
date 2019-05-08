#include <jni.h>
#include <string>

extern "C"
JNIEXPORT jstring JNICALL
Java_sohero_com_testhlsvideo_MainActivity_stringFromJNI(
        JNIEnv* env,
        jobject /* this */) {
    std::string hello = "Hello from C++";
    return env->NewStringUTF(hello.c_str());
}
