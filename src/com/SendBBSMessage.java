package com;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SendBBSMessage {
    /**
     * @parm 版本控制
     */
    String str="1:改变了用户id功能.\n" +
            "2:新增UUID唯一id识别号\n";//要注入更新的信息
    String version="1.1";//改两个
    GetMessage getMessage=new GetMessage();
    public SendBBSMessage()
    {
        String sql="select *from bbs_tab";//查询所有的主机信息
        ResultSet resultSet=getMessage.cha(sql);
        while (true)
        {
            try {
                if (!resultSet.next())
                    break;
                else//查到了则更新这个Message
                {
                    String hostname=resultSet.getString("Hostname");//取得这里面所有的主机名
                    System.err.println("主机名为"+hostname+"要更新！");
                    getMessage.ChangeValue("update rulebbs_tab set version='"+version+"' where Hostname='"+hostname+"'");
                    getMessage.ChangeValue("update bbs_tab set BBS='" + str + "' where Hostname='"+hostname+"'");//更新里面所有的Message
                    System.err.println(hostname+"更新完成！");
                }
            } catch (SQLException sqlException) {
                sqlException.printStackTrace();
            }

        }
        System.err.println("一键更新完成！");
    }

//    public static void main(String[] args) {
//        new SendBBSMessage();
//    }
}
