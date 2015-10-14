package com.agent;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.lang.instrument.Instrumentation;
import java.security.ProtectionDomain;
import java.util.List;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.bytecode.AttributeInfo;

public class Agent {

    public static void premain(String agentArgs, Instrumentation inst) {
        inst.addTransformer(new ClassFileTransformer() {
            @Override
            public byte[] transform(ClassLoader classLoader, String s, Class<?> aClass, ProtectionDomain protectionDomain, byte[] bytes) throws IllegalClassFormatException {

                if ("com/xk72/charles/gui/Licence".equals(s)) {
                    // Javassist
                    try {
                        ClassPool cp = ClassPool.getDefault();
                        CtClass cc = cp.get("com.xk72.charles.gui.Licence");
                        //CtClass cc2 = cp.get("com.xk72.charles.gui.Licence.SerialException");
                        CtMethod m = cc.getDeclaredMethod("c", new CtClass[]{cp.get("java.lang.String"), cp.get("java.lang.String")});

                        //CtConstructor c = cc.getDeclaredConstructor(new CtClass[]{cp.get("java.lang.String"), cp.get("java.lang.String")});
//CtMethod m2 = cc.getDeclaredMethod("c", new CtClass[]{cp.get("java.lang.String"), cp.get("java.lang.String")});
                        //m.addLocalVariable("elapsedTime", CtClass.longType);
                        /*m.insertBefore("elapsedTime = System.currentTimeMillis();");
                         m.insertAfter("{elapsedTime = System.currentTimeMillis() - elapsedTime;"
                         + "System.out.println(\"Method Executed in ms: \" + elapsedTime);}");*/
                        /*m.insertBefore("{if (str.equals(str)) {"
                         + "            return true;"
                         + "        }}");*/
                        //m.insertAt(1,"{System.out.println(\"Hola \");}");
                        List<AttributeInfo> l = m.getMethodInfo().getAttributes();
                        /*for (int i = 0; i < l.size(); i++) {
                         System.out.println(i);
                            
                         //l.get(i).getConstPool().print();
                         System.out.println(l.get(i).getName());
                         }*/
                        System.out.println(l.get(0).getConstPool().getStringInfo(17));
                        l.get(0).getConstPool().setStringInfo(17, "bbeda9831c78994f43");
                        l.get(0).getConstPool().setStringInfo(17, "rbeda9831c78994f43");
                        l.get(0).getConstPool().setStringInfo(17, "kbeda9831c78994f43");
                        l.get(0).getConstPool().setStringInfo("1beda9831c78994f43", "bbeda9831c78994f43jhgjkghjgkjghjggkhkjhgkjghkhj");
                        System.out.println(l.get(0).getConstPool().getStringInfo(17));
                        //m.getMethodInfo().getConstPool().getStringInfo(10);
                        //l.get(0).getConstPool().print();
                        //l.get(0).getConstPool().getStringInfo(1);
                        /*c.setBody("{this.d = $1;\n"
                         + "    this.c = true;}");*/

                        /**
                         * # NOME: # # anthony ortolani # # KEY: # #
                         * a4036b2761c9583fda #
                         *
                         * # NOME: # # Levin Prescott Tull # # KEY: # #
                         * 1beda9831c78994f43 #
                         *
                         * # NOME: # # Stephen A Muniz # # KEY: # #
                         * 8a2b9debb15766bff9 #
                         *
                         * # NOME: # # Juan A. Rodriguez # # KEY: # #
                         * da0e7561b10d974216 #
                         */
                        //m.addCatch("{}", cp.get("com.xk72.charles.gui.Licence$SerialException"));
                        //System.out.println(m.getMethodInfo().getDescriptor().contains("5bae9d8cdea32760ae"));
                        byte[] byteCode = cc.toBytecode();
                        cc.detach();
                        return byteCode;
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
                return null;
            }
        });
    }
}
