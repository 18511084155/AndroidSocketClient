# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# You can edit the include path and order by changing the proguardFiles
# directive in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Add any project specific keep options here:

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile
-dontwarn com.woodys.libsocket.**
-keep class com.woodys.libsocket.impl.abilities.** { *; }
-keep class com.woodys.libsocket.impl.exceptions.** { *; }
-keep class com.woodys.libsocket.impl.EnvironmentalManager { *; }
-keep class com.woodys.libsocket.impl.BlockConnectionManager { *; }
-keep class com.woodys.libsocket.impl.UnBlockConnectionManager { *; }
-keep class com.woodys.libsocket.impl.SocketActionHandler { *; }
-keep class com.woodys.libsocket.impl.PulseManager { *; }
-keep class com.woodys.libsocket.impl.ManagerHolder { *; }
-keep class com.woodys.libsocket.interfaces.** { *; }
-keep class com.woodys.libsocket.sdk.** { *; }

# 枚举类不能被混淆
-keepclassmembers enum * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}
-keep class com.woodys.libsocket.sdk.OkSocketOptions$* {
    *;
}
-keep class com.woodys.libsocket.sdk.OkSocketSSLConfig$* {
    *;
}
-keepattributes Exceptions,InnerClasses,Signature,Deprecated,SourceFile,LineNumberTable,*Annotation*,EnclosingMethod