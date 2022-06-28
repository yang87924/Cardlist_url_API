#include <jni.h>




JNIEXPORT jstring JNICALL
Java_com_example_cardlist_MainActivity_getApi(JNIEnv *env, jobject thiz) {
    return (*env)-> NewStringUTF(env, "https://smuat.megatime.com.tw/EAS/Apps/systex/hr_elearning/hr_elearning_20220602_181350.json");
}

