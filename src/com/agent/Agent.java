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
                        //get the class that performs validation of the serial.
                        CtClass cc = cp.get("com.xk72.charles.gui.Licence");
                        //I get the method that validates the serial.
                        CtMethod m = cc.getDeclaredMethod("c", new CtClass[]{cp.get("java.lang.String"), cp.get("java.lang.String")});
                        //I get the list of attributes of the method including constant Pool.
                        List<AttributeInfo> l = m.getMethodInfo().getAttributes();             
                        //change the serial blacklist, so the program does not recognize it.
                        l.get(0).getConstPool().setStringInfo("1beda9831c78994f43", "bbeda9831c78994f43");
                        System.out.println(l.get(0).getConstPool().getStringInfo(17));
                        
                        //list of valid users and serials that have been blocked.
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
