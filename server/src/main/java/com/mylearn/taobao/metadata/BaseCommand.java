package com.mylearn.taobao.metadata;


/**
 * Created by IntelliJ IDEA.
 * User: yingkuohao
 * Date: 14-1-23
 * Time: ????10:12
 * CopyRight:360buy
 * Descrption:
 * To change this template use File | Settings | File Templates.
 */
public abstract class BaseCommand implements Command {

    protected Command nextCommand;

    protected Rule rule;

    public boolean execute(Context context) {

        beforeProcess(context);
        boolean bool = process(context);
        if (bool) {
            //���ִ�гɹ�������
            afterProcess(context);
        } else {
            //���ش���ʧ�ܣ�ԭ���
            return false;
        }
        return true;
    }


    protected void beforeProcess(Context context) {
    }

    protected abstract boolean process(Context context);

    protected void afterProcess(Context context) {
        if (this.getNextCommand() != null) {
            this.getNextCommand().execute(context);
        }
    }

    public Command getNextCommand() {
        return nextCommand;
    }

    public void setNextCommand(Command nextCommand) {
        this.nextCommand = nextCommand;
    }

    public Rule getRule() {
        return rule;
    }

    public void setRule(Rule rule) {
        this.rule = rule;
    }

}
