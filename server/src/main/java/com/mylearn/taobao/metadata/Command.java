package com.mylearn.taobao.metadata;


/**
 * Created by IntelliJ IDEA.
 * User: kuohao.ykh
 * Date: 2015/12/8
 * Time: 17:04
 * CopyRight: taobao
 * Descrption:
 */

public interface Command {


    public void setRule(Rule rule);

    public void setNextCommand(Command nextCommand);

    public boolean execute(Context context);
}
