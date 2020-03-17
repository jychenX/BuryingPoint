#-ignorewarnings
#-dontoptimize
#-dontusemixedcaseclassnames
#-dontskipnonpubliclibraryclasses
#-dontpreverify
#-verbose
#-dontwarn
#-dontskipnonpubliclibraryclassmembers
#-dontshrink

#-keep class com.mob.at.ATMain {
#    public static synchronized void start(java.lang.String);
#}

#-keepclassmembers class com.common.obusi.HInstrumentation{*;}

#-assumenosideeffects class android.util.Log {
#   public static *** i(...);
#   public static *** v(...);
#   public static *** w(...);
#   public static *** e(...);
#   public static *** d(...);
#}
#-assumenosideeffects class java.lang.Throwable {
#   public void printStackTrace();
#}
#-assumenosideeffects class java.lang.Exception {
#   public void printStackTrace();
#}


#-repackageclasses com.mob.at.jt
#-repackageclasses com.common.mbusi


-injars F:\SVN\DynaFuc\mdc\raw.jar
-outjars F:\SVN\DynaFuc\mdc\obfuscate.jar
-libraryjars D:\Android_eclipse\SDk\platforms\android-P\android.jar
-libraryjars F:\SVN\DynaFuc\mdc\1578127501039.jar

-dontoptimize
-dontusemixedcaseclassnames
-dontskipnonpubliclibraryclasses
-dontpreverify
-verbose
-dontnote
-dontwarn
-dontshrink
-allowaccessmodification
-optimizations !code/simplification/arithmetic,!field/*,!class/merging/*
-keepattributes SourceFile,LineNumberTable,Exceptions,InnerClasses,Signature,Deprecated,EnclosingMethod,*Annotation*

-keep public class * extends android.app.Activity
-keep public class * extends android.app.Application
-keep public class * extends android.app.Service
-keep public class * extends android.content.BroadcastReceiver
-keep public class * extends android.content.ContentProvider
-keep public class * extends android.app.backup.BackupAgentHelper
-keep public class * extends android.preference.Preference
-keep class android.net.http.SslError
-keep class android.webkit.**{*;}

#-keepclasseswithmembernames class * {
#    native <methods>;
#}
#
#-keepclassmembers enum * {
#    public static **[] values();
#    public static ** valueOf(java.lang.String);
#}
#
#-keep class * implements android.os.Parcelable {
#  public static final android.os.Parcelable$Creator *;
#}
-keep class com.mob.at.ATMain {
	public static void start(java.lang.String);
}
#-repackageclasses com.common.hx.mbusi