-dontnote retrofit2.Platform
-dontwarn retrofit2.Platform$Java8

-keepattributes Signature
-keepattributes Exceptions

-dontwarn okio.**
-dontwarn javax.annotation.**
-dontwarn com.squareup.okhttp.**
-dontwarn com.google.errorprone.annotations.*

-keep public class * implements com.bumptech.glide.module.GlideModule
-keep public class * extends com.bumptech.glide.module.AppGlideModule
-keep public enum com.bumptech.glide.load.resource.bitmap.ImageHeaderParser$** {
  **[] $VALUES;
  public *;
}

-keep class dev.suhockii.lifetest.model.** { *; }
-keep class dev.suhockii.lifetest.model.** { *; }
-keep class dev.suhockii.lifetest.data.local.entity.** { *; }
-keep class dev.suhockii.lifetest.data.remote.entity.** { *; }

-keep class android.support.transition.** { *; }
