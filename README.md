# AndroidData
Android常用数据存储

SharedPerfence


Android 外部存储：
内存 Memory  
内部存储 InternalStorage  只能被APP私有访问
外部存储 ExternalStorage 

Android获取外部存储的目录
可以通过清除数据清除
Context.getExternalFilesDir(String type)
获取到SDCard/Android/data/包名/files/目录

可以通过清除缓存方式清除
Context.getExternalCacheDir();
获取到SDCard/Android/data/包名/chche/目录


获取内部存储的目录
  Context.getFileDir()
  获取/data/data/包名/files

  Context.getCacheDir()
  获取/data/data/包名/cache
