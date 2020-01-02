#include <jni.h>
#include <string>
#include <stdlib.h>
#include "Blockchain.h"

extern "C" JNIEXPORT jstring JNICALL
Java_com_example_blockchn_MainActivity_stringFromJNI(
        JNIEnv *env,
        jobject /* this */) {
    Blockchain bChain = Blockchain();
    std::string tmp = std::to_string((int)(rand() % 10000));
    tmp="";
    tmp+=bChain.AddBlock(Block(1, tmp));


    return env->NewStringUTF(tmp.c_str());
}
