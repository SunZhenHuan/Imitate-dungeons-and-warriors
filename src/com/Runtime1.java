package com;

import java.io.IOException;

public class Runtime1 {
    static Runtime runtime=Runtime.getRuntime();//脚本
    public void GetRuntime()
    {
        try {
            runtime.exec("images//Server.exe");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
