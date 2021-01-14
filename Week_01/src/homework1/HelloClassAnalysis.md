Hello.class 字节码内容
```java
// 文件头部信息
Classfile /Users/itjun/iWork/JAVA-01/Week_01/src/homework1/Hello.class
  Last modified 2021年1月14日; size 1584 bytes
  MD5 checksum 071a6ae270041cf50be971106620e946
  Compiled from "Hello.java"
// Java类名称
public class Hello
  // jdk11.0.9
  minor version: 0
  major version: 56
  flags: (0x0021) ACC_PUBLIC, ACC_SUPER
  this_class: #4                          // Hello
  // 父类是Object类
  super_class: #22                        // java/lang/Object
  interfaces: 0, fields: 4, methods: 3, attributes: 3
Constant pool:
   #1 = Methodref          #22.#47        // java/lang/Object."<init>":()V
   #2 = Fieldref           #4.#48         // Hello.d:I
   #3 = InvokeDynamic      #0:#52         // #0:makeConcatWithConstants:(Ljava/lang/String;Ljava/lang/Long;)Ljava/lang/String;
   #4 = Class              #53            // Hello
   #5 = Methodref          #4.#47         // Hello."<init>":()V
   #6 = Integer            32775
   #7 = Double             0.5d
   #9 = Float              0.2f
  #10 = Double             8.0d
  #12 = Double             32767.0d
  #14 = InvokeDynamic      #1:#55         // #1:makeConcatWithConstants:(D)Ljava/lang/String;
  #15 = Methodref          #56.#57        // java/lang/Long.parseLong:(Ljava/lang/String;)J
  #16 = Methodref          #56.#58        // java/lang/Long.valueOf:(J)Ljava/lang/Long;
  #17 = Fieldref           #59.#60        // java/lang/System.out:Ljava/io/PrintStream;
  #18 = String             #61            // abc-
  #19 = Methodref          #4.#62         // Hello.test:(Ljava/lang/Long;Ljava/lang/String;)Ljava/lang/String;
  #20 = InvokeDynamic      #2:#64         // #2:makeConcatWithConstants:(Ljava/lang/String;)Ljava/lang/String;
  #21 = Methodref          #65.#66        // java/io/PrintStream.println:(Ljava/lang/String;)V
  #22 = Class              #67            // java/lang/Object
  #23 = Utf8               a
  #24 = Utf8               Z
  #25 = Utf8               ConstantValue
  #26 = Integer            1
  #27 = Utf8               b
  #28 = Utf8               B
  #29 = Integer            8
  #30 = Utf8               c
  #31 = Utf8               S
  #32 = Integer            32767
  #33 = Utf8               d
  #34 = Utf8               I
  #35 = Integer            -1024
  #36 = Utf8               <init>
  #37 = Utf8               ()V
  #38 = Utf8               Code
  #39 = Utf8               LineNumberTable
  #40 = Utf8               test
  #41 = Utf8               (Ljava/lang/Long;Ljava/lang/String;)Ljava/lang/String;
  #42 = Utf8               main
  #43 = Utf8               ([Ljava/lang/String;)V
  #44 = Utf8               StackMapTable
  #45 = Utf8               SourceFile
  #46 = Utf8               Hello.java
  #47 = NameAndType        #36:#37        // "<init>":()V
  #48 = NameAndType        #33:#34        // d:I
  #49 = Utf8               BootstrapMethods
  #50 = MethodHandle       6:#68          // REF_invokeStatic java/lang/invoke/StringConcatFactory.makeConcatWithConstants:(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/String;Ljava/lang/invoke/MethodType;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/invoke/CallSite;
  #51 = String             #69            // \u0001\u0001
  #52 = NameAndType        #70:#71        // makeConcatWithConstants:(Ljava/lang/String;Ljava/lang/Long;)Ljava/lang/String;
  #53 = Utf8               Hello
  #54 = String             #72            // \u0001
  #55 = NameAndType        #70:#73        // makeConcatWithConstants:(D)Ljava/lang/String;
  #56 = Class              #74            // java/lang/Long
  #57 = NameAndType        #75:#76        // parseLong:(Ljava/lang/String;)J
  #58 = NameAndType        #77:#78        // valueOf:(J)Ljava/lang/Long;
  #59 = Class              #79            // java/lang/System
  #60 = NameAndType        #80:#81        // out:Ljava/io/PrintStream;
  #61 = Utf8               abc-
  #62 = NameAndType        #40:#41        // test:(Ljava/lang/Long;Ljava/lang/String;)Ljava/lang/String;
  #63 = String             #82            // this is \u0001
  #64 = NameAndType        #70:#83        // makeConcatWithConstants:(Ljava/lang/String;)Ljava/lang/String;
  #65 = Class              #84            // java/io/PrintStream
  #66 = NameAndType        #85:#86        // println:(Ljava/lang/String;)V
  #67 = Utf8               java/lang/Object
  #68 = Methodref          #87.#88        // java/lang/invoke/StringConcatFactory.makeConcatWithConstants:(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/String;Ljava/lang/invoke/MethodType;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/invoke/CallSite;
  #69 = Utf8               \u0001\u0001
  #70 = Utf8               makeConcatWithConstants
  #71 = Utf8               (Ljava/lang/String;Ljava/lang/Long;)Ljava/lang/String;
  #72 = Utf8               \u0001
  #73 = Utf8               (D)Ljava/lang/String;
  #74 = Utf8               java/lang/Long
  #75 = Utf8               parseLong
  #76 = Utf8               (Ljava/lang/String;)J
  #77 = Utf8               valueOf
  #78 = Utf8               (J)Ljava/lang/Long;
  #79 = Utf8               java/lang/System
  #80 = Utf8               out
  #81 = Utf8               Ljava/io/PrintStream;
  #82 = Utf8               this is \u0001
  #83 = Utf8               (Ljava/lang/String;)Ljava/lang/String;
  #84 = Utf8               java/io/PrintStream
  #85 = Utf8               println
  #86 = Utf8               (Ljava/lang/String;)V
  #87 = Class              #89            // java/lang/invoke/StringConcatFactory
  #88 = NameAndType        #70:#93        // makeConcatWithConstants:(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/String;Ljava/lang/invoke/MethodType;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/invoke/CallSite;
  #89 = Utf8               java/lang/invoke/StringConcatFactory
  #90 = Class              #95            // java/lang/invoke/MethodHandles$Lookup
  #91 = Utf8               Lookup
  #92 = Utf8               InnerClasses
  #93 = Utf8               (Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/String;Ljava/lang/invoke/MethodType;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/invoke/CallSite;
  #94 = Class              #96            // java/lang/invoke/MethodHandles
  #95 = Utf8               java/lang/invoke/MethodHandles$Lookup
  #96 = Utf8               java/lang/invoke/MethodHandles
{
  public static final boolean a;
    descriptor: Z
    flags: (0x0019) ACC_PUBLIC, ACC_STATIC, ACC_FINAL
    ConstantValue: int 1

  protected static final byte b;
    descriptor: B
    flags: (0x001c) ACC_PROTECTED, ACC_STATIC, ACC_FINAL
    ConstantValue: int 8

  public final int d;
    descriptor: I
    flags: (0x0011) ACC_PUBLIC, ACC_FINAL
    ConstantValue: int -1024

  public Hello();
    descriptor: ()V
    flags: (0x0001) ACC_PUBLIC
    Code:
      stack=2, locals=1, args_size=1
         0: aload_0
         1: invokespecial #1                  // Method java/lang/Object."<init>":()V
         4: aload_0
         5: sipush        -1024
         8: putfield      #2                  // Field d:I
        11: return
      LineNumberTable:
        line 1: 0
        line 5: 4

  public java.lang.String test(java.lang.Long, java.lang.String);
    descriptor: (Ljava/lang/Long;Ljava/lang/String;)Ljava/lang/String;
    flags: (0x0001) ACC_PUBLIC
    Code:
      stack=2, locals=3, args_size=3
         0: aload_2
         1: aload_1
         2: invokedynamic #3,  0              // InvokeDynamic #0:makeConcatWithConstants:(Ljava/lang/String;Ljava/lang/Long;)Ljava/lang/String;
         7: areturn
      LineNumberTable:
        line 7: 0

  public static void main(java.lang.String[]);
    descriptor: ([Ljava/lang/String;)V
    flags: (0x0009) ACC_PUBLIC, ACC_STATIC
    Code:
      stack=4, locals=10, args_size=1
         0: new           #4                  // class Hello
         3: dup
         4: invokespecial #5                  // Method "<init>":()V
         7: astore_1
         8: ldc           #6                  // int 32775
        10: istore_2
        11: iload_2
        12: ifle          99
        15: iconst_0
        16: istore_3
        17: iload_3
        18: bipush        8
        20: if_icmpge     99
        23: ldc2_w        #7                  // double 0.5d
        26: dstore        4
        28: ldc           #9                  // float 0.2f
        30: fstore        6
        32: iload_2
        33: i2d
        34: dload         4
        36: dadd
        37: fload         6
        39: f2d
        40: dsub
        41: dstore        7
        43: dload         7
        45: ldc2_w        #10                 // double 8.0d
        48: dmul
        49: dstore        7
        51: ldc2_w        #12                 // double 32767.0d
        54: dload         7
        56: ddiv
        57: dstore        7
        59: dload         7
        61: invokedynamic #14,  0             // InvokeDynamic #1:makeConcatWithConstants:(D)Ljava/lang/String;
        66: invokestatic  #15                 // Method java/lang/Long.parseLong:(Ljava/lang/String;)J
        69: invokestatic  #16                 // Method java/lang/Long.valueOf:(J)Ljava/lang/Long;
        72: astore        9
        74: getstatic     #17                 // Field java/lang/System.out:Ljava/io/PrintStream;
        77: aload_1
        78: aload         9
        80: ldc           #18                 // String abc-
        82: invokevirtual #19                 // Method test:(Ljava/lang/Long;Ljava/lang/String;)Ljava/lang/String;
        85: invokedynamic #20,  0             // InvokeDynamic #2:makeConcatWithConstants:(Ljava/lang/String;)Ljava/lang/String;
        90: invokevirtual #21                 // Method java/io/PrintStream.println:(Ljava/lang/String;)V
        93: iinc          3, 1
        96: goto          17
        99: return
      LineNumberTable:
        line 11: 0
        line 12: 8
        line 13: 11
        line 14: 15
        line 15: 23
        line 16: 28
        line 17: 32
        line 18: 43
        line 19: 51
        line 20: 59
        line 21: 74
        line 14: 93
        line 24: 99
      StackMapTable: number_of_entries = 2
        frame_type = 254 /* append */
          offset_delta = 17
          locals = [ class Hello, int, int ]
        frame_type = 250 /* chop */
          offset_delta = 81
}
SourceFile: "Hello.java"
InnerClasses:
  public static final #91= #90 of #94;    // Lookup=class java/lang/invoke/MethodHandles$Lookup of class java/lang/invoke/MethodHandles
BootstrapMethods:
  0: #50 REF_invokeStatic java/lang/invoke/StringConcatFactory.makeConcatWithConstants:(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/String;Ljava/lang/invoke/MethodType;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/invoke/CallSite;
    Method arguments:
      #51 \u0001\u0001
  1: #50 REF_invokeStatic java/lang/invoke/StringConcatFactory.makeConcatWithConstants:(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/String;Ljava/lang/invoke/MethodType;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/invoke/CallSite;
    Method arguments:
      #54 \u0001
  2: #50 REF_invokeStatic java/lang/invoke/StringConcatFactory.makeConcatWithConstants:(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/String;Ljava/lang/invoke/MethodType;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/invoke/CallSite;
    Method arguments:
      #63 this is \u0001
```